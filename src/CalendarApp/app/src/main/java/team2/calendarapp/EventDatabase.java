package team2.calendarapp;
import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by anmol on 11/29/2017.
 */

public class EventDatabase extends SQLiteOpenHelper {
    SQLiteDatabase mydatabase = SQLiteDatabase.openOrCreateDatabase(dbName,null);
    static final String dbName="eventDB";
    // database field names
    static final String calendarEventsTable="CalendarEvents";
    static final String colID="EventID";
    static final String colName="EventName";
    static final String colDescription = "Description";
    static final String colLocation="Location";
    static final String colDate="Date";
    static final String colStart="StartTime";
    static final String colEnd="EndTime";
    static final String colCategory="Category";

    // making database a singleton so there is only one instance of the database for the app
    private static EventDatabase sInstance;

    /**
     * This gets an instance of the database helper
     * @param context the context for the event database helper used to set to app context
     * @return sInstance the instance of the event database helper
     */
    public static EventDatabase getInstance (Context context){
        if (sInstance == null) {
            // get Application context to prevent leaking an Activity's content
            sInstance = new EventDatabase(context.getApplicationContext());
        }
        return sInstance;
    }

    /**
     * make constructor private to prevent direct instantiation of the database
     * @param context
     */
    private EventDatabase(Context context) {
        super(context, dbName, null, 1);
        //specify database directory
        File database = new File (dbName);
    }


    //getters and setters
    public static String getColID() {
        return colID;
    }

    public static String getColName() {
        return colName;
    }

    public static String getColDescription() {
        return colDescription;
    }

    public static String getColLocation() {
        return colLocation;
    }

    public static String getColStart() {
        return colStart;
    }

    public static String getColEnd() {
        return colEnd;
    }

    public static String getColDate() {
        return colDate;
    }

    public static String getColcategory() {
        return colCategory;
    }

    public static String getDbName() {
        return dbName;
    }

    public static String getCalendarEventsTable() {
        return calendarEventsTable;
    }

    // creating table when SQLite Database is created
    public void onCreate (SQLiteDatabase EventDatabase) {
        EventDatabase.execSQL("CREATE TABLE " + calendarEventsTable + " (" + colID + " INTEGER PRIMARY KEY ," +
                        colName + "TEXT)" + colDescription + " TEXT)" + colLocation + " TEXT)"+
                colStart + " TEXT)" + colEnd + " TEXT)" + colDate + " TEXT)" + colCategory+ " TEXT)");
    }

    // if version is upgraded, drop the old calendar events table

    /**
     * @param db the SQLite database that has calendar events
     * @param oldVersion the int value of the oldVersion
     * @param newVersion the int value of the newVersion
     */
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS "+calendarEventsTable);
        onCreate(db);
    }

    /**
     * Used to add Events into the Event Database
     * @param eventID the event ID used as a primary key
     * @param eventName the name of the event
     * @param eventDescription the description of the event
     * @param eventLocation the location of the event
     * @param eventStartTime the start time of the event
     * @param eventEndTime the end time of the event
     * @param eventDate the date of the event
     * @param eventCategory the category of the event
     */
    public void addRecord (int eventID, String eventName, String eventDescription, String eventLocation,
                           int eventStartTime, int eventEndTime, Date eventDate, String eventCategory) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(colID, eventID);
        cv.put(colName, eventName);
        cv.put(colDescription, eventDescription);
        cv.put(colLocation, eventLocation);
        cv.put(colStart, eventStartTime);
        cv.put(colEnd, eventEndTime);
        cv.put(colDate, eventDate.toString());
        cv.put(colCategory, eventCategory);
        db.insert(calendarEventsTable, colID, cv);
        db.close();
    }

    /**
     * Used to edit the calendar events in the database
     * @param eventToEdit the event object to be edited
     * @return an integer whether or not updating was successful
     */
    public int updateRecord (Event eventToEdit){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(colID, eventToEdit.getID());
        cv.put(colName, eventToEdit.getName());
        cv.put(colDescription, eventToEdit.getDescription());
        cv.put(colLocation, eventToEdit.getLocation());
        cv.put(colStart, eventToEdit.getStart());
        cv.put(colEnd, eventToEdit.getEnd());
        cv.put(colDate, eventToEdit.getDate().toString());
        cv.put(colCategory, eventToEdit.getCategory());
        return db.update(calendarEventsTable, cv, colID+"=?",
                new String []{String.valueOf(eventToEdit.getID())});

    }

    /**
     * Used to delete the calendar event from the database
     * @param eventToDelete the event object to be deleted
     */
    public void deleteRecord (Event eventToDelete)
    { try {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(calendarEventsTable, colID + "=?", new String[]{String.valueOf(eventToDelete.getID())});
        db.close();
    }
    catch (Exception e){
        System.out.println(e.getMessage());
    }
    }

    /**
     * gets all of the calendar events in the database and puts them into an array list
     * @return calendarEvents which is the array that has all calendar events
     */
    public ArrayList GetRecords(int eventID)
    {
        ArrayList calendarEvents = null;
        int index = 0; // index is used to help traverse to the next rows in the database
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor result= db.rawQuery( "select * from calendarEventsTable where id =" + eventID, null );
        while (result.moveToNext()){
            calendarEvents.add(result.getString(0));
            calendarEvents.add(result.getString(1));
            calendarEvents.add(result.getString(2));
            calendarEvents.add(result.getString(3));
            calendarEvents.add(result.getString(4));
            calendarEvents.add(result.getString(5));
            calendarEvents.add(result.getString(6));
            calendarEvents.add(result.getString(7));
        }
        return calendarEvents;
    }

    }

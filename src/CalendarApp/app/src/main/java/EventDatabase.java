
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

/**
 * Created by anmol on 11/29/2017.
 */

public class EventDatabase extends SQLiteOpenHelper {

    static final String dbName="eventDB";
    static final String calendarEventsTable="CalendarEvents";
    static final String colID="EventID";
    static final String colName="EventName";
    static final String colDescription = "Description";
    static final String colLocation="Location";
    static final String colDate="Date";
    static final String colStart="StartTime";
    static final String colEnd="EndTime";
    static final String colCategory="Category";

    // default constructor
    public EventDatabase(Context context) {
        super(context, dbName, null,1);
    }

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
        EventDatabase.execSQL("CREATE TABLE " + calendarEventsTable + " (" + colID + " INTEGER PRIMARY KEY , " +
                        colName + " TEXT)"+ colDescription + " TEXT)"+ colLocation + " TEXT)"+
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
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(calendarEventsTable,colID+"=?", new String [] {String.valueOf(eventToDelete.getID())});
        db.close();
    }

    /**
     *
     * @param eventName the name of the event to find the eventID
     * @return the eventID which is the event's unique identifier
     */
    public int GetEventID(String eventName)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.query(calendarEventsTable, new String[]{colID+" as _id",colID},
                colName+"=?", new String[]{eventName}, null, null, null);
        //Cursor c=db.rawQuery("SELECT "+colDeptID+" as _id FROM "+deptTable+"
        //WHERE "+colDeptName+"=?", new String []{Dept});
        c.moveToFirst();
        return c.getInt(c.getColumnIndex("_id"));
    }

    }

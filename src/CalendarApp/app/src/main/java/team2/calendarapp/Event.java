package team2.calendarapp;
import java.util.Date;
/**
 * Created by Andrew on 10/31/2017.
 */

public class Event {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String name;
    private String start;
    private String end;
    private String description;
    public Event(String name, String description, String start, String end){
        this.name = name;
        this.start = start;
        this.end = end;
        this.description = description;
    }
}

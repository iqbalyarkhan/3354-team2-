package team2.calendarapp;
/** 
AgendaEvents is for events used for demonstrating the Agenda View 
returns and sets Agenda Events attributes such as eventName, endTime and  startTime
*/ 
public class AgendaEvents {
    private String eventName;
    private String startTime;
    private String endTime;
    public AgendaEvents (String eventName, String startTime, String endTime){
        this.eventName = eventName;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    String getEventName (){
        return eventName;
    }
    String getStartTime(){
        return startTime;
    }
    String getEndTime(){
        return endTime;
    }
    void setEventName(String eventName){
        this.eventName = eventName;
    }
    void setStartTime(String startTime){
        this.startTime = startTime;
    }
    void setEndTime(String endTime){
        this.endTime = endTime;
    }
}

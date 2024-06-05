package catering.businesslogic.event;

import catering.businesslogic.assignment.ServiceSummary;
import catering.businesslogic.menu.Menu;

public class Service {
    private int id;
    private int eventId;
    private String name;
    private String place;
    private int approvedMenuId = 0;
    private java.sql.Date serviceDate;
    private java.sql.Time timeStart;
    private java.sql.Time timeEnd;
    private Integer expectedParticipants;
    private Menu menu;
    private ServiceSummary serviceSummary;

    public ServiceSummary getServiceSummary() {
        return serviceSummary;
    }
    public void setServiceSummary(ServiceSummary serviceSummary) {
        this.serviceSummary = serviceSummary;
    }
    public Menu getMenu() {
        return menu;
    }
    public void setMenu(Menu menu) {
        this.menu = menu;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getEventId() {
        return eventId;
    }
    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPlace() {
        return place;
    }
    public void setPlace(String place) {
        this.place = place;
    }
    public int getApprovedMenuId() {
        return approvedMenuId;
    }
    public void setApprovedMenuId(int approvedMenuId) {
        this.approvedMenuId = approvedMenuId;
    }
    public java.sql.Date getServiceDate() {
        return serviceDate;
    }
    public void setServiceDate(java.sql.Date serviceDate) {
        this.serviceDate = serviceDate;
    }
    public java.sql.Time getTimeStart() {
        return timeStart;
    }
    public void setTimeStart(java.sql.Time timeStart) {
        this.timeStart = timeStart;
    }
    public java.sql.Time getTimeEnd() {
        return timeEnd;
    }
    public void setTimeEnd(java.sql.Time timeEnd) {
        this.timeEnd = timeEnd;
    }
    public Integer getExpectedParticipants() {
        return expectedParticipants;
    }
    public void setExpectedParticipants(Integer expectedParticipants) {
        this.expectedParticipants = expectedParticipants;
    }
}


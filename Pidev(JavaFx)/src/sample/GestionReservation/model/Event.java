package sample.GestionReservation.model;

import java.util.Date;

public class Event {
    private String idevent;
    private String name;
    private String period;
    private String location;
    private Date  date ;
    private String available;
    private int prix;

    public Event(String idevent, String name,String period,String location, Date date, String available,int prix) {
        this.idevent = idevent;
        this.name = name;
        this.period=period;
        this.location=location;
        this.date =date;
        this.available = available;
        this.prix = prix ;
    }

    public String getIdevent() {
        return idevent;
    }

    public int getPrix() {
        return prix;
    }

    public String getId() {
        return idevent;
    }

    public String getName() {
        return name;
    }

    public String getPeriod() {
        return period;
    }
    public String getLocation(){
        return location;
    }

    public Date getDate() {
        return date;
    }

    public String getAvailable() {
        return available;
    }
}

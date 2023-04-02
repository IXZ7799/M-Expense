package com.example.m_expense;

public class Trip {
    private int id;
    private final int people_attending;
    private final boolean risk_assessment;
    private final int picture;
    private final String trip_name, destination, trip_date, description, transportation;

    public Trip(int id, int picture, String trip_name, String destination, String trip_date, Boolean risk_assessment, String description, int people_attending, String transportation) {
        this.id = id;
        this.picture = picture;
        this.trip_name = trip_name;
        this.destination = destination;
        this.trip_date = trip_date;
        this.risk_assessment = risk_assessment;
        this.description = description;
        this.people_attending = people_attending;
        this.transportation = transportation;
    }

    public Trip(int picture, String trip_name, String destination, String trip_date, Boolean risk_assessment, String description, int people_attending, String transportation) {
        this.picture = picture;
        this.trip_name = trip_name;
        this.destination = destination;
        this.trip_date = trip_date;
        this.risk_assessment = risk_assessment;
        this.description = description;
        this.people_attending = people_attending;
        this.transportation = transportation;
    }

    @SuppressWarnings("unused")
    public int getId() {return id;}

    public int getPicture() {return picture;}

    public String getTripName() {
        return trip_name;
    }

    public String getDestination() {
        return destination;
    }

    public String getTripDate(){return trip_date;}
    public Boolean getRiskAssessment(){return risk_assessment;}
    public String getDescription(){return description;}
    public int getPeopleAttending(){return people_attending;}
    public String getTransportation(){return transportation;}
}
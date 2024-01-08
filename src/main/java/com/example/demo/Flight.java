package com.example.demo;

import java.sql.Time;
import java.sql.Timestamp;

public class Flight {
   Timestamp arrivalTime,departureTime;
   int emptySeats;

    public Timestamp getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Timestamp arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setDepartureTime(Timestamp departureTime) {
        this.departureTime = departureTime;
    }

    public void setEmptySeats(int emptySeats) {
        this.emptySeats = emptySeats;
    }

    public void setCrewLeader(String crewLeader) {
        this.crewLeader = crewLeader;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setPlane(String plane) {
        this.plane = plane;
    }

    public Timestamp getDepartureTime() {
        return departureTime;
    }

    public int getEmptySeats() {
        return emptySeats;
    }

    public String getCrewLeader() {
        return crewLeader;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getPlane() {
        return plane;
    }

    public Flight(String from, String to, Timestamp departureTime, Timestamp arrivalTime, String plane, String crewLeader, int emptySeats) {
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.emptySeats = emptySeats;
        this.crewLeader = crewLeader;
        this.from = from;
        this.to = to;
        this.plane = plane;
    }

    String crewLeader,from,to,plane;
}

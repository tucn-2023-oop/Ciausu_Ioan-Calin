package com.example.demo;

import java.sql.Timestamp;

public class Ticket {
    int ticketId1,flightId1,seatNumber1;
    String to1,from1,plane1,userName1;
    Timestamp departureTime1;

    public Ticket(String userName,int ticketId, int flightId, int seatNo, String from, String to,Timestamp departureTime) {
        this.userName1 = userName;
        this.ticketId1 = ticketId;
        this.flightId1 = flightId;
        this.seatNumber1 = seatNo;
        this.to1 = to;
        this.from1 = from;
        this.departureTime1 = departureTime;
    }

    public Ticket() {

    }

    public int getTicketId1() {
        return ticketId1;
    }

    public int getFlightId1() {
        return flightId1;
    }

    public int getSeat1() {
        return seatNumber1;
    }

    public String getTo1() {
        return to1;
    }

    public String getFrom1() {
        return from1;
    }

    public String getPlane1() {
        return plane1;
    }

    public Timestamp getDepartureTime1() {
        return departureTime1;
    }

    public void setTicketId1(int ticketId) {
        this.ticketId1 = ticketId;
    }

    public void setFlightId1(int flightId) {
        this.flightId1 = flightId;
    }

    public void setSeatNo(int seatNo) {
        this.seatNumber1 = seatNo;
    }

    public void setTo(String to) {
        this.to1 = to;
    }

    public void setFrom(String from) {
        this.from1 = from;
    }

    public void setPlane(String plane) {
        this.plane1 = plane;
    }

    public void setDepartureTime(Timestamp departureTime) {
        this.departureTime1 = departureTime;
    }
}

package com.example.proiectsiemens.model;

import java.time.LocalDateTime;

public class Reservation {
    private int id;
    private String name;
    private String cnp;
    private LocalDateTime startDate;
    private  int numNights;
    private String payMethod;
    private int roomId;

    public Reservation() {
    }

    public Reservation(int id, String name, String cnp, LocalDateTime startDate, int numNIghts, String payMethod, int roomId) {
        this.id = id;
        this.name = name;
        this.cnp = cnp;
        this.startDate = startDate;
        this.numNights = numNIghts;
        this.payMethod = payMethod;
        this.roomId = roomId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public int getNumNights() {
        return numNights;
    }

    public void setNumNights(int numNights) {
        this.numNights = numNights;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cnp='" + cnp + '\'' +
                ", startDate=" + startDate +
                ", numNights=" + numNights +
                ", payMethod='" + payMethod + '\'' +
                ", roomId=" + roomId +
                '}';
    }

}

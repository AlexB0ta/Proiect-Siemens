package com.example.proiectsiemens.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Room {
    private int roomNumber;

    private int hotelId;
    private int type;

    private double price;

    private boolean isAvailable;

    public Room() {
    }

    public Room(int roomNumber, int hotelId, int type, double price, boolean isAvailable) {
        this.roomNumber = roomNumber;
        this.hotelId = hotelId;
        this.type = type;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber=" + roomNumber +
                ", hotelId=" + hotelId +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", isAvailable=" + isAvailable +
                '}';
    }
}

package com.example.proiectsiemens.model;

public class LocationData {
    private double latitude;
    private double longitude;
    private int raza;

    public LocationData(double latitude, double longitude,int raza) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.raza=raza;
    }

    public int getRaza() {
        return raza;
    }

    public void setRaza(int raza) {
        this.raza = raza;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "LocationData{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", raza=" + raza +
                '}';
    }
}

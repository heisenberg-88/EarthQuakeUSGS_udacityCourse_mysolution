package com.parth.earthquakeapplearn;

public class Earthquake {
    private String location_name;

    private double magnitute;

    private long earthquake_date;

    private String url;

    public Earthquake(double magnitute, String location_name, long earthquake_date, String url) {
        this.location_name = location_name;
        this.magnitute = magnitute;
        this.earthquake_date = earthquake_date;
        this.url = url;
    }


    public String getLocation_name() {
        return location_name;
    }

    public double getMagnitute() {
        return magnitute;
    }

    public long getEarthquake_date() {
        return earthquake_date;
    }

    public String getUrl() { return url; }
}

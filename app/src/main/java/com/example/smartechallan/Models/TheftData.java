package com.example.smartechallan.Models;

public class TheftData {
    String vehilnun , phnum ,stations;

    public TheftData(String vehilnun, String phnum,String stations) {
        this.vehilnun = vehilnun;
        this.phnum = phnum;
        this.stations = stations;
    }

    public String getStations() {
        return stations;
    }

    public void setStations(String stations) {
        this.stations = stations;
    }

    public String getVehilnun() {
        return vehilnun;
    }

    public void setVehilnun(String vehilnun) {
        this.vehilnun = vehilnun;
    }

    public String getPhnum() {
        return phnum;
    }

    public void setPhnum(String phnum) {
        this.phnum = phnum;
    }
}

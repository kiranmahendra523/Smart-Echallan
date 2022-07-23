package com.example.smartechallan.Models;

public class UpdateChallan {
    String vehicle_num,type_of_violation,Challan_Time,Challan_date,challan_amount;
    UpdateChallan(){

    }

    public UpdateChallan(String vehicle_num, String type_of_violation, String challan_Time, String challan_date, String challan_amount) {
        this.vehicle_num = vehicle_num;
        this.type_of_violation = type_of_violation;
        this.Challan_Time = challan_Time;
        this.Challan_date = challan_date;
        this.challan_amount = challan_amount;
    }


    public String getVehicle_num(String key) {
        return vehicle_num;
    }

    public void setVehicle_num(String vehicle_num) {
        this.vehicle_num = vehicle_num;
    }

    public String getType_of_violation() {
        return type_of_violation;
    }

    public void setType_of_violation(String type_of_violation) {
        this.type_of_violation = type_of_violation;
    }

    public String getChallan_Time() {
        return Challan_Time;
    }

    public void setChallan_Time(String challan_Time) {
        Challan_Time = challan_Time;
    }

    public String getChallan_date() {
        return Challan_date;
    }

    public void setChallan_date(String challan_date) {
        Challan_date = challan_date;
    }

    public String getChallan_amount() {
        return challan_amount;
    }

    public void setChallan_amount(String challan_amount) {
        this.challan_amount = challan_amount;
    }


    public String getVehicle_num() {
        return vehicle_num;
    }
}

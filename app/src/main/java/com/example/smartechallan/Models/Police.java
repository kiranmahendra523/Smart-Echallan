package com.example.smartechallan.Models;

public class Police {
    String Officer_Id,Officer_Name,Officer_Password;

    public Police(String officer_Id, String officer_Name, String officer_Password) {
        Officer_Id = officer_Id;
        Officer_Name = officer_Name;
        Officer_Password = officer_Password;
    }

    public String getOfficer_Id() {
        return Officer_Id;
    }

    public String getOfficer_Name() {
        return Officer_Name;
    }

    public String getOfficer_Password() {
        return Officer_Password;
    }

    public void setOfficer_Id(String officer_Id) {
        Officer_Id = officer_Id;
    }

    public void setOfficer_Name(String officer_Name) {
        Officer_Name = officer_Name;
    }

    public void setOfficer_Password(String officer_Password) {
        Officer_Password = officer_Password;
    }
}

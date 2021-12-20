package com.example.kms;

public class KitchenManager extends Employee {

    //Default constructor
    //KitchenManager() {}


    //Parameterized constructor
    KitchenManager(String fname, String lname, String username, String password, String empType)
    {
        //idCounter++;//incrementing ID counter

        this.firstName = fname;
        this.lastName = lname;
        this.username = username;
        this.password = password;
        this.employeeType = empType;
    }

    //void CreateWorkSchedule(){}

    //void MachineryMaintenance function

}

package com.litmus7.employeemanager.dto;

import java.time.LocalDate;

public class Employee {

    private String id;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String email;
    private String joiningDate;
    private boolean activeStatus;

    

    public Employee(String id, String firstName, String lastName, String mobileNumber, String email, String joiningDate,
            boolean activeStatus) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.joiningDate = joiningDate;
        this.activeStatus = activeStatus;
    }

    public Employee(){}

    public String getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getMobileNumber() {
        return mobileNumber;
    }
    public String getEmail() {
        return email;
    }
    public String getJoiningDate() {
        return joiningDate;
    }
    public boolean isActiveStatus() {
        return activeStatus;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }
    public void setActiveStatus(boolean activeStatus) {
        this.activeStatus = activeStatus;
    }
    
}

package com.example.db;

public class Employee {
    private int employeeId;
    private String surname;
    private String firstName;
    private String address;
    private String phoneNumber;

    public Employee(int employeeId, String surname, String firstName, String address, String phoneNumber){
        this.employeeId = employeeId;
        this.surname = surname;
        this.firstName = firstName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getSurname() {
        return surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

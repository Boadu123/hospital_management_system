package com.example.db;

public class Patient {
    private int patientId;
    private String surname;
    private String firstName;
    private String address;
    private String phoneNumber;

    public Patient(int patientId, String surname, String firstName, String address, String phoneNumber) {
        this.patientId = patientId;
        this.surname = surname;
        this.firstName = firstName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public int getPatientId() {
        return patientId;
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

    public void setPatientId(int patientId) {
        this.patientId = patientId;
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

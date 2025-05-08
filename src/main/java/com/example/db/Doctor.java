package com.example.db;

public class Doctor {
    private int employeeId;
    private String speciality;

    public Doctor(int employeeId, String speciality) {
        this.employeeId = employeeId;
        this.speciality = speciality;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}

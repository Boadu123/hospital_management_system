package com.example.db;

public class Doctor {
    private int employeeId;
    private String specialty;

    public Doctor(int employeeId, String specialty) {
        this.employeeId = employeeId;
        this.specialty = specialty;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}

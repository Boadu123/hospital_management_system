package com.example.db;

public class Ward {
    private int wardId;
    private String wardNumber;
    private int numberOfBeds;
    private int departmentId;
    private int supervisorId;

    public Ward(int wardId, String wardNumber, int numberOfBeds, int departmentId, int supervisorId) {
        this.wardId = wardId;
        this.wardNumber = wardNumber;
        this.numberOfBeds = numberOfBeds;
        this.departmentId = departmentId;
        this.supervisorId = supervisorId;
    }

    public int getWardId() {
        return wardId;
    }

    public void setWardId(int wardId) {
        this.wardId = wardId;
    }

    public String getWardNumber() {
        return wardNumber;
    }

    public void setWardNumber(String wardNumber) {
        this.wardNumber = wardNumber;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(int supervisorId) {
        this.supervisorId = supervisorId;
    }
}

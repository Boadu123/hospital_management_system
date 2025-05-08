package com.example.db;

public class Department {
    private int departmentId;
    private String name;
    private String building;
    private int directorId;

    public Department(int departmentId, String name, String building, int directorId) {
        this.departmentId = departmentId;
        this.name = name;
        this.building = building;
        this.directorId = directorId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public String getName() {
        return name;
    }

    public String getBuilding() {
        return building;
    }

    public int getDirectorId() {
        return directorId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public void setDirectorId(int directorId) {
        this.directorId = directorId;
    }
}

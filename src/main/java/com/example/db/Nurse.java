package com.example.db;

public class Nurse {
    private int employeeId;
    private String rotation;
    private double salary;
    private int departmentId;

    public Nurse(int employeeId, String rotation, double salary, int departmentId) {
        this.employeeId = employeeId;
        this.rotation = rotation;
        this.salary = salary;
        this.departmentId = departmentId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getRotation() {
        return rotation;
    }

    public double getSalary() {
        return salary;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setRotation(String rotation) {
        this.rotation = rotation;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
}

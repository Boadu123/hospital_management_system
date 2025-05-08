package com.example;

import com.example.dao.DoctorDao;
import com.example.dao.EmployeeDao;
import com.example.db.Doctor;
import com.example.db.Employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/hospital";
        String username = "root";
        String password = "Abame123456";

        try(Connection conn = DriverManager.getConnection(url, username, password)){

            System.out.println("Connected successfully");
            EmployeeDao employeeDao = new EmployeeDao(conn);

            Employee employee = new Employee(2, "John", "Asiedu", "123 Main St", "0551234567");
            Employee employee1 = new Employee(3, "Ben", "Huv", "South Sudan", "0589234567");

//            employeeDao.addEmployee(employee1);

//            employeeDao.deleteEmployee(1);

            Employee found = employeeDao.getEmployee(3);

            found.setAddress("John East");

            employeeDao.updateEmployee(found);

            if(found != null){
                System.out.println(found.getFirstName());
                System.out.println(found.getSurname());
                System.out.println(found.getAddress());
            }else{
                System.out.println("[]");
            }

//            List<Employee> allEmployees = employeeDao.getAllEmployees();
//            for (Employee emp : allEmployees) {
//                System.out.println("ID: " + emp.getEmployeeId()
//                        + ", Name: " + emp.getFirstName() + " " + emp.getSurname()
//                        + ", Address: " + emp.getAddress()
//                        + ", Phone: " + emp.getPhoneNumber());
//            }

//            DoctorDao doctorDao = new DoctorDao(conn);
//
//            Doctor doctor = new Doctor(1, "Heart");
//
//            doctorDao.addDoctor(doctor);

        } catch (SQLException e){
            System.out.println("Connection failed: " + e.getMessage());
        }
    }
}


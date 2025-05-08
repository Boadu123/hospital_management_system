package com.example.dao;

import com.example.db.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {
    private Connection connection;

    public EmployeeDao(Connection connection){
        this.connection = connection;
    }

    public void addEmployee(Employee employee) throws SQLException {
        String query = "INSERT INTO Employee (employee_id, surname, firstname, address, phone_number) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, employee.getEmployeeId());
        preparedStatement.setString(2, employee.getSurname());
        preparedStatement.setString(3, employee.getFirstName());
        preparedStatement.setString(4, employee.getAddress());
        preparedStatement.setString(5, employee.getPhoneNumber());
        preparedStatement.executeUpdate();

    }

    public Employee getEmployee(int id) throws SQLException{
        String query = "SELECT * FROM Employee WHERE employee_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()){
            return new Employee(
                    resultSet.getInt("employee_id"),
                    resultSet.getString("surname"),
                    resultSet.getString("firstname"),
                    resultSet.getString("address"),
                    resultSet.getString("phone_number")
            );
        } else{
            return null;
        }
    }

    public void deleteEmployee(int id) throws SQLException{
        String query = "DELETE FROM Employee WHERE employee_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, id);

        preparedStatement.executeUpdate();
    }

    public void updateEmployee(Employee employee) throws SQLException{
        String query = "UPDATE Employee SET surname=?, firstname=?, address=?, phone_number=? WHERE employee_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, employee.getSurname());
        preparedStatement.setString(2, employee.getFirstName());
        preparedStatement.setString(3, employee.getAddress());
        preparedStatement.setString(4, employee.getPhoneNumber());
        preparedStatement.setInt(5, employee.getEmployeeId());
        preparedStatement.executeUpdate();
    }

    public List<Employee> getAllEmployees() throws SQLException{
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM Employee";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Employee employee = new Employee(
                    resultSet.getInt("employee_id"),
                    resultSet.getString("surname"),
                    resultSet.getString("firstname"),
                    resultSet.getString("address"),
                    resultSet.getString("phone_number")
            );
            employees.add(employee);
        }
        return employees;
    }
}

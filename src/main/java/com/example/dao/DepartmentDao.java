package com.example.dao;

import com.example.db.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentDao {
    private final Connection connection;

    public DepartmentDao(Connection connection) {
        this.connection = connection;
    }

    public void addDepartment(Department department) throws SQLException {
        String query = "INSERT INTO Department (department_id, name, building, director_id) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, department.getDepartmentId());
        preparedStatement.setString(2, department.getName());
        preparedStatement.setString(3, department.getBuilding());
        preparedStatement.setInt(4, department.getDirectorId());

        preparedStatement.executeUpdate();
    }

    public Department getDepartment(int id) throws SQLException {
        String query = "SELECT * FROM Department WHERE department_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return new Department(
                    resultSet.getInt("department_id"),
                    resultSet.getString("name"),
                    resultSet.getString("building"),
                    resultSet.getInt("director_id")
            );
        } else {
            return null;
        }
    }

    public void deleteDepartment(int id) throws SQLException {
        String query = "DELETE FROM Department WHERE department_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }

    public void updateDepartment(Department department) throws SQLException {
        String query = "UPDATE Department SET name=?, building=?, director_id=? WHERE department_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, department.getName());
        preparedStatement.setString(2, department.getBuilding());
        preparedStatement.setInt(3, department.getDirectorId());
        preparedStatement.setInt(4, department.getDepartmentId());

        preparedStatement.executeUpdate();
    }
}

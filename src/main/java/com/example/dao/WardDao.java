package com.example.dao;

import com.example.db.Ward;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WardDao {
    private final Connection connection;

    public WardDao(Connection connection) {
        this.connection = connection;
    }

    public void addWard(Ward ward) throws SQLException {
        String query = "INSERT INTO Ward (ward_id, ward_number, beds, department_id, supervisor_id) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, ward.getWardId());
        preparedStatement.setString(2, ward.getWardNumber());
        preparedStatement.setInt(3, ward.getNumberOfBeds());
        preparedStatement.setInt(4, ward.getDepartmentId());
        preparedStatement.setInt(5, ward.getSupervisorId());

        preparedStatement.executeUpdate();
    }

    public Ward getWard(int id) throws SQLException {
        String query = "SELECT * FROM Ward WHERE ward_id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, id);

        ResultSet resultSet = ps.executeQuery();
        if (resultSet.next()) {
            return new Ward(
                    resultSet.getInt("ward_id"),
                    resultSet.getString("ward_number"),
                    resultSet.getInt("beds"),
                    resultSet.getInt("department_id"),
                    resultSet.getInt("supervisor_id")
            );
        } else {
            return null;
        }
    }

    public void deleteWard(int id) throws SQLException {
        String query = "DELETE FROM Ward WHERE ward_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }

    public void updateWard(Ward ward) throws SQLException {
        String query = "UPDATE Ward SET ward_number=?, beds=?, department_id=?, supervisor_id=? WHERE ward_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, ward.getWardNumber());
        preparedStatement.setInt(2, ward.getNumberOfBeds());
        preparedStatement.setInt(3, ward.getDepartmentId());
        preparedStatement.setInt(4, ward.getSupervisorId());
        preparedStatement.setInt(5, ward.getWardId());

        preparedStatement.executeUpdate();
    }
}

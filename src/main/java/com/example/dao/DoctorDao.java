package com.example.dao;

import com.example.db.Doctor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorDao {
    private final Connection connection;

    public DoctorDao(Connection connection) {
        this.connection = connection;
    }

    public void addDoctor(Doctor doctor) throws SQLException {
        String query = "INSERT INTO Doctor (employee_id, speciality) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, doctor.getEmployeeId());
        preparedStatement.setString(2, doctor.getSpeciality());

        preparedStatement.executeUpdate();
    }

    public Doctor getDoctor(int id) throws SQLException {
        String query = "SELECT * FROM Doctor WHERE employee_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return new Doctor(
                    resultSet.getInt("employee_id"),
                    resultSet.getString("speciality")
            );
        } else {
            return null;
        }
    }

    public void deleteDoctor(int id) throws SQLException {
        String query = "DELETE FROM Doctor WHERE employee_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }

    public void updateDoctor(Doctor doctor) throws SQLException {
        String query = "UPDATE Doctor SET speciality = ? WHERE employee_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, doctor.getSpeciality());
        preparedStatement.setInt(2, doctor.getEmployeeId());

        preparedStatement.executeUpdate();
    }
}

package com.example.dao;

import com.example.db.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientDao {
    private final Connection connection;

    public PatientDao(Connection connection) {
        this.connection = connection;
    }

    public void addPatient(Patient patient) throws SQLException {
        String query = "INSERT INTO Patient (patient_id, surname, first_name, address, phone_number) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, patient.getPatientId());
        preparedStatement.setString(2, patient.getSurname());
        preparedStatement.setString(3, patient.getFirstName());
        preparedStatement.setString(4, patient.getAddress());
        preparedStatement.setString(5, patient.getPhoneNumber());

        preparedStatement.executeUpdate();
    }

    public Patient getPatient(int id) throws SQLException {
        String query = "SELECT * FROM Patient WHERE patient_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return new Patient(
                    resultSet.getInt("patient_id"),
                    resultSet.getString("surname"),
                    resultSet.getString("first_name"),
                    resultSet.getString("address"),
                    resultSet.getString("phone_number")
            );
        } else {
            return null;
        }
    }

    public void deletePatient(int id) throws SQLException {
        String query = "DELETE FROM Patient WHERE patient_id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    public void updatePatient(Patient patient) throws SQLException {
        String query = "UPDATE Patient SET surname=?, first_name=?, address=?, phone_number=? WHERE patient_id=?";
        PreparedStatement ps = connection.prepareStatement(query);

        ps.setString(1, patient.getSurname());
        ps.setString(2, patient.getFirstName());
        ps.setString(3, patient.getAddress());
        ps.setString(4, patient.getPhoneNumber());
        ps.setInt(5, patient.getPatientId());

        ps.executeUpdate();
    }
}

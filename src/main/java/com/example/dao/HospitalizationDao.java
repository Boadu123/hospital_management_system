package com.example.dao;

import com.example.db.Hospitalization;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class HospitalizationDao {
    private final Connection connection;

    public HospitalizationDao(Connection connection) {
        this.connection = connection;
    }

    public void addHospitalization(Hospitalization hospitalization) throws SQLException {
        String query = "INSERT INTO Hospitalization (hospitalization_id, patient_id, ward_id, bed_number, diagnosis, doctor_id, admission_date, discharge_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, hospitalization.getHospitalizationId());
        preparedStatement.setInt(2, hospitalization.getPatientId());
        preparedStatement.setInt(3, hospitalization.getWardId());
        preparedStatement.setInt(4, hospitalization.getBedNumber());
        preparedStatement.setString(5, hospitalization.getDiagnosis());
        preparedStatement.setInt(6, hospitalization.getDoctorId());
        preparedStatement.setDate(7, hospitalization.getAdmissionDate());
        preparedStatement.setDate(8, hospitalization.getDischargeDate());

        preparedStatement.executeUpdate();
    }

    public Hospitalization getHospitalization(int id) throws SQLException {
        String query = "SELECT * FROM Hospitalization WHERE hospitalization_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return new Hospitalization(
                    resultSet.getInt("hospitalization_id"),
                    resultSet.getInt("patient_id"),
                    resultSet.getInt("ward_id"),
                    resultSet.getInt("bed_number"),
                    resultSet.getString("diagnosis"),
                    resultSet.getInt("doctor_id"),
                    resultSet.getDate("admission_date"),
                    resultSet.getDate("discharge_date")
            );
        } else {
            return null;
        }
    }

    public void deleteHospitalization(int id) throws SQLException {
        String query = "DELETE FROM Hospitalization WHERE hospitalization_id = ?";
        PreparedStatement resultSet = connection.prepareStatement(query);
        resultSet.setInt(1, id);
        resultSet.executeUpdate();
    }

    public void updateHospitalization(Hospitalization hospitalization) throws SQLException {
        String query = "UPDATE Hospitalization SET patient_id=?, ward_id=?, bed_number=?, diagnosis=?, doctor_id=?, admission_date=?, discharge_date=? WHERE hospitalization_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, hospitalization.getPatientId());
        preparedStatement.setInt(2, hospitalization.getWardId());
        preparedStatement.setInt(3, hospitalization.getBedNumber());
        preparedStatement.setString(4, hospitalization.getDiagnosis());
        preparedStatement.setInt(5, hospitalization.getDoctorId());
        preparedStatement.setDate(6, hospitalization.getAdmissionDate());
        preparedStatement.setDate(7, hospitalization.getDischargeDate());
        preparedStatement.setInt(8, hospitalization.getHospitalizationId());

        preparedStatement.executeUpdate();
    }
}


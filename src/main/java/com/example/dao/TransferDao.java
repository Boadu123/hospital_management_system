package com.example.dao;

import com.example.db.Transfer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class TransferDao {
    private final Connection connection;

    public TransferDao(Connection connection) {
        this.connection = connection;
    }

    public void addTransfer(Transfer transfer) throws SQLException {
        String query = "INSERT INTO Transfer (transfer_id, patient_id, from_ward_id, to_ward_id, transfer_date) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, transfer.getTransferId());
        preparedStatement.setInt(2, transfer.getPatientId());
        preparedStatement.setInt(3, transfer.getFromWardId());
        preparedStatement.setInt(4, transfer.getToWardId());
        preparedStatement.setDate(5, transfer.getTransferDate());

        preparedStatement.executeUpdate();
    }

    public Transfer getTransfer(int id) throws SQLException {
        String query = "SELECT * FROM Transfer WHERE transfer_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return new Transfer(
                    resultSet.getInt("transfer_id"),
                    resultSet.getInt("patient_id"),
                    resultSet.getInt("from_ward_id"),
                    resultSet.getInt("to_ward_id"),
                    resultSet.getDate("transfer_date")
            );
        } else {
            return null;
        }
    }

    public void deleteTransfer(int id) throws SQLException {
        String query = "DELETE FROM Transfer WHERE transfer_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }

    public void updateTransfer(Transfer transfer) throws SQLException {
        String query = "UPDATE Transfer SET patient_id=?, from_ward_id=?, to_ward_id=?, transfer_date=? WHERE transfer_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, transfer.getPatientId());
        preparedStatement.setInt(2, transfer.getFromWardId());
        preparedStatement.setInt(3, transfer.getToWardId());
        preparedStatement.setDate(4, transfer.getTransferDate());
        preparedStatement.setInt(5, transfer.getTransferId());

        preparedStatement.executeUpdate();
    }
}

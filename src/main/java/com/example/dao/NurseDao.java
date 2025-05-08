package com.example.dao;

import com.example.db.Nurse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NurseDao {
    private final Connection connection;

    public NurseDao(Connection connection) {
        this.connection = connection;
    }

    public void addNurse(Nurse nurse) throws SQLException {
        String query = "INSERT INTO Nurse (employee_id, rotation, salary, department_id) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, nurse.getEmployeeId());
        preparedStatement.setString(2, nurse.getRotation());
        preparedStatement.setDouble(3, nurse.getSalary());
        preparedStatement.setInt(4, nurse.getDepartmentId());

        preparedStatement.executeUpdate();
    }

    public Nurse getNurse(int id) throws SQLException {
        String query = "SELECT * FROM Nurse WHERE employee_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return new Nurse(
                    resultSet.getInt("employee_id"),
                    resultSet.getString("rotation"),
                    resultSet.getDouble("salary"),
                    resultSet.getInt("department_id")
            );
        } else {
            return null;
        }
    }

    public void deleteNurse(int id) throws SQLException {
        String query = "DELETE FROM Nurse WHERE employee_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }

    public void updateNurse(Nurse nurse) throws SQLException {
        String query = "UPDATE Nurse SET rotation = ?, salary = ?, department_id = ? WHERE employee_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, nurse.getRotation());
        preparedStatement.setDouble(2, nurse.getSalary());
        preparedStatement.setInt(3, nurse.getDepartmentId());
        preparedStatement.setInt(4, nurse.getEmployeeId());

        preparedStatement.executeUpdate();
    }
}

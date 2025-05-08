package com.example.dao;

import com.example.db.Doctor;
import com.example.db.Employee;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DoctorDaoTest {
    private static Connection connection;
    private static DoctorDao doctorDao;
    private static final int TEST_DOCTOR_ID = 9999; // High ID unlikely to conflict

    @BeforeAll
    public static void setUp() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/hospital?useSSL=false&allowPublicKeyRetrieval=true";
        String username = "root";
        String password = "Abame123456";
        connection = DriverManager.getConnection(url, username, password);
        doctorDao = new DoctorDao(connection);
    }

    @BeforeEach
    public void prepareTest() throws SQLException {
        // Disable foreign key checks temporarily
        connection.prepareStatement("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();

        // Clear any existing test data
        connection.prepareStatement("DELETE FROM doctor WHERE employee_id = " + TEST_DOCTOR_ID).executeUpdate();
        connection.prepareStatement("DELETE FROM employee WHERE employee_id = " + TEST_DOCTOR_ID).executeUpdate();

        // Create the required employee record first
        String insertEmployee = "INSERT INTO employee (employee_id, firstname, surname, address, phone_number) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(insertEmployee)) {
            stmt.setInt(1, TEST_DOCTOR_ID);
            stmt.setString(2, "Test");
            stmt.setString(3, "Doctor");
            stmt.setString(4, "Test Address");
            stmt.setString(5, "0000000000");
            stmt.executeUpdate();
        }

        // Re-enable foreign key checks
        connection.prepareStatement("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();
    }

    @AfterAll
    public static void tearDown() throws SQLException {
        if (connection != null) {
            // Final cleanup
            connection.prepareStatement("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();
            connection.prepareStatement("DELETE FROM doctor WHERE employee_id = " + TEST_DOCTOR_ID).executeUpdate();
            connection.prepareStatement("DELETE FROM employee WHERE employee_id = " + TEST_DOCTOR_ID).executeUpdate();
            connection.prepareStatement("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();
            connection.close();
        }
    }

    @Test
    @Order(1)
    public void testAddDoctor() throws SQLException {
        Doctor doctor = new Doctor(TEST_DOCTOR_ID, "Cardiology");
        doctorDao.addDoctor(doctor);

        Doctor found = doctorDao.getDoctor(TEST_DOCTOR_ID);
        assertNotNull(found, "Doctor should exist after being added");
        assertEquals("Cardiology", found.getSpeciality(), "Specialty should match");
    }

    @Test
    @Order(2)
    public void testGetDoctor() throws SQLException {
        // First ensure the doctor exists
        doctorDao.addDoctor(new Doctor(TEST_DOCTOR_ID, "Neurology"));

        Doctor doctor = doctorDao.getDoctor(TEST_DOCTOR_ID);
        assertNotNull(doctor, "Doctor should be retrievable");
        assertEquals("Neurology", doctor.getSpeciality(), "Specialty should match");
    }

    @Test
    @Order(3)
    public void testUpdateDoctor() throws SQLException {
        // First create the doctor
        doctorDao.addDoctor(new Doctor(TEST_DOCTOR_ID, "Pediatrics"));

        Doctor doctor = doctorDao.getDoctor(TEST_DOCTOR_ID);
        doctor.setSpeciality("Oncology");
        doctorDao.updateDoctor(doctor);

        Doctor updated = doctorDao.getDoctor(TEST_DOCTOR_ID);
        assertEquals("Oncology", updated.getSpeciality(), "Speciality should be updated");
    }

    @Test
    @Order(4)
    public void testDeleteDoctor() throws SQLException {
        // First create the doctor
        doctorDao.addDoctor(new Doctor(TEST_DOCTOR_ID, "Dermatology"));

        doctorDao.deleteDoctor(TEST_DOCTOR_ID);
        Doctor deleted = doctorDao.getDoctor(TEST_DOCTOR_ID);
        assertNull(deleted, "Doctor should be deleted");
    }

    @Test
    @Order(5)
    public void testGetNonExistentDoctor() throws SQLException {
        Doctor nonExistent = doctorDao.getDoctor(-1); // Assuming -1 doesn't exist
        assertNull(nonExistent, "Non-existent doctor should return null");
    }
}
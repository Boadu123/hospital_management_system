package com.example.dao;

import com.example.db.Employee;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeDaoTest {
    private static Connection connection;
    private static EmployeeDao employeeDao;
    private static final int TEST_EMPLOYEE_ID = 9999; // High ID unlikely to conflict

    @BeforeAll
    public static void setUp() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/hospital?useSSL=false&allowPublicKeyRetrieval=true";
        String username = "root";
        String password = "Abame123456";
        connection = DriverManager.getConnection(url, username, password);
        employeeDao = new EmployeeDao(connection);
    }

    @BeforeEach
    public void prepareTest() throws SQLException {
        // Disable foreign key checks temporarily
        connection.prepareStatement("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();

        // Clear any existing test data
        try {
            connection.prepareStatement("DELETE FROM doctor WHERE employee_id = " + TEST_EMPLOYEE_ID).executeUpdate();
        } catch (SQLException e) {
            // Ignore if doctor table doesn't exist or has no matching records
        }

        connection.prepareStatement("DELETE FROM employee WHERE employee_id = " + TEST_EMPLOYEE_ID).executeUpdate();

        // Re-enable foreign key checks
        connection.prepareStatement("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();
    }

    @AfterAll
    public static void tearDown() throws SQLException {
        if (connection != null) {
            // Final cleanup
            connection.prepareStatement("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();
            connection.prepareStatement("DELETE FROM doctor WHERE employee_id = " + TEST_EMPLOYEE_ID).executeUpdate();
            connection.prepareStatement("DELETE FROM employee WHERE employee_id = " + TEST_EMPLOYEE_ID).executeUpdate();
            connection.prepareStatement("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();
            connection.close();
        }
    }

    @Test
    @Order(1)
    public void testAddEmployee() throws SQLException {
        Employee employee = new Employee(TEST_EMPLOYEE_ID, "TestSurname", "TestFirstName", "TestAddress", "0550000000");
        employeeDao.addEmployee(employee);

        Employee found = employeeDao.getEmployee(TEST_EMPLOYEE_ID);
        assertNotNull(found, "Employee should exist after being added");
        assertEquals("TestSurname", found.getSurname(), "Surname should match");
    }

    @Test
    @Order(2)
    public void testGetEmployee() throws SQLException {
        // First ensure the employee exists
        employeeDao.addEmployee(new Employee(TEST_EMPLOYEE_ID, "TestSurname", "TestFirstName", "TestAddress", "0550000000"));

        Employee employee = employeeDao.getEmployee(TEST_EMPLOYEE_ID);
        assertNotNull(employee, "Employee should be retrievable");
        assertEquals("TestFirstName", employee.getFirstName(), "First name should match");
    }

    @Test
    @Order(3)
    public void testUpdateEmployee() throws SQLException {
        // First create the employee
        employeeDao.addEmployee(new Employee(TEST_EMPLOYEE_ID, "TestSurname", "TestFirstName", "TestAddress", "0550000000"));

        Employee employee = employeeDao.getEmployee(TEST_EMPLOYEE_ID);
        employee.setAddress("Updated Address");
        employeeDao.updateEmployee(employee);

        Employee updated = employeeDao.getEmployee(TEST_EMPLOYEE_ID);
        assertEquals("Updated Address", updated.getAddress(), "Address should be updated");
    }

    @Test
    @Order(4)
    public void testGetAllEmployees() throws SQLException {
        // First create a test employee
        employeeDao.addEmployee(new Employee(TEST_EMPLOYEE_ID, "TestSurname", "TestFirstName", "TestAddress", "0550000000"));

        List<Employee> employees = employeeDao.getAllEmployees();
        assertNotNull(employees, "Employee list should not be null");
        assertFalse(employees.isEmpty(), "Employee list should not be empty");
        assertTrue(employees.stream().anyMatch(e -> e.getEmployeeId() == TEST_EMPLOYEE_ID),
                "Test employee should be in the list");
    }

    @Test
    @Order(5)
    public void testDeleteEmployee() throws SQLException {
        // First create the employee
        employeeDao.addEmployee(new Employee(TEST_EMPLOYEE_ID, "TestSurname", "TestFirstName", "TestAddress", "0550000000"));

        // Ensure any doctor references are removed first
        try {
            connection.prepareStatement("DELETE FROM doctor WHERE employee_id = " + TEST_EMPLOYEE_ID).executeUpdate();
        } catch (SQLException e) {
            // Ignore if doctor table doesn't exist
        }

        // Now delete should work
        employeeDao.deleteEmployee(TEST_EMPLOYEE_ID);
        Employee deleted = employeeDao.getEmployee(TEST_EMPLOYEE_ID);
        assertNull(deleted, "Employee should be deleted");
    }
}
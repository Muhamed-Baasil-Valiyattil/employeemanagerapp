package com.litmus7.employeemanager.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.litmus7.employeemanager.dto.Employee;

public class EmployeeDAO {

    private Connection getConnection() throws SQLException {

	    Properties properties = new Properties();
	    try (FileInputStream fileInputStream = new FileInputStream("empdb.properties")) {
	        properties.load(fileInputStream);
	    }
	    catch (IOException e) {
	        throw new SQLException("Failed to load database properties", e);
	    }

	    String URL = properties.getProperty("empdburl");
	    String USERNAME = properties.getProperty("user");
	    String PASSWORD = properties.getProperty("password");

	    return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}

    
    public int createEmployee(Employee employee) throws SQLException{

        int rowsAffected = 0;
        Connection myConn;
        String sql = "INSERT INTO employee(id,first_name,last_name,mobile_number,email,joining_date,active_status) VALUES (?,?,?,?,?,?,?)";
                                                            
        myConn = getConnection();

        PreparedStatement myStmt = myConn.prepareStatement(sql);
        myStmt.setInt(1, employee.getId());
        myStmt.setString(2, employee.getFirstName());
        myStmt.setString(3, employee.getLastName());
        myStmt.setString(4, employee.getMobileNumber());
        myStmt.setString(5, employee.getEmail());
        myStmt.setBoolean(7, employee.isActiveStatus());

        LocalDate joiningDate = employee.getJoiningDate();
        if (joiningDate != null) {
            myStmt.setDate(6, java.sql.Date.valueOf(joiningDate));
        } else {
            myStmt.setNull(6, Types.DATE);
        }
    
        rowsAffected = myStmt.executeUpdate();


        return rowsAffected;


    }


    public List<Employee> getAllEmployees() throws SQLException{

        List<Employee> employees = new ArrayList<>();
        Connection myConn;
        String sql = "SELECT * FROM employee";

        myConn = getConnection();
        Statement myStmt = myConn.createStatement();
        ResultSet myRes = myStmt.executeQuery(sql);

        while (myRes.next()) {

            Employee employee = new Employee();
            employee.setId(myRes.getInt("id"));
            employee.setFirstName(myRes.getString("first_name"));
            employee.setLastName(myRes.getString("last_name"));
            employee.setMobileNumber(myRes.getString("mobile_number"));
            employee.setEmail(myRes.getString("email"));
            
            Date sqlDate = myRes.getDate("joining_date");
            if (sqlDate != null) {
                employee.setJoiningDate(sqlDate.toLocalDate());
            }
            employee.setActiveStatus(myRes.getBoolean("active_status"));
            employees.add(employee);
        }

        return employees;
    }

    public Employee getEmployeeById(int empid) throws SQLException{

        Employee employee = new Employee();
        String sql = "SELECT * FROM employee WHERE id = ?";                                                       
        Connection myConn;


        myConn = getConnection();
        PreparedStatement myStmt = myConn.prepareStatement(sql); 
        myStmt.setInt(1, empid);   

        ResultSet myRes = myStmt.executeQuery();

        if(myRes.next()){
            employee.setId(myRes.getInt("id"));
            employee.setFirstName(myRes.getString("first_name"));
            employee.setLastName(myRes.getString("last_name"));
            employee.setMobileNumber(myRes.getString("mobile_number"));
            employee.setEmail(myRes.getString("email"));
            
            Date sqlDate = myRes.getDate("joining_date");
            if (sqlDate != null) {
                employee.setJoiningDate(sqlDate.toLocalDate());
            }
            employee.setActiveStatus(myRes.getBoolean("active_status"));
        }
        

        return employee;
    }

    public int deleteEmployee(int empid) throws SQLException{

        String sql = "DELETE FROM employee WHERE id=?";
        Connection myConn;

        int rowsAffected = 0;
     
        myConn = getConnection();
        PreparedStatement myStmt = myConn.prepareStatement(sql);
        myStmt.setInt(1, empid);
        rowsAffected = myStmt.executeUpdate();
        
        return rowsAffected;
        
    }

    public int updateEmployee(Employee employee) throws SQLException {
        String sql = "UPDATE employee SET first_name = ?, last_name = ?, mobile_number = ?, email = ?, joining_date = ?, active_status = ? WHERE id = ?";
        Connection myConn;
        int rowsAffected = 0;
        
            
            
        myConn = getConnection();
        PreparedStatement myStmt = myConn.prepareStatement(sql);

        myStmt.setString(1, employee.getFirstName());
        myStmt.setString(2, employee.getLastName());
        myStmt.setString(3, employee.getMobileNumber());
        myStmt.setString(4, employee.getEmail());
        myStmt.setBoolean(6, employee.isActiveStatus());

        LocalDate joiningDate = employee.getJoiningDate();
        if (joiningDate != null) {
            myStmt.setDate(5, java.sql.Date.valueOf(joiningDate));
        } else {
            myStmt.setNull(5, Types.DATE);
        }

        myStmt.setInt(7, employee.getId());
        
    
        
        // Execute update
        rowsAffected = myStmt.executeUpdate();
        
        if (rowsAffected == 0) {
            throw new SQLException("Update failed, no rows affected");
        }
    

        return rowsAffected;
}
    
}

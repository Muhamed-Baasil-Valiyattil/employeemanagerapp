package com.litmus7.employeemanager.dao;


import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.util.DatabaseConnectionUtil;

public class EmployeeDAO {

    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;
    private static final String CONFIG_FILE = "empdb.properties";
    
    static {

        Properties properties = new Properties();
        
        try (FileInputStream fileInputStream = new FileInputStream(CONFIG_FILE)) {
            
            properties.load(fileInputStream);
            
            URL = properties.getProperty("empdburl");
            if (URL == null) throw new RuntimeException("empdburl not found in properties");
            
            USERNAME = properties.getProperty("user");
            if (USERNAME == null) throw new RuntimeException("user not found in properties");
            
            PASSWORD = properties.getProperty("password");
            if (PASSWORD == null) throw new RuntimeException("password not found in properties");
            
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize database connection pool", e);
        }

    }


    
    public int createEmployee(Employee employee) throws SQLException{

        String insertEmployeeQuery = "INSERT INTO employee(id,first_name,last_name,mobile_number,email,joining_date,active_status) VALUES (?,?,?,?,?,?,?)";
                          
        try (Connection connection = DatabaseConnectionUtil.getConnection(URL,USERNAME,PASSWORD);
             PreparedStatement statement = connection.prepareStatement(insertEmployeeQuery)) {

            
            statement.setInt(1, employee.getId());
            statement.setString(2, employee.getFirstName());
            statement.setString(3, employee.getLastName());
            statement.setString(4, employee.getMobileNumber());
            statement.setString(5, employee.getEmail());
            statement.setBoolean(7, employee.isActiveStatus());

            LocalDate joiningDate = employee.getJoiningDate();
            if (joiningDate != null) {
                statement.setDate(6, java.sql.Date.valueOf(joiningDate));
            } else {
                statement.setNull(6, Types.DATE);
            }
        
            return statement.executeUpdate();
            
        } 


    }


    public List<Employee> getAllEmployees() throws SQLException{

        List<Employee> employees = new ArrayList<>();
        String selectEmployeeQuery  = "SELECT id,first_name,last_name,mobile_number,email,joining_date,active_status FROM employee";

        try (Connection connection = DatabaseConnectionUtil.getConnection(URL,USERNAME,PASSWORD);
             Statement statment = connection.createStatement();
             ResultSet resultSet = statment.executeQuery(selectEmployeeQuery)) {

            while (resultSet.next()) {

                Employee employee = new Employee();
                employee.setId(resultSet.getInt("id"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setMobileNumber(resultSet.getString("mobile_number"));
                employee.setEmail(resultSet.getString("email"));
                
                Date sqlDate = resultSet.getDate("joining_date");
                if (sqlDate != null) {
                    employee.setJoiningDate(sqlDate.toLocalDate());
                }
                employee.setActiveStatus(resultSet.getBoolean("active_status"));
                employees.add(employee);
           }

           return employees;
            
        } 
    }

    public Employee getEmployeeById(int employeeId) throws SQLException{

        Employee employee = new Employee();
        String selectEmployeeByIdQuery = "SELECT first_name,last_name,mobile_number,email,joining_date,active_status FROM employee WHERE id = ?";    
        
        try (Connection connection = DatabaseConnectionUtil.getConnection(URL,USERNAME,PASSWORD);
             PreparedStatement statment = connection.prepareStatement(selectEmployeeByIdQuery)) {

            
            statment.setInt(1, employeeId);   

            ResultSet resultSet = statment.executeQuery();

            if(resultSet.next()){
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setMobileNumber(resultSet.getString("mobile_number"));
                employee.setEmail(resultSet.getString("email"));
                
                Date sqlDate = resultSet.getDate("joining_date");
                if (sqlDate != null) {
                    employee.setJoiningDate(sqlDate.toLocalDate());
                }
                employee.setActiveStatus(resultSet.getBoolean("active_status"));
            }
            
        } 

        return employee;
    }

    public int deleteEmployeeById(int employeeId) throws SQLException{

        String deleteEmployeeByIdQuery = "DELETE FROM employee WHERE id=?";
     
        try (Connection connection = DatabaseConnectionUtil.getConnection(URL,USERNAME,PASSWORD);
             PreparedStatement statment = connection.prepareStatement(deleteEmployeeByIdQuery);) {

            statment.setInt(1, employeeId);
            return statment.executeUpdate();
            
        } 
        
    }

    public int updateEmployee(Employee employee) throws SQLException {
        String updateEmployeeQuery = "UPDATE employee SET first_name = ?, last_name = ?, mobile_number = ?, email = ?, joining_date = ?, active_status = ? WHERE id = ?";

        try (Connection connection = DatabaseConnectionUtil.getConnection(URL,USERNAME,PASSWORD);
             PreparedStatement statment = connection.prepareStatement(updateEmployeeQuery);) {

            
            statment.setString(1, employee.getFirstName());
            statment.setString(2, employee.getLastName());
            statment.setString(3, employee.getMobileNumber());
            statment.setString(4, employee.getEmail());
            statment.setBoolean(6, employee.isActiveStatus());

            LocalDate joiningDate = employee.getJoiningDate();
            if (joiningDate != null) {
                statment.setDate(5, java.sql.Date.valueOf(joiningDate));
            } else {
                statment.setNull(5, Types.DATE);
            }

            statment.setInt(7, employee.getId());
    
            return statment.executeUpdate();
            
        } 
        
}
    
}

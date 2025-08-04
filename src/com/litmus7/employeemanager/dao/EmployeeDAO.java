package com.litmus7.employeemanager.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.litmus7.employeemanager.dto.Employee;

public class EmployeeDAO {

    private Connection myConn;

    public EmployeeDAO(){

        try {
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/empdb","walker","password");
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    
    public void createEmployee(Employee employee){

    }


    public List<Employee> getAllEmployees() {

        List<Employee> employees = new ArrayList<>();

        try {
            Statement myStmt = myConn.createStatement();
            ResultSet myRes = myStmt.executeQuery("SELECT * FROM employee");

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
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        

        return employees;
    }

    public Employee gteEmployeeById(int empid){

        Employee employee = new Employee();

        try {

            PreparedStatement myStmt = myConn.prepareStatement("SELECT * FROM employee"+
                                                                " WHERE id=?");
            
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
            


        } catch (Exception e) {
            System.out.print("Error : " + e.getMessage());
        }

        return employee;
    }
    
}

package com.litmus7.employeemanager.controller;

import java.sql.SQLException;
import java.util.List;

import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.dto.EmployeeResponse;
import com.litmus7.employeemanager.services.EmployeeService;

/**
* Controller class coordinates handles backend. 
*/

public class EmployeeController {

    private static final int SUCCESS_STATUS_CODE = 200;
	private static final int ERROR_STATUS_CODE = 400;

    private EmployeeService employeeService = new EmployeeService();

    public EmployeeResponse<Void> createEmployee(Employee employee){

        EmployeeResponse<Void> response = new EmployeeResponse<>();
        try {
            employeeService.addEmployee(employee);
            response.setStatusCode(SUCCESS_STATUS_CODE);
            response.setSuccessMessage("Employee Information Inserted Successfully!");

        } catch (SQLException e) {
            response.setStatusCode(ERROR_STATUS_CODE);
            response.setErrorMessage("Employee Information Insertion Failed : "+e.getMessage());
        }

        return response;      
    }

    public EmployeeResponse<List<Employee>> getAllEmployeesData(){

        EmployeeResponse<List<Employee>> response = new EmployeeResponse<>();
        try {
            List<Employee> employees = employeeService.getAllEmployeesData();
            response.setObject(employees);
            response.setStatusCode(SUCCESS_STATUS_CODE);
        } catch (SQLException e) {

            response.setStatusCode(ERROR_STATUS_CODE);
            response.setErrorMessage("Failed Retrieval : "+e.getMessage());
            
        }

        return response;
        
    }

    public EmployeeResponse<Employee> getEmployeeById(int employeeId){

        EmployeeResponse<Employee> response = new EmployeeResponse<>();
        try {
            Employee employee = employeeService.getEmployeeById(employeeId);
            response.setObject(employee);
            response.setStatusCode(SUCCESS_STATUS_CODE);
        } catch (SQLException e) {
            response.setStatusCode(ERROR_STATUS_CODE);
            response.setErrorMessage("Failed to Fetch Employee Data : "+e.getMessage());
            
        }

        return response;
    }

    public EmployeeResponse<Void> deleteEmployeeById(int id){

        EmployeeResponse<Void> response = new EmployeeResponse<>();
        try {
            employeeService.deleteEmployeeById(id);
            response.setStatusCode(SUCCESS_STATUS_CODE);
            response.setSuccessMessage("Deletion Successful !");
        } catch (Exception e) {

            response.setStatusCode(ERROR_STATUS_CODE);
            response.setErrorMessage("Deletion Failed : "+e.getMessage());
           
        }
        return response;
    }

    public EmployeeResponse<Void> updateEmployee(Employee employee){

        EmployeeResponse<Void> response = new EmployeeResponse<>();
        try {
            employeeService.updateEmployee(employee);
            response.setStatusCode(SUCCESS_STATUS_CODE);
            response.setSuccessMessage("Update Successful !");
        } catch (Exception e) {
            response.setStatusCode(ERROR_STATUS_CODE);
            response.setErrorMessage("Update Failed : "+e.getMessage());
            
        }

        return response;
    }


     

}
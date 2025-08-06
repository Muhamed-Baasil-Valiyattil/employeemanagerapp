package com.litmus7.employeemanager.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.litmus7.employeemanager.dao.EmployeeDAO;
import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.exception.ValidationException;
import com.litmus7.employeemanager.util.ValidationUtil;

public class EmployeeService {

    EmployeeDAO employeeDao = new EmployeeDAO();

    public void addEmployee(Employee employee) throws SQLException , ValidationException{

        try {
            ValidationUtil.validateID(employee.getId());
            ValidationUtil.validateName(employee.getFirstName());
            ValidationUtil.validateName(employee.getLastName());
            ValidationUtil.validateMobileNumber(employee.getMobileNumber());
            ValidationUtil.validateEmail(employee.getEmail());
            ValidationUtil.validateJoiningDate(employee.getJoiningDate());
            employeeDao.createEmployee(employee);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } catch (ValidationException e){
            throw new ValidationException(e.getMessage());
        }
    }

    public List<Employee> getAllEmployeesData() throws SQLException{

        List<Employee> employees = new ArrayList<>();

        try {
            employees = employeeDao.getAllEmployees();
        
        } catch (SQLException e) {
           throw new SQLException(e.getMessage());
        } 


        return employees;
    }

    public Employee getEmployeeById(int employeeId) throws SQLException{


        Employee employee = new Employee();
        try {
            employee = employeeDao.getEmployeeById(employeeId);
            if (employee.getFirstName()==null) {
                throw new SQLException("Employee of ID "+employeeId+" doesn't exists");
            }
        } catch (SQLException e) {

            throw new SQLException(e.getMessage());
        }

        return employee;
    }
    
    public void deleteEmployeeById (int employeeId) throws SQLException{

        try {
            
           int rowsDeleted =  employeeDao.deleteEmployeeById(employeeId);

           if(rowsDeleted == 0){
            throw new SQLException("Entry Doesn't Exists !");
           }
        } catch (SQLException e) {

            throw new SQLException(e.getMessage());
            
        }
    }

    public void updateEmployee(Employee employee)throws SQLException , ValidationException{

        try {
            ValidationUtil.validateID(employee.getId());
            ValidationUtil.validateName(employee.getFirstName());
            ValidationUtil.validateName(employee.getLastName());
            ValidationUtil.validateMobileNumber(employee.getMobileNumber());
            ValidationUtil.validateEmail(employee.getEmail());
            ValidationUtil.validateJoiningDate(employee.getJoiningDate());
           employeeDao.updateEmployee(employee);
        } catch (SQLException e) {

            throw new SQLException(e.getMessage());        
        } catch (ValidationException e){
            throw new ValidationException(e.getMessage());
        }
    }
}

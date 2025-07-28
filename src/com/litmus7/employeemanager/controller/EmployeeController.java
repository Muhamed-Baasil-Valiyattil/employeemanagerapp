package com.litmus7.employeemanager.controller;

import java.util.ArrayList;

import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.util.TextFileUtil;

public class EmployeeController {

    private static final int ID_WIDTH = 5;
    private static final int NAME_WIDTH = 16;
    private static final int EMAIL_WIDTH = 25;
    private static final int MOBILE_NUMBER_WIDTH = 13;
    private static final int DATE_WIDTH = 13;
    private static final int ACTIVER_STATUS_WIDTH = 13;
    private static final int TABLE_WIDTH = 117;


    public ArrayList<Employee> loadEmployeeDataFromTextFile(String filepath){
        ArrayList<Employee> employees = TextFileUtil.dataFromTextFile(filepath);
        
        // Validate all records
        // for (Employee emp : employees) {
        //     ValidationUtil.validateEmployee(emp);
        // }
        
        return employees;
    }

    public ArrayList<Employee> loadEmployeeDataFromCsvFile(){

        ArrayList<Employee> employees = TextFileUtil.dataFromCsvFile();
        return employees;
    }

    public void writeEmployeeDataToCsvFile(ArrayList<Employee> employees){

        TextFileUtil.parseToCsv(employees);

    }
    public void printDataToTable(ArrayList<Employee> employees){
        
        System.out.println("-".repeat(TABLE_WIDTH));
        
        for (Employee employee : employees) {

        System.out.print("| "+employee.getId()+" ".repeat((ID_WIDTH-employee.getId().length())));
        System.out.print("| "+employee.getFirstName()+" ".repeat((NAME_WIDTH-employee.getFirstName().length())));
        System.out.print("| "+employee.getLastName()+" ".repeat((NAME_WIDTH-employee.getLastName().length())));
        System.out.print("| "+employee.getMobileNumber()+" ".repeat((MOBILE_NUMBER_WIDTH-employee.getMobileNumber().length())));
        System.out.print("| "+employee.getEmail()+" ".repeat((EMAIL_WIDTH-employee.getEmail().length())));
        System.out.print("| "+employee.getJoiningDate()+" ".repeat((DATE_WIDTH-employee.getJoiningDate().length())));
        System.out.print("| "+employee.isActiveStatus()+" ".repeat((ACTIVER_STATUS_WIDTH-Boolean.toString(employee.isActiveStatus()).length())));
        System.out.println(" |");
        System.out.println("-".repeat(TABLE_WIDTH));
            
        }
        
        
    
    }
}

package com.litmus7.employeemanager.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.exception.ValidationException;
import com.litmus7.employeemanager.util.TextFileUtil;
import com.litmus7.employeemanager.util.ValidationUtil;

/**
* Controller class coordinates handles backend. 
*/

public class EmployeeController {
     
    /**
    * loads employee data from text file with '$' delimiter
    *
    * @param filepath user specified file path to .txt file
    * @throws IOException as it uses TextUtil method .dataFromTextFile(filepath)
    * @return employees , list of employee objects
    */

    public List<Employee> loadEmployeeDataFromTextFile(String filepath) throws IOException , ValidationException{

        List<String> lines = new ArrayList<>();
        List<Employee> employees = new ArrayList<>();

        lines = TextFileUtil.getdataFromFile(filepath); 

        for (String line : lines) {

            String[] parts = line.split("\\$");
            ValidationUtil.validateID(parts[0]);
            ValidationUtil.validateName(parts[1]);
            ValidationUtil.validateName(parts[2]);
            ValidationUtil.validateMobileNumber(parts[3]);
            ValidationUtil.validateEmail(parts[4].trim());
            ValidationUtil.validateJoiningDate(parts[5]);
            ValidationUtil.validateActiveStatus(parts[6].toLowerCase());

            Employee employee = new Employee(
                Integer.parseInt(parts[0]),parts[1], parts[2],
                parts[3], parts[4], LocalDate.parse(parts[5]),
                Boolean.parseBoolean(parts[6].toLowerCase())
            );

            employees.add(employee);
            
        }   

            return employees;      
    } 


    /**
    * writes employee object into csv file
    * "%d,%s,%s,%s,%s,%s,%b" format is used
    * Non String types like date and boolean values are parsed into String values
    *
    * @param employees list of employee objects to write to csv file
    */

    public void writeEmployeeDataToCsvFile(List<Employee> employees, String filePath) throws IOException{

        List<String> lines = new ArrayList<>();
        String line;

        // List of lines of type String in csv format added to List lines.
        for (Employee employee: employees) {
          
            line = String.format("%d,%s,%s,%s,%s,%s,%b\n",employee.getId(),employee.getFirstName(),employee.getLastName(),
                employee.getMobileNumber(),employee.getEmail(),employee.getJoiningDate().format(DateTimeFormatter.ISO_DATE),employee.isActiveStatus());
            
            lines.add(line);
            ValidationUtil.idCache.add(employee.getId());
            
        }

        try {

            //List<String> is wrritten to csv file
            TextFileUtil.writeDataToFile(lines , filePath);
            
        } catch (IOException e) {

            System.out.println("Error : "+e.getMessage());
            
        }

        


    }


    public void addUserEntryIntoCsvFile(String[] entries, String filepath) throws ValidationException , IOException{

        
        ValidationUtil.validateID(entries[0]);
        ValidationUtil.validateName(entries[1]);
        ValidationUtil.validateName(entries[2]);
        ValidationUtil.validateMobileNumber(entries[3]);
        ValidationUtil.validateEmail(entries[4]);
        ValidationUtil.validateJoiningDate(entries[5]);
        ValidationUtil.validateActiveStatus(entries[6]);
        
        String line = String.format("%s,%s,%s,%s,%s,%s,%s\n", entries[0],entries[1],entries[2],
                                    entries[3],entries[4],entries[5],entries[6]);
        
        TextFileUtil.appendDataToFile(line, filepath);
        ValidationUtil.idCache.add(Integer.parseInt(entries[0]));

    }
}
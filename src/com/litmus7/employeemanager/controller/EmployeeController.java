package com.litmus7.employeemanager.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.dto.ResponseRecord;
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
    * @return response , is a type ResponseRecord containing List<Employee> and StringBuilder variables
    */

    public ResponseRecord loadEmployeeDataFromTextFile(String filepath) throws IOException{

        List<String> lines = new ArrayList<>();
        List<Employee> employees = new ArrayList<>();
        StringBuilder errorResponse = new StringBuilder();

        lines = TextFileUtil.getdataFromFile(filepath);
        ValidationUtil.resetIdCache(); 

        for (String line : lines) {

            String[] parts = line.split("\\$");
            

            try{

                ValidationUtil.validateID(parts[0]);
                ValidationUtil.validateName(parts[1]);
                ValidationUtil.validateName(parts[2]);
                ValidationUtil.validateMobileNumber(parts[3]);
                ValidationUtil.validateEmail(parts[4]);
                ValidationUtil.validateJoiningDate(parts[5]);
                ValidationUtil.validateActiveStatus(parts[6]);

                Employee employee = new Employee(
                Integer.parseInt(parts[0].trim()),parts[1].trim(), parts[2].trim(),
                parts[3].trim(), parts[4].trim(),LocalDate.parse(parts[5].trim()),
                Boolean.parseBoolean(parts[6].toLowerCase().trim())
                );

                employees.add(employee);  
            } catch (ValidationException e){ //if ValidationException Occurs append errorResponse

                errorResponse.append(e.getMessage()+"\n");
    
            }
  
        }
        
        ResponseRecord response = new ResponseRecord(employees,errorResponse);
        return response;      
    } 


    /**
    * writes employee object into csv file
    * "%d,%s,%s,%s,%s,%s,%b" format is used
    * Non String types like date and boolean values are parsed into String values
    *
    * @param employees list of employee objects to write to csv file
    * @param filPath path of file to write the data
    */

    public void writeEmployeeDataToCsvFile(List<Employee> employees, String filePath) throws IOException{

        List<String> lines = new ArrayList<>();
        String line;

        // List of lines of type String in csv format added to List lines.
        for (Employee employee: employees) {

        
            line = String.format("%d,%s,%s,%s,%s,%s,%b\n",employee.getId(),employee.getFirstName(),employee.getLastName(),
                employee.getMobileNumber(),employee.getEmail(),employee.getJoiningDate().format(DateTimeFormatter.ISO_DATE),employee.isActiveStatus());
            
            lines.add(line);

           
            
        }

            //List<String> is wrritten to csv file
            TextFileUtil.writeDataToFile(lines , filePath);


    }

    /**
     * append employee information onto csv file
     * inputs are Validated
     * @param entries , String array of size 7 containg employee data
     * @param filePath , path of file where data need to be appended
     * @throws IOException
     * @throws ValidationException exceptions are forwarded to main app
     */


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
        

    }
}
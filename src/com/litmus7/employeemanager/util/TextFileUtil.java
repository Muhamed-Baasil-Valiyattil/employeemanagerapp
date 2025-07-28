package com.litmus7.employeemanager.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.litmus7.employeemanager.dto.Employee;

public class TextFileUtil {

    private static final String TARGET_CSV = "employeemanagerapp/employees.csv";
    private static final String TEXT_FILE_PATH = "employeemanagerapp/employees.txt";
    private static final String DOLLAR_DELIMITER = "\\$";
    private static final String COMMA_DELIMITER = ",";

    public static ArrayList<Employee> dataFromTextFile(String filepath){

	    ArrayList<Employee> employees = new ArrayList<>();
	    String line;

	    try (BufferedReader reader = new BufferedReader(
            new FileReader((
                filepath.trim().isEmpty()? TEXT_FILE_PATH : filepath)))){

	        while ((line = reader.readLine()) != null) {

	            String[] attributes = line.split(DOLLAR_DELIMITER);

	            Employee employee = new Employee(
	                    attributes[0], // ID
	                    attributes[1], // First Name
	                    attributes[2], // Last Name
	                    attributes[3], // Mobile
	                    attributes[4], // Email
	                    attributes[5], // Joining Date
	                    Boolean.parseBoolean(attributes[6]) // Active Status
	                );

	            employees.add(employee);

	            
	        }

	    } catch (IOException e){

	            System.out.println("Error reading file: " + e.getMessage());

	    }

	    return employees;
	   
    }

    public static ArrayList<Employee> dataFromCsvFile(){

	    ArrayList<Employee> employees = new ArrayList<>();
	    String line;

	    try (BufferedReader reader = new BufferedReader(
            new FileReader(TARGET_CSV))){

	        while ((line = reader.readLine()) != null) {

	            String[] attributes = line.split(COMMA_DELIMITER);

	            Employee employee = new Employee(
	                    attributes[0], // ID
	                    attributes[1], // First Name
	                    attributes[2], // Last Name
	                    attributes[3], // Mobile
	                    attributes[4], // Email
	                    attributes[5], // Joining Date
	                    Boolean.parseBoolean(attributes[6]) // Active Status
	                );

	            employees.add(employee);

	            
	        }

	    } catch (IOException e){

	            System.out.println("Error reading file: " + e.getMessage());

	    }

	    return employees;
	   
    }
    
    public static void parseToCsv(ArrayList<Employee> employees){


        try (PrintWriter writer = new PrintWriter(TARGET_CSV)){

            for (Employee employee : employees) {

                writer.printf("%s,%s,%s,%s,%s,%s,%s\n",employee.getId(),employee.getFirstName(),employee.getLastName(),
                  employee.getMobileNumber(),employee.getEmail(),employee.getJoiningDate(),Boolean.toString(employee.isActiveStatus()));
                
            }
            
        } catch (Exception e) {

            System.out.println("Error"+e.getMessage());
        }

    }

}

package com.litmus7.employeemanager.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.litmus7.employeemanager.dto.Employee;

public class TextFileUtil {

    private static final String TARGET_CSV = "employees.csv";
    private static final String TEXT_FILE_PATH = "employees.txt";
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

    public static ArrayList<Employee> dataFromCsvFile(){ //same as dataFromTextFile method but there is no user specified path

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


        try (PrintWriter writer = new PrintWriter(TARGET_CSV)){ //file overwrites data

            for (Employee employee : employees) {
                
                //written in ID,FirstName,LastName,MobileNumber,Email,JoiningDate,ActiveStatus Format
                writer.printf("%s,%s,%s,%s,%s,%s,%s\n",employee.getId(),employee.getFirstName(),employee.getLastName(),
                  employee.getMobileNumber(),employee.getEmail(),employee.getJoiningDate(),Boolean.toString(employee.isActiveStatus()));
                
            }
            
        } catch (Exception e) {

            System.out.println("Error"+e.getMessage());
        }

    }

    public static void appendToCsv(ArrayList<Employee> employees){


        try (PrintWriter writer = new PrintWriter(new FileWriter(TARGET_CSV,true))){ //file appends data

            for (Employee employee : employees) {
                
                //written in ID,FirstName,LastName,MobileNumber,Email,JoiningDate,ActiveStatus Format
                writer.printf("%s,%s,%s,%s,%s,%s,%s\n",employee.getId(),employee.getFirstName(),employee.getLastName(),
                  employee.getMobileNumber(),employee.getEmail(),employee.getJoiningDate(),Boolean.toString(employee.isActiveStatus()));
                
            }
            
        } catch (Exception e) {

            System.out.println("Error"+e.getMessage());
        }


    }

}

package com.litmus7.employeemanager.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.util.TextFileUtil;
import com.litmus7.employeemanager.util.ValidationUtil;

public class EmployeeController {

    private static final int ID_WIDTH = 5;
    private static final int NAME_WIDTH = 16;
    private static final int EMAIL_WIDTH = 25;
    private static final int MOBILE_NUMBER_WIDTH = 13;
    private static final int DATE_WIDTH = 13;
    private static final int ACTIVER_STATUS_WIDTH = 13;
    private static final int TABLE_WIDTH = 117;
    private static final String DOLLAR_DELIMITER = "\\$";
    private static final String COMMA_DELIMITER = ",";
    private static final String VALID = "valid";


    public ArrayList<Employee> loadEmployeeDataFromTextFile(String filepath){

        List<String> lines = new ArrayList<>();
        ArrayList<Employee> employees = new ArrayList<>();
        String validation;

        try {

            lines = TextFileUtil.dataFromTextFile(filepath); //fetches data from text file

        } catch (IOException e){

            System.out.println("Error : "+e.getMessage());

        }

            //convert lines read from text file into employee object

            for (String line : lines) {


                String[] attributes = line.split(DOLLAR_DELIMITER);

                try{
                    Employee employee = new Employee(
                            attributes[0], // ID
                            attributes[1], // First Name
                            attributes[2], // Last Name
                            attributes[3], // Mobile
                            attributes[4], // Email
                            LocalDate.parse(attributes[5]), // Joining Date
                            Boolean.parseBoolean(attributes[6]) // Active Status
                    );

                    // validation occurs here
                    validation = ValidationUtil.validateEmployeeObject(employee);
                    if (validation.compareTo(VALID)==0) {

                        // if data is valid it's added to employee list which is further saved to database
                        employees.add(employee);
                        
                    }else {

                        // if data is not valid the message is printed
                        printDataToTable(employee);
                        System.out.println(validation);

                    }
                }catch (Exception e) {
            
                    // catches invalid type exceptions in case of date and boolean values 
                    System.out.println("Error : "+e.getMessage());
                
                }           

            }

            return employees;      
        } 

    public ArrayList<Employee> loadEmployeeDataFromCsvFile(){
        
        List<String> lines = new ArrayList<>();
        ArrayList<Employee> employees = new ArrayList<>();

        try {

            lines = TextFileUtil.dataFromCsvFile(); //fetches data from text file

            // for each line from csv file , it's converted into employee object which is later used for output in human readable way
            for (String line : lines) {

                String[] attributes = line.split(COMMA_DELIMITER);
                Employee employee = new Employee(
                        attributes[0], // ID
                        attributes[1], // First Name
                        attributes[2], // Last Name
                        attributes[3], // Mobile
                        attributes[4], // Email
                        LocalDate.parse(attributes[5]), // Joining Date
                        Boolean.parseBoolean(attributes[6]) // Active Status
                );

                employees.add(employee);

            }
        } catch (Exception e) {
            
            System.out.println("Error : "+e.getMessage());

        }

        return employees;
    }

    public void writeEmployeeDataToCsvFile(ArrayList<Employee> employees){

        List<String> lines = new ArrayList<>();
        String line;

        // List of lines of type String in csv format added to List lines.
        for (Employee employee: employees) {
          
            line = String.format("%s,%s,%s,%s,%s,%s,%s\n",employee.getId(),employee.getFirstName(),employee.getLastName(),
                employee.getMobileNumber(),employee.getEmail(),employee.getJoiningDate().toString(),Boolean.toString(employee.isActiveStatus()));
            
            lines.add(line);
            
        }

        try {

            //List<String> is wrritten to csv file
            TextFileUtil.parseToCsv(lines);
            
        } catch (Exception e) {

            System.out.println("Error : "+e.getMessage());
            
        }

        


    }
    

    // to print List of Employee objects in human readable way
    public void printDataToTable(ArrayList<Employee> employees){

        /* ------------------------------------------------------------------------------------
           |ID  |First Name   |Last Name  |Mobile Number |Email  |Joining Date |Active Status |   <-- Format
           ------------------------------------------------------------------------------------
        */
        
        System.out.println("-".repeat(TABLE_WIDTH));
        
        for (Employee employee : employees) {

        System.out.print("| "+employee.getId()+" ".repeat((ID_WIDTH-employee.getId().length())));
        System.out.print("| "+employee.getFirstName()+" ".repeat((NAME_WIDTH-employee.getFirstName().length())));
        System.out.print("| "+employee.getLastName()+" ".repeat((NAME_WIDTH-employee.getLastName().length())));
        System.out.print("| "+employee.getMobileNumber()+" ".repeat((MOBILE_NUMBER_WIDTH-employee.getMobileNumber().length())));
        System.out.print("| "+employee.getEmail()+" ".repeat((EMAIL_WIDTH-employee.getEmail().length())));
        System.out.print("| "+employee.getJoiningDate().toString()+" ".repeat((DATE_WIDTH-employee.getJoiningDate().toString().length())));
        System.out.print("| "+employee.isActiveStatus()+" ".repeat((ACTIVER_STATUS_WIDTH-Boolean.toString(employee.isActiveStatus()).length())));
        System.out.println(" |");
        System.out.println("-".repeat(TABLE_WIDTH));
            
        }
        
        
    
    }


     // to print Single Employee object in human readable way 
    public void printDataToTable(Employee employee){

        /* ------------------------------------------------------------------------------------
           |ID  |First Name   |Last Name  |Mobile Number |Email  |Joining Date |Active Status |   <-- Format
           ------------------------------------------------------------------------------------
        */
        
        System.out.println("-".repeat(TABLE_WIDTH));
        System.out.print("| "+employee.getId()+" ".repeat((ID_WIDTH-employee.getId().length())));
        System.out.print("| "+employee.getFirstName()+" ".repeat((NAME_WIDTH-employee.getFirstName().length())));
        System.out.print("| "+employee.getLastName()+" ".repeat((NAME_WIDTH-employee.getLastName().length())));
        System.out.print("| "+employee.getMobileNumber()+" ".repeat((MOBILE_NUMBER_WIDTH-employee.getMobileNumber().length())));
        System.out.print("| "+employee.getEmail()+" ".repeat((EMAIL_WIDTH-employee.getEmail().length())));
        System.out.print("| "+employee.getJoiningDate().toString()+" ".repeat((DATE_WIDTH-employee.getJoiningDate().toString().length())));
        System.out.print("| "+employee.isActiveStatus()+" ".repeat((ACTIVER_STATUS_WIDTH-Boolean.toString(employee.isActiveStatus()).length())));
        System.out.println(" |");
        System.out.println("-".repeat(TABLE_WIDTH)); 
    
    }

    public ArrayList<Employee> readDataFromUser(){

        ArrayList<Employee> employees = new ArrayList<>();

        try {

           //console input
           BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in)); 
        
           

        String id;
        String firstName;
        String lastName;
        String mobileNumber;
        String email;
        String joiningDate;
        String activeStatus;
        String exitOption; // to exit data entry
        String validation;

        
        do {
            
            System.out.print("Enter Employee ID : ");
            id = inputReader.readLine();
             
            System.out.print("Enter Employee First Name : ");
            firstName = inputReader.readLine();

            System.out.print("Enter Employee Last Name : ");
            lastName = inputReader.readLine();

            System.out.print("Enter Employee Mobile Number : ");
            mobileNumber = inputReader.readLine();

            System.out.print("Enter Employee email : ");
            email = inputReader.readLine();

            System.out.print("Enter Employee Joining Date (YYYY-MM-DD): ");
            joiningDate = inputReader.readLine();

            System.out.print("Enter Employeed Active Status (true/false) : ");
            activeStatus = inputReader.readLine();

            Employee employee = new Employee(id,firstName,lastName,mobileNumber,email,LocalDate.parse(joiningDate),Boolean.parseBoolean(activeStatus));

            //validate employee here
            validation = ValidationUtil.validateEmployeeObject(employee);
                    if (validation.compareTo(VALID)==0) {
                        
                        // if data is valid it's added to employee list which is further saved to database
                        employees.add(employee);
                        
                    }else {

                        // if data is not valid the message is printed
                        printDataToTable(employee);
                        System.out.println(validation);

                    }
            
            System.out.print("Do you wish to enter another entry (y/n) : ");
            exitOption = inputReader.readLine();

        } while (exitOption.compareTo("y")==0);
            
        } catch (Exception e) {
            
            System.out.print("Error" + e.getMessage());
        }

        return employees;
    }

    public void insertIntoCsvFile(ArrayList<Employee> employees){

       List<String> lines = new ArrayList<>();
        String line;

        // List of lines of type String in csv format added to List lines.
        for (Employee employee: employees) {
          
            line = String.format("%s,%s,%s,%s,%s,%s,%s\n",employee.getId(),employee.getFirstName(),employee.getLastName(),
                employee.getMobileNumber(),employee.getEmail(),employee.getJoiningDate().toString(),Boolean.toString(employee.isActiveStatus()));
            
            lines.add(line);
            
        }

        //List<String> lines written to csv file
        try {

            TextFileUtil.appendToCsv(lines);
            
        } catch (Exception e) {

            System.out.println("Error : "+e.getMessage());
            
        }

    }






}

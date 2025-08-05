package com.litmus7.employeemanager.app;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.litmus7.employeemanager.dao.EmployeeDAO;
import com.litmus7.employeemanager.dto.Employee;

public class EmployeeManagerApp {

    public static void main(String[] args) {

        int userChoice; 
        int rowsAffected;
        int id;
        boolean exit = false; // To exit Application
        Employee emp = new Employee(); //Employee object shared among Services
        Scanner input = new Scanner(System.in);
        EmployeeDAO employeeDao = new EmployeeDAO(); //Instance of EmployeeDAO
    

        while (!exit) {

            //Menu
            System.out.println("-".repeat(25)+" Services "+"-".repeat(25)+"\n"
                    +"1. Create Employee\n"
                    +"2. List All Employees\n"
                    +"3. Get Employee Information By Id\n"
                    +"4. Delete an Employee\n"
                    +"5. Update an Employee\n"
                    +"6. Exit Apllication");

            System.out.print("Enter Choice : ");
            userChoice = input.nextInt();

            switch (userChoice) {

                case 1:

                    //To Create an Employee
                    rowsAffected = 0;

                    try {

                        System.out.println("\n<"+"-".repeat(24)+"Entry to database"+"-".repeat(24)+">"+"\n");
                        System.out.print("Enter ID : ");
                        emp.setId(input.nextInt());
                        input.nextLine();

                        System.out.print("\nEnter First Name : ");
                        emp.setFirstName(input.nextLine());

                        System.out.print("\nEnter Last Name : ");
                        emp.setLastName(input.nextLine());

                        System.out.print("\nEnter Mobile Number : ");
                        emp.setMobileNumber(input.nextLine());

                        System.out.print("\nEnter Email: ");
                        emp.setEmail(input.nextLine());

                        System.out.print("\nEnter Joining Date (YYYY-MM-DD) : ");
                        emp.setJoiningDate(LocalDate.parse(input.nextLine()));

                        System.out.print("\nEnter Active Status (true / false) :");
                        emp.setActiveStatus(Boolean.parseBoolean(input.nextLine()));

                        rowsAffected = employeeDao.createEmployee(emp);
                        System.out.println("Rows Affected : "+rowsAffected);


                        
                    } catch (Exception e) {
                        
                        System.out.println(e.getMessage());
                    }

                    

                    break;


                case 2:

                    //To List all Employees data from database
                    List<Employee> employees = new ArrayList<>();

                    try {

                        employees = employeeDao.getAllEmployees();
                        System.out.println("\n<"+"-".repeat(24)+"List of Employees"+"-".repeat(24)+">"+"\n");

                        for (Employee employee : employees) {

                            System.out.println("\n"+"*".repeat(25));
                            System.out.println("ID : "+employee.getId());
                            System.out.println("First Name : "+employee.getFirstName());
                            System.out.println("Last Name : "+employee.getLastName());
                            System.out.println("Email : "+employee.getEmail());
                            System.out.println("Mobile Number : "+employee.getMobileNumber());
                            System.out.println("Joining Date : "+employee.getJoiningDate());
                            System.out.println("Active Status : "+employee.isActiveStatus());
                            System.out.println("*".repeat(25)+"\n");

                        }  
                        
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                         
                    break;
                
                case 3:

                    System.out.print("\nEnter Id of Employee to retrieve : ");
                    id = input.nextInt();

                    try {

                        emp = employeeDao.getEmployeeById(id);

                        System.out.println("Employee of ID :"+id+" Data");
                        System.out.println("First Name : "+emp.getFirstName());
                        System.out.println("Last Name : "+emp.getLastName());
                        System.out.println("Email : "+emp.getEmail());
                        System.out.println("Mobile Number : "+emp.getMobileNumber());
                        System.out.println("Joining Date : "+emp.getJoiningDate());
                        System.out.println("Active Status : "+emp.isActiveStatus());
                        System.out.println("*".repeat(25)+"\n");
                        

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    

                    break;


                case 4:
                    
                    rowsAffected = 0;
                    System.out.print("\nEnter Id of Employee to delete : ");
                    id = input.nextInt();

                    try {

                        rowsAffected = employeeDao.deleteEmployee(id);
                        System.out.println("\nRows Affected : "+rowsAffected);
                        
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    

                    break;
                
                case 5:

                    rowsAffected = 0;

                    try {

                        //     System.out.println("\n<"+"-".repeat(24)+"Update Employee"+"-".repeat(24)+">"+"\n");
                        //     System.out.print("Enter ID of the Employee you want to Update : ");
                        //     emp.setId(input.nextInt());
                        //     input.nextLine();

                        //     System.out.print("\nEnter First Name : ");
                        //     emp.setFirstName(input.nextLine());

                        //     System.out.print("\nEnter Last Name : ");
                        //     emp.setLastName(input.nextLine());

                        //     System.out.print("\nEnter Mobile Number : ");
                        //     emp.setMobileNumber(input.nextLine());

                        //     System.out.print("\nEnter Email: ");
                        //     emp.setEmail(input.nextLine());

                        //     System.out.print("\nEnter Joining Date (YYYY-MM-DD) : ");
                        //     emp.setJoiningDate(LocalDate.parse(input.nextLine()));

                        //     System.out.print("\nEnter Active Status (true / false) :");
                        //     emp.setActiveStatus(Boolean.parseBoolean(input.nextLine()));

                        //     rowsAffected = employeeDao.updateEmployee(emp);
                        //     System.out.println("Rows Affected : "+rowsAffected);

                        System.out.println("\n<" + "-".repeat(24) + "Update Employee" + "-".repeat(24) + ">" + "\n");
                        System.out.print("Enter ID of the Employee you want to Update: ");
                        id = input.nextInt();
                        input.nextLine();                                       // Consume newline
                        emp.setId(id);

                        // Get current employee data from database
                        emp = employeeDao.getEmployeeById(id);
                        if (emp == null) {
                            System.out.println("Employee with ID " + id + " not found!");
                            return;
                        }

                        System.out.println("\nCurrent Employee Data:");
                        System.out.println("1. First Name: " + emp.getFirstName());
                        System.out.println("2. Last Name: " + emp.getLastName());
                        System.out.println("3. Mobile Number: " + emp.getMobileNumber());
                        System.out.println("4. Email: " + emp.getEmail());
                        System.out.println("5. Joining Date: " + emp.getJoiningDate());
                        System.out.println("6. Active Status: " + emp.isActiveStatus());

                        System.out.println("\nChoose what to update:");
                        System.out.println("1. Update all fields");
                        System.out.println("2. Update specific fields");
                        System.out.println("3. Cancel");
                        System.out.print("Enter your choice (1-3): ");

                        int choice = input.nextInt();
                        input.nextLine();                                        // Consume newline

                        switch (choice) {
                            case 1:  // Update all fields
                                System.out.print("\nEnter First Name (current: " + emp.getFirstName() + "): ");
                                emp.setFirstName(input.nextLine());

                                System.out.print("\nEnter Last Name (current: " + emp.getLastName() + "): ");
                                emp.setLastName(input.nextLine());

                                System.out.print("\nEnter Mobile Number (current: " + emp.getMobileNumber() + "): ");
                                emp.setMobileNumber(input.nextLine());

                                System.out.print("\nEnter Email (current: " + emp.getEmail() + "): ");
                                emp.setEmail(input.nextLine());

                                System.out.print("\nEnter Joining Date (YYYY-MM-DD) (current: " + emp.getJoiningDate() + "): ");
                                emp.setJoiningDate(LocalDate.parse(input.nextLine()));

                                System.out.print("\nEnter Active Status (true/false) (current: " + emp.isActiveStatus() + "): ");
                                emp.setActiveStatus(Boolean.parseBoolean(input.nextLine()));

                                break;

                            case 2:  // Update specific fields
                                System.out.println("\nEnter field numbers to update (comma separated):");
                                System.out.println("1. First Name, 2. Last Name, 3. Mobile Number, 4. Email, 5. Joining Date, 6. Active Status");
                                System.out.print("Your selection: ");
                                
                                String[] fieldsToUpdate = input.nextLine().split(",");
                                
                                for (String field : fieldsToUpdate) {
                                    try {
                                        int fieldNum = Integer.parseInt(field.trim());
                                        switch (fieldNum) {
                                            case 1:
                                                System.out.print("\nEnter First Name (current: " + emp.getFirstName() + "): ");
                                                emp.setFirstName(input.nextLine());
                                                break;
                                            case 2:
                                                System.out.print("\nEnter Last Name (current: " + emp.getLastName() + "): ");
                                                emp.setLastName(input.nextLine());
                                                break;
                                            case 3:
                                                System.out.print("\nEnter Mobile Number (current: " + emp.getMobileNumber() + "): ");
                                                emp.setMobileNumber(input.nextLine());
                                                break;
                                            case 4:
                                                System.out.print("\nEnter Email (current: " + emp.getEmail() + "): ");
                                                emp.setEmail(input.nextLine());
                                                break;
                                            case 5:
                                                System.out.print("\nEnter Joining Date (YYYY-MM-DD) (current: " + emp.getJoiningDate() + "): ");
                                                emp.setJoiningDate(LocalDate.parse(input.nextLine()));
                                                break;
                                            case 6:
                                                System.out.print("\nEnter Active Status (true/false) (current: " + emp.isActiveStatus() + "): ");
                                                emp.setActiveStatus(Boolean.parseBoolean(input.nextLine()));
                                                break;
                                            default:
                                                System.out.println("Invalid field number: " + fieldNum);
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("Invalid input: " + field);
                                    }
                                }
                                break;

                            case 3:  // Cancel
                                System.out.println("Update cancelled.");
                                return;

                            default:
                                System.out.println("Invalid choice!");
                                return;
                        }

                        // Perform the update
                        rowsAffected = employeeDao.updateEmployee(emp);
                        System.out.println("Rows Affected: " + rowsAffected);

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 6:

                    System.out.println("Exiting....");
                    exit = true;
                    break;
            
                default:

                    System.out.println("Invalid Option");
                    break;
            }
            
        }



        input.close();

    }
    
}



// EmployeeController controller = new EmployeeController();
        // Scanner input = new Scanner(System.in);                       // To read user input
        // List<Employee> employees = new ArrayList<>();

        // boolean exit = false;         //for exiting the application
        // int userChoice;               //options to opt in menu
        // String textFilePath;         //path of user specified .txt file
        // String targetCsvPath;

        // while (!exit) {
            
        //     //Application Menu
        //     System.out.println("*".repeat(25)+"Available Services"+"*".repeat(25));	
        //     System.out.println("1. Load Text Files");
        //     System.out.println("2. Insert Employee details into database");
        //     System.out.println("3. Exit Application\n");
        //     System.out.print("Enter choice : ");

        //     try {
                
        //         userChoice = input.nextInt(); // Read user command
        //         System.out.println();

        //     } catch (NumberFormatException e) {
        //         System.out.println("Choose available service , Input must be a whole number between 1 and 3!");
        //         continue;
        //     }
            

        //     switch (userChoice) {


        //         // Load Text File into a raw Text, Text is later processed to extract employee fields.
        //         case 1:

        //             String errorResponse;   
                    
        //             // Loads '$' delimiter file into formatted output in Console.
        //             System.out.print("Enter the path of file to load the data : ");
        //             input.nextLine();
        //             textFilePath = input.nextLine();
        //             System.out.println("\n");

        //             try {

                        
        //                 ResponseRecord response = controller.loadEmployeeDataFromTextFile(textFilePath);  // Extracted employee data is converted into employee objects
        //                 employees = response.employees();

        //                 for (Employee employee : employees) {

        //                     System.out.println("*".repeat(25));
        //                     System.out.println("ID : "+employee.getId());
        //                     System.out.println("First Name : "+employee.getFirstName());
        //                     System.out.println("Last Name : "+employee.getLastName());
        //                     System.out.println("Email : "+employee.getEmail());
        //                     System.out.println("Mobile Number : "+employee.getMobileNumber());
        //                     System.out.println("Joining Date : "+employee.getJoiningDate());
        //                     System.out.println("Active Status : "+employee.isActiveStatus());
        //                     System.out.println("*".repeat(25)+"\n");

        //                 }

        //                 // System.out.println("\nWriting to Csv");

        //                 // controller.writeEmployeeDataToCsvFile(employees, targetCsvPath);
                     
        //                 // System.out.println("\nWriting Successful");
                        
        //                 // System.out.println("\n");

        //                 errorResponse = response.log().toString();
        //                 if (errorResponse.isBlank()) {
        //                     System.out.println("Entry Fully Successful");
        //                 }else{
        //                     System.out.println("Errors have Occured\n" + "Backend Response : "+errorResponse);
        //                 }    

        //                 System.out.print("Enter the path of csv file to the data : ");
        //                 targetCsvPath = input.nextLine();
        //                 System.out.println();

        //                 controller.writeEmployeeDataToCsvFile(employees,targetCsvPath);

        //             } catch (IOException e) {

        //                 System.out.print(e.getMessage()+"\n");

        //             }

                    

        //             break;
                  

        //         case 2:

        //             String[] entries = new String[7];
        //             char exitEntry = 'y';

        //             input.nextLine();
        //             System.out.print("Enter file path to insert user entries : ");
        //             targetCsvPath = input.nextLine();
        //             System.out.println("\n<"+"-".repeat(24)+"Entry to database"+"-".repeat(24)+">"+"\n");

                        
        //             while(exitEntry == 'y'){

        //                 System.out.print("Enter ID : ");
        //                 entries[0] = input.nextLine();

        //                 System.out.print("\nEnter First Name : ");
        //                 entries[1] = input.nextLine();

        //                 System.out.print("\nEnter Last Name : ");
        //                 entries[2] = input.nextLine();

        //                 System.out.print("\nEnter Mobile Number : ");
        //                 entries[3] = input.nextLine();

        //                 System.out.print("\nEnter Email: ");
        //                 entries[4] = input.nextLine();

        //                 System.out.print("\nEnter Joining Date (YYYY-MM-DD) : ");
        //                 entries[5] = input.nextLine();

        //                 System.out.print("\nEnter Active Status (true / false) :");
        //                 entries[6] = input.nextLine();
                        
        //                 System.out.println();

        //                 try {
                            
        //                     controller.addUserEntryIntoCsvFile(entries, targetCsvPath);
        //                     System.out.println("Entry insertion success!");

        //                 } catch (Exception e) {
        //                     System.out.println(e);
        //                     System.out.print("Entry insertion failed!");
        //                 }

        //                 System.out.print("\nDo you want to enter another entry (if yes enter \"y\") : ");
        //                 exitEntry = input.nextLine().charAt(0);
        //                 System.out.println();

        //             }
                    
        //             break;   

            
        //         case 3:

        //             //To exit the Application
        //             System.out.println("Exiting the App");
        //             exit = true;
        //             break;

		//         default:
                
        //             System.out.println("Invalid Command");  //In case of User inputs invalid command
        //     }

            
        // }

        // input.close();

package com.litmus7.employeemanager.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.litmus7.employeemanager.controller.EmployeeController;
import com.litmus7.employeemanager.dto.Employee;


public class EmployeeManagerApp {

    public static void main(String[] args) {
        
        EmployeeController controller = new EmployeeController();
        Scanner input = new Scanner(System.in);                       // To read user input
        List<Employee> employees = new ArrayList<>();

        boolean exit = false;          //for exiting the application
        int command;
        char save;                   //options to opt in menu
        String path;                   //path of user specified .txt file

        while (!exit) {
            
            //Application Menu
            System.out.println("*".repeat(25)+"Available Services"+"*".repeat(25));	
            System.out.println("1. Load Text Files");
            System.out.println("2. Insert Employee details into database");
            System.out.println("3. Exit Application\n");
            System.out.print("Enter command : ");

            command = input.nextInt(); // Read user command
            System.out.println();

            switch (command) {


                // Load Text File into a raw Text, Text is later processed to extract employee fields.
                case 1:   
                    
                    // Loads '$' delimiter file into formatted output in Console.
                    System.out.print("Enter the path of file to load the data : ");
                    input.nextLine();
                    path = input.nextLine();
                    System.out.println("\n");

                    try {

                        employees = controller.loadEmployeeDataFromTextFile(path);  // Extracted employee data is converted into employee objects

                        for (Employee employee : employees) {

                            System.out.println("ID : "+employee.getId());
                            System.out.println("First Name : "+employee.getFirstName());
                            System.out.println("Last Name : "+employee.getLastName());
                            System.out.println("Email : "+employee.getEmail());
                            System.out.println("Mobile Number : "+employee.getMobileNumber());
                            System.out.println("Joining Date : "+employee.getJoiningDate());
                            System.out.println("Active Status : "+employee.isActiveStatus()+"\n");

                        }

                        //Save Loaded File into Csv
                        System.out.print("\nDo You Want To Save the Data into a csv file (enter y if yes): "); // Asks user if he want to save Valid Entries
                        save = input.next().charAt(0);

                        if(save == 'y'){   // if yes Entries are written into file     

                            input.nextLine();
                            System.out.print("\nEnter file path : ");
                            path = input.nextLine();
                            System.out.println();

                            controller.writeEmployeeDataToCsvFile(employees, path);

                        }
                        
                        System.out.print("\n\n");
                        
                    } catch (Exception e) {

                        System.out.print(e.getMessage()+"\n");

                    }

                    break;

                case 2:

                String[] entries = new String[7];
                char exitEntry = 'y';

                input.nextLine();
                System.out.print("Enter file path to insert user entries : ");
                path = input.nextLine();
                System.out.println("\n<"+"-".repeat(24)+"Entry to database"+"-".repeat(24)+">"+"\n");

                    
                while(exitEntry == 'y'){

                    System.out.print("Enter ID : ");
                    entries[0] = input.nextLine();

                    System.out.print("\nEnter First Name : ");
                    entries[1] = input.nextLine();

                    System.out.print("\nEnter Last Name : ");
                    entries[2] = input.nextLine();

                    System.out.print("\nEnter Mobile Number : ");
                    entries[3] = input.nextLine();

                    System.out.print("\nEnter Email: ");
                    entries[4] = input.nextLine();

                    System.out.print("\nEnter Joining Date (YYYY-MM-DD) : ");
                    entries[5] = input.nextLine();

                    System.out.print("\nEnter Active Status (true / false) :");
                    entries[6] = input.nextLine();
                    
                    System.out.println();

                    try {
                        
                        controller.addUserEntryIntoCsvFile(entries, path);
                        System.out.println("Entry insertion success!");

                    } catch (Exception e) {
                        System.out.println(e);
                        System.out.print("Entry insertion failed!");
                    }

                    System.out.print("\nDo you want to enter another entry (if yes enter \"y\") : ");
                    exitEntry = input.next().charAt(0);
                    System.out.println();

                }
                    
                    break;   

            
                case 3:

                    //To exit the Application
                    System.out.println("Exiting the App");
                    exit = true;
                    break;

		        default:
                
                    System.out.println("Invalid Command");  //In case of User inputs invalid command
            }

            
        }

        input.close();

    }
    
}

package com.litmus7.employeemanager.app;

import java.util.ArrayList;
import java.util.Scanner;

import com.litmus7.employeemanager.controller.EmployeeController;
import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.util.ValidationUtil;

public class EmployeeManagerApp {

    public static void main(String[] args) {
        
        EmployeeController controller = new EmployeeController();
        Scanner input = new Scanner(System.in);                       // To read user input
        ArrayList<Employee> employees = new ArrayList<>();

        boolean exit = false;          //for exiting the application
        int command;                   //options to opt in menu
        char save;    
        String path;                   //path of user specified .txt file

        while (!exit) {
            
            //Application Menu
            System.out.println("*".repeat(25)+"Available Services"+"*".repeat(25));	
            System.out.println("1. Load Text Files");
            System.out.println("2. Insert Employee details into database");
            System.out.println("3. View Database");
            System.out.println("4. Exit Application\n");
            System.out.print("Enter command : ");

            command = input.nextInt(); // Read user command

            switch (command) {


                case 1:  // Load Text File into a raw Text, Text is later processed to extract employee fields. 
                    
                    // Loads '$' delimiter file into formatted output in Console.
                    System.out.print("\nEnter the path of file to load the data : ");
                    path = input.nextLine();
                    System.out.println();
                    employees = controller.loadEmployeeDataFromTextFile(path);  // Extracted employee data is converted into employee objects
                    controller.printDataToTable(employees);
 
                    // Save Loaded File into Csv
                    System.out.print("\nDo You Want To Save the Data into a csv file (enter y if yes): ");
                    save = input.next().charAt(0);
                    if(save == 'y'){
                        controller.writeEmployeeDataToCsvFile(employees);
                    }else{
                        ValidationUtil.deleteFromCache(employees);
                    }
                    
                    break;

                case 2:

                   
                    System.out.println("Entry to database");
                    employees = controller.readDataFromUser();  //Reads Employee entry from User
                    controller.printDataToTable(employees);     //Read entries are displayed to the user

                    //If user accepts the entries are saved to csv file
                    System.out.print("\nDo you want to insert these entries into databse (enter y if yes): ");
                    System.out.println();
                    save = input.next().charAt(0);
                    if(save == 'y'){
                        System.out.println("Inserting  into database");
                        controller.insertIntoCsvFile(employees);
                    }else{
                        ValidationUtil.deleteFromCache(employees);
                    }
                    break;

                case 3:

                    //Option to see values in csv file in human readable way
                    System.out.println("Employee Database View");
                    employees = controller.loadEmployeeDataFromCsvFile();
                    controller.printDataToTable(employees);
                    break;
                

            
                case 4:

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

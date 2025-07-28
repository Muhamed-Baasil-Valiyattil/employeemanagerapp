package com.litmus7.employeemanager.app;

import java.util.ArrayList;
import java.util.Scanner;

import com.litmus7.employeemanager.controller.EmployeeController;
import com.litmus7.employeemanager.dto.Employee;

public class EmployeeManagerApp {

    public static void main(String[] args) {
        
        EmployeeController controller = new EmployeeController();
        Scanner input = new Scanner(System.in);
        ArrayList<Employee> employees = new ArrayList<>();

        boolean exit = false;
        int command;
        char save;
        String path;

        while (exit) {
            
            
            System.out.println("*".repeat(25)+"Available Services"+"*".repeat(25));	
            System.out.println("1. Load Text Files");
            System.out.println("2. Insert Employee details into database");
            System.out.println("3. View Database");
            System.out.println("4. Exit Application\n");
            System.out.print("Enter command : ");

            command = input.nextInt();

            switch (command) {
                case 1:
                    
                    // Loads '$' file into formatted output in Console.
                    System.out.print("/nEnter the path of file to load the data : ");
                    path = input.nextLine();
                    employees = controller.loadEmployeeDataFromTextFile(path);
                    controller.printDataToTable(employees);

                    System.out.print("\nDo You Want To Save the Data into a csv file (enter y if yes): ");
                    save = input.next().charAt(0);

                    if(save == 'y'){
                        controller.writeEmployeeDataToCsvFile(employees);
                    }
                    
                    break;

                case 3:

                    System.out.println("Employee Database View");
                    employees = controller.loadEmployeeDataFromCsvFile();
                    controller.printDataToTable(employees);
                    break;
            
                default:
                    break;
            }

            
        }

        input.close();

    }
    
}

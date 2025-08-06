package com.litmus7.employeemanager.app;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.litmus7.employeemanager.controller.EmployeeController;
import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.dto.EmployeeResponse;

public class EmployeeManagerApp {

    public static void main(String[] args) {

        int userChoice; 
        int id;
        boolean exit = false; // To exit Application
        Employee employee = new Employee(); //Employee object shared among Services
        Scanner input = new Scanner(System.in);

        EmployeeController employeeController = new EmployeeController();
    

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

                    try {

                        System.out.println("\n<"+"-".repeat(24)+"Entry to Employee database"+"-".repeat(24)+">"+"\n");
                        System.out.print("Enter ID : ");
                        employee.setId(input.nextInt());
                        input.nextLine();

                        System.out.print("\nEnter First Name : ");
                        employee.setFirstName(input.nextLine());

                        System.out.print("\nEnter Last Name : ");
                        employee.setLastName(input.nextLine());

                        System.out.print("\nEnter Mobile Number : ");
                        employee.setMobileNumber(input.nextLine());

                        System.out.print("\nEnter Email: ");
                        employee.setEmail(input.nextLine());

                        System.out.print("\nEnter Joining Date (YYYY-MM-DD) : ");
                        employee.setJoiningDate(LocalDate.parse(input.nextLine()));

                        System.out.print("\nEnter Active Status (true / false) :");
                        employee.setActiveStatus(Boolean.parseBoolean(input.nextLine()));

                        
                        EmployeeResponse<Void> createEmployeeResponse = employeeController.createEmployee(employee);
                        if (createEmployeeResponse.getStatusCode()==200)
                            System.out.println(createEmployeeResponse.getSuccessMessage());
                        else
                            System.out.println(createEmployeeResponse.getErrorMessage());


                        
                    } catch (Exception e) {
                        
                        System.out.println(e.getMessage());
                    }

                    

                    break;


                case 2: //Tested -- OK Checked Cases --> SQLException

                    List<Employee> employees = new ArrayList<>();
                    EmployeeResponse<List<Employee>> allEmployeesResponse = employeeController.getAllEmployeesData();

                    if (allEmployeesResponse.getStatusCode()==200) {

                        employees = allEmployeesResponse.getObject();
                        System.out.println(allEmployeesResponse.getStatusCode());
                        System.out.println(allEmployeesResponse.getErrorMessage());
                        
                        System.out.println("\n<"+"-".repeat(24)+"List of Employees"+"-".repeat(24)+">"+"\n");

                        for (Employee emp: employees) {

                            System.out.println("\n"+"*".repeat(25));
                            System.out.println("ID : "+emp.getId());
                            System.out.println("First Name : "+emp.getFirstName());
                            System.out.println("Last Name : "+emp.getLastName());
                            System.out.println("Email : "+emp.getEmail());
                            System.out.println("Mobile Number : "+emp.getMobileNumber());
                            System.out.println("Joining Date : "+emp.getJoiningDate());
                            System.out.println("Active Status : "+emp.isActiveStatus());
                            System.out.println("*".repeat(25)+"\n");

                        }  
                        
                    }else{
                        System.out.print(allEmployeesResponse.getErrorMessage());
                    }
                         
                    break;
                
                case 3: //Tested -- OK  Checked Cases -> SQLException , ID Don't Exists

                    System.out.print("\nEnter Id of Employee to retrieve : ");
                    id = input.nextInt();

                    EmployeeResponse<Employee> employeeByIdResponse = employeeController.getEmployeeById(id);
                    if (employeeByIdResponse.getStatusCode()==200) {

                        employee = employeeByIdResponse.getObject();

                        System.out.println("\nEmployee of ID "+id+" Data\n");
                        System.out.println("First Name : "+employee.getFirstName());
                        System.out.println("Last Name : "+employee.getLastName());
                        System.out.println("Email : "+employee.getEmail());
                        System.out.println("Mobile Number : "+employee.getMobileNumber());
                        System.out.println("Joining Date : "+employee.getJoiningDate());
                        System.out.println("Active Status : "+employee.isActiveStatus());
                        System.out.println("*".repeat(25)+"\n");
                        
                        
                    }else{
                        System.out.println(employeeByIdResponse.getErrorMessage());
                    }

                    break;


                case 4: //Tested -- OK  Checked Cases -> SQLException , ID Don't Exists
                    
                    System.out.print("\nEnter Id of Employee to delete : ");
                    id = input.nextInt();
                    EmployeeResponse<Void> employeeDeletionResponse = employeeController.deleteEmployeeById(id);
                    
                    if (employeeDeletionResponse.getStatusCode()==200)
                        System.out.println(employeeDeletionResponse.getSuccessMessage());   
                    else
                        System.out.println(employeeDeletionResponse.getErrorMessage());


                    break;
                
                case 5:
       

                    System.out.println("\n<" + "-".repeat(24) + "Update Employee" + "-".repeat(24) + ">" + "\n");
                    System.out.print("Enter ID of the Employee you want to Update: ");
                    id = input.nextInt();
                    input.nextLine();                                       
                    employee.setId(id);

                    // Get current employee data from database
                    EmployeeResponse<Employee> employeeInformationResponse = employeeController.getEmployeeById(id);

                    if (employeeInformationResponse.getStatusCode()==200) {

                        employee = employeeInformationResponse.getObject();
                        System.out.println("\nChoose what to update:");
                        System.out.println("1. Update all fields");
                        System.out.println("2. Update specific fields");
                        System.out.println("3. Cancel");
                        System.out.print("Enter your choice: ");

                        int choice = input.nextInt();
                        input.nextLine(); 

                        switch (choice) {
                            case 1:  // Update all fields
                                System.out.print("\nEnter First Name (current: " + employee.getFirstName() + "): ");
                                employee.setFirstName(input.nextLine());

                                System.out.print("\nEnter Last Name (current: " + employee.getLastName() + "): ");
                                employee.setLastName(input.nextLine());

                                System.out.print("\nEnter Mobile Number (current: " + employee.getMobileNumber() + "): ");
                                employee.setMobileNumber(input.nextLine());

                                System.out.print("\nEnter Email (current: " + employee.getEmail() + "): ");
                                employee.setEmail(input.nextLine());

                                System.out.print("\nEnter Joining Date (YYYY-MM-DD) (current: " + employee.getJoiningDate() + "): ");
                                employee.setJoiningDate(LocalDate.parse(input.nextLine()));

                                System.out.print("\nEnter Active Status (true/false) (current: " + employee.isActiveStatus() + "): ");
                                employee.setActiveStatus(Boolean.parseBoolean(input.nextLine()));

                                EmployeeResponse<Void> updateAllEmployeeDataResponse = employeeController.updateEmployee(employee);

                                if (updateAllEmployeeDataResponse.getStatusCode()==200)
                                    System.out.println(updateAllEmployeeDataResponse.getSuccessMessage());   
                                else
                                    System.out.println(updateAllEmployeeDataResponse.getErrorMessage());
                                
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
                                                System.out.print("\nEnter First Name (current: " + employee.getFirstName() + "): ");
                                                employee.setFirstName(input.nextLine());
                                                break;
                                            case 2:
                                                System.out.print("\nEnter Last Name (current: " + employee.getLastName() + "): ");
                                                employee.setLastName(input.nextLine());
                                                break;
                                            case 3:
                                                System.out.print("\nEnter Mobile Number (current: " + employee.getMobileNumber() + "): ");
                                                employee.setMobileNumber(input.nextLine());
                                                break;
                                            case 4:
                                                System.out.print("\nEnter Email (current: " + employee.getEmail() + "): ");
                                                employee.setEmail(input.nextLine());
                                                break;
                                            case 5:
                                                System.out.print("\nEnter Joining Date (YYYY-MM-DD) (current: " + employee.getJoiningDate() + "): ");
                                                employee.setJoiningDate(LocalDate.parse(input.nextLine()));
                                                break;
                                            case 6:
                                                System.out.print("\nEnter Active Status (true/false) (current: " + employee.isActiveStatus() + "): ");
                                                employee.setActiveStatus(Boolean.parseBoolean(input.nextLine()));
                                                break;
                                            default:
                                                System.out.println("Invalid field number: " + fieldNum);
                                        }

                                    }catch (NumberFormatException e) {
                                        System.out.println("Invalid input: " + field);
                                    }
                                }

                                EmployeeResponse<Void> updateEmployeeDataResponse = employeeController.updateEmployee(employee);
                                if (updateEmployeeDataResponse.getStatusCode()==200)
                                        System.out.println(updateEmployeeDataResponse.getSuccessMessage());   
                                    else
                                        System.out.println(updateEmployeeDataResponse.getErrorMessage());
                                break;

                            case 3:  // Cancel
                                System.out.println("Update cancelled.");
                                break;

                            default:
                                System.out.println("Invalid choice!");
                            }
                    }else 
                        System.out.println(employeeInformationResponse.getErrorMessage());
                    
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
    




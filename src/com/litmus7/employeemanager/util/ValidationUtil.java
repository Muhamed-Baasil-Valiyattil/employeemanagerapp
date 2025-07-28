package com.litmus7.employeemanager.util;

import java.time.LocalDate;
import java.util.HashSet;
import com.litmus7.employeemanager.dto.Employee;

public class ValidationUtil {

    private static final int ID_WIDTH = 5;
    private static final int NAME_WIDTH = 16;
    private static final int EMAIL_WIDTH = 25;
    private static HashSet<Integer> idCache = new HashSet<>();
    private static HashSet<String> mobileNumberCache = new HashSet<>();
    private static HashSet<String> emailCache = new HashSet<>();
    private static String namePattern = "[A-Za-z ]+";
    private static String mobileNumberPattern = "\\d{10}";
    private static String emailPattern = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}"; 
    private static LocalDate todayDate = LocalDate.now();
    private static LocalDate companyCreationDate = LocalDate.parse("2009-06-30");
    private static final String VALID = "valid";


    public static String validateEmployeeObject(Employee employee){

        String message = VALID;

        int id;

        try {
           
            id = Integer.parseInt(employee.getId());  
            
        } catch (Exception e) {
            
            message = "ID is not a valid Integer";
            return message;
        }

        if (id<0) {

            message = "ID must be a positive number";
            return message;
            
        }else if(idCache.contains(id)){

            message = "ID is Duplicate! Please Change";
            return message;
    
        }else if (Integer.toString(id).length() > ID_WIDTH) {

            message = "ID out of limit!";
            return message;
        
        }
        

        String firstName = employee.getFirstName();

		if(firstName.isEmpty())
		{
			message = "First Name cannot be empty.";
			return message;

		}else if (!firstName.matches(namePattern)) {

            message = "Invalid First Name!, Name should only contain alphabets";
            return message;

        }else if (firstName.length() > NAME_WIDTH) {

            message = String.format("First Name is too long , it should be under %d characters",NAME_WIDTH);
            return message;
        }
		
		
		String lastName = employee.getLastName();
		if(lastName.isEmpty())
		{
			message = "Last Name cannot be empty.";
			return message;

		}else if (!lastName.matches(namePattern)) {

            message = "Invalid Last Name!, Name should only contain alphabets";
            return message;

        }else if (lastName.length() > NAME_WIDTH) {

            message = String.format("Last Name is too long , it should be under %d characters",NAME_WIDTH);
            return message;
        }
		
		String mobileNumber = employee.getMobileNumber();
		if (!mobileNumber.matches(mobileNumberPattern))
		{
		    message = "Invalid mobile number. Must be 10 digits.";
		    return message;
		}else if (mobileNumberCache.contains(mobileNumber)) {

            message = "Mobile Number already exists!";
            return message;
            
        }
		
		String email = employee.getEmail();
		if(!email.matches(emailPattern))
		{
			message = "Invalid email address.";
			return message;

		}else if (email.length() > EMAIL_WIDTH) {

            message = String.format("email is too long , must be under %d characters",EMAIL_WIDTH);
            return message;
            
        }else if (emailCache.contains(email)) {

            message = "Email Already Exists! ";
            return message;
            
        }
		
		LocalDate date = employee.getJoiningDate();
		
		if(date.isAfter(todayDate) || date.isBefore(companyCreationDate))
		{
			message = "Invalid joining date.";
			return message;
		}

		idCache.add(id);
        emailCache.add(email);
        mobileNumberCache.add(mobileNumber);

        return message;

    }



}

package com.litmus7.employeemanager.util;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import com.litmus7.employeemanager.dto.Employee;

/**
* Handles employee object validation
*
*/

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


    /**
    * Validates Employee object fields
    *
    * @param employee
    * @return message
    */

    public static String validateEmployeeObject(Employee employee){

        String message = VALID;
 
        /**
         * ID validations :
         * If ID is integer type
         * If ID is positive
         * If ID is less than the limit defined
         * If ID is not duplicate
         */

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

        /**
         * FirstName validations :
         * If FirstName is not empty
         * If FirstName follows name pattern (Only alphabets)
         * If FirstName is less than the maximum String length defined
         */
        

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

        /**
         * LastName validations :
         * If LastName is not empty
         * If LastName follows name pattern (Only alphabets)
         * If LastName is less than the maximum String length defined
         */
		
		
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

        /**
         * Mobile Number validations :
         * If Mobile Number follows mobile number pattern (Only 10 digits)
         * If Mobile Number is not duplicate
         */
		

		String mobileNumber = employee.getMobileNumber();
		if (!mobileNumber.matches(mobileNumberPattern))
		{
		    message = "Invalid mobile number. Must be 10 digits.";
		    return message;
		}else if (mobileNumberCache.contains(mobileNumber)) {

            message = "Mobile Number already exists!";
            return message;
            
        }

        /**
         * Email validations :
         * If Email follows email pattern (abc@abc.abc)
         * If Email is not duplicate
         * If Email is less than defined size
         */
		
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

        /**
         * Joining Date validations :
         * If Joining Date is a valid date
         * If Joining Date is not a future date
         * If Joining Date is after company establishment
         */

        LocalDate date;
        try {

            date = LocalDate.parse(employee.getJoiningDate());
            
         } catch (Exception e) {
            
            message = e.getMessage();
            return message;

         }
		
		
		
		if(date.isAfter(todayDate) || date.isBefore(companyCreationDate))
		{
			message = "Invalid joining date.";
			return message;
		}

        //valid object ID,Email,MobileNumber is added their cache so it can be checked for duplicates
		idCache.add(id);
        emailCache.add(email);
        mobileNumberCache.add(mobileNumber);

        return message;

    }

    /**
     * Delete from Cache if user doesn't save the record
     * 
     * @param employees
     */

    public static void deleteFromCache(List<Employee> employees){

        for (Employee employee : employees) {
            
            //valid object ID,Email,MobileNumber is added their cache so it can be checked for duplicates
            idCache.remove(Integer.parseInt(employee.getId()));
            emailCache.remove(employee.getEmail());
            mobileNumberCache.remove(employee.getMobileNumber());

        }
        
    }



}

package com.litmus7.employeemanager.util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashSet;

import com.litmus7.employeemanager.exception.*;

/**
* Handles employee object validation
*
*/

public class ValidationUtil {


    private static HashSet<Integer> idCache = new HashSet<>();
    // private static HashSet<String> mobileNumberCache = new HashSet<>();
    // private static HashSet<String> emailCache = new HashSet<>(); 
    // private static LocalDate companyCreationDate = LocalDate.parse("2009-06-30");
    private static LocalDate todayDate = LocalDate.now();
    

    //Id Validation

    public static void validateID(String value) throws ValidationException{

        try {
            
            int id = Integer.parseInt(value);

            if (id < 0) {

                throw new ValidationException("ID",value+" must be positive Integer");
                
            }

            if (idCache.contains(id)) {
                

                throw new ValidationException("ID ",value+": is duplicate !");

            }

            idCache.add(id);
            
        } catch (NumberFormatException e) {

            throw new ValidationException("ID",value +" must be a valid integer value", e);
        
        }

    }

    //Name Validation


    public static void validateName(String value) throws ValidationException {

        if (value.isEmpty()) {

            throw new ValidationException("Name",value + " must not be Empty");
            
        }

        if (!value.matches("[A-Za-z ]+")) {
            
            throw new ValidationException("Name",value + " format is incorrect , must only contains alphabets");
        }

    }

    //Email Validation

    public static void validateEmail(String value) throws ValidationException{

        if (value.isEmpty()) {

            throw new ValidationException("Email",value + " must not be Empty");
            
        }

        if (!value.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.(com|in|org|net)$")) {
            
            throw new ValidationException("Email",value+ " format is incorrect ,format must be : user@domain.com");
        }


    }

    //Mobile Number Validaiton

    public static void validateMobileNumber(String value) throws ValidationException{

        if (value.isEmpty()) {

            throw new ValidationException("Mobile Number", value + " must not be Empty");
            
        }

        if (!value.matches("\\d{10}")) {
            
            throw new ValidationException("Mobile Number" ,value + " format is incorrect , it should have 10 digits");
        }

    }

    //Joining Date Validation

    public static void validateJoiningDate(String value) throws ValidationException{

        try {

            LocalDate joiningDate = LocalDate.parse(value);

            if(joiningDate.isAfter(todayDate))
            {
                throw new ValidationException("Date",value + " must not be a future date");
            }
            
        } catch (DateTimeParseException e) {

            throw new ValidationException("Date",value + " Invalid",e);
            
        }

    }

    //Active Status Validation

    public static void validateActiveStatus(String value) throws ValidationException{

        if(!((value.compareTo("true")==0) || (value.compareTo("false")==0))){

            throw new ValidationException("Active Status", value + " must be either true / false");

        }
    }

    //To reset the Cache

    public static void resetIdCache(){
        idCache.clear();
    }
        
    }





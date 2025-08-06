package com.litmus7.employeemanager.util;

import java.time.LocalDate;

import com.litmus7.employeemanager.exception.*;

/**
* Handles employee object validation
*
*/

public class ValidationUtil {


    private static LocalDate todayDate = LocalDate.now();
    //Id Validation

    public static void validateID(int id) throws ValidationException{

        if (id < 0)
            throw new ValidationException("ID "+id+" must be positive Integer");      
    }

    //Name Validation


    public static void validateName(String value) throws ValidationException {

        if (value.isBlank())
            throw new ValidationException("Name must not be Empty");

        if (!value.matches("[A-Za-z ]+")) 
            throw new ValidationException("Name format is incorrect , must only contains alphabets");
    }

    //Email Validation

    public static void validateEmail(String value) throws ValidationException{

        if (value.isBlank())
            throw new ValidationException("Email must not be Empty");

        if (!value.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.(com|in|org|net)$"))
            throw new ValidationException("Email "+value+" format is incorrect ,format must be : user@domain.com");
    }

    //Mobile Number Validaiton

    public static void validateMobileNumber(String value) throws ValidationException{

        if (value.isBlank())
            throw new ValidationException("Mobile Number must not be Empty");

        if (!value.matches("\\d{10}")) 
            throw new ValidationException("Mobile Number " +value +" format is incorrect , it should have 10 digits");
        

    }

    //Joining Date Validation

    public static void validateJoiningDate(LocalDate joiningDate) throws ValidationException{

        if(joiningDate.isAfter(todayDate))
            throw new ValidationException("Date "+joiningDate+" must not be a future date");
    }

        
    }





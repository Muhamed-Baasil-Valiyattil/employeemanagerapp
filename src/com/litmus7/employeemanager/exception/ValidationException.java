package com.litmus7.employeemanager.exception;

public class ValidationException extends Exception {
    
    public ValidationException(String message) {
        super(message);
        
    }

    public ValidationException(String type, String message , Throwable cause) {
        super(message, cause);
        
    }
    
}
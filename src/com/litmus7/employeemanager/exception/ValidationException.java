package com.litmus7.employeemanager.exception;

public class ValidationException extends Exception {
    private final String type;
    
    public ValidationException(String type, String message) {
        super(type + ": " + message + "\n");
        this.type = type;
    }

    public ValidationException(String type, String message , Throwable cause) {
        super(type + ": " + message + "\n" , cause);
        this.type = type;
    }
    
    public String getFieldName() {
        return type;
    }
}
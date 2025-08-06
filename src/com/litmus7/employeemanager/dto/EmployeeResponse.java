
package com.litmus7.employeemanager.dto;

public class EmployeeResponse <T>{

    private int statusCode;
    private String errorMessage;
    private String successMessage;
    private T object;

    public int getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    public String getSuccessMessage() {
        return successMessage;
    }
    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }
    public T getObject() {
        return object;
    }
    public void setObject(T object) {
        this.object = object;
    }
    
}
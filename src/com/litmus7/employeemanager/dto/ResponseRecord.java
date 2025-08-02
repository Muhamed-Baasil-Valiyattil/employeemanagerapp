package com.litmus7.employeemanager.dto;

import java.util.List;

public record ResponseRecord(
    List<Employee> employees,
    StringBuilder log
){} 

package com.owczarek;

import java.math.BigDecimal;

class Employee {
    private Long id;
    private String firstName;
    private String lastName;
    private BigDecimal salary;
    private static long lastEmployeeId = 0;

    Employee(Long id, String firstName, String lastName, BigDecimal salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    static long getNextEmployeeId() {
        return lastEmployeeId++;
    }

}
package com.owczarek;

import java.math.BigDecimal;

class Employee {
    private Long id;
    private String firstName;
    private String lastName;
    private BigDecimal salary;
    private static long lastEmployeeId = 0;

    Employee() {

    }

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

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public static void setLastEmployeeId(long lastEmployeeId) {
        Employee.lastEmployeeId = lastEmployeeId;
    }

    static long getNextEmployeeId() {
        return lastEmployeeId++;
    }

}
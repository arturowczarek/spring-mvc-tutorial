package com.owczarek;

import java.math.BigDecimal;

class Employee {
    private String firstName;
    private String lastName;
    private BigDecimal salary;

    Employee(String firstName, String lastName, BigDecimal salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
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

}

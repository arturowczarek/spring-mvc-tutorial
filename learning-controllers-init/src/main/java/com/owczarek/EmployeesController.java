package com.owczarek;

import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EmployeesController {
    @RequestMapping("getEmployees")
    public List<Employee> getEmployees() {
        List<Employee> result = new ArrayList<>();

        result.add(new Employee("Jan", "Nowak", new BigDecimal("3453.243")));
        result.add(new Employee("Ola", "Kowalczyk", new BigDecimal("7654.00")));
        result.add(new Employee("Karol", "Dłubak", new BigDecimal("2234.54")));

        return result;
    }
}

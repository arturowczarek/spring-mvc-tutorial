package com.owczarek;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("employees")
@RestController
public class EmployeesController {
    @RequestMapping(value = "/getEmployees", method = {RequestMethod.POST, RequestMethod.GET})
//    @PostMapping("/getEmployees")
    public List<Employee> getEmployees() {
        List<Employee> result = new ArrayList<>();

        result.add(new Employee("Jan", "Nowak", new BigDecimal("3453.243")));
        result.add(new Employee("Ola", "Kowalczyk", new BigDecimal("7654.00")));
        result.add(new Employee("Karol", "Dłubak", new BigDecimal("2234.54")));

        return result;
    }

    //    @RequestMapping(value = "findAny", method = RequestMethod.GET)
    @GetMapping("findAny")
    public Employee findAny() {
        return new Employee("Jan", "Nowak", new BigDecimal("3453.243"));
    }
}

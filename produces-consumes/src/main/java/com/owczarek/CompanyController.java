package com.owczarek;

import org.springframework.http.MediaType;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RequestMapping(value = "companies", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
@RestController
public class CompanyController {
    private final CompanyRepository companyRepository;

    public CompanyController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @GetMapping
    List<Company> findAll() {
        return companyRepository.findAll();
    }

    @GetMapping(value = "/{companyName}", produces = MediaType.APPLICATION_XML_VALUE)
    Company findOne(@PathVariable("companyName") String name) {
        return companyRepository.findOne(name);
    }

    @GetMapping("{companyName}/employees/{firstName:[a-zA-Z]+}")
    List<Employee> findCompanyEmployeesWithFirstName(
            @PathVariable String companyName,
            @PathVariable("firstName") String name) {
        return findOne(companyName)
                .getEmployees()
                .stream()
                .filter(employee -> Objects.equals(employee.getFirstName(), name))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "{companyName}/employees/{employeeId:\\d+}", produces = MediaType.APPLICATION_JSON_VALUE)
    Employee findCompanyEmployeeWithId(
            @PathVariable String companyName,
            @PathVariable long employeeId
    ) {
        return findOne(companyName)
                .getEmployees()
                .stream()
                .filter(employee -> employee.getId() == employeeId)
                .findAny()
                .orElse(null);
    }

    @GetMapping("{companyName}/employees/{lastName}/{firstName}")
    List<Employee> findCompanyEmployeesWithLastNameAndFirstName(@PathVariable Map<String, String> pathVariable) {
        return findOne(pathVariable.get("companyName"))
                .getEmployees()
                .stream()
                .filter(employee -> Objects.equals(employee.getLastName(), pathVariable.get("lastName")))
                .filter(employee -> Objects.equals(employee.getFirstName(), pathVariable.get("firstName")))
                .collect(Collectors.toList());
    }

    @PostMapping("{companyName}/employees")
    Employee addEmployee(
            @PathVariable String companyName,
            @RequestParam(value = "firstName", required = true) String name,
            @RequestParam String lastName,
            @RequestParam(required = false) BigDecimal salary
    ) {
        Company original = companyRepository.findOne(companyName);
        List<Employee> employees = new ArrayList<>(original.getEmployees());
        Employee employee = new Employee(Employee.getNextEmployeeId(), name, lastName, salary);
        employees.add(employee);
        Company newCompany = new Company(original.getName(), employees);
        companyRepository.save(newCompany);
        return employee;
    }

    @PostMapping(
            value = "{companyName}/employees/create",
            produces = MediaType.APPLICATION_XML_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Employee> addEmployees(
            @PathVariable String companyName,
            @RequestBody AddEmployeesRequest request
    ) {
        Company original = companyRepository.findOne(companyName);
        List<Employee> employees = new ArrayList<>(original.getEmployees());
        List<Employee> newEmployees = createEmployees(request.getEmployees());
        employees.addAll(newEmployees);
        Company newCompany = new Company(original.getName(), employees);
        companyRepository.save(newCompany);
        return newEmployees;
    }

    private List<Employee> createEmployees(List<Employee> employees) {
        return employees
                .stream()
                .map(employee ->
                        new Employee(
                                Employee.getNextEmployeeId(),
                                employee.getFirstName(),
                                employee.getLastName(),
                                employee.getSalary()))
                .collect(Collectors.toList());
    }
}

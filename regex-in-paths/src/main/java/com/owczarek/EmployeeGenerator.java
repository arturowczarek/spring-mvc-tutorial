package com.owczarek;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
class EmployeeGenerator {
    private static final String FIRST_NAMES_FILE_PATH = "firstNames";
    private static final double SALARY_BASE = 1_000.0;
    private static final double SALARY_SPREAD = 10_000.0;
    private final FileLinesLoader fileLinesLoader;
    private List<String> firstNames;
    private List<String> lastNames;
    private final Random random = new Random();
    private static long lastEmployeeId = 0;

    EmployeeGenerator(FileLinesLoader fileLinesLoader) {
        this.fileLinesLoader = fileLinesLoader;
    }

    List<Employee> generate(int numberOfEmployees) {
        return Stream
                .generate(this::generate)
                .limit(numberOfEmployees)
                .collect(Collectors.toList());
    }

    private Employee generate() {
        return new Employee(
                getEmployeeId(),
                getRandomFirstName(),
                getRandomLastName(),
                getRandomSalary()
        );
    }

    private long getEmployeeId() {
        return lastEmployeeId++;
    }

    private String getRandomFirstName() {
        return getRandom(getFirstNames());
    }

    private String getRandomLastName() {
        return getRandom(getLastNames());
    }

    private String getRandom(List<String> elements) {
        return elements.get(
                random.nextInt(
                        elements.size()));
    }

    private List<String> getFirstNames() {
        if (firstNames == null) {
            firstNames = fileLinesLoader.loadLines(FIRST_NAMES_FILE_PATH);
        }
        return firstNames;
    }

    private List<String> getLastNames() {
        if (lastNames == null) {
            lastNames = fileLinesLoader.loadLines("lastNames");
        }
        return lastNames;
    }


    private BigDecimal getRandomSalary() {
        return new BigDecimal(SALARY_BASE + Math.random() * SALARY_SPREAD);
    }

}

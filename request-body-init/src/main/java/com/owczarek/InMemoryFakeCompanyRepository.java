package com.owczarek;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class InMemoryFakeCompanyRepository implements CompanyRepository {
    private Map<String, Company> companies;

    public InMemoryFakeCompanyRepository(CompanyGenerator companyGenerator) {
        companies = companyGenerator
                .generate()
                .stream()
                .collect(
                        Collectors.toMap(
                                Company::getName,
                                Function.identity()));
    }

    @Override
    public List<Company> findAll() {
        return new ArrayList<>(companies.values());
    }

    @Override
    public Company findOne(String name) {
        return companies.get(name);
    }

    @Override
    public Company save(Company company) {
        return companies.put(company.getName(), company);
    }

    @Override
    public List<Company> save(List<Company> companies) {
        companies
                .forEach(company ->
                        this.companies.put(
                                company.getName(),
                                company));
        return companies;
    }
}

package com.owczarek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class InMemoryFakeCompanyRepository implements CompanyRepository {
    private List<Company> companies;

//    @Autowired
    public InMemoryFakeCompanyRepository(CompanyGenerator companyGenerator) {
        companies = companyGenerator.generate();
    }

    @Override
    public List<Company> findAll() {
        return new ArrayList<>(companies);
    }

    @Override
    public Company findOne(String name) {
        return companies
                .stream()
                .filter(company -> Objects.equals(company.getName(), name))
                .findFirst()
                .orElseGet(() -> null);
    }
}

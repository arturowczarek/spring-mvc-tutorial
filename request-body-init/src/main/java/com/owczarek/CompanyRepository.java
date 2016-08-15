package com.owczarek;

import java.util.List;

interface CompanyRepository {
    List<Company> findAll();
    Company findOne(String name);
    Company save(Company company);
    List<Company> save(List<Company> companies);
}

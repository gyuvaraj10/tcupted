package com.tcup.ted.services.companies.repositories;

import com.tcup.ted.services.companies.model.TcupCompany;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TcupCompanyRepository extends MongoRepository<TcupCompany, Long> {
    List<TcupCompany> findByName(String name);
}

package com.tcup.ted.services.companies.Impl;

import com.tcup.ted.services.companies.ICompany;
import com.tcup.ted.services.companies.model.TcupCompany;
import com.tcup.ted.services.companies.repositories.TcupCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService implements ICompany {

    @Autowired
    TcupCompanyRepository tcupCompanyRepository;

    @Override
    public TcupCompany createCompany(TcupCompany company) {
        tcupCompanyRepository.save(company);
        return company;
    }

    @Override
    public TcupCompany getCompany(String companyName) {
        return tcupCompanyRepository.findByName(companyName).get(0);
    }
}

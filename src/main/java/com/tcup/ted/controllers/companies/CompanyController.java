package com.tcup.ted.controllers.companies;

import com.tcup.ted.services.companies.Impl.CompanyService;
import com.tcup.ted.services.companies.model.TcupCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public ResponseEntity<TcupCompany> createCompany(HttpEntity<TcupCompany> companyHttpEntity) {
        TcupCompany createdCompany = companyService.createCompany(companyHttpEntity.getBody());
        return new ResponseEntity<TcupCompany>(createdCompany, HttpStatus.ACCEPTED);
    }

    @RequestMapping(path = "/update", method = RequestMethod.PUT)
    public ResponseEntity updateCompany(HttpEntity<TcupCompany> companyHttpEntity) {
        return null;
    }
}

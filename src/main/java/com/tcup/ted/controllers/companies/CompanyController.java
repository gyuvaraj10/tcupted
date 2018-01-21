package com.tcup.ted.controllers.companies;

import com.tcup.ted.services.companies.Impl.CompanyService;
import com.tcup.ted.services.companies.model.TcupCompany;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @RequestMapping(path = "/create", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<TcupCompany> createCompany(@RequestBody TcupCompany companyHttpEntity) {
        TcupCompany createdCompany = companyService.createCompany(companyHttpEntity);
        return new ResponseEntity<TcupCompany>(createdCompany, HttpStatus.ACCEPTED);
    }

    @RequestMapping(path = "/getAll", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<Collection<TcupCompany>> getCompanies() {
        return new ResponseEntity<>(companyService.getAllCompanyDetails(), HttpStatus.OK);
    }

    @RequestMapping(path = "/update", method = RequestMethod.PUT)
    public ResponseEntity updateCompany(HttpEntity<TcupCompany> companyHttpEntity) {
        return null;
    }
}

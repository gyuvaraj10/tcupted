package com.tcup.ted.controllers.companies;

import com.google.gson.Gson;
import com.tcup.ted.services.companies.Impl.CompanyService;
import com.tcup.ted.services.companies.model.TcupCompany;
import com.tcup.ted.services.git.IGitService;
import org.eclipse.egit.github.core.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private IGitService gitHubService;

    @RequestMapping(path = "/create", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<TcupCompany> createCompany(@RequestBody TcupCompany companyHttpEntity) throws Exception {
        TcupCompany createdCompany = companyService.createCompany(companyHttpEntity);
        String projectName = companyHttpEntity.getProjectList().get(0).getProjectName();
        Repository longRunnableFuture = gitHubService.createProject(projectName);
        Boolean importProject = gitHubService.importProject(projectName);
        if(longRunnableFuture!= null && importProject){
            return new ResponseEntity<>(createdCompany, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(createdCompany, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(path = "/getAll", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<Collection<TcupCompany>> getCompanies() {
        return new ResponseEntity<>(companyService.getAllCompanyDetails(), HttpStatus.OK);
    }
}

package com.tcup.ted.controllers.suites;

import com.tcup.ted.db.entities.TestSuite;
import com.tcup.ted.services.suites.impl.TestSuiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Yuvaraj on 21/01/2018.
 */
@RestController
@RequestMapping(path = "/suite")
public class TestSuiteController {

    @Autowired
    private TestSuiteService testSuiteService;

    @RequestMapping(path ="/{project}",method = RequestMethod.GET)
    @ResponseBody
    public List<TestSuite> getTestSuites(@PathVariable String project) {
        return testSuiteService.getTestSuitesByProjectName(project);
    }

    @RequestMapping(path ="/create/{project}/{name}",method = RequestMethod.POST)
    public boolean createTestSuite(@PathVariable String project, @PathVariable String name, @RequestParam String description) {
        return testSuiteService.createTestSuite(project, name, description);
    }

}

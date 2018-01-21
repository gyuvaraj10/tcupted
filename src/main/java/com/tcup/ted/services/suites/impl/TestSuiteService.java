package com.tcup.ted.services.suites.impl;

import com.tcup.ted.db.entities.TestSuite;
import com.tcup.ted.db.repositories.TestSuiteRepository;
import com.tcup.ted.services.suites.ISuiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Yuvaraj on 21/01/2018.
 */
@Service
public class TestSuiteService implements ISuiteService {

    @Autowired
    private TestSuiteRepository testSuiteRepository;

    public List<TestSuite> getTestSuitesByProjectName(String projectName) {
        return testSuiteRepository.findByProjectName(projectName);
    }

    @Override
    public boolean createTestSuite(String project, String name, String description) {
        TestSuite testSuite = new TestSuite();
        testSuite.setTestSuiteName(name);
        testSuite.setProjectName(project);
        testSuite.setTestSuiteDescription(description);
        TestSuite suite = testSuiteRepository.save(testSuite);
        if(suite!=null) {
            return true;
        }
        return false;
    }
}

package com.tcup.ted.db.entities;

import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * Created by Yuvaraj on 21/01/2018.
 */
public class TestSuite {

    @Id
    private String testSuiteName;

    private String projectName;

    private String testSuiteDescription;

    private List<String> tests;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTestSuiteName() {
        return testSuiteName;
    }

    public void setTestSuiteName(String testSuiteName) {
        this.testSuiteName = testSuiteName;
    }

    public String getTestSuiteDescription() {
        return testSuiteDescription;
    }

    public void setTestSuiteDescription(String testSuiteDescription) {
        this.testSuiteDescription = testSuiteDescription;
    }

    public List<String> getTests() {
        return tests;
    }

    public void setTests(List<String> tests) {
        this.tests = tests;
    }
}

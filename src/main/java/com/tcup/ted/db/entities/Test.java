package com.tcup.ted.db.entities;

/**
 * Created by Yuvaraj on 21/01/2018.
 */
public class Test {
    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestDescription() {
        return testDescription;
    }

    public void setTestDescription(String testDescription) {
        this.testDescription = testDescription;
    }

    private String testName;
    private String testDescription;
}

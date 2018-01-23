package com.tcup.ted.services.companies.model;


import java.io.Serializable;


public class TcupProject implements Serializable {

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String projectName;

    public String getProjectName() {
        return projectName;
    }

    public String getDescription() {
        return description;
    }

    private String description;

    public TcupProject() {
    }
}

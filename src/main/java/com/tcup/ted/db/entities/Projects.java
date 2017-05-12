package com.tcup.ted.db.entities;

public class Projects{

    private String id;
    private String code;
    private String project;

    public Projects(String code, String project) {
        this.code = code;
        this.project = project;
    }

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getProject() {
        return project;
    }
}

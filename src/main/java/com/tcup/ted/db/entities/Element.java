package com.tcup.ted.db.entities;

import org.springframework.data.annotation.Id;

/**
 * Created by Yuvaraj on 23/01/2018.
 */
public class Element {

    @Id
    private String id;
    private String identifier;
    private String value;
    private String code;
    private String name;
    private String projectName;
    private String pageName;

    public Element() {

    }

    public Element(String identifier, String value, String code, String name, String projectName, String pageName) {
        this.identifier = identifier;
        this.name = name;
        this.value = value;
        this.code = code;
        this.projectName = projectName;
        this.pageName = pageName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

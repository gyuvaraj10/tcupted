package com.tcup.ted.services.companies.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TcupCompany {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setProjectList(TcupProject[] projectList) {
        this.projectList = projectList;
    }

    private String email;

    @JsonFormat(with= JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private TcupProject[] projectList;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public TcupProject[] getProjectList() {
        return projectList;
    }

    public TcupCompany() {

    }

    @Override
    public String toString() {
        return "TcupCompany{'name='"+name+",'email'="+email+",'projectList'="+ Arrays.toString(projectList)+"}";
    }
}

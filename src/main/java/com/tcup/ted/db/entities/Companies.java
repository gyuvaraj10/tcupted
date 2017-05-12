package com.tcup.ted.db.entities;


import org.springframework.data.annotation.Id;

/**
 * Created by Yuvaraj on 16/03/2017.
 */
public class Companies{

    @Id
    private String id;
    private String code;
    private String company;

    public Companies(String code, String company) {
        this.code = code;
        this.company = company;
    }

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getCompany() {
        return company;
    }

}

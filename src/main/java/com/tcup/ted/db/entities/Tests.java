package com.tcup.ted.db.entities;

import org.springframework.data.annotation.Id;

public class Tests{

    @Id
    private String id;
    private String code;
    private String test;
    private String pageName;
    private String elementName;
    private String actionName;

    public Tests(String code, String test, String pageName, String elementName, String actionName) {
        this.code = code;
        this.test = test;
        this.pageName = pageName;
        this.elementName = elementName;
        this.actionName = actionName;
    }

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getTest() {
        return test;
    }

    public String getPageName() {
        return pageName;
    }

    public String getElementName() {
        return elementName;
    }

    public String getActionName() {
        return actionName;
    }
}

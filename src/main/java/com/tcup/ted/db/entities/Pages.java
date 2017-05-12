package com.tcup.ted.db.entities;

import org.springframework.data.annotation.Id;

public class Pages{

    @Id
    private String id;
    private String code;
    private String pageName;
    private String elementIdentifier;
    private String elementIdentifierValue;
    private String elementName;

    public Pages(String code, String pageName, String elementIdentifier,
                 String elementIdentifierValue, String elementName) {
        this.code = code;
        this.pageName = pageName;
        this.elementIdentifier = elementIdentifier;
        this.elementIdentifierValue = elementIdentifierValue;
        this.elementName = elementName;
    }

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getPageName() {
        return pageName;
    }

    public String getElementIdentifier() {
        return elementIdentifier;
    }

    public String getElementIdentifierValue() {
        return elementIdentifierValue;
    }

    public String getElementName() {
        return elementName;
    }
}

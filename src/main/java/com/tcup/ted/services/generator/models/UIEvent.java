package com.tcup.ted.services.generator.models;

public enum UIEvent {

    CLICK("click"),
    SELECT("select"),
    DRAGDROP("dragDrop"),
    HOVER("hover");

    public String getEvent() {
        return event;
    }

    private String event;

    UIEvent(String event){
        this.event = event;
    }
}

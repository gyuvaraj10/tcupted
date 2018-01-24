package com.tcup.ted.services.generator.models;

public enum UIEvent {

    CLICK("click"),
    SELECT("select"),
    DRAG_DROP("dragDrop"),
    HOVER("hover"),
    DOUBLE_CLICK("doubleClick");


    public String getEvent() {
        return event;
    }

    private String event;

    UIEvent(String event){
        this.event = event;
    }
}

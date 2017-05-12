package com.tcup.ted.db.entities;

import org.springframework.data.annotation.Id;

/**
 * Created by Yuvaraj on 16/03/2017.
 */
public class Actions {

    @Id
    private String id;
    private String actionName;

    public Actions(String id, String actionName) {
        this.id = id;
        this.actionName = actionName;
    }

    public String getId() {
        return id;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }
}

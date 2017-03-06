package com.tcup.ted.services.generator.models;

public class PageAction {

    private String actionName;

    private String element;

    private UIEvent uiEvent;

    public PageAction () {
    }

    private PageAction(String actionName, String element, UIEvent uiEvent) {
        this.actionName = actionName;
        this.element = element;
        this.uiEvent = uiEvent;
    }

    public String getActionName() {
        return actionName;
    }

    public String getElement() {
        return element;
    }

    public UIEvent getUiEvent() {
        return uiEvent;
    }


    public static class Builder {
        private String actionName;
        private String element;
        private UIEvent uiEvent;

        private Builder() {
        }

        public static Builder pageAction() {
            return new Builder();
        }

        public Builder withActionName(String actionName) {
            this.actionName = actionName;
            return this;
        }

        public Builder withElement(String element) {
            this.element = element;
            return this;
        }

        public Builder withUiEvent(UIEvent uiEvent) {
            this.uiEvent = uiEvent;
            return this;
        }

        public PageAction build() {
            return new PageAction();
        }
    }
}

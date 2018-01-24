package com.tcup.ted.services.generator.models;

public class ElementField {

    private String name;
    private boolean listType;
    private String identifier;
    private String value;

    public ElementField(){
    }

    private ElementField(String name, String identifier, String value, boolean listType){
        this.name = name;
        this.identifier = identifier;
        this.value = value;
        this.listType = listType;
    }

    public boolean isListType() {
        return listType;
    }

    public String getName() {
        return name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getValue() {
        return value;
    }

    public static class Builder {
        private String name;
        private String identifier;
        private String value;
        private boolean listType;

        private Builder() {
        }

        public static Builder elementField() {
            return new Builder();
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withListType(boolean listType) {
            this.listType = listType;
            return this;
        }

        public Builder withIdentifier(String identifier) {
            this.identifier = identifier;
            return this;
        }

        public Builder withValue(String value) {
            this.value = value;
            return this;
        }

        public ElementField build() {
            return new ElementField(name, identifier, value, listType);
        }
    }
}

package com.tcup.ted.services.generator.models;

public class ElementField {
    private String name;
    private String identifier;
    private String value;

    public ElementField(){

    }

    private ElementField(String name, String identifier, String value){
        this.name = name;
        this.identifier = identifier;
        this.value = value;
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

        private Builder() {
        }

        public static Builder elementField() {
            return new Builder();
        }

        public Builder withName(String name) {
            this.name = name;
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
            return new ElementField(name, identifier, value);
        }
    }
}

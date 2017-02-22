package com.tcup.ted.identifiers;

/**
 * Created by Yuvaraj on 21/02/2017.
 */
public class ElementField {
    public String name;
    public String identifier;
    public String value;

    public ElementField(String name, String identifier, String value){
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

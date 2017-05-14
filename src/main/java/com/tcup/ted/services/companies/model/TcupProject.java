package com.tcup.ted.services.companies.model;

public class TcupProject {

    private String projectName;
    private String description;

    public TcupProject(String projectName, String description) {
        this.projectName = projectName;
        this.description = description;
    }

    public static class Builder {
        private String projectName;
        private String description;

        private Builder() {
        }

        public static Builder tcupProject() {
            return new Builder();
        }

        public Builder withProjectName(String projectName) {
            this.projectName = projectName;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public TcupProject build() {
            return new TcupProject(this.projectName, this.description);
        }
    }
}

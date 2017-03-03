package com.tcup.ted.git.models;

/**
 * Created by Yuvaraj on 01/03/2017.
 */
public class Project {

    private String name;

    private boolean autoInit;

    private boolean Private;

    private String gitignoreTemplate;

    private Project(String name, boolean autoInit, boolean aPrivate, String gitignoreTemplate) {
        this.name = name;
        this.autoInit = autoInit;
        Private = aPrivate;
        this.gitignoreTemplate = gitignoreTemplate;
    }

    public static class Builder {
        private String name;
        private boolean autoInit;
        private boolean Private;
        private String gitignoreTemplate;

        private Builder() {
        }

        public static Builder project() {
            return new Builder();
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withAutoInit(boolean autoInit) {
            this.autoInit = autoInit;
            return this;
        }

        public Builder withPrivate(boolean Private) {
            this.Private = Private;
            return this;
        }

        public Builder withGitignoreTemplate(String gitignoreTemplate) {
            this.gitignoreTemplate = gitignoreTemplate;
            return this;
        }

        public Project build() {
            return new Project(name, autoInit, Private, gitignoreTemplate);
        }
    }
}

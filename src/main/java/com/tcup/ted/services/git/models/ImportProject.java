package com.tcup.ted.services.git.models;

/**
 * Created by Yuvaraj on 01/04/2017.
 */
public class ImportProject {

    private String vcs;

    private String vcs_url;

    private String vcs_username;

    private String vcs_password;

    public ImportProject(String vcs, String vcs_url, String vcs_username, String vcs_password) {
        this.vcs = vcs;
        this.vcs_url = vcs_url;
        this.vcs_username = vcs_username;
        this.vcs_password = vcs_password;
    }

    public static class Builder {
        private String vcs;
        private String vcs_url;
        private String vcs_username;
        private String vcs_password;

        private Builder() {
        }

        public static Builder importProject() {
            return new Builder();
        }

        public Builder withVcs(String vcs) {
            this.vcs = vcs;
            return this;
        }

        public Builder withVcs_url(String vcs_url) {
            this.vcs_url = vcs_url;
            return this;
        }

        public Builder withVcs_username(String vcs_username) {
            this.vcs_username = vcs_username;
            return this;
        }

        public Builder withVcs_password(String vcs_password) {
            this.vcs_password = vcs_password;
            return this;
        }

        public ImportProject build() {
            return new ImportProject(vcs, vcs_url, vcs_username, vcs_password);
        }
    }
}

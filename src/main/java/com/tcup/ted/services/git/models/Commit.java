package com.tcup.ted.services.git.models;

/**
 * Created by Yuvaraj on 31/03/2017.
 */
public class Commit {
    private String message;
    private String content;
    //The blob SHA of the file being replaced
    private String sha;

    public Commit(String message, String content, String sha) {
        this.message = message;
        this.content = content;
        this.sha = sha;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public static class Builder {
        private String message;
        private String content;
        private String sha;

        private Builder() {
        }

        public static Builder commit() {
            return new Builder();
        }

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder withContent(String content) {
            this.content = content;
            return this;
        }

        public Builder withSHA(String sha) {
            this.sha = sha;
            return this;
        }

        public Commit build() {
            return new Commit(message, content, sha);
        }
    }
}

package com.tcup.ted.services.companies.model;

import java.util.ArrayList;
import java.util.List;

public class TcupCompany {

    private String name;
    private String otherInformation;
    private List<TcupProject> projectList;

    public String getName() {
        return name;
    }

    public String getOtherInformation() {
        return otherInformation;
    }

    public List<TcupProject> getProjectList() {
        return projectList;
    }

    public TcupCompany() {

    }

    public TcupCompany(String name, String otherInformation, List<TcupProject> projects) {
        this.name = name;
        this.otherInformation = otherInformation;
        this.projectList = projects;
    }

    public static class Builder {
        private String name;
        private String otherInformation;
        private List<TcupProject> projectList;

        private Builder() {
        }

        public static Builder tcupCompany() {
            return new Builder();
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withOtherInformation(String otherInformation) {
            this.otherInformation = otherInformation;
            return this;
        }

        public Builder withProjectList(List<TcupProject> projectList) {
            this.projectList = projectList;
            return this;
        }

        public Builder withProject(TcupProject tcupProject) {
            if(this.projectList == null) {
                this.projectList = new ArrayList<>();
            }
            this.projectList.add(tcupProject);
            return this;
        }

        public TcupCompany build() {
            return new TcupCompany(this.name, this.otherInformation, this.projectList);
        }
    }
}

package com.tcup.ted.git.impl;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tcup.ted.generator.impl.SeleniumPOGenerator;
import com.tcup.ted.git.IGitService;
import com.tcup.ted.git.models.Project;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.client.RequestException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.comparator.BooleanComparator;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.Future;

import static java.lang.String.format;

@Service
public class GitHubService implements IGitService{

    @Value("${github.api.url}")
    private String url;

    @Value("${github.access.token}")
    private String token;

    @Autowired
    private GitHubClient gitHubClient;

    private static Logger log4JLogger = LoggerFactory.getLogger(SeleniumPOGenerator.class);

    @Override
    @Async
    public ListenableFuture<Repository> createProject(String projectName) throws Exception {
        Repository object = null;
        try {
            gitHubClient.setOAuth2Token(token);
            Project project = Project.Builder.project()
                    .withAutoInit(true)
                    .withPrivate(false)
                    .withName(projectName)
                    .withGitignoreTemplate("nanoc")
                    .build();
            object = gitHubClient.post("/user/repos", project, Object.class);
            log4JLogger.info("Successfully Created A Repository for project:\n{}", getGson().toJson(object));
        }
        catch (RequestException ex){
            //Do Nothing
        }
        return new AsyncResult<>(object);
    }

    @Override
    @Async
    public ListenableFuture<Boolean> deleteProject(String projectName) throws Exception {
        try {
            gitHubClient.setOAuth2Token(token);
            gitHubClient.delete(format("/repos/tcupted/%s", projectName));
            log4JLogger.info("Deleting the Project {}", projectName);
            return new AsyncResult<>(Boolean.TRUE);
        }
        catch (Exception ex){
            log4JLogger.error("Failed to delete the Project due to {}", ex.getMessage());
            return new AsyncResult<>(Boolean.FALSE);
        }
    }

    private Gson getGson(){
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }
}

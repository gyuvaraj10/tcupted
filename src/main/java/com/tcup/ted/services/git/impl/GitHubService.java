package com.tcup.ted.services.git.impl;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.tcup.ted.services.generator.impl.SeleniumPOGenerator;
import com.tcup.ted.services.git.IGitService;
import com.tcup.ted.services.git.models.Commit;
import com.tcup.ted.services.git.models.Fork;
import com.tcup.ted.services.git.models.ImportProject;
import com.tcup.ted.services.git.models.Project;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.client.GitHubRequest;
import org.eclipse.egit.github.core.client.GitHubResponse;
import org.eclipse.egit.github.core.client.RequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.format;

@Service
public class GitHubService implements IGitService{

    @Value("${github.api.url}")
    private String url;

    @Value("${github.access.token}")
    private String token;

    @Value("${github.repo.owner}")
    private String owner;

    @Value("${github.repo.projectName}")
    private String repoName;

    @Value("${github.access.password}")
    private String password;

    @Value("${vcs.import.name}")
    private String vcsName;

    @Value("${git.pages.path}")
    private String pagePath;

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
                    .withAutoInit(false)
                    .withPrivate(false)
                    .withName(projectName)
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
    public ListenableFuture<Boolean> importProject(String projectName) throws Exception {
        Repository object = null;
        try {
            gitHubClient.setOAuth2Token(token);
            ImportProject project = ImportProject.Builder.importProject()
                    .withVcs(vcsName)
                    .withVcs_password(password)
                    .withVcs_username(owner)
                    .withVcs_url(format("https://github.com/%s/%s",owner, repoName))
                    .build();
            gitHubClient.setHeaderAccept("application/vnd.github.barred-rock-preview");
            object = gitHubClient.put(format("/repos/%s/%s/import", owner,projectName), project, Repository.class);
            log4JLogger.info("Successfully Created A Repository for project:\n{}", getGson().toJson(object));
            return new AsyncResult<>(Boolean.TRUE);
        }
        catch (RequestException ex){
            //Do Nothing
            return new AsyncResult<>(Boolean.FALSE);
        }
    }

    @Override
    @Async
    public ListenableFuture<Boolean> deleteProject(String projectName) throws Exception {
        try {
            gitHubClient.setOAuth2Token(token);
            gitHubClient.delete(format("/repos/%s/%s",owner, projectName));
            log4JLogger.info("Deleting the Project {}", projectName);
            return new AsyncResult<>(Boolean.TRUE);
        }
        catch (Exception ex){
            log4JLogger.error("Failed to delete the Project due to {}", ex.getMessage());
            //return this error as an error response
            return new AsyncResult<>(Boolean.FALSE);
        }
    }

    @Override
    @Async
    public ListenableFuture<Boolean> createAFile(String projectName, String filePath, String content) throws Exception {
        try{
            gitHubClient.setOAuth2Token(token);
            Commit commit = Commit.Builder.commit()
                    .withContent(content)
                    .withMessage("Creating the page class")
                    .build();
            filePath = projectName+"/"+pagePath+filePath;
            gitHubClient.put(format("/repos/%s/%s/contents/%s", owner, projectName, filePath), commit, Object.class);
            log4JLogger.info("Created A Page Class");
            return new AsyncResult<>(Boolean.TRUE);
        }
        catch (Exception ex) {
            log4JLogger.error("Failed to create a file due to {}", ex.getMessage());
            //return this error as an error response
            return new AsyncResult<>(Boolean.FALSE);
        }
    }

    @Override
    public ListenableFuture<Boolean> updateAFile(String projectName, String filePath, String content) throws Exception {
        try{
            gitHubClient.setOAuth2Token(token);
            String sha = getFileSHA(projectName, filePath);
            Commit commit = Commit.Builder.commit()
                    .withContent(content)
                    .withMessage("Updating the page class")
                    .withSHA(sha)
                    .build();
            gitHubClient.put(format("/repos/%s/%s/contents/%s", owner, projectName, filePath), commit, Object.class);
            log4JLogger.info("Created A Page Class");
            return new AsyncResult<>(Boolean.TRUE);
        }
        catch (Exception ex) {
            log4JLogger.error("Failed to create a file due to {}", ex.getMessage());
            //return this error as an error response
            return new AsyncResult<>(Boolean.FALSE);
        }
    }

    @Override
    public ListenableFuture<Boolean> getFile(String projectName, String filePath) throws Exception {
        try{
            gitHubClient.setOAuth2Token(token);

            log4JLogger.info("Created A Page Class");
            return new AsyncResult<>(Boolean.TRUE);
        }
        catch (Exception ex) {
            log4JLogger.error("Failed to create a file due to {}", ex.getMessage());
            //return this error as an error response
            return new AsyncResult<>(Boolean.FALSE);
        }
    }

    public String getFileSHA(String projectName, String filePath) throws Exception {
        try{
            gitHubClient.setOAuth2Token(token);
            GitHubRequest request = new GitHubRequest();
            request.setUri(format("/repos/%s/%s/contents/%s", owner, projectName, filePath));
            GitHubResponse response = gitHubClient.get(request);
            Matcher matcher = Pattern.compile("\"sha\": \"\\w+\"").matcher(response.getBody().toString());
            matcher.find();
            String fileSha = "";
            while(matcher.matches()) {
                fileSha = matcher.group();
                break;
            }
            String sha = fileSha.split(":")[1].trim().replace("\"","");
            log4JLogger.info(sha);
            return sha;
        }
        catch (Exception ex) {
            log4JLogger.error("Failed to create a file due to {}", ex.getMessage());
            //return this error as an error response
            return "";
        }
    }

    private Gson getGson(){
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }
}

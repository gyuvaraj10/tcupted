package com.tcup.ted.services.git.impl;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tcup.ted.services.generator.impl.SeleniumPOGenerator;
import com.tcup.ted.services.git.IGitService;
import com.tcup.ted.services.git.models.Commit;
import com.tcup.ted.services.git.models.ImportProject;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.client.GitHubRequest;
import org.eclipse.egit.github.core.client.GitHubResponse;
import org.eclipse.egit.github.core.client.RequestException;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.format;

@Service
public class GitHubService implements IGitService{

    @Value("${github.access.token}")
    private String token;

    @Value("${github.api.url}")
    private String url;

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

    @Autowired
    private RepositoryService service;

    private static Logger log4JLogger = LoggerFactory.getLogger(SeleniumPOGenerator.class);

    @Override
    public Repository createProject(String projectName) throws Exception {
        Repository object = null;
        try {
            Repository repository = new Repository();
            repository.setName(projectName);
            object = service.createRepository(repository);
            log4JLogger.info("Successfully Created A Repository for project:\n{}", getGson().toJson(object));
        }
        catch (RequestException ex){
            log4JLogger.error(ex.getMessage());
        }
        return object;
    }

    @Override
    public Boolean importProject(String projectName) throws Exception {
        Repository object = null;
        try {
            ImportProject project = ImportProject.Builder.importProject()
                    .withVcs(vcsName)
                    .withVcs_password(password)
                    .withVcs_username(owner)
                    .withVcs_url(format ("https://github.com/%s/%s",owner, repoName))
                    .build();
            object = gitHubClient.put(format("/repos/%s/%s/import", owner,projectName), project, Repository.class);
            log4JLogger.info("Successfully Created A Repository for project:\n{}", getGson().toJson(object));
            return Boolean.TRUE;
        }
        catch (RequestException ex){
            //Do Nothing
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean deleteProject(String projectName) throws Exception {
        try {
            gitHubClient.delete(format("/repos/%s/%s",owner, projectName));
            log4JLogger.info("Deleting the Project {}", projectName);
            return Boolean.TRUE;
        }
        catch (Exception ex){
            log4JLogger.error("Failed to delete the Project due to {}", ex.getMessage());
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean createAFile(String projectName, String filePath, String content) throws Exception {
        try{
            Commit commit = Commit.Builder.commit()
                    .withContent(content)
                    .withMessage("Creating the page class")
                    .build();
            filePath = pagePath+"/"+filePath+".java";
            gitHubClient.put(format("/repos/%s/%s/contents/%s", owner, projectName, filePath), commit, Object.class);
            log4JLogger.info("Created A Page Class");
            return Boolean.TRUE;
        }
        catch (Exception ex) {
            log4JLogger.error("Failed to create a file due to {}", ex.getMessage());
            //return this error as an error response
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean updateAFile(String projectName, String filePath, String content) throws Exception {
        try{
            String sha = getFileSHA(projectName, filePath);
            Commit commit = Commit.Builder.commit()
                    .withContent(content)
                    .withMessage("Updating the page class")
                    .withSHA(sha)
                    .build();
            gitHubClient.put(format("/repos/%s/%s/contents/%s", owner, projectName, filePath), commit, Object.class);
            log4JLogger.info("Created A Page Class");
            return Boolean.TRUE;
        }
        catch (Exception ex) {
            log4JLogger.error("Failed to create a file due to {}", ex.getMessage());
            //return this error as an error response
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean getFile(String projectName, String filePath) throws Exception {
        try{
            log4JLogger.info("Created A Page Class");
            return Boolean.TRUE;
        }
        catch (Exception ex) {
            log4JLogger.error("Failed to create a file due to {}", ex.getMessage());
            //return this error as an error response
            return Boolean.FALSE;
        }
    }
//
//    @Override
//    @Async
//    public ListenableFuture<Repository> createProject(String projectName) throws Exception {
//        Repository object = null;
//        try {
//            Repository repository = new Repository();
//            repository.setName(projectName);
//            object = service.createRepository(repository);
//            log4JLogger.info("Successfully Created A Repository for project:\n{}", getGson().toJson(object));
//        }
//        catch (RequestException ex){
//            log4JLogger.error(ex.getMessage());
//        }
//        return new AsyncResult<>(object);
//    }
//
//    @Override
//    @Async
//    public ListenableFuture<Boolean> importProject(String projectName) throws Exception {
//        Repository object = null;
//        try {
//            ImportProject project = ImportProject.Builder.importProject()
//                    .withVcs(vcsName)
//                    .withVcs_password(password)
//                    .withVcs_username(owner)
//                    .withVcs_url(format ("https://github.com/%s/%s",owner, repoName))
//                    .build();
//            object = gitHubClient.put(format("/repos/%s/%s/import", owner,projectName), project, Repository.class);
//            log4JLogger.info("Successfully Created A Repository for project:\n{}", getGson().toJson(object));
//            return new AsyncResult<>(Boolean.TRUE);
//        }
//        catch (RequestException ex){
//            //Do Nothing
//            return new AsyncResult<>(Boolean.FALSE);
//        }
//    }
//
//    @Override
//    @Async
//    public ListenableFuture<Boolean> deleteProject(String projectName) throws Exception {
//        try {
//            gitHubClient.delete(format("/repos/%s/%s",owner, projectName));
//            log4JLogger.info("Deleting the Project {}", projectName);
//            return new AsyncResult<>(Boolean.TRUE);
//        }
//        catch (Exception ex){
//            log4JLogger.error("Failed to delete the Project due to {}", ex.getMessage());
//            return new AsyncResult<>(Boolean.FALSE);
//        }
//    }
//
//    @Override
//    @Async
//    public ListenableFuture<Boolean> createAFile(String projectName, String filePath, String content) throws Exception {
//        try{
//            Commit commit = Commit.Builder.commit()
//                    .withContent(content)
//                    .withMessage("Creating the page class")
//                    .build();
//            filePath = pagePath+"/"+filePath+".java";
//            gitHubClient.put(format("/repos/%s/%s/contents/%s", owner, projectName, filePath), commit, Object.class);
//            log4JLogger.info("Created A Page Class");
//            return new AsyncResult<>(Boolean.TRUE);
//        }
//        catch (Exception ex) {
//            log4JLogger.error("Failed to create a file due to {}", ex.getMessage());
//            //return this error as an error response
//            return new AsyncResult<>(Boolean.FALSE);
//        }
//    }
//
//    @Override
//    @Async
//    public ListenableFuture<Boolean> updateAFile(String projectName, String filePath, String content) throws Exception {
//        try{
//            String sha = getFileSHA(projectName, filePath);
//            Commit commit = Commit.Builder.commit()
//                    .withContent(content)
//                    .withMessage("Updating the page class")
//                    .withSHA(sha)
//                    .build();
//            gitHubClient.put(format("/repos/%s/%s/contents/%s", owner, projectName, filePath), commit, Object.class);
//            log4JLogger.info("Created A Page Class");
//            return new AsyncResult<>(Boolean.TRUE);
//        }
//        catch (Exception ex) {
//            log4JLogger.error("Failed to create a file due to {}", ex.getMessage());
//            //return this error as an error response
//            return new AsyncResult<>(Boolean.FALSE);
//        }
//    }
//
//    @Override
//    @Async
//    public ListenableFuture<Boolean> getFile(String projectName, String filePath) throws Exception {
//        try{
//            log4JLogger.info("Created A Page Class");
//            return new AsyncResult<>(Boolean.TRUE);
//        }
//        catch (Exception ex) {
//            log4JLogger.error("Failed to create a file due to {}", ex.getMessage());
//            //return this error as an error response
//            return new AsyncResult<>(Boolean.FALSE);
//        }
//    }

    public String getFileSHA(String projectName, String filePath) throws Exception {
        try{
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

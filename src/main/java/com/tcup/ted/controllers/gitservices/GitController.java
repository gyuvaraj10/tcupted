package com.tcup.ted.controllers.gitservices;

import com.google.gson.Gson;
import com.tcup.ted.services.generator.models.ElementField;
import com.tcup.ted.services.git.IGitService;
import org.apache.commons.io.FileUtils;
import org.eclipse.egit.github.core.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/repo")
public class GitController {

    @Autowired
    private IGitService gitHubService;

    @Value("${github.repo.projectName")
    private String projectName;

    @RequestMapping(value= "/create", method = RequestMethod.POST)
    public DeferredResult<ResponseEntity<String>> createRepository(@RequestParam String name) throws Exception{
        final DeferredResult<ResponseEntity<String>> deferredResult = new DeferredResult<>(5000l);
        deferredResult.onTimeout(new Runnable() {
            @Override
            public void run() {
                deferredResult.setErrorResult(ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT)
                        .body("Request timeout occurred."));

            }
        });
        ListenableFuture<Repository> longRunnableFuture = gitHubService.createProject(name);
        longRunnableFuture.addCallback(new ListenableFutureCallback<Repository>() {
            @Override
            public void onFailure(Throwable throwable) {
                deferredResult.setErrorResult(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(throwable));
            }

            @Override
            public void onSuccess(Repository o) {
                if(o != null) {
                    deferredResult.setResult(ResponseEntity.ok(new Gson().toJson(o)));
                } else{
                  deferredResult.setErrorResult(ResponseEntity.status(HttpStatus.BAD_REQUEST));
                }
            }
        });
        return deferredResult;
    }

    @RequestMapping(value= "/import", method = RequestMethod.POST)
    public DeferredResult<ResponseEntity<Boolean>> importRepository(@RequestParam String name) throws Exception{
        final DeferredResult<ResponseEntity<Boolean>> deferredResult = new DeferredResult<>(5000l);
        deferredResult.onTimeout(() ->
                deferredResult.setErrorResult(ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT)
                        .body("Request Timed out")));
        ListenableFuture<Boolean> longRunnableFuture =  gitHubService.importProject(name);
        longRunnableFuture.addCallback(new ListenableFutureCallback<Boolean>() {
            @Override
            public void onFailure(Throwable throwable) {
                deferredResult.setErrorResult(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(throwable));
            }
            @Override
            public void onSuccess(Boolean o) {
                if(o != null) {
                    deferredResult.setResult(ResponseEntity.ok(o));
                } else{
                    deferredResult.setErrorResult(ResponseEntity.status(HttpStatus.BAD_REQUEST));
                }
            }
        });
        return deferredResult;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public DeferredResult<ResponseEntity<Boolean>> deleteRepository(@RequestParam String name) throws Exception {
        final DeferredResult<ResponseEntity<Boolean>> deferredResult = new DeferredResult<>(5000l);
        deferredResult.onTimeout(() ->
                deferredResult.setErrorResult(ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT)
                        .body("Request Timed out")));

        ListenableFuture<Boolean> longRunnableFuture = gitHubService.deleteProject(name);
        longRunnableFuture.addCallback(new ListenableFutureCallback<Boolean>() {
            @Override
            public void onFailure(Throwable throwable) {
                deferredResult.setErrorResult(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(throwable));
            }

            @Override
            public void onSuccess(Boolean o) {
                if(o != null) {
                    deferredResult.setResult(ResponseEntity.ok(o));
                } else{
                    deferredResult.setErrorResult(ResponseEntity.status(HttpStatus.BAD_REQUEST));
                }
            }
        });
        return deferredResult;
    }

    @RequestMapping(value = "/create/page", method = RequestMethod.POST)
    public DeferredResult<ResponseEntity<Boolean>> createAPage(@RequestParam String projectName,@RequestParam String name,
                                                               HttpEntity<List<ElementField>> entity) throws Exception {
        final DeferredResult<ResponseEntity<Boolean>> deferredResult = new DeferredResult<>(5000l);
        String content = entity.getBody().toString();
        String gitContent = Base64Utils.encodeToUrlSafeString(content.getBytes());
        deferredResult.onTimeout(() ->
                deferredResult.setErrorResult(ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT)
                        .body("Request Timed out")));
        ListenableFuture<Boolean> longRunnableFuture = gitHubService.createAFile(projectName, name, gitContent);
        longRunnableFuture.addCallback(new ListenableFutureCallback<Boolean>() {
            @Override
            public void onFailure(Throwable throwable) {
                deferredResult.setErrorResult(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(throwable));
            }
            @Override
            public void onSuccess(Boolean o) {
                if(o != null) {
                    deferredResult.setResult(ResponseEntity.ok(o));
                } else{
                    deferredResult.setErrorResult(ResponseEntity.status(HttpStatus.BAD_REQUEST));
                }
            }
        });
        return deferredResult;
    }
}

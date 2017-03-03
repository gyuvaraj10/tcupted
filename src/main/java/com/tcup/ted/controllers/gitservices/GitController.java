package com.tcup.ted.controllers.gitservices;

import com.google.gson.Gson;
import com.tcup.ted.git.IGitService;
import org.eclipse.egit.github.core.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
@RequestMapping("/repo")
public class GitController {

    @Autowired
    private IGitService gitHubService;

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
}

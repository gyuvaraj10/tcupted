package com.tcup.ted.git;

import org.eclipse.egit.github.core.Repository;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * Created by Yuvaraj on 01/03/2017.
 */
public interface IGitService {

    ListenableFuture<Repository> createProject(String projectName) throws Exception;

    ListenableFuture<Boolean> deleteProject(String projectName) throws Exception;
}

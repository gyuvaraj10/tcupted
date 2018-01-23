package com.tcup.ted.services.git;

import org.eclipse.egit.github.core.Repository;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * Created by Yuvaraj on 01/03/2017.
 */
public interface IGitService {

//    ListenableFuture<Repository> createProject(String projectName) throws Exception;
//
//    ListenableFuture<Boolean> importProject(String projectName) throws Exception;
//
//    ListenableFuture<Boolean> deleteProject(String projectName) throws Exception;
//
//    ListenableFuture<Boolean> createAFile(String projectName, String filePath, String content) throws Exception;
//
//    ListenableFuture<Boolean> updateAFile(String projectName, String filePath, String content) throws Exception;
//
//    ListenableFuture<Boolean> getFile(String projectName, String filePath) throws Exception;

    Repository createProject(String projectName) throws Exception;

    Boolean importProject(String projectName) throws Exception;

    Boolean deleteProject(String projectName) throws Exception;

    Boolean createAFile(String projectName, String filePath, String content) throws Exception;

    Boolean updateAFile(String projectName, String filePath, String content) throws Exception;

    Boolean getFile(String projectName, String filePath) throws Exception;
}

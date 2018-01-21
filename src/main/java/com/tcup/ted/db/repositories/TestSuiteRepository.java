package com.tcup.ted.db.repositories;

import com.tcup.ted.db.entities.TestSuite;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by Yuvaraj on 21/01/2018.
 */
public interface TestSuiteRepository extends MongoRepository<TestSuite, Long> {

    List<TestSuite> findByProjectName(String project);
}

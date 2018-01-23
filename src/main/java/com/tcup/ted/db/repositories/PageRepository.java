package com.tcup.ted.db.repositories;

import com.tcup.ted.db.entities.Pages;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PageRepository extends MongoRepository<Pages, Long> {
    List<Pages> findAllPagesByPageNameAndProjectName(String pageName, String projectName);
}

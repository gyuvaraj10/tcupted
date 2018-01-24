package com.tcup.ted.db.repositories;

import com.tcup.ted.db.entities.Element;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by Yuvaraj on 23/01/2018.
 */
public interface ElementRepository extends MongoRepository<Element, Long> {

    List<Element> findByProjectNameAndPageName(String projectName, String pageName);

    void deleteByNameAndProjectNameAndPageName(String name, String projectName, String pageName);
}

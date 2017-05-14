package com.tcup.ted.services.companies.repositories;

import com.tcup.ted.services.companies.model.TcupProject;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Yuvaraj on 13/05/2017.
 */
public interface TcupProjectRepository extends MongoRepository<TcupProject, Long> {
}

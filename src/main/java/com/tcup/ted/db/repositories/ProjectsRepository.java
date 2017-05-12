package com.tcup.ted.db.repositories;

import com.tcup.ted.db.entities.Projects;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectsRepository extends MongoRepository<Projects, Long> {
}

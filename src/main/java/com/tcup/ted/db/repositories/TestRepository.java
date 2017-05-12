package com.tcup.ted.db.repositories;

import com.tcup.ted.db.entities.Tests;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TestRepository extends MongoRepository<Tests, Long> {
}

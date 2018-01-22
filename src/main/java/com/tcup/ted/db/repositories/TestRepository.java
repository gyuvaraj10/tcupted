package com.tcup.ted.db.repositories;

import com.tcup.ted.db.entities.Test;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TestRepository extends MongoRepository<Test, Long> {
}

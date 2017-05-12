package com.tcup.ted.db.repositories;

import com.tcup.ted.db.entities.Pages;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PageRepository extends MongoRepository<Pages, Long> {
}

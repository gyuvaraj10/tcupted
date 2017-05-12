package com.tcup.ted.db.repositories;

import com.tcup.ted.db.entities.Actions;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ActionsRepository extends MongoRepository<Actions, String> {
    List<Actions> findByActionName(String Name);
}

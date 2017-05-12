package com.tcup.ted.db.repositories;

import com.tcup.ted.db.entities.Companies;
import org.springframework.data.mongodb.repository.MongoRepository;
/**
 * Created by Yuvaraj on 16/03/2017.
 */

public interface CompaniesRepository extends MongoRepository<Companies, Long> {

}

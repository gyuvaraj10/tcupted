package com.tcup.ted.controllers.suites;

import com.tcup.ted.db.entities.Test;
import com.tcup.ted.db.repositories.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Yuvaraj on 21/01/2018.
 */
@RestController
@RequestMapping(path = "/test")
public class TestController {

    @Autowired
    private TestRepository testRepository;

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public ResponseEntity<Test> createTest(HttpEntity<Test> testHttpEntity){
        return  new ResponseEntity<>(testRepository.insert(testHttpEntity.getBody()), HttpStatus.ACCEPTED);
    }

    @RequestMapping(path = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<Test>> getAllTests(){
        return  new ResponseEntity<List<Test>>(testRepository.findAll(), HttpStatus.ACCEPTED);
    }
}

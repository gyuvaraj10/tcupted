package com.tcup.ted.controllers.defaults;

import com.tcup.ted.db.entities.Actions;
import com.tcup.ted.db.repositories.ActionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Yuvaraj on 12/05/2017.
 */
@RestController
@RequestMapping("/defaults")
public class ActionDefaults {


    @Autowired
    ActionsRepository actionsRepository;

    @RequestMapping(path = "/uiactions", method = RequestMethod.GET)
    public ResponseEntity getUIActions(){
        return new ResponseEntity(actionsRepository.findAll(), HttpStatus.ACCEPTED);
    }
}

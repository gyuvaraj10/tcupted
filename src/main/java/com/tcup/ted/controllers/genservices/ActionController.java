package com.tcup.ted.controllers.genservices;

import com.tcup.ted.services.generator.models.PageAction;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/actions")
public class ActionController {

    @PostMapping("/{projectName}/{pageName}")
    public void createPageAction(@PathVariable String projectName, @PathVariable String pageName,
                                 HttpEntity<List<PageAction>> entity) {
        try {
            //TODO implement code to put the page actions
        } catch (Exception ex) {

        }
    }

    @PutMapping("/update/{page}")
    public void updatePageAction(@PathVariable String page, HttpEntity<List<PageAction>> entity) {
        try {
            //TODO implement code to put the page actions
        } catch (Exception ex) {

        }
    }


    @DeleteMapping("/delete/{page}")
    public void deletePageAction(@PathVariable String page, HttpEntity<List<PageAction>> entity) {
        try {
            //TODO implement code to put the page actions
        } catch (Exception ex) {

        }
    }
}

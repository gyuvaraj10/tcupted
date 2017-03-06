package com.tcup.ted.controllers.genservices;

import com.tcup.ted.services.generator.PageObjectProvider;
import com.tcup.ted.services.generator.models.ElementField;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/page")
public class PageController {

    @Autowired
    private PageObjectProvider provider;

    @Autowired
    private Logger logger;

    @PostMapping("/create/{name}")
    public void createPage(@PathVariable String name, HttpEntity<List<ElementField>> entity){
        try {
            String content = provider.getObject().generatePageObject(name, entity.getBody());
            //TODO implement the code to persist the new page information into database
            FileUtils.writeStringToFile(new File("/Users/Yuvaraj/dev/mytoold/tcupted/src/main/resources/" + name + ".java"),
                    content, "UTF-8");
        } catch (Exception ex){
            logger.error("There is a problem creating the page {}", ex.getMessage());
        }
    }

    @PutMapping("/update/{name}")
    public void updatePage(@PathVariable String name, HttpEntity<List<ElementField>> entity) {
        try {
            String content = provider.getObject().generatePageObject(name, entity.getBody());
            //TODO implement the code to update the page information into database
        } catch (Exception ex){
            logger.error("There is a problem updating the page {}", ex.getMessage());
        }
    }


    @DeleteMapping("/delete/{name}")
    public void deletePage(@PathVariable String name) {
        try {
            //TODO implement code to delete the page

        } catch (Exception ex) {
            logger.error("There is a problem deleting the page {}. Exception {}", name, ex.getMessage());
        }
    }
}

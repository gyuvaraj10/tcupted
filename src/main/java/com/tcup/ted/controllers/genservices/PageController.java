package com.tcup.ted.controllers.genservices;

import com.tcup.ted.db.entities.Pages;
import com.tcup.ted.db.repositories.PageRepository;
import com.tcup.ted.services.generator.PageObjectProvider;
import com.tcup.ted.services.generator.models.ElementField;
import com.tcup.ted.services.git.IGitService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/page")
public class PageController {

    @Autowired
    private PageObjectProvider provider;

    @Autowired
    private PageRepository pageRepository;

    @Autowired
    private IGitService gitHubService;

    private static Logger logger = LoggerFactory.getLogger(PageController.class);

    @GetMapping("/{project}/{name}/all")
    public List<Pages> getPageElements(@PathVariable String project, @PathVariable String pageName) {
        return pageRepository.findAllPagesByPageNameAndProjectName(pageName, project);
    }

    @PostMapping("/create/{project}/{name}")
    public void createPage(@PathVariable String project, @PathVariable String pageName, HttpEntity<List<ElementField>> entity){
        try {
            String content = provider.getObject().generatePageObject(pageName, entity.getBody());
            String gitContent = Base64Utils.encodeToUrlSafeString(content.getBytes());
            gitHubService.createAFile(project, pageName, gitContent);
            ElementField field = entity.getBody().get(0);
            Pages pages = new Pages(pageName, field.getIdentifier(), field.getValue(), field.getName(), project);
            pageRepository.save(pages);
            logger.info("Successfully saved the Page into the Database");
        } catch (Exception ex){
            logger.error("There is a problem creating the page {}", ex.getMessage());
        }
    }
//
//    @PutMapping("/update/{name}")
//    public void updatePage(@PathVariable String name, HttpEntity<List<ElementField>> entity) {
//        try {
//            String content = provider.getObject().generatePageObject(name, entity.getBody());
//            File file = new File("/Users/Yuvaraj/dev/mytoold/tcupted/src/main/resources/" + name + ".java");
//            FileUtils.writeStringToFile(file, content, "UTF-8");
//            String gitContent = Base64Utils.encodeToUrlSafeString(FileUtils.readFileToString(file, "UTF-8").getBytes());
//            gitHubService.updateAFile("yuvaraj", name+".java", gitContent);
//            ElementField field = entity.getBody().get(0);
//            Pages pages = new Pages(name, field.getIdentifier(), field.getValue(), field.getName());
//            pageRepository.save(pages);
//            logger.info("Successfully saved the Page into the Database");
//        } catch (Exception ex){
//            logger.error("There is a problem updating the page {}", ex.getMessage());
//        }
//    }


    @DeleteMapping("/delete/{name}")
    public void deletePage(@PathVariable String name) {
        try {
            //TODO implement code to delete the page

        } catch (Exception ex) {
            logger.error("There is a problem deleting the page {}. Exception {}", name, ex.getMessage());
        }
    }
}

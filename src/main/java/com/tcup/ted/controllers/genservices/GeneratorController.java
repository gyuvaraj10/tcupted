package com.tcup.ted.controllers.genservices;

import com.tcup.ted.generator.PageObjectProvider;
import com.tcup.ted.generator.identifiers.ElementField;
import com.tcup.ted.git.impl.GitHubService;
import com.tcup.ted.utilities.TemplateUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;

@RestController
public class GeneratorController {

    @Autowired
    private TemplateUtil templateUtil;

    @Autowired
    private PageObjectProvider provider;

    @PostMapping("/page/elements/{name}")
    public void upsertPageElements(@ModelAttribute String pageName, HttpEntity<List<ElementField>> entity){
        try {
            List<ElementField> elementFields = entity.getBody();
            templateUtil.load("/templates/selenium-page.vm");
            templateUtil.put("pageclass", pageName);
            templateUtil.put("fields", elementFields);
            templateUtil.fillTemplate();
            String content = templateUtil.getFilledTemplate();
            FileUtils.writeStringToFile(new File("/Users/Yuvaraj/dev/mytoold/tcupted/src/main/resources/" + pageName + ".java"),
                    content, "UTF-8");
        }
        catch (Exception ex){

        }
    }
}

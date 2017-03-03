package com.tcup.ted.controllers.genservices;

import com.google.gson.Gson;
import com.tcup.ted.generator.PageObjectProvider;
import com.tcup.ted.generator.identifiers.ElementField;
import com.tcup.ted.git.impl.GitHubService;
import com.tcup.ted.utilities.TemplateUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/page")
public class GeneratorController {

    @Autowired
    private PageObjectProvider provider;

    @PostMapping("/create/{name}")
    public void createPage(@PathVariable String name, HttpEntity<List<ElementField>> entity){
        try {
            String content = provider.getObject().generatePageObject(name, entity.getBody());
            FileUtils.writeStringToFile(new File("/Users/Yuvaraj/dev/mytoold/tcupted/src/main/resources/" + name + ".java"),
                    content, "UTF-8");
        }
        catch (Exception ex){

        }
    }
}

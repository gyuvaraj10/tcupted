package com.tcup.ted.generator;

import com.tcup.ted.identifiers.ElementField;
import com.tcup.ted.utilities.TemplateUtil;
import org.apache.commons.io.FileUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;
import org.springframework.util.FileSystemUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yuvaraj on 21/02/2017.
 */
public class GeneratePageObjects {

    public GeneratePageObjects(){

    }

    public void generatePageObject(String pageName) throws Exception{
        TemplateUtil util = new TemplateUtil();
        util.load("/templates/selenium-page.vm");
        util.put("pageclass", pageName);
        ElementField elementField = ElementField.Builder.elementField()
                .withIdentifier("name")
                .withName("username")
                .withValue("user")
                .build();
        ElementField elementField1 = ElementField.Builder.elementField()
                .withIdentifier("name1")
                .withName("username1")
                .withValue("user1")
                .build();
        List<ElementField> fields = new ArrayList<ElementField>();
        fields.add(elementField);
        fields.add(elementField1);
        util.put("fields", fields);
        util.fillTemplate();
        String content = util.getFilledTemplate();
        FileUtils.writeStringToFile(new File("/Users/Yuvaraj/dev/mytoold/tcupted/src/main/java/"+pageName+".java"),content, "UTF-8" );
    }

    @Test
    public void test() throws Exception{
        generatePageObject("LogonPage");
    }

}
package com.tcup.ted.generator.impl;

import com.tcup.ted.generator.IPageObjectGenerator;
import com.tcup.ted.generator.identifiers.ElementField;
import com.tcup.ted.utilities.TemplateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope("prototype")
public class SeleniumPOGenerator implements IPageObjectGenerator{

    @Autowired
    private TemplateUtil util;

    private static Logger log4JLogger = LoggerFactory.getLogger(SeleniumPOGenerator.class);

    @Override
    public String generatePageObject(String pageName, List<ElementField> elementFields) throws Exception{
        util.load("/templates/selenium-page.vm");
        util.put("pageclass", pageName);
        util.put("fields", elementFields);
        util.fillTemplate();
        String filledTemplate = util.getFilledTemplate();
        log4JLogger.info("The Filled Template is \n {}", filledTemplate);
        return filledTemplate;
    }

}
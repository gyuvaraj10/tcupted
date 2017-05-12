package com.tcup.ted.services.generator.impl;

import com.tcup.ted.services.generator.IPageObjectGenerator;
import com.tcup.ted.services.generator.models.ElementField;
import com.tcup.ted.utilities.TemplateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope("prototype")
public class SeleniumPOGenerator implements IPageObjectGenerator{

    @Autowired
    private TemplateUtil util;

    @Value("${selenium.template.path}")
    private String seleniumTemplatePath;

    private static Logger log4JLogger = LoggerFactory.getLogger(SeleniumPOGenerator.class);

    @Override
    public String generatePageObject(String pageName, List<ElementField> elementFields) throws Exception{
        util.load(seleniumTemplatePath);
        util.put("pageclass", pageName);
        util.put("fields", elementFields);
        util.fillTemplate();
        String filledTemplate = util.getFilledTemplate();
        log4JLogger.info("The Filled Template is \n {}", filledTemplate);
        return filledTemplate;
    }

}
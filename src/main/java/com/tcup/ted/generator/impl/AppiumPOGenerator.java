package com.tcup.ted.generator.impl;

import com.tcup.ted.generator.IPageObjectGenerator;
import com.tcup.ted.generator.identifiers.ElementField;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AppiumPOGenerator implements IPageObjectGenerator {

    @Override
    public String generatePageObject(String pageName, List<ElementField> elementFieldList) throws Exception {
        return null;
    }
}

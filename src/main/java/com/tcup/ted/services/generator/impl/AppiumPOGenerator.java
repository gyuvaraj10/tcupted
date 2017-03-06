package com.tcup.ted.services.generator.impl;

import com.tcup.ted.services.generator.IPageObjectGenerator;
import com.tcup.ted.services.generator.models.ElementField;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppiumPOGenerator implements IPageObjectGenerator {

    @Override
    public String generatePageObject(String pageName, List<ElementField> elementFieldList) throws Exception {
        return null;
    }
}

package com.tcup.ted.generator.impl;

import com.tcup.ted.generator.IPageObjectGenerator;
import com.tcup.ted.generator.identifiers.ElementField;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppiumPOGenerator implements IPageObjectGenerator {

    @Override
    public String generatePageObject(String pageName, List<ElementField> elementFieldList) throws Exception {
        return null;
    }
}

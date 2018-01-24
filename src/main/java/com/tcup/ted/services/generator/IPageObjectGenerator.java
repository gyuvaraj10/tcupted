package com.tcup.ted.services.generator;


import com.tcup.ted.services.generator.models.ElementField;

import java.util.List;

public interface IPageObjectGenerator {

    String generatePageObject(String pageName, List<ElementField> elementFieldList);

    String generatePageElements(String pageName, List<ElementField> elementField);

}

package com.tcup.ted.generator;


import com.tcup.ted.generator.identifiers.ElementField;

import java.util.List;

public interface IPageObjectGenerator {

    String generatePageObject(String pageName, List<ElementField> elementFieldList) throws Exception;

}

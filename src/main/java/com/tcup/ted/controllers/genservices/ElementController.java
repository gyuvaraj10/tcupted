package com.tcup.ted.controllers.genservices;


import com.tcup.ted.db.entities.Element;
import com.tcup.ted.db.repositories.ElementRepository;
import com.tcup.ted.services.generator.PageObjectProvider;
import com.tcup.ted.services.generator.models.ElementField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by Yuvaraj on 23/01/2018.
 */
@RestController
@RequestMapping("/element")
public class ElementController {

    @Autowired
    private PageObjectProvider provider;

    @Autowired
    private ElementRepository elementRepository;

    @RequestMapping(path = "/{projectName}/{pageName}", method = RequestMethod.POST)
    public ResponseEntity<List<Element>> createElements(@PathVariable String projectName, @PathVariable String pageName,
                                        HttpEntity<List<ElementField>> entity) {
        List<ElementField> elementFields = entity.getBody();
        String code = provider.getObject().generatePageElements(pageName, elementFields);
        List<Element> elements = new ArrayList<>();
        elementFields.forEach(x->{
            Element element = new Element();
            element.setName(x.getName());
            element.setIdentifier(x.getIdentifier());
            element.setValue(x.getValue());
            element.setCode(code);
            element.setProjectName(projectName);
            element.setPageName(pageName);
            elements.add(element);
        });
        List<Element> insertedElements = elementRepository.insert(elements);
        return new ResponseEntity<>(insertedElements, HttpStatus.CREATED);
    }


    @RequestMapping(path = "/{projectName}/{pageName}", method = RequestMethod.PUT)
    public ResponseEntity<List<Element>> updateElements(@PathVariable String projectName, @PathVariable String pageName,
                                                       HttpEntity<List<ElementField>> entity) {
        List<ElementField> requestElements = entity.getBody();
        List<Element> mongoElements = elementRepository.findByProjectNameAndPageName(projectName, pageName);
        List<Element> elementsToUpsert = new ArrayList<>();
        for(ElementField requestElement: requestElements) {
            String code = provider.getObject().generatePageElements(pageName, Arrays.asList(requestElement));
            String elementName = requestElement.getName();
            Optional<Element> elementOptional = mongoElements.stream().filter(x->x.getName().equals(elementName)).findFirst();
            if(elementOptional.isPresent()) {
                Element mongoElement = elementOptional.get();
                if(!(mongoElement.getIdentifier().equals(requestElement.getIdentifier())
                        && mongoElement.getValue().equals(requestElement.getValue()))){
                    mongoElement.setValue(requestElement.getValue());
                    mongoElement.setIdentifier(requestElement.getIdentifier());
                    mongoElement.setCode(code);
                }
                elementsToUpsert.add(elementOptional.get());
            } else {
                Element element = new Element();
                element.setName(requestElement.getName());
                element.setIdentifier(requestElement.getIdentifier());
                element.setValue(requestElement.getValue());
                element.setCode(code);
                element.setProjectName(projectName);
                element.setPageName(pageName);
                elementsToUpsert.add(element);
            }
        }
        List<Element> upsertedElements = elementRepository.save(elementsToUpsert);
        return new ResponseEntity<>(upsertedElements, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/{projectName}/{pageName}/{name}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> removeElements(@PathVariable String projectName, @PathVariable String pageName,
                                                        @PathVariable String name) {

        elementRepository.deleteByNameAndProjectNameAndPageName(name, projectName, pageName);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}

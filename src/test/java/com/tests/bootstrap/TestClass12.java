package com.tests.bootstrap;

import com.google.gson.Gson;
import com.tcup.ted.configuration.TcupTedScanner;
import com.tcup.ted.db.entities.Actions;
import com.tcup.ted.db.repositories.ActionsRepository;
import com.tcup.ted.services.generator.models.ElementField;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Yuvaraj on 02/03/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        PropertyPlaceholderAutoConfiguration.class, TcupTedScanner.class})
public class TestClass12 {


    @Autowired
    ActionsRepository repository;

    @Test
    public void yesy(){
        String[] ii = {"12", "34", "56"};
        List<String> items = Arrays.asList(ii);
                ElementField elementField = ElementField.Builder.elementField()
                .withIdentifier("name")
                .withName("username")
                .withValue("user")
                .build();
        ElementField elementField1 = ElementField.Builder.elementField()
            .withIdentifier("css")
            .withName("username1")
            .withValue("user1")
            .build();
        List<ElementField> fields = new ArrayList<>();
        fields.add(elementField);
        fields.add(elementField1);
        String json = new Gson().toJson(fields);
        System.out.println(json);
    }

    @Test
    public void testDatabaseCreate() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(TcupTedScanner.class);
        context.refresh();
        Actions actions = new Actions("4","dragAndDrop");
        repository.save(actions);

//        Actions actions1 = repository.findByActionName("click").get(0);
//        assert actions1.getActionName().equalsIgnoreCase(actions.getActionName());
    }
}

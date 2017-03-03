package com.tests.bootstrap;

import com.google.gson.Gson;
import com.tcup.ted.generator.identifiers.ElementField;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Yuvaraj on 02/03/2017.
 */
public class TestClass12 {


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
}

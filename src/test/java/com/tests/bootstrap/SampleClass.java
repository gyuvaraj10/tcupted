package com.tests.bootstrap;

import com.google.gson.Gson;
import com.tcup.ted.services.companies.model.TcupCompany;
import com.tcup.ted.services.companies.model.TcupProject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Yuvaraj on 20/01/2018.
 */
public class SampleClass {

    int  driver;

    @Before
    public void setup() {
        System.out.println("Start");
    }

    @Test
    public void test() {
        driver = 10;



    }

    @After
    public void cleanUp() {
        System.out.println(driver);
    }
}

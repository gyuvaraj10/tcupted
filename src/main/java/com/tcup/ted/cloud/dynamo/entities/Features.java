//package com.tcup.ted.cloud.dynamo.entities;
//
//import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
//import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
//
//import java.util.List;
//
//@DynamoDBTable(tableName = "features")
//public class Features {
//
//    @DynamoDBAttribute(attributeName = "feature")
//    private String featureName;
//
//    @DynamoDBAttribute(attributeName = "scenario")
//    private String scenarioName;
//
//    @DynamoDBAttribute(attributeName = "steps")
//    private List<String> steps;
//
//    public List<String> getSteps() {
//        return steps;
//    }
//
//    public String getFeatureName() {
//        return featureName;
//    }
//
//    public void setFeatureName(String featureName) {
//        this.featureName = featureName;
//    }
//
//    public String getScenarioName() {
//        return scenarioName;
//    }
//
//    public void setScenarioName(String scenarioName) {
//        this.scenarioName = scenarioName;
//    }
//
//    public void setSteps(List<String> steps) {
//        this.steps = steps;
//    }
//
//}

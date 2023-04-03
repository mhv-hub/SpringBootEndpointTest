package com.akash.spring_junit_test_app.user.enities;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class TestEntity {
    ArrayList<Integer> testList;

    public ArrayList<Integer> getTestList() {
        return this.testList;
    }

    public void setTestList(ArrayList<Integer> testList) {
        this.testList = testList;
    }

    public TestEntity() {
    }

}

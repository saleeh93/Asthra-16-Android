package com.saleeh.astra.api.models;

/**
 * Created by saleeh on 2/4/16.
 */
public class Participant {
    public String id;
    public String name;
    public String dept;
    public String sem;

    public Participant() {
    }

    public Participant(String name, String dept, String sem) {
        this.name = name;
        this.dept = dept;
        this.sem = sem;
    }
}

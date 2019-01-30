package ru.stqa.pft.rest;

import java.util.Objects;

public class Issue {

    private int id;
    private String subject;
    private String description;
    private String state_name;

    public String getState_name() {
        return state_name;
    }

    public Issue withState_name(String state_name) {
        this.state_name = state_name;
        return this;
    }

    public int getId() {
        return id;
    }

    public Issue withId(int id) {
        this.id = id;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public Issue withSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Issue withDescription(String description) {
        this.description = description;
        return this;
    }

}

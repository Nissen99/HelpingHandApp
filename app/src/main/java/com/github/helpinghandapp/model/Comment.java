package com.github.helpinghandapp.model;

public class Comment {

    private String body;
    private String name;

    public Comment() {
    }

    public Comment(String name, String body) {
        this.body = body;
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.github.helpinghandapp.model;

import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;
import java.util.UUID;

public class Comment implements Serializable {

    private String body;
    private String authorId;
    private String id;


    public Comment() {
    }

    public Comment(String body, String authorId) {
        this.authorId = authorId;
        this.body = body;
        this.id = java.util.UUID.randomUUID().toString();

    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

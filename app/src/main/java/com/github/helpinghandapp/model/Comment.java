package com.github.helpinghandapp.model;

import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;

public class Comment {

    private String body;
    private LiveData<FirebaseUser> author;


    public Comment(String body, LiveData<FirebaseUser> author) {
        this.author = author;
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LiveData<FirebaseUser> getAuthor() {
        return author;
    }
}

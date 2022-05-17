package com.github.helpinghandapp.model;

import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Post implements Serializable {

    private String id;

    private String body;
    private String title;
    private String authorId;
    private List<Comment> comments;

    public Post() {
        comments = new ArrayList<>();
    }

    public Post(String title, String body, String authorId) {
        this.authorId = authorId;
        this.id = java.util.UUID.randomUUID().toString();
        this.body = body;
        this.title = title;
        comments = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void addComment(Comment comment){
        comments.add(comment);
    }

    public List<Comment> getComments(){
        return comments;
    }

    public void removeComment(Comment comment){
        comments.remove(comment);
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

}

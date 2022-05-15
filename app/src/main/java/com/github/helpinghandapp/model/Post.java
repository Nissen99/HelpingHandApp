package com.github.helpinghandapp.model;

import java.util.ArrayList;
import java.util.List;

public class Post {

    private String body;
    private String title;
    private int id;
    private List<Comment> comments;


    public Post(String title, String body) {
        this.body = body;
        this.title = title;
        comments = new ArrayList<>();
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

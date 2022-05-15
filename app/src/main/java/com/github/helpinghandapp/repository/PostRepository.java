package com.github.helpinghandapp.repository;

import com.github.helpinghandapp.model.Comment;
import com.github.helpinghandapp.model.Post;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class PostRepository {


    private static PostRepository instance = new PostRepository();

    private List<Post> mockListOfPosts;

    private PostRepository() {
        mockListOfPosts = new ArrayList<>();
    }

    public static PostRepository getInstance(){
        return instance;
    }

    public void submitPost(Post newPost) {

        System.out.println("TSTSTAATTSATSAATSTSAATTATA");
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://helping-hand-3b6f3-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference("post");

        myRef.setValue("Test4");

    }

    public Post getPostFromId(int postId) {

        return null;
    }

    public void addCommentOnPost(Post postInspected, Comment comment) {


    }
}

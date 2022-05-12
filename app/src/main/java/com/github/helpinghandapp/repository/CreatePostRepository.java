package com.github.helpinghandapp.repository;


import com.github.helpinghandapp.model.Post;

import java.util.ArrayList;
import java.util.List;


public class CreatePostRepository {



    private List<Post> mockListOfPosts;

    public CreatePostRepository() {
        mockListOfPosts = new ArrayList<>();
    }

    public void submitPost(Post newPost) {
        mockListOfPosts.add(newPost);
        for (int i = 0; i < mockListOfPosts.size(); i++) {
            System.out.println(i + " ----POST PRINT----: " + mockListOfPosts.get(i).getTitle());

        }

    }


}

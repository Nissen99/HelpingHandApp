package com.github.helpinghandapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.github.helpinghandapp.model.Post;
import com.github.helpinghandapp.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;

public class ShowPostsViewModel extends ViewModel {


    private PostRepository postRepository;

    public ShowPostsViewModel() {
        postRepository = PostRepository.getInstance();
    }

    public LiveData<List<Post>> getListOfPosts() {
        return postRepository.getListOfPosts();
    }


}

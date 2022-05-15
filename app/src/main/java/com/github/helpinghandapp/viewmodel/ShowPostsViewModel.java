package com.github.helpinghandapp.viewmodel;

import androidx.lifecycle.ViewModel;

import com.github.helpinghandapp.model.Post;

import java.util.ArrayList;
import java.util.List;

public class ShowPostsViewModel extends ViewModel {


    private List<Post> listOfPosts;

    public ShowPostsViewModel() {
        listOfPosts = new ArrayList<>();
    }

    public List<Post> getListOfPosts() {


        return listOfPosts;
    }


}

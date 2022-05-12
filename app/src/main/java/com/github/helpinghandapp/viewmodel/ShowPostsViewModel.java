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


        listOfPosts.add(new Post("Hello1", "BODY"));
        listOfPosts.add(new Post("Hello2", "BODY221321"));
        listOfPosts.add(new Post("Hello3", "BO321123DY2"));
        listOfPosts.add(new Post("Hello4", "BODY312312"));
        listOfPosts.add(new Post("Hello5", "BODY"));
        listOfPosts.add(new Post("Hello6", "BODY231321"));
        listOfPosts.add(new Post("Hello7", "BOD231231Y"));
        listOfPosts.add(new Post("Hello8", "BOD321321Y"));


        return listOfPosts;
    }


}

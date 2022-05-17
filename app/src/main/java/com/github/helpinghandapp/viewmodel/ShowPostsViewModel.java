package com.github.helpinghandapp.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.github.helpinghandapp.model.Comment;
import com.github.helpinghandapp.model.Post;
import com.github.helpinghandapp.repository.PostRepository;
import com.github.helpinghandapp.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class ShowPostsViewModel extends AndroidViewModel {


    private PostRepository postRepository;
    private MutableLiveData<List<Post>> listOfPost;
    private UserRepository userRepository;

    public ShowPostsViewModel(Application application) {
        super(application);
        userRepository = UserRepository.getInstance(application);
        postRepository = PostRepository.getInstance();
        listOfPost = new MutableLiveData<>();
    }

    public LiveData<List<Post>> getListOfPosts() {
        listOfPost = postRepository.getListOfPosts();
        return listOfPost;
    }

    public void filterOnlyMyPost(){

        String userId = userRepository.getCurrentUser().getValue().getUid();
        List<Post> postWithCorrectAuthor = new ArrayList<>();
        List<Post> listOfAllPosts = listOfPost.getValue();

        for (Post post : listOfAllPosts) {
            if (post.getAuthorId().equals(userId)){
                postWithCorrectAuthor.add(post);
            }
        }
        listOfPost.postValue(postWithCorrectAuthor);
        System.out.println("DONE");

    }


    public void filterOnlyPostICommentedOn() {

        String userId = userRepository.getCurrentUser().getValue().getUid();
        List<Post> postUserCommentedOn = new ArrayList<>();
        List<Post> listOfAllPosts = listOfPost.getValue();

        for (Post post : listOfAllPosts) {
            for (Comment comment : post.getComments()) {
                if (comment.getAuthorId().equals(userId)){
                    postUserCommentedOn.add(post);
                    break;
                }
            }
        }

        listOfPost.postValue(postUserCommentedOn);
    }

    public void removeAllFilters() {
        postRepository.getListOfPosts().observeForever(new Observer<List<Post>>() {
            @Override
            public void onChanged(List<Post> posts) {
                listOfPost.postValue(posts);
            }
        });
    }
}

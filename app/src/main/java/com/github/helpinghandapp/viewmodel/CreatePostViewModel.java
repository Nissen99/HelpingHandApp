package com.github.helpinghandapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.helpinghandapp.model.Post;
import com.github.helpinghandapp.repository.CreatePostRepository;

public class CreatePostViewModel extends ViewModel {

    private MutableLiveData<String> title;
    private MutableLiveData<String> body;
    private CreatePostRepository repository;

    public CreatePostViewModel() {
        super();
        repository = new CreatePostRepository();
        title = new MutableLiveData<>();
        body = new MutableLiveData<>();

    }

    public void submitPost() {
        System.out.println("Post submited: " + title.getValue());
        Post newPost = new Post(title.getValue(), body.getValue());
        repository.submitPost(newPost);
    }

    public LiveData<String> getTitle() {
        return title;
    }

    public void setTitle(String newTitle) {

        this.title.setValue(newTitle);
    }

    public LiveData<String> getBody() {
        return body;
    }

    public void setBody(String newBody) {

        this.body.setValue(newBody);
    }
}
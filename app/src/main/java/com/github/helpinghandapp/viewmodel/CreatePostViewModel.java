package com.github.helpinghandapp.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.helpinghandapp.model.Post;
import com.github.helpinghandapp.repository.PostRepository;
import com.github.helpinghandapp.signin.data.UserLiveData;
import com.github.helpinghandapp.signin.data.UserRepository;
import com.google.firebase.auth.FirebaseUser;

public class CreatePostViewModel extends AndroidViewModel {

    private MutableLiveData<String> title;
    private MutableLiveData<String> body;
    private PostRepository postRepository;
    private Application application;

    public CreatePostViewModel(Application application) {
        super(application);
        this.application = application;
        postRepository = PostRepository.getInstance();
        title = new MutableLiveData<>();
        body = new MutableLiveData<>();

    }

    public void submitPost() {
        UserRepository userRepository = UserRepository.getInstance(application);

        LiveData<FirebaseUser> currentUser = userRepository.getCurrentUser();
        Post newPost = new Post(title.getValue(), body.getValue(), currentUser);
        postRepository.submitPost(newPost);
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
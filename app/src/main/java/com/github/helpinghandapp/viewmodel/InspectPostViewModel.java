package com.github.helpinghandapp.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.github.helpinghandapp.model.Comment;
import com.github.helpinghandapp.model.Post;
import com.github.helpinghandapp.repository.PostRepository;
import com.github.helpinghandapp.repository.UserRepository;

public class InspectPostViewModel extends AndroidViewModel {


    private PostRepository postRepository;
    private MutableLiveData<String> commentBody;
    private Application application;

    public InspectPostViewModel(Application application){
        super(application);
        this.application = application;
        postRepository = PostRepository.getInstance();
        commentBody = new MutableLiveData<>();
    }


    public LiveData<Post> getPostFromId(String postId) {

        return postRepository.getPostFromId(postId);
    }

    public void addCommentOnPost(Post postInspected) {

        if ( commentBody.getValue() == null || commentBody.getValue().isEmpty()){
            throw new IllegalArgumentException();
        }
        UserRepository userRepository = UserRepository.getInstance(application);

        Comment commentOnPost = new Comment(commentBody.getValue(), userRepository.getCurrentUser().getValue().getUid());
        postRepository.addCommentOnPost(postInspected, commentOnPost);


    }

    public void setCommentBody(String commentBody) {
        this.commentBody.setValue(commentBody);
    }
}

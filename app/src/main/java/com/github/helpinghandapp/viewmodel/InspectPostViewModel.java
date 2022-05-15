package com.github.helpinghandapp.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.helpinghandapp.model.Comment;
import com.github.helpinghandapp.model.Post;
import com.github.helpinghandapp.repository.PostRepository;
import com.github.helpinghandapp.signin.data.UserRepository;

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


    public Post getPostFromId(int postId) {

        return postRepository.getPostFromId(postId);
    }

    public void addCommentOnPost(Post postInspected) {

        UserRepository userRepository = UserRepository.getInstance(application);

        Comment commentOnPost = new Comment(commentBody.getValue(), userRepository.getCurrentUser());
        postRepository.addCommentOnPost(postInspected, commentOnPost);


    }

    public void setCommentBody(String commentBody) {
        this.commentBody.setValue(commentBody);
    }
}

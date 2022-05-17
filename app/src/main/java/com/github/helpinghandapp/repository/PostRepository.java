package com.github.helpinghandapp.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.github.helpinghandapp.model.Comment;
import com.github.helpinghandapp.model.Post;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PostRepository extends RepositoryUtil {


    private static PostRepository instance = new PostRepository();
    private final String POST_REFERENCE = "post";


    private PostRepository() {
    }

    public static PostRepository getInstance(){
        return instance;
    }

    public void submitPost(Post newPost) {

            getDatabaseReference()
                    .child(POST_REFERENCE)
                    .child(newPost.getId())
                    .setValue(newPost);


    }

    public LiveData<Post> getPostFromId(String postId) {
        MutableLiveData<Post> searchedForPost = new MutableLiveData<>();

        getDatabaseReference()
                .child(POST_REFERENCE)
                .child(postId)
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                searchedForPost.postValue(snapshot.getValue(Post.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println(error);
            }
        });

        return searchedForPost;
    }

    public void addCommentOnPost(Post postInspected, Comment comment) {


        postInspected.addComment(comment);
        getDatabaseReference()
                .child(POST_REFERENCE)
                .child(postInspected.getId())
                .setValue(postInspected);

    }

    public MutableLiveData<List<Post>> getListOfPosts() {
        MutableLiveData<List<Post>> listOfPosts = new MutableLiveData<>();

        getDatabaseReference()
                .child(POST_REFERENCE)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        List<Post> list = new ArrayList<>();
                        for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                            list.add(postSnapshot.getValue(Post.class));
                        }
                        listOfPosts.postValue(list);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        return listOfPosts;
    }


}

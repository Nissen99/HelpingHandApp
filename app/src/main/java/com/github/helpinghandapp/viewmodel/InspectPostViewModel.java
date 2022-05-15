package com.github.helpinghandapp.viewmodel;

import androidx.lifecycle.ViewModel;

import com.github.helpinghandapp.model.Comment;
import com.github.helpinghandapp.model.Post;

public class InspectPostViewModel extends ViewModel {

    public Post getPostFromId(int postId) {
        System.out.println(postId);

        Post dummy = new Post("hello2", "sadsadsadsa");
        dummy.addComment(new Comment("seaesa", "asdsdadsadsadsadsa"));
        dummy.addComment(new Comment("seaesa", "asdsdadsadsadsadsa"));
        dummy.addComment(new Comment("seaesa", "asdsdadsadsadsadsa"));
        dummy.addComment(new Comment("seaesa", "asdsdadsadsadsadsa"));
        dummy.addComment(new Comment("seaesa", "asdsdadsadsadsadsa"));

        return dummy;
    }
}

package com.github.helpinghandapp.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.helpinghandapp.R;
import com.github.helpinghandapp.adapter.MyCommentRecyclerViewAdapter;
import com.github.helpinghandapp.model.Post;
import com.github.helpinghandapp.viewmodel.InspectPostViewModel;
import com.github.helpinghandapp.viewmodel.UserViewModel;

/**
 * A fragment representing a list of Items.
 */
public class InspectPostFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String POST_ID = "postId";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    private InspectPostViewModel viewModel;
    private UserViewModel userViewModel;


    private LiveData<Post> postInspected = new MutableLiveData<>();

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public InspectPostFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_inspect_post_with_comments, container, false);
        viewModel = new ViewModelProvider(this).get(InspectPostViewModel.class);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);


        View viewForPost = view.findViewById(R.id.postFromInspect);

        TextView postTitleView = viewForPost.findViewById(R.id.title);

        TextView postBodyView = viewForPost.findViewById(R.id.body);

        TextView postAuthorView = viewForPost.findViewById(R.id.author);


        String postId = getArguments().getString(POST_ID);
        postInspected = viewModel.getPostFromId(postId);


        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.commentsForInspect);
        Context context = recyclerView.getContext();
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }


        postInspected.observe(getViewLifecycleOwner(), new Observer<Post>() {
            @Override
            public void onChanged(Post post) {
                postTitleView.setText(post.getTitle());

                postBodyView.setText(post.getBody());


                userViewModel.getDisplayNameFromUid(post.getAuthorId()).observe(getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        postAuthorView.setText(s);
                    }
                });


                recyclerView.setAdapter(new MyCommentRecyclerViewAdapter(post.getComments(), getActivity().getApplication()));
            }
        });

        EditText writeCommentField = view.findViewById(R.id.inspectCommentEditText);
        Button buttonToSubmitComment = view.findViewById(R.id.inspectCommentButton);


        listenToEditTextAndUpdateViewModel(writeCommentField);

        buttonToSubmitComment.setOnClickListener(view1 -> {
            try {
                viewModel.addCommentOnPost(postInspected.getValue());
                writeCommentField.setText("");
            } catch (IllegalArgumentException e){
                Toast.makeText(context, "Cannot post empty comment", Toast.LENGTH_SHORT).show();
            }

        });



        return view;
    }

    private void listenToEditTextAndUpdateViewModel(EditText writeCommentField) {
        writeCommentField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            viewModel.setCommentBody(writeCommentField.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
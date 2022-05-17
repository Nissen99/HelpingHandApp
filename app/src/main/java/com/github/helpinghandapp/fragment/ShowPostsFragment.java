package com.github.helpinghandapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.helpinghandapp.R;
import com.github.helpinghandapp.adapter.MyPostRecyclerViewAdapter;
import com.github.helpinghandapp.model.Post;
import com.github.helpinghandapp.SignInActivity;
import com.github.helpinghandapp.viewmodel.SignInViewModel;
import com.github.helpinghandapp.viewmodel.ShowPostsViewModel;

import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class ShowPostsFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String POST_ID = "postId";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    private ShowPostsViewModel viewModel;
    private SignInViewModel signInViewModel;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ShowPostsFragment() {
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
        container.removeAllViews();
        View view = inflater.inflate(R.layout.fragment_show_posts_list, container, false);

        checkIfSignedIn();


        savedInstanceState = new Bundle();

        viewModel = new ViewModelProvider(this).get(ShowPostsViewModel.class);

        RecyclerView recyclerViewForPost = view.findViewById(R.id.listForPost);
        // Set the adapter

            Context context = view.getContext();
            if (mColumnCount <= 1) {
                recyclerViewForPost.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerViewForPost.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            Bundle finalSavedInstanceState = savedInstanceState;
            viewModel.getListOfPosts().observe(getViewLifecycleOwner(), new Observer<List<Post>>() {
                @Override
                public void onChanged(List<Post> posts) {
                    MyPostRecyclerViewAdapter postViewAdapter = new MyPostRecyclerViewAdapter(posts, getActivity().getApplication());

                    postViewAdapter.setOnClickListener(post -> {
                        finalSavedInstanceState.putString(POST_ID, post.getId());
                        Bundle newBundle = new Bundle();
                        newBundle.putString(POST_ID, post.getId());
                        NavHostFragment.findNavController(ShowPostsFragment.this)
                                .navigate(R.id.action_showPostsFragment_to_inspectPostFragment, newBundle);
                    });
                    recyclerViewForPost.setAdapter(postViewAdapter);
                }
            });


            Button showMyPostsButton = view.findViewById(R.id.showMyPostButton);
            showMyPostsButton.setOnClickListener(view1 -> {
                viewModel.filterOnlyMyPost();
            });

            Button showICommentedOnButton = view.findViewById(R.id.showCommentedButton);
            showICommentedOnButton.setOnClickListener(view1 -> {
                viewModel.filterOnlyPostICommentedOn();
            });

            Button removeFilterButton = view.findViewById(R.id.removeFilterButton);
            removeFilterButton.setOnClickListener(view1 -> {
                viewModel.removeAllFilters();
            });




        return view;
    }


    public void checkIfSignedIn(){
        signInViewModel = new ViewModelProvider(this).get(SignInViewModel.class);
        signInViewModel.getCurrentUser().observe(getViewLifecycleOwner(), user -> {
            if (user == null) {
               startLoginActivity();

            }
        });
    }

    public void startLoginActivity(){
        startActivity(new Intent(getContext(), SignInActivity.class));
        getActivity().finish();
    }
}
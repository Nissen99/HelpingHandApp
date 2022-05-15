package com.github.helpinghandapp.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.helpinghandapp.R;
import com.github.helpinghandapp.adapter.MyCommentRecyclerViewAdapter;
import com.github.helpinghandapp.model.Post;
import com.github.helpinghandapp.viewmodel.InspectPostViewModel;

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

    private Post postInspected;

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



        int postId = getArguments().getInt(POST_ID);
        postInspected = viewModel.getPostFromId(postId);

        View viewForPost = view.findViewById(R.id.postFromInspect);
        TextView postTitleView = viewForPost.findViewById(R.id.item_number);
        postTitleView.setText(postInspected.getTitle());


        TextView postBodyView = viewForPost.findViewById(R.id.content);
        postBodyView.setText(postInspected.getBody());



        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.commentsForInspect);
        Context context = recyclerView.getContext();
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyCommentRecyclerViewAdapter(postInspected.getComments()));

        return view;
    }
}
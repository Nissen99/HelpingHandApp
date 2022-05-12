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

import com.github.helpinghandapp.R;
import com.github.helpinghandapp.viewmodel.ShowPostsViewModel;

/**
 * A fragment representing a list of Items.
 */
public class ShowPostsFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    private ShowPostsViewModel viewModel;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ShowPostsFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ShowPostsFragment newInstance(int columnCount) {
        ShowPostsFragment fragment = new ShowPostsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
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

        viewModel = new ViewModelProvider(this).get(ShowPostsViewModel.class);


        // Set the adapter
        if (view instanceof RecyclerView) {
            System.out.println("_____________RECYCLER__________________");
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                System.out.println("if mColumnCount <= 1");
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                System.out.println("ELSE");

                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyPostRecyclerViewAdapter(viewModel.getListOfPosts()));
        }
        System.out.println("DONNNNNNNNEEE");

        return view;
    }
}
package com.github.helpinghandapp.adapter;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.helpinghandapp.databinding.FragmentInspectPostBinding;
import com.github.helpinghandapp.model.Comment;
import com.github.helpinghandapp.repository.UserRepository;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Comment}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyCommentRecyclerViewAdapter extends RecyclerView.Adapter<MyCommentRecyclerViewAdapter.ViewHolder> {

    private final List<Comment> comments;
    private UserRepository userRepository;

    public MyCommentRecyclerViewAdapter(List<Comment> items, Application application) {
        comments = items;
        userRepository = UserRepository.getInstance(application);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentInspectPostBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mContentView.setText(comments.get(position).getBody());
        userRepository.getDisplayNameFromUid(comments.get(position).getAuthorId()).observeForever( new Observer<String>() {
            @Override
            public void onChanged(String s) {

                holder.mIdView.setText(s);

            }
        });
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;
        public Comment mItem;

        public ViewHolder(FragmentInspectPostBinding binding) {
            super(binding.getRoot());
            mIdView = binding.name;
            mContentView = binding.content;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
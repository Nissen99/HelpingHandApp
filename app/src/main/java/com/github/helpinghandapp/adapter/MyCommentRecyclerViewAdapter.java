package com.github.helpinghandapp.adapter;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.helpinghandapp.databinding.FragmentInspectPostBinding;
import com.github.helpinghandapp.model.Comment;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Comment}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyCommentRecyclerViewAdapter extends RecyclerView.Adapter<MyCommentRecyclerViewAdapter.ViewHolder> {

    private final List<Comment> comments;

    public MyCommentRecyclerViewAdapter(List<Comment> items) {
        comments = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentInspectPostBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = comments.get(position);

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
            mIdView = binding.itemNumber;
            mContentView = binding.content;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
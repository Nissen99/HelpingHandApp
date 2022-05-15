package com.github.helpinghandapp.adapter;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.helpinghandapp.databinding.FragmentShowPostsBinding;
import com.github.helpinghandapp.model.Post;

import java.util.List;


public class MyPostRecyclerViewAdapter extends RecyclerView.Adapter<MyPostRecyclerViewAdapter.ViewHolder> {

    private final List<Post> posts;
    private OnClickListener onClickListener;

    public MyPostRecyclerViewAdapter(List<Post> items) {
        posts = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentShowPostsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.mIdView.setText(posts.get(position).getTitle());
        holder.mContentView.setText(posts.get(position).getBody());
    }

    public void setOnClickListener(OnClickListener listener) {
        this.onClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;

        public ViewHolder(FragmentShowPostsBinding binding) {
            super(binding.getRoot());
            mIdView = binding.itemNumber;
            mContentView = binding.content;
            binding.getRoot().setOnClickListener(view -> {
                onClickListener.onClick(posts.get(getBindingAdapterPosition()));
            });
        }




        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }

    public interface OnClickListener{
        void onClick(Post post);
    }
}
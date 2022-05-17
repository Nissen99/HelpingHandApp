package com.github.helpinghandapp.adapter;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.helpinghandapp.databinding.FragmentShowPostsBinding;
import com.github.helpinghandapp.model.Comment;
import com.github.helpinghandapp.model.Post;
import com.github.helpinghandapp.repository.UserRepository;

import java.util.List;


public class MyPostRecyclerViewAdapter extends RecyclerView.Adapter<MyPostRecyclerViewAdapter.ViewHolder> {

    private final List<Post> posts;
    private OnClickListener onClickListener;
    private UserRepository userRepository;


    public MyPostRecyclerViewAdapter(List<Post> items, Application application) {
        posts = items;
        userRepository = UserRepository.getInstance(application);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentShowPostsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.mIdView.setText(posts.get(position).getTitle());
        holder.mContentView.setText(posts.get(position).getBody());
        userRepository.getDisplayNameFromUid(posts.get(position).getAuthorId()).observeForever( new Observer<String>() {
            @Override
            public void onChanged(String s) {

                holder.mAuthorView.setText(s);

            }
        });

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
        public final TextView mAuthorView;

        public ViewHolder(FragmentShowPostsBinding binding) {
            super(binding.getRoot());
            mIdView = binding.title;
            mContentView = binding.body;
            binding.getRoot().setOnClickListener(view -> {
                onClickListener.onClick(posts.get(getBindingAdapterPosition()));
            });
            mAuthorView = binding.author;

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
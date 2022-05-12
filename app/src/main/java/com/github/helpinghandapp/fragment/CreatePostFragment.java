package com.github.helpinghandapp.fragment;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.github.helpinghandapp.R;
import com.github.helpinghandapp.viewmodel.CreatePostViewModel;

public class CreatePostFragment extends Fragment {

    private CreatePostViewModel mViewModel;

    public static CreatePostFragment newInstance() {
        return new CreatePostFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        container.removeAllViews();
        View view = inflater.inflate(R.layout.create_post_fragment, container, false);

        EditText titleEditText = view.findViewById(R.id.postTitleTextView);
        listenerForChangeInTitleEditText(titleEditText);
        EditText bodyEditText = view.findViewById(R.id.postBodyMultiLine);
        listenerForChangeInBodyEditText(bodyEditText);


        Button submitPostButton = view.findViewById(R.id.submitPostButton);
        submitPostButton.setOnClickListener(view1 -> {
            mViewModel.submitPost();

        });


        return view;
    }

    private void listenerForChangeInBodyEditText(EditText bodyEditText) {
        bodyEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            mViewModel.setBody(bodyEditText.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void listenerForChangeInTitleEditText(EditText titleEditText) {
        titleEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            mViewModel.setTitle(titleEditText.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CreatePostViewModel.class);
        // TODO: Use the ViewModel
    }

}
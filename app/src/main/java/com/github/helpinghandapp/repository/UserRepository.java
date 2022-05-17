package com.github.helpinghandapp.repository;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.firebase.ui.auth.AuthUI;
import com.github.helpinghandapp.data.UserLiveData;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class UserRepository extends RepositoryUtil {
    private final UserLiveData currentUser;
    private final Application app;
    private static UserRepository instance;
    private final String USER_REFERENCE = "user";

    private UserRepository(Application app) {
        this.app = app;
        currentUser = new UserLiveData();
    }

    public static synchronized UserRepository getInstance(Application app) {
        if(instance == null)
            instance = new UserRepository(app);
        return instance;
    }

    public LiveData<FirebaseUser> getCurrentUser() {
        return currentUser;
    }

    public void signOut() {
        AuthUI.getInstance()
                .signOut(app.getApplicationContext());
    }

    public void saveDisplayNameFromUser() {
        getDatabaseReference()
                .child(USER_REFERENCE)
                .child(getCurrentUser().getValue().getUid())
                .setValue(getCurrentUser().getValue().getDisplayName());
    }

    public LiveData<String> getDisplayNameFromUid(String uid){

        MutableLiveData<String> nameOfUser = new MutableLiveData<>();

        getDatabaseReference()
                .child(USER_REFERENCE)
                .child(uid)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        nameOfUser.postValue(snapshot.getValue(String.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        return nameOfUser;
    }
}
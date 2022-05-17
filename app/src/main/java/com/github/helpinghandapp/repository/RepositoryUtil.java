package com.github.helpinghandapp.repository;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RepositoryUtil {

    protected final String URI = "https://helping-hand-3b6f3-default-rtdb.europe-west1.firebasedatabase.app/";


    protected DatabaseReference getDatabaseReference(){
        FirebaseDatabase database = FirebaseDatabase.getInstance(URI);
        DatabaseReference myRef = database.getReference();
        return myRef;
    }



}

package com.github.helpinghandapp;

import android.content.Intent;
import android.os.Bundle;

import com.github.helpinghandapp.fragment.CreatePostFragment;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.github.helpinghandapp.databinding.ActivityMainBinding;
import com.github.helpinghandapp.fragment.ShowPostsFragment;
import com.github.helpinghandapp.signin.SignInActivity;
import com.github.helpinghandapp.signin.SignInViewModel;
import com.google.firebase.auth.FirebaseAuth;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private NavController navController;
    private SignInViewModel signInViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);


        signInViewModel = new ViewModelProvider(this).get(SignInViewModel.class);


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.createPostFragment);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            Toast.makeText(this, "LOGOUTTTTTTT", Toast.LENGTH_SHORT).show();
            signInViewModel.signOut();
            Intent intent = new Intent(getApplicationContext(), SignInActivity.class);

            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
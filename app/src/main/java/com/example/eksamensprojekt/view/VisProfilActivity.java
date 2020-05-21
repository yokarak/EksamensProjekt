package com.example.eksamensprojekt.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.example.eksamensprojekt.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;


public class VisProfilActivity extends AppCompatActivity {

    private Button  mLogudBtn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vis_profil);


        //Tilføjer custom action bar
        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);

        mAuth = FirebaseAuth.getInstance();

        mLogudBtn = (Button) findViewById(R.id.log_ud_btn);


        mLogudBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(VisProfilActivity.this, MainActivity.class));
                finish();

            }
        });



    }


    //Tjekker om bruger er logget ind. Hvis ikke, vises opret bruger activity
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);


        if (currentUser == null) {

            Intent opretBrugerIntent = new Intent(VisProfilActivity.this, OpretBrugerActivity.class);
            startActivity(opretBrugerIntent);
            finish();
        }

    }
}
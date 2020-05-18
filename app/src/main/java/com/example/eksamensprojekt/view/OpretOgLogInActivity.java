package com.example.eksamensprojekt.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.example.eksamensprojekt.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class OpretOgLogInActivity extends AppCompatActivity {

    private TextInputLayout mFornavn;
    private TextInputLayout mEfternavn;
    private TextInputLayout mEmail;
    private TextInputLayout mTelefonNr;
    private TextInputLayout mAdgangskode;
    private TextInputLayout iEmail;
    private TextInputLayout iAdgangskode;

    private Button mBekraeftBtn;
    private Button mGotoLoginBtn;
    private Button mGoToOpretBrugerBtn;
    private Button iLoginPaaBrugerBtn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opret_bruger);

        mAuth = FirebaseAuth.getInstance();

        //Tilføjer custom actionbar
        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);


        //TextInput for opret ny bruger xml
        mFornavn = (TextInputLayout) findViewById(R.id.angivFornavn);
        mEfternavn = (TextInputLayout) findViewById(R.id.angivEfternavn);
        mEmail = (TextInputLayout) findViewById(R.id.angivEmail);
        mAdgangskode = (TextInputLayout) findViewById(R.id.angivAdgangskode);
        mTelefonNr = (TextInputLayout) findViewById(R.id.angivTelefonNr);

        //TextInput for log in xml
        iEmail = (TextInputLayout) findViewById(R.id.indsaetEmail);
        iAdgangskode = (TextInputLayout) findViewById(R.id.indsaetAdgangskode);


        //Setting up button to correct ids
        mBekraeftBtn = (Button) findViewById(R.id.bekraeft_ny_bruger_btn);
        mGotoLoginBtn = (Button) findViewById(R.id.goto_loginin_btn);
        mGoToOpretBrugerBtn = (Button) findViewById(R.id.goto_opret_bruger_btn);
        iLoginPaaBrugerBtn = (Button) findViewById(R.id.login_btn);


        //Tager angivet inputs og registere ny bruger
        mBekraeftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fornavn = mFornavn.getEditText().getText().toString();
                String efternavn = mEfternavn.getEditText().getText().toString();
                String email = mEmail.getEditText().getText().toString();
                String telefonNr = mTelefonNr.getEditText().getText().toString();
                String adgangskode = mAdgangskode.getEditText().getText().toString();

                registerNyBruger(email, adgangskode);


            }
        });

        //Skifter til login in layout
        mGotoLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setContentView(R.layout.activity_login_in);

            }
        });

        //Skifter til opret bruger layout
        mGoToOpretBrugerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setContentView(R.layout.activity_opret_bruger);
            }
        });

    }



    //Metode til registrering af ny bruger gennem firebase
    private void registerNyBruger( String email, String adgangskode) {

        mAuth.createUserWithEmailAndPassword(email,adgangskode).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

                    Intent visProfilIntet = new Intent(OpretOgLogInActivity.this, VisProfilActivity.class);
                    startActivity(visProfilIntet);
                    Toast.makeText(OpretOgLogInActivity.this, "Oprettelse af bruger gennemført", Toast.LENGTH_LONG).show();
                    finish();

                }else {
                    Toast.makeText(OpretOgLogInActivity.this, "Der opstod en fejl", Toast.LENGTH_LONG).show();
                }
            }
        });


    }


}
package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
   // public static String EMAIL;
    EditText emailId, password;
    TextView tvSignUp;
    Button buttonSignIn;
    private static int AUTH_REQUEST_CODE = 1342;
    private FirebaseAuth mFireBaseAuth;
    private FirebaseAuth.AuthStateListener listener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFireBaseAuth = FirebaseAuth.getInstance();

        emailId = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        tvSignUp = findViewById(R.id.tvSignUp);
        buttonSignIn = findViewById(R.id.signInButton);
        listener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFireBaseUser = mFireBaseAuth.getCurrentUser();

                if(mFireBaseUser !=null){
                    String email = mFireBaseAuth.getCurrentUser().getEmail().toString();
                    Toast.makeText(MainActivity.this,"You are logged in",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this,HomeActivity.class);
                    i.putExtra("scoreForUpdate",0);
                    i.putExtra("e-mail",email);
                    startActivity(i);
                }else{
                    Toast.makeText(MainActivity.this,"Please log in",Toast.LENGTH_SHORT).show();
                }
            }
        };

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailId.getText().toString();
                String pass = password.getText().toString();
                int score = 0;
                if(email.isEmpty())
                {
                    emailId.setError("Please enter e-mail");
                    emailId.requestFocus();
                }
                if(pass.isEmpty())
                {
                    password.setError("Please enter password");
                    password.requestFocus();
                }
                if(!(email.isEmpty() && pass.isEmpty()))
                {

                    mFireBaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(MainActivity.this,"Login error",Toast.LENGTH_SHORT).show();
                            }else{
                                Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                                intent.putExtra("scoreForUpdate",0);
                                intent.putExtra("e-mail",email);
                                startActivity(intent);
                            }
                        }
                    });
                } else
                {
                    Toast.makeText(MainActivity.this,"ERROR OCCURED",Toast.LENGTH_SHORT).show();
                }
            }
        });
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        mFireBaseAuth.addAuthStateListener(listener);
    }
}
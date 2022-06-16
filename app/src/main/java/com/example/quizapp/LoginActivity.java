package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText emailId, password;
    TextView tvSignUp;
    Button buttonSignUp;
    private static int AUTH_REQUEST_CODE = 1342;
    private FirebaseAuth mFireBaseAuth;
    private FirebaseAuth.AuthStateListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFireBaseAuth = FirebaseAuth.getInstance();

        Hooks();

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailId.getText().toString();
                String pass = password.getText().toString();
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

                    mFireBaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful())
                            {
                                Toast.makeText(LoginActivity.this,"Sign Up unsuccesful,please try again",Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                                intent.putExtra("scoreForUpdate",0);
                                intent.putExtra("e-mail",email);
                                startActivity(intent);

                            }
                        }
                    });
                } else
                {
                    Toast.makeText(LoginActivity.this,"ERROR OCCURED",Toast.LENGTH_SHORT).show();
                }
            }
        });
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
    private void Hooks() {
        emailId = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        buttonSignUp = findViewById(R.id.signUpButton);
        tvSignUp = findViewById(R.id.tvSignUp);
    }
}
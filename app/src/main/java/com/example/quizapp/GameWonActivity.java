package com.example.quizapp;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.Arrays;

public class GameWonActivity extends AppCompatActivity {
    CircularProgressBar CPB;
    int maxvalue,correct,wrong;
    String email;
    TextView score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_won);
        Hooks();
        Bundle arguments = getIntent().getExtras();
        if(arguments!=null)
        {
            maxvalue = arguments.getInt("maxvalue",0);
            correct = arguments.getInt("CorrectCount",0);
            wrong = arguments.getInt("WrongCount",0);
            email = arguments.getString("e-mail");
        }
        CPB.setProgressMax(maxvalue);
        CPB.setProgress(correct);
        String string_score = correct +"/"+maxvalue;
        score.setText(string_score);
    }
    public void Hooks(){
        CPB = findViewById(R.id.circularProgressBar);
        score = findViewById(R.id.TVscore);
    }

    public void ExitFromEndGameActivity(View view) {
        Intent intent = new Intent(GameWonActivity.this,HomeActivity.class);
        intent.putExtra("e-mail",email);
        startActivity(intent);
    }
}
package com.example.quizapp;

import static com.example.quizapp.SplashActivity.lstOfQ;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class DashBoardActivityEasy extends AppCompatActivity {
    String[] names;
    String[] phones;
    CountDownTimer timer;
    int timeValue = 20;
    int index = 0 ;
    int CorrectCount=0;
    int WrongCount=0;
    TextView card_question,optionA,optionB;
    CardView CardOA,CardOB;
    ArrayList<ModelClass> allQuestionList;
    ModelClass modelclass;
    Button btnNext;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Hooks();

        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            email = arguments.getString("e-mail");
            Log.d("au", email);
        }

        allQuestionList = lstOfQ;
        Collections.shuffle(allQuestionList);
        modelclass = allQuestionList.get(index);
        CardOA.setBackgroundColor(getResources().getColor(R.color.white));
        CardOB.setBackgroundColor(getResources().getColor(R.color.white));
        btnNext.setClickable(false);

        setAllData();


    }

    private void setAllData() {
        card_question.setText(modelclass.getQuestion());
        optionA.setText(modelclass.getoA());
        optionB.setText(modelclass.getoB());
    }

    private void Hooks(){
        card_question = findViewById(R.id.TVcard_question);
        optionA = findViewById(R.id.TVcardop1);
        optionB = findViewById(R.id.TVcardop2);
        CardOA = findViewById(R.id.cardop1);
        CardOB = findViewById(R.id.cardop2);
        btnNext = findViewById(R.id.btnNext);

    }
    public void Correct(CardView cardview){
        cardview.setBackgroundColor(getResources().getColor(R.color.green));
        CorrectCount+=1;
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(index <lstOfQ.size()-1) {
                    index++;
                    modelclass = allQuestionList.get(index);
                    resetColor();
                    setAllData();
                    enableButton();
                    btnNext.setClickable(false);
                }else{
                    GameWon();
                }

            }
        });

    }
    public void Wrong(CardView cardOA){
        cardOA.setBackgroundColor(getResources().getColor(R.color.red));
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WrongCount+=1;
                if(index < lstOfQ.size()-1)
                {
                    index++;
                    modelclass = allQuestionList.get(index);
                    setAllData();
                    resetColor();
                    enableButton();
                    btnNext.setClickable(false);

                }else {
                    GameWon();
                }
            }
        });

    }

    private void GameWon() {
        Intent intent = new Intent(DashBoardActivityEasy.this,GameWonActivity.class);
        intent.putExtra("maxvalue",lstOfQ.size());
        intent.putExtra("CorrectCount",CorrectCount);
        intent.putExtra("WrongCount",WrongCount);
        intent.putExtra("e-mail",email);
        startActivity(intent);
    }

    public void enableButton(){
        CardOA.setClickable(true);
        CardOB.setClickable(true);
    }
    public void disableButton(){
        CardOA.setClickable(false);
        CardOB.setClickable(false);
    }
    public void resetColor(){
        CardOA.setBackgroundColor(getResources().getColor(R.color.white));
        CardOB.setBackgroundColor(getResources().getColor(R.color.white));
    }

    public void OptionBClick(View view) {
        disableButton();
        btnNext.setClickable(true);
        if(modelclass.getoB().equals(modelclass.getAns())) {
            CardOB.setBackgroundColor(getResources().getColor(R.color.green));
            if(index < lstOfQ.size()-1) {
                Correct(CardOB);
            }else{
                CorrectCount+=1;
                GameWon();
            }
        }else{
            Wrong(CardOB);
        }
    }

    public void OptionAClick(View view) {
        disableButton();
        btnNext.setClickable(true);
        if(modelclass.getoA().equals(modelclass.getAns())) {
            CardOA.setBackgroundColor(getResources().getColor(R.color.green));
            if(index < lstOfQ.size()) {
                Correct(CardOA);
            }else{
                CorrectCount+=1;
                GameWon();
            }
        }else{
            Wrong(CardOA);
        }
    }

    public void exitFromGame(View view) {
        Intent intent = new Intent(DashBoardActivityEasy.this,HomeActivity.class);
        intent.putExtra("e-mail",email);
        startActivity(intent);
    }
}
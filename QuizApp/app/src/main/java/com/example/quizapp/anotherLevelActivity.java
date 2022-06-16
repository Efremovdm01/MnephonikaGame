package com.example.quizapp;


import static com.example.quizapp.SplashActivity.lstOfQ;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class anotherLevelActivity extends AppCompatActivity {
    String email;
    CardView cardOA,cardOB,cardOC;
    int index = 0 ;
    int CorrectCount=0;
    int WrongCount=0;
    TextView card_questionAN,optionAAN,optionBAN,optionCAN;
    ArrayList<ModelClass> allQuestionListAN;
    ModelClass modelclass;
    Button btnNextAN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another_level);
        Hooks();
        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            email = arguments.getString("e-mail");
            Log.d("au", email);
        }
        cardOA.setBackgroundColor(getResources().getColor(R.color.white));
        cardOB.setBackgroundColor(getResources().getColor(R.color.white));
        cardOC.setBackgroundColor(getResources().getColor(R.color.white));
        allQuestionListAN = lstOfQ;
        Collections.shuffle(allQuestionListAN);
        modelclass = allQuestionListAN.get(index);
        Log.d("???",modelclass.getQuestion());
        btnNextAN.setClickable(false);
        setAllData();
    }
    private void setAllData() {
        card_questionAN.setText(modelclass.getQuestion());
        optionAAN.setText(modelclass.getoA().toString());
        optionBAN.setText(modelclass.getoB().toString());
        optionCAN.setText(modelclass.getoC().toString());
    }

    private void Hooks(){
        card_questionAN = findViewById(R.id.TVcard_questionANd);
        optionAAN = findViewById(R.id.TVcardop1AN);
        optionBAN = findViewById(R.id.TVcardop2AN);
        optionCAN = findViewById(R.id.TVcardop3AN);
        cardOA = findViewById(R.id.cardop1AN);
        cardOB = findViewById(R.id.cardop2AN);
        cardOC = findViewById(R.id.cardop3AN);

        btnNextAN = findViewById(R.id.btnNextAN);

    }
    public void Correct(CardView cardview){
        cardview.setBackgroundColor(getResources().getColor(R.color.green));
        CorrectCount+=1;
        btnNextAN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(index <lstOfQ.size()-1) {
                    index++;
                    modelclass = allQuestionListAN.get(index);
                    resetColor();
                    setAllData();
                    enableButton();
                    btnNextAN.setClickable(false);
                }else{
                    GameWon();
                }

            }
        });

    }
    public void Wrong(CardView cardOA){
        cardOA.setBackgroundColor(getResources().getColor(R.color.red));


        btnNextAN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WrongCount+=1;
                if(index < lstOfQ.size()-1)
                {
                    index++;
                    modelclass = allQuestionListAN.get(index);
                    setAllData();
                    resetColor();
                    enableButton();
                    btnNextAN.setClickable(false);

                }else {
                    GameWon();
                }
            }
        });

    }

    private void GameWon() {
        Intent intent = new Intent(anotherLevelActivity.this,GameWonActivity.class);
        intent.putExtra("maxvalue",lstOfQ.size());
        intent.putExtra("CorrectCount",CorrectCount);
        intent.putExtra("WrongCount",WrongCount);
        intent.putExtra("e-mail",email);
        startActivity(intent);
    }

    public void enableButton(){
        cardOA.setClickable(true);
        cardOB.setClickable(true);
        cardOC.setClickable(true);
    }
    public void disableButton(){
        cardOA.setClickable(false);
        cardOB.setClickable(false);
        cardOC.setClickable(false);
    }
    public void resetColor(){
        cardOA.setBackgroundColor(getResources().getColor(R.color.white));
        cardOB.setBackgroundColor(getResources().getColor(R.color.white));
        cardOC.setBackgroundColor(getResources().getColor(R.color.white));
    }

    public void OptionBClickAN(View view) {
        disableButton();
        btnNextAN.setClickable(true);
        if(modelclass.getoB().equals(modelclass.getAns()))
        {
            cardOB.setBackgroundColor(getResources().getColor(R.color.green));
            if(index < lstOfQ.size()-1) {
                Correct(cardOB);
            }else{
                CorrectCount+=1;
                GameWon();
            }
        }else{
            Wrong(cardOB);
        }
    }

    public void OptionAClickAN(View view) {
        disableButton();
        btnNextAN.setClickable(true);
        if(modelclass.getoA().equals(modelclass.getAns()))
        {
            cardOA.setBackgroundColor(getResources().getColor(R.color.green));
            if(index < lstOfQ.size()) {
                Correct(cardOA);
            }else{
                CorrectCount+=1;
                GameWon();
            }
        }else{
            Wrong(cardOA);
        }
    }

    public void OptionCClickAN(View view) {
        disableButton();
        btnNextAN.setClickable(true);
        if(modelclass.getoC().equals(modelclass.getAns()))
        {
            cardOC.setBackgroundColor(getResources().getColor(R.color.green));
            if(index < lstOfQ.size()) {
                Correct(cardOC);
            }else{
                CorrectCount+=1;
                GameWon();
            }
        }else{
            Wrong(cardOC);
        }
    }

    public void exitFromGameAN(View view) {

        Intent intent = new Intent(anotherLevelActivity.this,HomeActivity.class);
        intent.putExtra("e-mail",email);
        startActivity(intent);
    }

}
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

public class DashBoardActivityMedium extends AppCompatActivity{
    String[] namesM;
    String[] phonesM;
    int timeValue = 20;
    int indexM = 0 ;
    int CorrectCountM=0;
    int WrongCountM=0;
    TextView card_questionM,optionAM,optionBM,optionCM;
    CardView CardOAM,CardOBM,CardOCM;
    ArrayList<ModelClass> allQuestionListM;
    ModelClass modelclassM;
    Button btnNextM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        card_questionM = findViewById(R.id.TVcard_questionM);
        optionAM = findViewById(R.id.TVcardop1M);
        optionBM = findViewById(R.id.TVcardop2M);
        optionCM = (TextView) findViewById(R.id.TVcardop3M);
        CardOAM = (CardView) findViewById(R.id.cardop1M);
        CardOBM = (CardView)findViewById(R.id.cardop2M);
        CardOCM = (CardView)findViewById(R.id.cardop3M);
        if(CardOAM == null)
        {
            Log.d("NULL","NULL");
        }
        btnNextM = findViewById(R.id.btnNextM);
             HooksM();

            Log.d("A?","AAAAAAAAAAA");
             allQuestionListM = lstOfQ;
        Log.d("A?","AAAAAAAAAAA");
             Collections.shuffle(allQuestionListM);
        Log.d("A?","AAAAAAAAAAA");
             modelclassM = allQuestionListM.get(indexM);
        Log.d("A?","AAAAAAAAAAA");

        Log.d("A?","AAAAAAAAAAA");
        CardOAM.setBackgroundColor(getResources().getColor(R.color.black));
        CardOBM.setBackgroundColor(getResources().getColor(R.color.white));
        CardOCM.setBackgroundColor(getResources().getColor(R.color.white));
             btnNextM.setClickable(false);
        Log.d("A?","AAAAAAAAAAA");
             setAllDataM();
        Log.d("A?","AAAAAAAAAAA");

    }

    private void setAllDataM() {
        card_questionM.setText(modelclassM.getQuestion());
        optionAM.setText(modelclassM.getoA());
        optionBM.setText(modelclassM.getoB());
        optionCM.setText(modelclassM.getoC());
    }

    private void HooksM(){


    }
    public void CorrectM(CardView cardview){
        cardview.setBackgroundColor(getResources().getColor(R.color.green));
        CorrectCountM+=1;
        btnNextM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(indexM <lstOfQ.size()-1) {
                    indexM++;
                    modelclassM = allQuestionListM.get(indexM);
                    resetColorM();
                    setAllDataM();
                    enableButtonM();
                    btnNextM.setClickable(false);
                }else{
                    GameWonM();
                }

            }
        });

    }
    public void WrongM(CardView cardOA){

        cardOA.setBackgroundColor(getResources().getColor(R.color.red));


        btnNextM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WrongCountM+=1;
                if(indexM < lstOfQ.size()-1)
                {
                    indexM++;
                    modelclassM = allQuestionListM.get(indexM);
                    setAllDataM();
                    resetColorM();
                    enableButtonM();
                    btnNextM.setClickable(false);

                }else {
                    GameWonM();
                }
            }
        });

    }

    private void GameWonM() {
        Intent intent = new Intent(DashBoardActivityMedium.this,GameWonActivity.class);
        intent.putExtra("maxvalue",lstOfQ.size());
        intent.putExtra("CorrectCount",CorrectCountM);
        intent.putExtra("WrongCount",WrongCountM);
        startActivity(intent);
    }

    public void enableButtonM(){
        CardOAM.setClickable(true);
        CardOBM.setClickable(true);
        CardOCM.setClickable(true);
    }
    public void disableButtonM(){
        CardOAM.setClickable(false);
        CardOBM.setClickable(false);
        CardOCM.setClickable(false);
    }
    public void resetColorM(){
        CardOAM.setBackgroundColor(getResources().getColor(R.color.white));
        CardOBM.setBackgroundColor(getResources().getColor(R.color.white));
        CardOCM.setBackgroundColor(getResources().getColor(R.color.white));
    }

    public void OptionBClickM(View view) {
        disableButtonM();
        btnNextM.setClickable(true);
        if(modelclassM.getoB().equals(modelclassM.getAns()))
        {
            CardOBM.setBackgroundColor(getResources().getColor(R.color.green));
            if(indexM < lstOfQ.size()-1) {
                CorrectM(CardOBM);
            }else{
                CorrectCountM+=1;
                GameWonM();
            }
        }else{
            WrongM(CardOBM);
        }
    }

    public void OptionAClickM(View view) {
        disableButtonM();
        btnNextM.setClickable(true);
        if(modelclassM.getoA().equals(modelclassM.getAns()))
        {
            CardOAM.setBackgroundColor(getResources().getColor(R.color.green));
            if(indexM < lstOfQ.size()) {
                CorrectM(CardOAM);
            }else{
                CorrectCountM+=1;
                GameWonM();
            }
        }else{
            WrongM(CardOAM);
        }
    }

    public void exitFromGameM(View view) {
        Intent intent = new Intent(DashBoardActivityMedium.this,HomeActivity.class);
        startActivity(intent);
    }

    public void OptionCClickM(View view) {
        disableButtonM();
        btnNextM.setClickable(true);
        if(modelclassM.getoC().equals(modelclassM.getAns()))
        {
            CardOCM.setBackgroundColor(getResources().getColor(R.color.green));
            if(indexM < lstOfQ.size()) {
                CorrectM(CardOCM);
            }else{
                CorrectCountM+=1;
                GameWonM();
            }
        }else{
            WrongM(CardOCM);
        }
    }
}
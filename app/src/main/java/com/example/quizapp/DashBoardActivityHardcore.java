package com.example.quizapp;

import static com.example.quizapp.SplashActivity.lstOfQ;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;

public class DashBoardActivityHardcore extends AppCompatActivity {
    CountDownTimer countDownTimer;
    int timerValue = 20;
    ProgressBar progressBar;
    String email;
    CardView cardOA,cardOB,cardOC;
    int index = 0 ;
    int CorrectCount=0;
    int WrongCount=0;
    TextView card_questionH,optionAH,optionBH,optionCH;
    ArrayList<ModelClass> allQuestionListH;
    ModelClass modelclass;
    Button btnNextH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board_hardcore);

        Hooks();

        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            email = arguments.getString("e-mail");
            Log.d("au", email);
        }
        allQuestionListH = lstOfQ;
        Collections.shuffle(allQuestionListH);
        modelclass = allQuestionListH.get(index);

       // Log.d("???",modelclass.getQuestion());
        btnNextH.setClickable(false);

        setAllData();

        if(countDownTimer!=null){
            countDownTimer.cancel();
        }
        countDownTimer = new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long l) {
                timerValue-=2;
                progressBar.setProgress(timerValue);
            }

            @Override
            public void onFinish() {

                Dialog dialog1 = new Dialog(DashBoardActivityHardcore.this,R.style.Dialoge);
                Window window = dialog1.getWindow();

                window.setGravity(Gravity.CENTER);
                window.setLayout(WindowManager.LayoutParams.FILL_PARENT,
                        WindowManager.LayoutParams.FILL_PARENT);
                window.setBackgroundDrawable(new ColorDrawable());
                dialog1.setTitle(null);
                dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
                window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
                dialog1.setContentView(R.layout.time_is_out);

                dialog1.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                dialog1.show();
                Button btn = (Button)dialog1.findViewById(R.id.btnTryAgain);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(DashBoardActivityHardcore.this,HomeActivity.class);
                        i.putExtra("e-mail",email);
                        startActivity(i);
                    }
                });
            }
        }.start();
    }

    private void setAllData() {
        progressBar.setProgress(timerValue);
        card_questionH.setText(modelclass.getQuestion());
        optionAH.setText(modelclass.getoA().toString());
        optionBH.setText(modelclass.getoB().toString());
        optionCH.setText(modelclass.getoC().toString());
    }
    private void Hooks(){
        card_questionH = findViewById(R.id.TVcard_questionH);
        optionAH = findViewById(R.id.TVcardop1H);
        optionBH = findViewById(R.id.TVcardop2H);
        optionCH = findViewById(R.id.TVcardop3H);
        cardOA = findViewById(R.id.cardop1H);
        cardOB = findViewById(R.id.cardop2H);
        cardOC = findViewById(R.id.cardop3H);
        progressBar = findViewById(R.id.quiz_timer);
        btnNextH = findViewById(R.id.btnNextH);
    }
    public void Correct(CardView cardview){
        cardview.setBackgroundColor(getResources().getColor(R.color.green));
        CorrectCount+=1;
        btnNextH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(index <lstOfQ.size()-1) {
                    index++;
                    if(countDownTimer!=null){
                        countDownTimer.cancel();
                    }
                    modelclass = allQuestionListH.get(index);
                    resetColor();
                    setAllData();
                    enableButton();
                    timerValue=20;
                    countDownTimer = new CountDownTimer(10000,1000) {

                        @Override
                        public void onTick(long l) {
                            timerValue-=2;
                            progressBar.setProgress(timerValue);
                        }

                        @Override
                        public void onFinish() {

                            Dialog dialog1 = new Dialog(DashBoardActivityHardcore.this,R.style.Dialoge);
                            Window window = dialog1.getWindow();

                            window.setGravity(Gravity.CENTER);
                            window.setLayout(WindowManager.LayoutParams.FILL_PARENT,
                                    WindowManager.LayoutParams.FILL_PARENT);
                            window.setBackgroundDrawable(new ColorDrawable());
                            dialog1.setTitle(null);
                            dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
                            dialog1.setContentView(R.layout.time_is_out);

                            dialog1.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                            dialog1.show();
                            Button btn = (Button)dialog1.findViewById(R.id.btnTryAgain);
                            btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent i = new Intent(DashBoardActivityHardcore.this,HomeActivity.class);
                                    i.putExtra("e-mail",email);
                                    startActivity(i);
                                }
                            });
                        }
                    };
                    countDownTimer.start();
                    btnNextH.setClickable(false);
                }else{
                    GameWon();
                }

            }
        });

    }
    public void Wrong(CardView cardOA){
        cardOA.setBackgroundColor(getResources().getColor(R.color.red));


        btnNextH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WrongCount+=1;
                if(index < lstOfQ.size()-1) {
                    index++;
                    modelclass = allQuestionListH.get(index);
                    setAllData();
                    resetColor();
                    enableButton();
                    btnNextH.setClickable(false);

                }else {
                    GameWon();
                }
            }
        });

    }

    private void GameWon() {
        Intent intent = new Intent(DashBoardActivityHardcore.this,GameWonActivity.class);
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

    public void OptionAClickH(View view) {
        disableButton();
        btnNextH.setClickable(true);
        if(modelclass.getoA().equals(modelclass.getAns())) {
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

    public void OptionBClickH(View view) {
        disableButton();
        btnNextH.setClickable(true);
        if(modelclass.getoB().equals(modelclass.getAns())) {
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

    public void OptionCClickH(View view) {
        disableButton();
        btnNextH.setClickable(true);
        if(modelclass.getoC().equals(modelclass.getAns())) {
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
        Intent intent = new Intent(DashBoardActivityHardcore.this,HomeActivity.class);
        intent.putExtra("e-mail",email);
        startActivity(intent);
    }

}


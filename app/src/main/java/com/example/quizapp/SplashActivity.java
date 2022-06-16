package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity {
    public static ArrayList<ModelClass> lstOfQ;
    String[] names;
    String[] phones;
    String level;
    String email;
    String easy = "easy";
    String medium = "medium";
    String hard = "hard";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        lstOfQ = new ArrayList<>();
        Bundle arguments = getIntent().getExtras();
        if (arguments != null){
            names = arguments.getStringArray("names");
            phones = arguments.getStringArray("phones");
            level = arguments.getString("level");
            email = arguments.getString("e-mail");
        }

        Intent intent;

        switch (level) {
            case "easy":
                SplashForEasy();
                intent = new Intent(SplashActivity.this, DashBoardActivityEasy.class);
                intent.putExtra("e-mail",email);
                startActivity(intent);
                break;
            case "medium":
                SplashForMedium();
                Log.d("au", "medium");
                intent = new Intent(SplashActivity.this, DashBoardActivityMedium.class);
                intent.putExtra("e-mail",email);
                startActivity(intent);
                break;
            case "hard":
                SplashForMedium();
                intent = new Intent(SplashActivity.this, DashBoardActivityHardcore.class);
                intent.putExtra("e-mail",email);
                startActivity(intent);
                break;
        }
    }

    public void SplashForEasy() {
        //String modelclas;
        Log.d("au", level);
        for (int i = 0; i < names.length; ++i) {
            int n = (int) Math.floor(Math.random() * names.length);
            if (i % 2 == 0) {
                if (n == i) {
                    while (n == i) {
                        n = (int) Math.floor(Math.random() * names.length);
                    }
                }
                //modelclas = phones[i] + "/" + names[i] + "/" + names[n] + "/" + names[i];
                lstOfQ.add(new ModelClass(phones[i], names[i], names[n], names[i]));


            } else {
                if (i % 2 == 1) {
                    if (n == i) {
                        while (n == i) {
                            n = (int) Math.floor(Math.random() * names.length);
                        }
                    }
                    //modelclas = phones[i] + "/" + names[n] + "/" + names[i] + "/" + names[i];
                    lstOfQ.add(new ModelClass(phones[i], names[n], names[i], names[i]));
                }
            }
        }
    }

    public void SplashForMedium() {
        //String modelclas;
        int n = (int) Math.floor(Math.random() * names.length);
        int m = (int) Math.floor(Math.random() * names.length);
        for (int i = 0; i < names.length; ++i) {
            if (i % 3 == 0) {
                if (n == i) {
                    while (n == i) {
                        n = (int) Math.floor(Math.random() * names.length);
                    }
                    for (int j = 0; j < names.length; ++j) {
                        if ((j != i) & (j != n)) {
                            //modelclas = phones[i] + "/" + names[i] + "/" + names[n] + "/" + names[j] + "/" + names[i];
                            lstOfQ.add(new ModelClass(phones[i], names[i], names[n], names[j], names[i]));
                        }
                    }

                } else {
                    for (int j = 0; j < names.length; ++j) {
                        if ((j != i) & (j != n)) {
                            //modelclas = phones[i] + "/" + names[n] + "/" + names[i] + "/" + names[j] + "/" + names[i];
                            lstOfQ.add(new ModelClass(phones[i], names[n], names[i], names[j], names[i]));
                        }
                    }
                }
            } else{
                if (i % 3 == 1) {
                    if (n == i) {
                        while (n == i) {
                            n = (int) Math.floor(Math.random() * names.length);
                        }
                        for (int j = 0; j < names.length; ++j) {
                            if ((j != i) & (j != n)) {
                                //modelclas = phones[i] + "/" + names[n] + "/" + names[i] + "/" + names[j] + "/" + names[i];
                                lstOfQ.add(new ModelClass(phones[i], names[n], names[i], names[j], names[i]));
                            }
                        }
                    } else {
                        for (int j = 0; j < names.length; ++j) {
                            if ((j != i) & (j != n)) {
                                //modelclas = phones[i] + "/" + names[n] + "/" + names[i] + "/" + names[j] + "/" + names[i];
                                lstOfQ.add(new ModelClass(phones[i], names[n], names[i], names[j], names[i]));
                            }
                        }
                    }
                } else {
                    if (i % 3 == 2) {
                        if (n == i) {
                            while (n == i) {
                                n = (int) Math.floor(Math.random() * names.length);
                            }
                            for (int j = 0; j < names.length; ++j) {
                                if ((j != i) & (j != n)) {
                                    //modelclas = phones[i] + "/" + names[n] + "/" + names[j] + "/" + names[i] + "/" + names[i];
                                    lstOfQ.add(new ModelClass(phones[i], names[n], names[j], names[i], names[i]));
                                }
                            }

                        } else {
                            for (int j = 0; j < names.length; ++j) {
                                if ((j != i) & (j != n)) {
                                    //modelclas = phones[i] + "/" + names[n] + "/" + names[j] + "/" + names[i] + "/" + names[i];
                                    lstOfQ.add(new ModelClass(phones[i], names[n], names[j], names[i], names[i]));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
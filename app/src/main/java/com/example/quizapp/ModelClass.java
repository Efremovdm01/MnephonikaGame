package com.example.quizapp;

public class ModelClass {
    String Question;
    String oA;
    String oB;
    String oC;
    String oD;
    String ans;

    public ModelClass(String question, String oA, String oB, String ans) {
        Question = question;
        this.oA = oA;
        this.oB = oB;
        this.ans = ans;
    }
    public ModelClass(String question, String oA, String oB,String oC, String ans) {
        Question = question;
        this.oA = oA;
        this.oB = oB;
        this.oC = oC;
        this.ans = ans;
    }

    public ModelClass(String question, String oA, String oB,String oC,String oD, String ans) {
        Question = question;
        this.oA = oA;
        this.oB = oB;
        this.oC = oC;
        this.oD = oD;
        this.ans = ans;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getoA() {
        return oA;
    }

    public void setoA(String oA) {
        this.oA = oA;
    }

    public String getoB() {
        return oB;
    }

    public void setoB(String oB) {
        this.oB = oB;
    }

    public String getoC() {
        return oC;
    }

    public void setoC(String oC) {
        this.oC = oC;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }
}

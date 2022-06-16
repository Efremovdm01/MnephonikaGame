package com.example.quizapp;

public class Contact {
    private int Id;
    private String Name,Phonenumber;

    public Contact() {
    }

    public Contact(int id, String name, String phonenumber) {
        Id = id;
        Name = name;
        Phonenumber = phonenumber;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getPhonenumber() {
        return Phonenumber;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPhonenumber(String phonenumber) {
        Phonenumber = phonenumber;
    }
}

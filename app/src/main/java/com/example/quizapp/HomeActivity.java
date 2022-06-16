package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    EditText edtId,edtName,edtPhoneNumber;
    Button btnPlay,btnLogOut,btnAdd,btnDelete,btnRefresh;
    Spinner spinner;
    DataBaseHelper db;

    ListView lstContacts;
    List<Contact> data = new ArrayList<>();
    List<String> lstNames = new ArrayList<String>();
    List<String> lstPhones = new ArrayList<String>();

    TextView realusername,scoreNumber;
    int scorenumber = 0;
    String email;
    String chosenlevel;

    private FirebaseAuth mFireBaseAuth;
    private FirebaseAuth.AuthStateListener listener;
    private String[] levels = { "easy", "medium", "hard" };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        db = new DataBaseHelper(this);

        Hooks();

        Bundle arguments = getIntent().getExtras();
        if(arguments!=null)
        {
            email = arguments.getString("e-mail");
        }

        String string_score = scorenumber+"";
        realusername.setText(email);

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, levels);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        refreshData();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = db.getAllContacts().size();
                id +=1;
                if (edtName.getText().toString().isEmpty() | edtPhoneNumber.getText().toString().isEmpty()) {
                    Toast.makeText(HomeActivity.this,"Fill all params",Toast.LENGTH_SHORT).show();
                } else {
                    Contact contact = new Contact(id,edtName.getText().toString(),edtPhoneNumber.getText().toString());
                    db.addContact(contact);
                    refreshData();
                    edtId.setText("");
                    edtName.setText("");
                    edtPhoneNumber.setText("");
                }
            }
        });

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtId.getText().toString().isEmpty() | edtName.getText().toString().isEmpty() | edtPhoneNumber.getText().toString().isEmpty()) {
                    Toast.makeText(HomeActivity.this,"Fill all params",Toast.LENGTH_SHORT).show();
                } else {
                    Contact contact = new Contact(Integer.parseInt(edtId.getText().toString()), edtName.getText().toString(), edtPhoneNumber.getText().toString());
                    db.updateContact(contact);
                    refreshData();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtId.getText().toString().isEmpty() | edtName.getText().toString().isEmpty() | edtPhoneNumber.getText().toString().isEmpty()) {
                    Toast.makeText(HomeActivity.this,"Fill all params",Toast.LENGTH_SHORT).show();
                } else {
                    Contact contact = new Contact(Integer.parseInt(edtId.getText().toString()),edtName.getText().toString(),edtPhoneNumber.getText().toString());
                    db.deleteContact(contact);
                    refreshData();
                    edtId.setText("");
                    edtName.setText("");
                    edtPhoneNumber.setText("");
                }
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = db.getAllContacts();

                String message = data.toString();
                chosenlevel = spinner.getSelectedItem().toString();
                if(!chosenlevel.equals("easy")){
                    if(db.getAllContacts().size() <= 2){
                        Toast.makeText(HomeActivity.this,"It is imposible to start,please add more contacts",Toast.LENGTH_SHORT).show();
                    }else{
                        //Log.d("levelb", chosenlevel);
                        //Log.d("this is my array", "arr: " + data.toString());
                        for (int i = 0; i < lstContacts.getAdapter().getCount(); ++i) {

                            Contact contact = (Contact) lstContacts.getItemAtPosition(i);
                            String name = contact.getName().toString();
                            String phoneNumber = contact.getPhonenumber().toString();
                            lstNames.add(name);
                            lstPhones.add(phoneNumber);
                            //Log.d("name?", name);
                            //Log.d("phone?", phoneNumber);
                        }
                        String names[] = new String[lstNames.size()];
                        lstNames.toArray(names);
                        String phones[] = new String[lstPhones.size()];
                        lstPhones.toArray(phones);

                        Bundle b = new Bundle();
                        Bundle d = new Bundle();
                        Bundle level = new Bundle();
                        Bundle em = new Bundle();
                        d.putStringArray("phones", phones);
                        b.putStringArray("names", names);
                        level.putString("level", chosenlevel);
                        em.putString("e-mail", email);
                        Intent intent = new Intent(HomeActivity.this, SplashActivity.class);
                        intent.putExtras(b);
                        intent.putExtras(d);
                        intent.putExtras(level);
                        intent.putExtras(em);
                        startActivity(intent);
                    }
                }else{
                    if(!(lstContacts.getAdapter().getCount() <2)){
                       // Log.d("levelb", chosenlevel);
                        //Log.d("this is my array", "arr: " + data.toString());
                        for (int i = 0; i < lstContacts.getAdapter().getCount(); ++i) {

                            Contact contact = (Contact) lstContacts.getItemAtPosition(i);
                            String name = contact.getName().toString();
                            String phoneNumber = contact.getPhonenumber().toString();
                            lstNames.add(name);
                            lstPhones.add(phoneNumber);
                            //Log.d("name?", name);
                           // Log.d("phone?", phoneNumber);
                        }
                        String names[] = new String[lstNames.size()];
                        lstNames.toArray(names);
                        String phones[] = new String[lstPhones.size()];
                        lstPhones.toArray(phones);

                        Bundle b = new Bundle();
                        Bundle d = new Bundle();
                        Bundle level = new Bundle();
                        Bundle em = new Bundle();
                        d.putStringArray("phones", phones);
                        b.putStringArray("names", names);
                        level.putString("level", chosenlevel);
                        em.putString("e-mail", email);
                        Intent intent = new Intent(HomeActivity.this, SplashActivity.class);
                        intent.putExtras(b);
                        intent.putExtras(d);
                        intent.putExtras(level);
                        intent.putExtras(em);
                        startActivity(intent);
                    }else{
                        Toast.makeText(HomeActivity.this,"It is imposible to start,please add more contacts",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(HomeActivity.this,MainActivity.class);
                startActivity(i);
            }
        });

    }



    private void refreshData()
    {
        data = db.getAllContacts();
        ContactAdapter adapter = new ContactAdapter(HomeActivity.this,data,edtId,edtName,edtPhoneNumber);
        lstContacts.setAdapter(adapter);
    }
    private void Hooks(){
        edtName = (EditText)findViewById(R.id.editname);
        edtPhoneNumber = (EditText) findViewById(R.id.editphone);
        edtId = (EditText)findViewById(R.id.editId);
        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnDelete = (Button)findViewById(R.id.btnDelete);
        btnRefresh = (Button)findViewById(R.id.btnUpdate);
        lstContacts = (ListView) findViewById(R.id.lstContacts);
        btnLogOut = (Button)findViewById(R.id.btnLogOut);
        btnPlay = (Button)findViewById(R.id.btnplay);
        spinner = (Spinner)findViewById(R.id.spinner);
        realusername = (TextView)findViewById(R.id.txtViewUserRealName);
    }
}
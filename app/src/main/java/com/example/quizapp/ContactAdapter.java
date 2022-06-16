package com.example.quizapp;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import java.util.List;
import java.util.zip.Inflater;

public class ContactAdapter extends BaseAdapter {
    Activity activity;
    List<Contact> lstContacts;
    LayoutInflater inflater;
    EditText edtId,edtName, edtPhoneNumber;

    public ContactAdapter() {
    }

    public ContactAdapter(Activity activity, List<Contact> lstContacts, EditText edtId, EditText edtName, EditText edtPhoneNumber) {
        this.activity = activity;
        this.lstContacts = lstContacts;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.edtId = edtId;
        this.edtName = edtName;
        this.edtPhoneNumber = edtPhoneNumber;

    }


    @Override
    public int getCount() {
        return lstContacts.size();
    }

    @Override
    public Object getItem(int i) {
        return lstContacts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return lstContacts.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView;
        rowView = inflater.inflate(R.layout.row,null);
        final TextView txtRowId,txtName,txtPhoneNumber;

        txtRowId = (TextView)rowView.findViewById(R.id.txtRowId);
        txtPhoneNumber = (TextView)rowView.findViewById(R.id.txtViewUserPhoneNumberRow);
        txtName = (TextView)rowView.findViewById(R.id.txtViewUserNameRow);

        txtRowId.setText(""+lstContacts.get(i).getId());
        txtName.setText(""+lstContacts.get(i).getName());
        txtPhoneNumber.setText(""+lstContacts.get(i).getPhonenumber());

        String message = txtRowId.getText().toString();
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtId.setText(""+txtRowId.getText());
                edtName.setText(""+txtName.getText());
                edtPhoneNumber.setText(""+txtPhoneNumber.getText());
            }
        });
        return rowView;
    }
}

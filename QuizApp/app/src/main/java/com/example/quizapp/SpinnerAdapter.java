package com.example.quizapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class SpinnerAdapter extends ArrayAdapter<String> {
    private Context context;
    private int textViewResourceId;
    private String[] objects;
    public static boolean flag = false;
    public SpinnerAdapter(Context context, int textViewResourceId,
                         String[] objects) {
        super(context, textViewResourceId, objects);
        this.context = context;
        this.textViewResourceId = textViewResourceId;
        this.objects = objects;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = View.inflate(context, textViewResourceId, null);
        if (flag != false) {
            TextView tv = (TextView) convertView;
            tv.setText(objects[position]);
        }
        return convertView;
    }
}

package com.example.quizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "FOR_ANDRDSTD_MEMORY_TRAINING_GAME";


    private static final String TABLE_NAME = "Contacts";
    private static final String Key_Id = "Id";
    private static final String Key_Name = "Name";
    private static final String Key_PhoneNumber = "PhoneNumber";

    public DataBaseHelper(@Nullable Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME + "(" +
                Key_Id + " INTEGER PRIMARY KEY," + Key_Name + " TEXT," +
                Key_PhoneNumber +" TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addContact(Contact contact)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Key_Name, contact.getName());
        values.put(Key_PhoneNumber, contact.getPhonenumber());

        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public int updateContact(Contact contact)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Key_Name, contact.getName());
        values.put(Key_PhoneNumber, contact.getPhonenumber());

        return db.update(TABLE_NAME,values, Key_Id + " =?",new String[]{String.valueOf(contact.getId())});
    }

    public void deleteContact(Contact contact)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,Key_Id + " =?",new String[]{String.valueOf(contact.getId())});
        db.close();
    }

    public Contact getContact(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{Key_Id,Key_Name,Key_PhoneNumber},Key_Id+ " =?",
                new String[]{String.valueOf(id)},null,null,null,null);
        if(cursor != null)
            cursor.moveToFirst();
        return new Contact(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
    }

    public List<Contact> getAllContacts() {
        List<Contact> lstContacts = new ArrayList<>();
        String selectQuerry = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuerry, null);
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setId(cursor.getInt(0));
                contact.setName(cursor.getString(1));
                contact.setPhonenumber(cursor.getString(2));

                lstContacts.add(contact);
            }
            while (cursor.moveToNext());

        }
        return lstContacts;
    }
}

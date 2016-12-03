package com.example.asheransari.habittrack;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asheransari.habittrack.database_material.*;

public class login extends AppCompatActivity {

    habitDbHelper mHabitDbHelper;
    EditText user_psk, user_name;
    TextView signup;

    Button btn_uName_psk;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mHabitDbHelper = new habitDbHelper(this);
        user_name = (EditText) findViewById(R.id.edit_Text_user);
        user_psk = (EditText) findViewById(R.id.edit_Text_psk);
        signup = (TextView) findViewById(R.id.signup);
        btn_uName_psk = (Button) findViewById(R.id.fab);
//        mFloatingActionButton = (FloatingActionButton)findViewById(R.id.float_new);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(login.this, sign_up.class);
                startActivity(i);
            }
        });


        btn_uName_psk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String getData_user = user_psk.getText().toString();
                String getData_psk = user_psk.getText().toString();
//                boolean check = checkUserName(getData_user);
//                        user_psk.setInputType( InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD );
//                        user_psk.setTransformationMethod(PasswordTransformationMethod.getInstance());
//                        user_psk.setText("");

                String checkPsk = getSinlgeEntry(getData_user);

                if (getData_psk.equals(checkPsk))
                {
                    insertCurrent();
                }
                else
                {
                    Toast.makeText(login.this, "User Name and Password Did Not Match", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public String getSinlgeEntry(String userName) {

        SQLiteDatabase db = mHabitDbHelper.getReadableDatabase();
        String a,b;
        String query = "SELECT uname,psk FROM "+loginContract.TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        b="NOT EXISTS";
        if (cursor.moveToFirst())
        {
            do {
                a = cursor.getString(0);
                b = cursor.getString(1);
                if (a.equals(userName))
                {
                    b = cursor.getString(1);
                    break;
                }
            }
            while(cursor.moveToNext());
        }
        db.close();
        return b;
    }


    private String nameGet(String userName, String psk)
    {
        SQLiteDatabase db = mHabitDbHelper.getReadableDatabase();
        String a,b;
        String c = "User Name Not Found",d = "example@example.com";
        String query = "SELECT name,email,uname,psk FROM "+loginContract.TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        b="NOT EXISTS";
        if (cursor.moveToFirst())
        {
            do {
                a = cursor.getString(2);
                b = cursor.getString(3);
                if (a.equals(userName)&& psk.equals(b))
                {
                    c = cursor.getString(0);
                    d = cursor.getString(1);
                    break;
                }
            }
            while(cursor.moveToNext());
        }
        db.close();
        return c+","+d;
    }
//    name,email,uname,psk
    public void insertCurrent()
    {
        String uname, psk;
        uname = user_name.getText().toString();
        psk = user_psk.getText().toString();
        String temp = null;

        temp = nameGet(uname,psk);
        String[] data = temp.split(",");

        SQLiteDatabase db = mHabitDbHelper.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(currentContract.COLUMN_CURRENT_NAME,data[0]);
        v.put(currentContract.COLUMN_CURRENT_EMAIL,data[0]);
        v.put(currentContract.COLUMN_CURRENT_UNAME,uname);
        v.put(currentContract.COLUMN_CURRENT_PSK,psk);
        db.insert(currentContract.TABLE_NAME, null, v);
        Intent i = new Intent(login.this,MainActivity.class);
        i.putExtra("uniqueID","login_activity");
        i.putExtra("uname",uname);
        i.putExtra("psk",psk);
        i.putExtra("email",data[1]);
        i.putExtra("name",data[0]);
        SQLiteDatabase sqLiteDatabase = mHabitDbHelper.getReadableDatabase();
        String Name =null,UName = null,email=null;

//        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+ currentContract.TABLE_NAME,null);
//        if (cursor!=null|| cursor.getCount()>1)
//        {
//            int nameIndex = cursor.getColumnIndex(currentContract.COLUMN_CURRENT_NAME);
//            int unameIndex = cursor.getColumnIndex(currentContract.COLUMN_CURRENT_UNAME);
//            int emailIndex = cursor.getColumnIndex(currentContract.COLUMN_CURRENT_EMAIL);
//            while(cursor.moveToNext())
//            {
//                Name = cursor.getString(nameIndex);
//                UName = cursor.getString(unameIndex);
//                email = cursor.getString(emailIndex);
//            }
//
//        }

//        i.putExtra("NAME",Name);
//        i.putExtra("UNAME",UName);
//        i.putExtra("EMAIL",email);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        db.close();
        startActivity(i);



    }

    private boolean checkUserName(String uName) {
        boolean check = false;

        SQLiteDatabase db = mHabitDbHelper.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT COUNT(uname) FROM " + loginContract.TABLE_NAME + " WHERE " + loginContract.COLUMN_UNAME_LOGIN + "='" + uName + "'", null);

//        Toast.makeText(login.this, "Cursor = "+c.getCount()+" - "+c.getString(0), Toast.LENGTH_SHORT).show();

        if (c.getCount() == 1) {
            check = true;
        } else {
            check = false;
        }

        return check;
    }

    private int count() {
        SQLiteDatabase sqLiteDatabase = mHabitDbHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + loginContract.TABLE_NAME, null);

        int tempCOunt = cursor.getCount();

//        Toast.makeText(login.this,"TEMO COUNT = "+String.valueOf(tempCOunt),Toast.LENGTH_SHORT).show();

        return tempCOunt;
    }

    private boolean userValidity(String uName) {
        boolean result = false;
        mHabitDbHelper = new habitDbHelper(this);

        SQLiteDatabase db = mHabitDbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT COUNT(" + loginContract.COLUMN_UNAME_LOGIN + ") FROM " + loginContract.TABLE_NAME + " WHERE " + loginContract.COLUMN_UNAME_LOGIN + " ='" + uName + "'", null);
        int count = cursor.getInt(0);
        Toast.makeText(login.this, "count = " + String.valueOf(count), Toast.LENGTH_SHORT).show();
        return result;
    }

    @Override
    public void onStart() {
        super.onStart();
     }

    @Override
    public void onStop() {
        super.onStop();
    }
}

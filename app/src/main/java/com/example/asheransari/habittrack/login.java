package com.example.asheransari.habittrack;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asheransari.habittrack.database_material.*;

public class login extends AppCompatActivity {

    habitDbHelper mHabitDbHelper;
    EditText user_psk;
    TextView signup;
//    FloatingActionButton mFloatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        mHabitDbHelper = new habitDbHelper(this);
//        user_psk = (EditText)findViewById(R.id.edit_Text_user_psk);
//        signup = (TextView)findViewById(R.id.signup);
//        mFloatingActionButton = (FloatingActionButton)findViewById(R.id.float_new);

//        signup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(login.this,sign_up.class);
//                startActivity(i);
//            }
//        });


//        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String uName = user_psk.getText().toString();
//
//                userValidity(uName);
//            }
//        });


    }


    private int count()
    {
        SQLiteDatabase sqLiteDatabase = mHabitDbHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+loginContract.TABLE_NAME,null);

        int tempCOunt = cursor.getCount();

//        Toast.makeText(login.this,"TEMO COUNT = "+String.valueOf(tempCOunt),Toast.LENGTH_SHORT).show();

        return tempCOunt;
    }

    private boolean userValidity(String uName)
    {
        boolean result = false;
        mHabitDbHelper = new habitDbHelper(this);

        SQLiteDatabase db = mHabitDbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT COUNT("+loginContract.COLUMN_UNAME_LOGIN+") FROM "+loginContract.TABLE_NAME+" WHERE "+loginContract.COLUMN_UNAME_LOGIN+" ='"+uName+"'",null);
        int count = cursor.getInt(0);
        Toast.makeText(login.this, "count = "+String.valueOf(count), Toast.LENGTH_SHORT).show();
        return result;
    }
}

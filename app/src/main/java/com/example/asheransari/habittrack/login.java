package com.example.asheransari.habittrack;

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
                    Toast.makeText(login.this, "Password Match", Toast.LENGTH_SHORT). show();
                }
                else
                {
                    Toast.makeText(login.this, "User Name and Password Did Not Match", Toast.LENGTH_SHORT).show();
                }

//                if (check.equals("NOT EXIST"))
//                {
//                    Toast.makeText(login.this, "Not Login", Toast.LENGTH_SHORT).show();
//                }
//                else if (check.equals("EXISTS"))
//                {
//                    Toast.makeText(login.this, "login Sucess", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    Toast.makeText(login.this, "check = " + check, Toast.LENGTH_SHORT).show();
//                }
            }
        });

    }

    public String getSinlgeEntry(String userName) {
        SQLiteDatabase db = mHabitDbHelper.getReadableDatabase();
//        Cursor cursor = db.query(loginContract.TABLE_NAME, null, "uname=?",
//                new String[] { userName }, null, null, null);
//        int count = cursor.getCount();
//        Cursor cursor = db.rawQuery("SELECT COUNT(uname) FROM login WHERE uname=? AND psk=?",new String[]{userName,psk});


//        Toast.makeText(login.this, ""+count, Toast.LENGTH_SHORT).show();

//        boolean check = checkUserName(userName);
////                Toast.makeText(login.this,"check = "+check,Toast.LENGTH_SHORT).show();
//        if (check == true) {
//            if (cursor.getCount() == 1) {
//                return "EXISTS";
//            } else {
//                return "NOT EXIST";
//            }
//        }
//        else
//        {
//            Toast.makeText(login.this, "User Name Not Exists", Toast.LENGTH_SHORT).show();
//            return "NOT EXIST";
//        }


//        Toast.makeText(login.this, ""+cursor.getString(cursor.getColumnIndex("psk")), Toast.LENGTH_SHORT).show();
//        if (cursor.getCount() < 1) {
//            cursor.close();
//            return "NOT EXIST";
//        }
//            cursor.moveToFirst();
//            String password = cursor.getString(cursor.getColumnIndex("psk"));
//            cursor.close();
//            Toast.makeText(login.this, "" + password, Toast.LENGTH_SHORT).show();
//            return password;

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
        return b;
    }

//    private String checkInDataBase(String name, String psk) {
//
//        SQLiteDatabase db = mHabitDbHelper.getReadableDatabase();
////        Cursor cu = null;
////        try{
////            cu = db.rawQuery("SELECT "+loginContract.COLUMN_UNAME_LOGIN+" FROM "+loginContract.TABLE_NAME+" WHERE "+loginContract.COLUMN_UNAME_LOGIN+"=? AND "+loginContract.COLUMN_PSK_LOGIN+"='"+psk+"';",new String[]{name,psk});
////            if (cu!= null) {
////                cu.moveToFirst();
////            if (cu.getCount() >= 0) {
////                return true;
////            }
////            }
////        }
////        catch(SQLException e)
////        {
////            Toast.makeText(login.this, "Some Probkem Occur", Toast.LENGTH_SHORT).show();
////        }
////        return false;
//
////        Cursor cursor = db.rawQuery("SELECT "+loginContract.COLUMN_PSK_LOGIN+" FROM "+loginContract.TABLE_NAME+" WHERE "+loginContract.COLUMN_UNAME_LOGIN+"=?;",new String[]{name});
//        Cursor cursor = db.rawQuery("SELECT * FROM "+loginContract.TABLE_NAME+" WHERE "+loginContract.COLUMN_UNAME_LOGIN+"=?;",new String[]{name});
//        Toast.makeText(login.this, ""+cursor.getCount(), Toast.LENGTH_SHORT).show();
//
//        if (cursor.getCount()<0)
//        {
//            Toast.makeText(login.this, "User Name & Password Not FOund!!", Toast.LENGTH_SHORT).show();
//            return "NOT EXISTS";
//        }
//        else
//        {
////            Toast.makeText(login.this,"",Toast.LENGTH_SHORT).show();
//            String psk1 = cursor.getString(cursor.getColumnIndex(loginContract.COLUMN_PSK_LOGIN));
//            Toast.makeText(login.this,"Password = "+psk1,Toast.LENGTH_SHORT).show();
//            if(psk.equals(psk1))
//            {
//                Toast.makeText(login.this, "Password Match", Toast.LENGTH_SHORT).show();
//                return psk1;
//            }
//            else
//            {
//
//                Toast.makeText(login.this, "Password Not Match..!!", Toast.LENGTH_SHORT).show();
//                return "NOT EXISTS";
//            }
//        }
////        //SELECT
////        String[] columns = {loginContract.COLUMN_UNAME_LOGIN};
////
////        //WHERE clause
////        String selection = loginContract.COLUMN_UNAME_LOGIN + "=? AND " + loginContract.COLUMN_PSK_LOGIN + "=?";
////
////        //WHERE clause arguments
////        String[] selectionArgs = {name, psk};
////        Cursor c = null;
////
////        String query = "SELECT * FROM " + loginContract.TABLE_NAME + " WHERE " + loginContract.COLUMN_UNAME_LOGIN + "='" + name + "' AND " + loginContract.COLUMN_PSK_LOGIN + "='" + psk + "'";
////        Toast.makeText(login.this, ""+query, Toast.LENGTH_SHORT).show();
////
//    }


//    private boolean checkPsk(String uPsk)
//    {
//
//        boolean check = false;
//
//        SQLiteDatabase db = mHabitDbHelper.getReadableDatabase();
//
////        Toast.makeText(login.this, "USERNAME = "+USERNAME, Toast.LENGTH_SHORT).show();
////        Cursor c = db.rawQuery("SELECT "+loginContract.COLUMN_PSK_LOGIN+" as ch FROM "+loginContract.TABLE_NAME+" WHERE "+loginContract.COLUMN_UNAME_LOGIN+"= ?",new String[]{USERNAME});
//
////        int psk_index = c.getColumnIndex(loginContract.COLUMN_PSK_LOGIN);
////        String uPsk_database = c.getString(c.getColumnIndex(loginContract.COLUMN_PSK_LOGIN));
//        String uPsk_database = c.getString(c.getColumnIndex("ch"));
//        Toast.makeText(login.this, "Pssword From Database is = "+uPsk_database, Toast.LENGTH_SHORT).show();
//
//        if (uPsk.equals(uPsk_database))
//        {
//            Toast.makeText(login.this, "Password Match", Toast.LENGTH_SHORT).show();
//        }
//        else
//        {
//            Toast.makeText(login.this, "Password Did not Match", Toast.LENGTH_SHORT).show();
//        }
//
//
//        return check;
//    }

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

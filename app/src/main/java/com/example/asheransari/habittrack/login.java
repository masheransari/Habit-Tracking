package com.example.asheransari.habittrack;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
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

    private String USERNAME=null, PASSWORD =null;
    habitDbHelper mHabitDbHelper;
    EditText user_psk;
    TextView signup;
//    FloatingActionButton mFloatingActionButton;
    Button btn_uName_psk;
    int quantity = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mHabitDbHelper = new habitDbHelper(this);
        user_psk = (EditText)findViewById(R.id.edit_Text_user_psk);
        signup = (TextView)findViewById(R.id.signup);
        btn_uName_psk = (Button)findViewById(R.id.fab);
//        mFloatingActionButton = (FloatingActionButton)findViewById(R.id.float_new);

        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(login.this,sign_up.class);
                startActivity(i);
            }
        });


        btn_uName_psk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (quantity == 0) {
                    String getData_user = user_psk.getText().toString();
                    boolean check = checkUserName(getData_user);
//                    user_psk.setInputType();
                    if (check == true) {
                        user_psk.setInputType( InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD );
                        user_psk.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        user_psk.setText("");
                    } else {
                        Toast.makeText(login.this, "User Name not FOund.!!", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    String getData_psk = user_psk.getText().toString();
                    boolean check1 = checkPsk(getData_psk);
                }

            }
        });


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

    private boolean checkPsk(String uPsk)
    {

        boolean check = false;

        SQLiteDatabase db = mHabitDbHelper.getReadableDatabase();

        Toast.makeText(login.this, "USERNAME = "+USERNAME, Toast.LENGTH_SHORT).show();
        Cursor c = db.rawQuery("SELECT "+loginContract.COLUMN_PSK_LOGIN+" as ch FROM "+loginContract.TABLE_NAME+" WHERE "+loginContract.COLUMN_UNAME_LOGIN+"= ?",new String[]{USERNAME});

//        int psk_index = c.getColumnIndex(loginContract.COLUMN_PSK_LOGIN);
//        String uPsk_database = c.getString(c.getColumnIndex(loginContract.COLUMN_PSK_LOGIN));
        String uPsk_database = c.getString(c.getColumnIndex("ch"));
        Toast.makeText(login.this, "Pssword From Database is = "+uPsk_database, Toast.LENGTH_SHORT).show();

        if (uPsk.equals(uPsk_database))
        {
            Toast.makeText(login.this, "Password Match", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(login.this, "Password Did not Match", Toast.LENGTH_SHORT).show();
        }


        return check;
    }

    private boolean checkUserName(String uName)
    {
        boolean check = false;

        SQLiteDatabase db = mHabitDbHelper.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT "+loginContract.COLUMN_UNAME_LOGIN+" FROM "+loginContract.TABLE_NAME+" WHERE "+loginContract.COLUMN_UNAME_LOGIN+"='"+uName+"'",null);

//        Toast.makeText(login.this, "Cursor = "+c.getCount()+" - "+c.getString(0), Toast.LENGTH_SHORT).show();

        if (c.getCount() == 1)
        {
            USERNAME =  uName;
            quantity = 1;
            check = true;
        }
        else
        {
            check = false;
            quantity = 0;
        }

        return check;
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

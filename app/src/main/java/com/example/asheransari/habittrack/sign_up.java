package com.example.asheransari.habittrack;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asheransari.habittrack.*;
import com.example.asheransari.habittrack.database_material.currentContract;
import com.example.asheransari.habittrack.database_material.habitDbHelper;
import com.example.asheransari.habittrack.database_material.loginContract;

public class sign_up extends AppCompatActivity {

    EditText email, uName, psk, rePsk, name;
    TextView loginTextView, strengthPsk;
    Button button;
    habitDbHelper mHabitDbHelper;

    private void ClearAll() {
        email.setText("");
        uName.setText("");
        psk.setText("");
        rePsk.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mHabitDbHelper = new habitDbHelper(this);
        loginTextView = (TextView) findViewById(R.id.login_text);
        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(sign_up.this, login.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });

        email = (EditText) findViewById(R.id.email);
        uName = (EditText) findViewById(R.id.uname);
        psk = (EditText) findViewById(R.id.psk);
        rePsk = (EditText) findViewById(R.id.rePsk);
        name = (EditText) findViewById(R.id.name);
        strengthPsk = (TextView)findViewById(R.id.textViewPasswordStrength);

        button = (Button) findViewById(R.id.signUp_btn);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                String UName = null, PSk = null, REPsk = null, EMail = null, TempName = null;
                UName = uName.getText().toString();
                EMail = email.getText().toString();
                PSk = psk.getText().toString();
                REPsk = rePsk.getText().toString();
                TempName = name.getText().toString();


                if (TempName.equals("")) {
                    Toast.makeText(sign_up.this, "Name Not Found..!!", Toast.LENGTH_SHORT).show();
                } else {
                    if (isValidEmail(EMail)) {
//                    Toast.makeText(sign_up.this,"Valid Email",Toast.LENGTH_SHORT).show();
                        if (!UName.equals("")) {
                            if (PSk.equals(REPsk)) {
                                if (PSk.length() >= 6) {
                                    insertDbLogin(EMail, UName, PSk, TempName);
//                            Toast.makeText(sign_up.this,"Password Match, ["+PSk+"  =  "+REPsk+"]",Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(sign_up.this,"password Lenght is Too Short, Enter Again",Toast.LENGTH_SHORT).show();
                                    psk.setText("");
                                    rePsk.setText("");
                                }
                            }else {
                                Toast.makeText(sign_up.this, "Password Not Match", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(sign_up.this, "User Name Not Found!!\nPlease Enter User Name", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(sign_up.this, "InValid Email", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            }

            );

        psk.addTextChangedListener(mTextEditorWatcher);

    }


    public boolean alreadyExists(String uName) {
        boolean result = false;

        SQLiteDatabase db = mHabitDbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + loginContract.TABLE_NAME + " WHERE " + loginContract.COLUMN_UNAME_LOGIN + " ='" + uName + "'", null);

        if (cursor.getCount() == 0) {
            result = true;
        } else {
            result = false;
        }

        return result;
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
//    private String nameGet(String userName, String psk)
//    {
//        SQLiteDatabase db = mHabitDbHelper.getReadableDatabase();
//        String a,b;
//        String c = "User Name Not Found",d = "example@example.com";
//        String query = "SELECT name,email,uname,psk FROM "+loginContract.TABLE_NAME;
//        Cursor cursor = db.rawQuery(query,null);
//        b="NOT EXISTS";
//        if (cursor.moveToFirst())
//        {
//            do {
//                a = cursor.getString(2);
//                b = cursor.getString(3);
//                if (a.equals(userName)&& psk.equals(b))
//                {
//                    c = cursor.getString(0);
//                    d = cursor.getString(1);
//                    break;
//                }
//            }
//            while(cursor.moveToNext());
//        }
//        db.close();
//        return c+","+d;
//    }

//    public void insertCurrent() {
//        String uname, psk;
//        uname = .getText().toString();
//        psk = user_psk.getText().toString();
//        String temp = null;
//
//        temp = nameGet(uname, psk);
//        String[] data = temp.split(",");
//
//        SQLiteDatabase db = mHabitDbHelper.getWritableDatabase();
//        ContentValues v = new ContentValues();
//        v.put(currentContract.COLUMN_CURRENT_NAME, data[0]);
//        v.put(currentContract.COLUMN_CURRENT_EMAIL, data[0]);
//        v.put(currentContract.COLUMN_CURRENT_UNAME, uname);
//        v.put(currentContract.COLUMN_CURRENT_PSK, psk);
//        db.insert(currentContract.TABLE_NAME, null, v);
//    }


    private void insertDbLogin(String email, String userName, String psk, String orignalName) {
        SQLiteDatabase db = mHabitDbHelper.getWritableDatabase();

        boolean checkVerification = false;
        checkVerification = alreadyExists(userName);
        if (checkVerification == false) {
            Toast.makeText(sign_up.this, "User Name Already Exists,\nTry Another Name", Toast.LENGTH_SHORT).show();
        } else {
            ContentValues values = new ContentValues();
            values.put(loginContract.COLUMN_NAME_LOGIN,orignalName);
            values.put(loginContract.COLUMN_EMAIL_LOGIN, email);
            values.put(loginContract.COLUMN_UNAME_LOGIN, userName);
            values.put(loginContract.COLUMN_PSK_LOGIN, psk);
            db.insert(loginContract.TABLE_NAME, null, values);
            insertCurrent(userName,orignalName,email,psk);
            ClearAll();
            Intent i = new Intent(sign_up.this, MainActivity.class);
//            SQLiteDatabase sqLiteDatabase = mHabitDbHelper.getReadableDatabase();
//
//            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+ currentContract.TABLE_NAME,null);
//            String Name = cursor.getString(cursor.getColumnIndex(loginContract.COLUMN_NAME_LOGIN));
//            String UName = cursor.getString(cursor.getColumnIndex(loginContract.COLUMN_UNAME_LOGIN));
//            String emailDb = cursor.getString(cursor.getColumnIndex(loginContract.COLUMN_EMAIL_LOGIN));
//                        yeha pe kam chal rha hai ke hum jab user se login lenge to jo uski personal detail hai wo MainActivity me kis tarha bhej na hai..
//                        yeha hu,m wo define kren ge same as hum yehe kam baki dono me bhe kren ge,,,...
            i.putExtra("uniqueID","sign_up_activity");
            i.putExtra("NAME",orignalName);
            i.putExtra("UNAME",userName);
            i.putExtra("EMAIL",email);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);

        }
    }
    public void insertCurrent(String uname, String name, String email, String psk)
    {
        SQLiteDatabase db = mHabitDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(currentContract.COLUMN_CURRENT_UNAME,uname);
        values.put(currentContract.COLUMN_CURRENT_NAME,name);
        values.put(currentContract.COLUMN_CURRENT_EMAIL,email);
        values.put(currentContract.COLUMN_CURRENT_PSK,psk);


        db.insert(currentContract.TABLE_NAME,null,values);
    }

    private final TextWatcher mTextEditorWatcher = new TextWatcher() {

        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        {
            // When No Password Entered
            strengthPsk.setText("Not Entered");
        }

        public void onTextChanged(CharSequence s, int start, int before, int count)
        {
            if(s.length()==0)
                strengthPsk.setText("Password Strength: Not Entered,  Enter Maximum 6 Digits");
            else if(s.length()<6)
                strengthPsk.setText("Password Strength: EASY");
            else if(s.length()<10)
                strengthPsk.setText("Password Strength: MEDIUM");
            else if(s.length()<15)
                strengthPsk.setText("Password Strength: STRONG");
            else
                strengthPsk.setText("Password Strength: STRONGEST");

            if(s.length()==20)
                strengthPsk.setText("Password Max Length Reached");
        }

        public void afterTextChanged(Editable s)
        {
//            if(s.length()==0)
//                strengthPsk.setText("Password Strength: Not Entered");
//            else if(s.length()<6)
//                strengthPsk.setText("Password Strength: EASY");
//            else if(s.length()<10)
//                strengthPsk.setText("Password Strength: MEDIUM");
//            else if(s.length()<15)
//                strengthPsk.setText("Password Strength: STRONG");
//            else
//                strengthPsk.setText("Password Strength: STRONGEST");
//
//            if(s.length()==20)
//                strengthPsk.setText("Password Max Length Reached");
        }
    };
}

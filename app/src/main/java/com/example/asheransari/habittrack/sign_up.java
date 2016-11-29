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
            ClearAll();
            Intent i = new Intent(sign_up.this, MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);

        }
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

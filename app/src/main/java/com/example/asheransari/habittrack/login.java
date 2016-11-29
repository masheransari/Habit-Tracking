package com.example.asheransari.habittrack;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asheransari.habittrack.database_material.*;

public class login extends AppCompatActivity {

    habitDbHelper mHabitDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mHabitDbHelper = new habitDbHelper(this);

        TextView t1 = (TextView)findViewById(R.id.temp_mainActivity);
        TextView t2 = (TextView)findViewById(R.id.temp_signup);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(login.this,MainActivity.class);
                startActivity(i);
            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(login.this,sign_up.class);
                startActivity(i);
            }
        });
    }

    private int count()
    {
        SQLiteDatabase sqLiteDatabase = mHabitDbHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+loginContract.TABLE_NAME,null);

        int tempCOunt = cursor.getCount();

        Toast.makeText(login.this,"TEMO COUNT = "+tempCOunt,Toast.LENGTH_SHORT).show();

        return tempCOunt;
    }
}

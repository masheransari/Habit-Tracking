package com.example.asheransari.habittrack;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.asheransari.habittrack.database_material.*;

public class login extends AppCompatActivity {

    habitDbHelper mHabitDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mHabitDbHelper = new habitDbHelper(this);


        int temp = count();
        if(temp == 0)
        {
            Intent i = new Intent(login.this,sign_up.class);
            startActivity(i);
        }
        Toast.makeText(login.this,""+temp,Toast.LENGTH_SHORT).show();
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

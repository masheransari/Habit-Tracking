package com.example.asheransari.habittrack;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.asheransari.habittrack.database_material.adapter.currentAdapter;
import com.example.asheransari.habittrack.database_material.currentContract;
import com.example.asheransari.habittrack.database_material.currentVariableClass;
import com.example.asheransari.habittrack.database_material.habitDbHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressbar;
    Long timerforprogressbar ;
    habitDbHelper mHabitDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<currentVariableClass> arrayList = new ArrayList<>();
        mHabitDbHelper = new habitDbHelper(this);
////this work is only for ProgressBar <!-- Start From Here-->
        timerforprogressbar = (long) 5000 ;
        new MyProgressBar().execute((Void)null);
//        <!--END HERE -->

        TextView t1 = (TextView)findViewById(R.id.temp_new);

        ListView listView = (ListView)findViewById(R.id.list);
        arrayList = fetchCurrentData();
//        if (arrayList.size() == 1)
//        {
//
//            t1.setText();
//        }
        currentAdapter adapter = new currentAdapter(this,arrayList);

        listView.setAdapter(adapter);
    }

    private ArrayList<currentVariableClass> fetchCurrentData()
    {
        SQLiteDatabase db = mHabitDbHelper.getWritableDatabase();

        String query = "SELECT * from "+ currentContract.TABLE_NAME;

        ArrayList<currentVariableClass> arrayList = new ArrayList<>();

        String[] projection={
                currentContract.COLUMN_CURRENT_NAME,
                currentContract.COLUMN_CURRENT_UNAME,
                currentContract.COLUMN_CURRENT_PSK,
                currentContract.COLUMN_CURRENT_EMAIL
        };

        Cursor cursor = db.query(currentContract.TABLE_NAME,projection,null,null,null,null,null);
        try
        {
            int nameIndex = cursor.getColumnIndex(currentContract.COLUMN_CURRENT_NAME);
            int unameIndex = cursor.getColumnIndex(currentContract.COLUMN_CURRENT_UNAME);
            int emailIndex = cursor.getColumnIndex(currentContract.COLUMN_CURRENT_EMAIL);
            do
            {
                String name = cursor.getString(nameIndex);
                String Uname = cursor.getString(unameIndex);
                String email = cursor.getString(emailIndex);

                arrayList.add(new currentVariableClass(name,Uname,email));
            }
            while (cursor.moveToNext());
        }
        finally {
            cursor.close();
            db.close();
        }
        return arrayList;

    }


    private class MyProgressBar extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... params) {
            // TODO Auto-generated method stub
            progressbar = (ProgressBar) findViewById(R.id.progress);
            //noinspection WrongThread
            progressbar.setVisibility(View.VISIBLE);
            try {
                Thread.sleep(timerforprogressbar);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            progressbar.setVisibility(View.GONE);
        }

    }
}

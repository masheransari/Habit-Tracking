package com.example.asheransari.habittrack;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asheransari.habittrack.database_material.adapter.habitAdapter;
import com.example.asheransari.habittrack.database_material.habitContract;
import com.example.asheransari.habittrack.database_material.habitDbHelper;
import com.example.asheransari.habittrack.database_material.variableContractClass;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressbar;
    Long timerforprogressbar ;
    habitDbHelper mHabitDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView uName_Text,name_text;
        uName_Text = (TextView)findViewById(R.id.uname_mainActivity);
        name_text = (TextView)findViewById(R.id.name_mainActivity);

        mHabitDbHelper = new habitDbHelper(this);
////this work is only for ProgressBar <!-- Start From Here-->
        timerforprogressbar = (long) 5000 ;
        new MyProgressBar().execute((Void)null);
//        <!--END HERE -->

        String name = null, uname = null,email = null;
        Intent getData = getIntent();
        if (getData !=null)
        {
            String uniqueId = getData.getExtras().getString("uniqueID");
            switch (uniqueId)
            {
                case "splash_activity":
                    Toast.makeText(MainActivity.this, "From Splash Activity", Toast.LENGTH_SHORT).show();
                    name = getData.getExtras().getString("NAME");
                    uname =getData.getExtras().getString("UNAME");
                    email = getData.getExtras().getString("EMAIL");
                    break;
                case "login_activity":
                    Toast.makeText(MainActivity.this, "From login Activity", Toast.LENGTH_SHORT).show();
                    name = getData.getExtras().getString("name");
                    uname =getData.getExtras().getString("uname");
                    email = getData.getExtras().getString("email");
                    break;
                case "sign_up_activity":
                    Toast.makeText(MainActivity.this, "From Sign-up Activity", Toast.LENGTH_SHORT).show();
                    name = getData.getExtras().getString("NAME");
                    uname =getData.getExtras().getString("UNAME");
                    email = getData.getExtras().getString("EMAIL");
                    break;
                default:
                Toast.makeText(MainActivity.this, "in Default Activity", Toast.LENGTH_SHORT).show();
                    break;
            }
            name_text.setText(name);
            uName_Text.setText("User Name: "+uname+"\nEmail: "+email);
        }

        ListView listView = (ListView)findViewById(R.id.list);

        ArrayList<variableContractClass> arrayList = new ArrayList<variableContractClass>();

        arrayList = displayDatabaseInfo(uname);

        habitAdapter HabitAdapter = new habitAdapter(MainActivity.this,arrayList);

        listView.setAdapter(HabitAdapter);

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);

        final String finalName = name;
        final String finalUname = uname;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,insert_data.class);

                i.putExtra("name", finalName);
                i.putExtra("uname", finalUname);
                startActivity(i);
            }
        });


    }


//    private ArrayList<currentVariableClass> fetchCurrentData()
//    {
//        SQLiteDatabase db = mHabitDbHelper.getWritableDatabase();
//
//        String query = "SELECT * from "+ currentContract.TABLE_NAME;
//
//        ArrayList<currentVariableClass> arrayList = new ArrayList<>();
//
//        String[] projection={
//                currentContract.COLUMN_CURRENT_NAME,
//                currentContract.COLUMN_CURRENT_UNAME,
//                currentContract.COLUMN_CURRENT_PSK,
//                currentContract.COLUMN_CURRENT_EMAIL
//        };
//
//        Cursor cursor = db.query(currentContract.TABLE_NAME,projection,null,null,null,null,null);
//        try
//        {
//            int nameIndex = cursor.getColumnIndex(currentContract.COLUMN_CURRENT_NAME);
//            int unameIndex = cursor.getColumnIndex(currentContract.COLUMN_CURRENT_UNAME);
//            int emailIndex = cursor.getColumnIndex(currentContract.COLUMN_CURRENT_EMAIL);
//            do
//            {
//                String name = cursor.getString(nameIndex);
//                String Uname = cursor.getString(unameIndex);
//                String email = cursor.getString(emailIndex);
//
//                arrayList.add(new currentVariableClass(name,Uname,email));
//            }
//            while (cursor.moveToNext());
//        }
//        finally {
//            cursor.close();
//            db.close();
//        }
//        return arrayList;
//
//    }

    private ArrayList<variableContractClass> displayDatabaseInfo(String uname)
    {
        ArrayList<variableContractClass> arrayList = new ArrayList<variableContractClass>();
        SQLiteDatabase db = mHabitDbHelper.getReadableDatabase();

//        String[] projection={
//
//        }

        Cursor cursor = db.rawQuery("SELECT * FROM "+ habitContract.TABLE_NAME+" WHERE "+habitContract.COLUMN_UNAME+"=?",new String[]{uname});

        try
        {
            int detailIndex = cursor.getColumnIndex(habitContract.COLUMN_DETAIL);
            int dateIndex = cursor.getColumnIndex(habitContract.COLUMN_DATE);
            int timeIndex = cursor.getColumnIndex(habitContract.COLUMN_TIME);

            while(cursor.moveToNext())
            {
                String detail =  cursor.getString(detailIndex);
                String date = cursor.getString(dateIndex);
                String time = cursor.getString(timeIndex);

                arrayList.add(new variableContractClass(detail,date,time));
            }
        }
        finally {
            cursor.close();
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

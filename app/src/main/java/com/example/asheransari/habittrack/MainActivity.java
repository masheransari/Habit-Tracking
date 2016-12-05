package com.example.asheransari.habittrack;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
//import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
//import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asheransari.habittrack.database_material.adapter.habitAdapter;
import com.example.asheransari.habittrack.database_material.currentContract;
import com.example.asheransari.habittrack.database_material.habitContract;
import com.example.asheransari.habittrack.database_material.habitDbHelper;
import com.example.asheransari.habittrack.database_material.variableContractClass;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

//    ProgressBar progressbar;
//    Long timerforprogressbar ;
    habitDbHelper mHabitDbHelper;
    ListView listView;
String UNAME = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView uName_Text,name_text;
        uName_Text = (TextView)findViewById(R.id.uname_mainActivity);
        name_text = (TextView)findViewById(R.id.name_mainActivity);

        mHabitDbHelper = new habitDbHelper(this);
////this work is only for ProgressBar <!-- Start From Here-->
//        timerforprogressbar = (long) 5000 ;
//        new MyProgressBar().execute((Void)null);
//        <!--END HERE -->

        String name = null, uname = null,email = null;
        int idMain = 0;
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
//                    Toast.makeText(MainActivity.this, ""+email, Toast.LENGTH_SHORT).show();
                    break;
                case "sign_up_activity":
                    Toast.makeText(MainActivity.this, "From Sign-up Activity", Toast.LENGTH_SHORT).show();
                    name = getData.getExtras().getString("NAME");
                    uname =getData.getExtras().getString("UNAME");
                    email = getData.getExtras().getString("EMAIL");
                    break;
                case "insert_date_activity":
                    Toast.makeText(MainActivity.this, "From Insert_data Activity", Toast.LENGTH_SHORT).show();
                    name = getData.getExtras().getString("NAME");
                    uname =getData.getExtras().getString("UNAME");
                    email = getData.getExtras().getString("EMAIL");
                    break;
                case "update_activity":
                    Toast.makeText(MainActivity.this, "From update_Activity", Toast.LENGTH_SHORT).show();
                    name = getData.getExtras().getString("NAME");
                    uname =getData.getExtras().getString("UNAME");
                    email = getData.getExtras().getString("EMAIL");
                    break;
                default:
                Toast.makeText(MainActivity.this, "in Default Activity", Toast.LENGTH_SHORT).show();
                    break;
            }
            name_text.setText(name);
            String temp_storage = "User Name: "+uname+"\nEmail: "+email;
            uName_Text.setText(temp_storage);
            Toast.makeText(MainActivity.this, ""+temp_storage, Toast.LENGTH_SHORT).show();
        }

        listView = (ListView)findViewById(R.id.list);

        ArrayList<variableContractClass> arrayList = new ArrayList<variableContractClass>();

        arrayList = displayDatabaseInfo(uname);

        habitAdapter HabitAdapter = new habitAdapter(MainActivity.this,arrayList);

        listView.setAdapter(HabitAdapter);

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);

//        final String finalName = name;
        final String finalUname = uname;
//        final String finalEmail = email;
        final String finalName1 = name;
        final String finalUname1 = uname;
        final String finalEmail1 = email;
        UNAME = uname;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,insert_data.class);

                i.putExtra("name", finalName1);
                i.putExtra("uname", finalUname1);
                i.putExtra("email", finalEmail1);
                startActivity(i);
            }
        });


        final ArrayList<variableContractClass> finalArrayList = arrayList;
        final String finalEmail2 = email;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(MainActivity.this, "line = "+i, Toast.LENGTH_SHORT).show();

                variableContractClass variableClass = finalArrayList.get(i);
                Intent i1 = new Intent(MainActivity.this,update_activity.class);
                i1.putExtra("detail",variableClass.getDetail());
                i1.putExtra("id",variableClass.getId());
                i1.putExtra("date",variableClass.getDate());
                i1.putExtra("time",variableClass.getTime());
                i1.putExtra("uname",finalUname);
                i1.putExtra("name", finalName1);
                i1.putExtra("email", finalEmail2);
                startActivity(i1);
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
            int idIndex = cursor.getColumnIndex(habitContract._Id);

            while(cursor.moveToNext())
            {
                String detail =  cursor.getString(detailIndex);
                String date = cursor.getString(dateIndex);
                String time = cursor.getString(timeIndex);
                int id = cursor.getInt(idIndex);
                arrayList.add(new variableContractClass(detail,date,time,id));
            }
        }
        finally {
            cursor.close();
        }
        return arrayList;
    }

//    private class MyProgressBar extends AsyncTask<Void, Void, Void> {
//
//
//        @Override
//        protected Void doInBackground(Void... params) {
//            // TODO Auto-generated method stub
////            progressbar = (ProgressBar) findViewById(R.id.progress);
//            //noinspection WrongThread
////            progressbar.setVisibility(View.VISIBLE);
//            try {
//                Thread.sleep(timerforprogressbar);
//            } catch (InterruptedException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void result) {
//            // TODO Auto-generated method stub
//            super.onPostExecute(result);
////            progressbar.setVisibility(View.GONE);
//        }
//
//    }

    public void deletedAllItem(String uname)
    {
        SQLiteDatabase db = mHabitDbHelper.getWritableDatabase();

        db.delete(habitContract.TABLE_NAME,habitContract.COLUMN_UNAME+"=?",new String[]{uname});
        db.close();
        ArrayList<variableContractClass> arrayList = new ArrayList<variableContractClass>();

        arrayList = displayDatabaseInfo(uname);

        habitAdapter HabitAdapter = new habitAdapter(MainActivity.this,arrayList);

        listView.setAdapter(HabitAdapter);

    }

    private void signOut_main()
    {
        SQLiteDatabase db = mHabitDbHelper.getWritableDatabase();

        db.delete(currentContract.TABLE_NAME,null,null);
        db.close();
        Intent i = new Intent(MainActivity.this,login.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }


    @Override
    public void onRestart()
    {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_dialer).setTitle("Exit")
                .setMessage("Are you sure you want to Exit?")
                .setPositiveButton("yes", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        startActivity(intent);
                        finish();
                    }
                }).setNegativeButton("no", null).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu MenuItem)
    {
        getMenuInflater().inflate(R.menu.menu_main_activity,MenuItem);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        switch (menuItem.getItemId())
        {
            case R.id.sign_out:
                Toast.makeText(MainActivity.this, "Signout Selected", Toast.LENGTH_SHORT).show();
                signOut_main();
                break;
            case R.id.removeData:
                Toast.makeText(MainActivity.this, "Remove Selectedx", Toast.LENGTH_SHORT).show();
                deletedAllItem(UNAME);
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }


}

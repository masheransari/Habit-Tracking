package com.example.asheransari.habittrack;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asheransari.habittrack.database_material.habitContract;
import com.example.asheransari.habittrack.database_material.habitDbHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class insert_data extends AppCompatActivity {

    Button btn_insert;
    EditText detail_EdtiText;
    habitDbHelper mHabitDbHelper;
    SimpleDateFormat simpledateformat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);

        mHabitDbHelper = new habitDbHelper(this);
        btn_insert = (Button)findViewById(R.id.btn_insert);
        detail_EdtiText = (EditText)findViewById(R.id.editText_detail);

        TextView nameText = (TextView)findViewById(R.id.insert_UserName);
               Intent i = getIntent();

        final String name = i.getExtras().getString("name");
        final String uname = i.getExtras().getString("uname");
        final String email = i.getExtras().getString("email");

        nameText.setText(""+name);
        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String detail = detail_EdtiText.getText().toString();
                if (detail.equals("") || detail.equals(" ") || detail == null)
                {
                    Toast.makeText(insert_data.this, "Please Enter Your Todo Task First..!!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    SimpleDateFormat dfDate_day= new SimpleDateFormat("E, dd/MM/yyyy");
                    Calendar cD = Calendar.getInstance();
                    String date = dfDate_day.format(cD.getTime());

                    SimpleDateFormat dfDate_time= new SimpleDateFormat("HH:mm:ss a");
                    Calendar cT = Calendar.getInstance();

                    String time = dfDate_time.format(cT.getTime());
//                    Toast.makeText(insert_data.this, ""+date, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(insert_data.this, "Time = "+time, Toast.LENGTH_SHORT).show();
                    insertData(detail,date,time,uname, name, email);
                }
            }
        });
    }

    public void insertData(String detail,String date, String time,String uname,String name, String emil)
    {
        SQLiteDatabase db = mHabitDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(habitContract.COLUMN_DETAIL,detail);
        values.put(habitContract.COLUMN_DATE,date);
        values.put(habitContract.COLUMN_TIME,time);
        values.put(habitContract.COLUMN_UNAME,uname);

        db.insert(habitContract.TABLE_NAME,null,values);
        db.close();
        Intent i = new Intent(insert_data.this,MainActivity.class);
        i.putExtra("uniqueID","insert_date_activity");
        i.putExtra("NAME",name);
        i.putExtra("UNAME",uname);
        i.putExtra("EMAIL",emil);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }
}

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

        String name = i.getExtras().getString("name");
        final String uname = i.getExtras().getString("uname");
//        nameText.setText(name);
//        Calendar calander =null;
//        calander = Calendar.getInstance();
//        simpledateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//        Date = simpledateformat.format(calander.getTime());
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            calander = Calendar.getInstance();
//            simpledateformat = new SimpleDateFormat("dd-MM-yyyy");// HH:mm:ss
//        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            date= simpledateformat.format(calander.getTime());
//        }

//        final String[] date = {null};
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
//                    String date = giveDate();

//                    String dt;
//                    Date cal = (Date) Calendar.getInstance().getTime();
//                    dt = cal.toLocaleString();

//                    long date = System.currentTimeMillis();
//
//                    SimpleDateFormat sdf = new SimpleDateFormat("MMM MM dd, yyyy h:mm a");
//                    String dateString = sdf.format(date);

//                    final Calendar c = Calendar.getInstance();
//                    yy = c.get(Calendar.YEAR);
//                    mm = c.get(Calendar.MONTH);
//                    dd = c.get(Calendar.DAY_OF_MONTH);
//
//                    // set current date into textview
//                    tvDisplayDate.setText(new StringBuilder()
//                            // Month is 0 based, just add 1
//                            .append(yy).append(" ").append("-").append(mm + 1).append("-")
//                            .append(dd));



//                    String dateString = updateDisplay();


                    SimpleDateFormat dfDate_day= new SimpleDateFormat("E, dd/MM/yyyy");
                    Calendar cD = Calendar.getInstance();
                    String date = dfDate_day.format(cD.getTime());

                    SimpleDateFormat dfDate_time= new SimpleDateFormat("HH:mm:ss a");
                    Calendar cT = Calendar.getInstance();

                    String time = dfDate_time.format(cT.getTime());
                    Toast.makeText(insert_data.this, ""+date, Toast.LENGTH_SHORT).show();
                    Toast.makeText(insert_data.this, "Time = "+time, Toast.LENGTH_SHORT).show();
                    insertData(detail,date,time,uname);
                }
            }
        });
//        c = Calendar.getInstance();
//    int mYear = c.get(Calendar.YEAR);
//    int mMonth = c.get(Calendar.MONTH);
//    int mDay = c.get(Calendar.DAY_OF_MONTH);
//
//    private String updateDisplay() {
//        StringBuilder date = (
//                new StringBuilder()
//                        // Month is 0 based so add 1
//                        .append(mMonth + 1).append("-")
//                        .append(mDay).append("-")
//                        .append(mYear).append(" "));
//        return String.valueOf(date);
//    }
    }
//    final Calendar

//    public String giveDate() {
//        Calendar cal = Calendar.getInstance();
//        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
//        return sdf.format(cal.getTime());
//    }

    //    public String getCurrentDate(View v) {
//        Calendar c = null;
//        String date=null;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            c = Calendar.getInstance();
//        }
//
//        SimpleDateFormat df = null;
//        String formattedDate=null;
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
//            df = new SimpleDateFormat("yyyy-MM-dd");//HH:mm:ss
//            formattedDate = df.format(c.getTime());
//        }
//        return formattedDate;
//    }

    public void insertData(String detail,String date, String time,String uname)
    {
        SQLiteDatabase db = mHabitDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(habitContract.COLUMN_DETAIL,detail);
        values.put(habitContract.COLUMN_DATE,date);
        values.put(habitContract.COLUMN_TIME,time);
        values.put(habitContract.COLUMN_UNAME,uname);

        db.insert(habitContract.TABLE_NAME,null,values);
        db.close();
    }
}

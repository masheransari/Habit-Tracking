package com.example.asheransari.habittrack;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asheransari.habittrack.database_material.habitContract;
import com.example.asheransari.habittrack.database_material.habitDbHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class update_activity extends AppCompatActivity {

    EditText update_EdtiText;
    habitDbHelper mHabitDbHelper;
    SimpleDateFormat simpledateformat;
    String name=null;
    String uname=null;
    String email=null;
    String detail= null;
    int id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_activity);

        mHabitDbHelper = new habitDbHelper(this);
        update_EdtiText = (EditText)findViewById(R.id.update_editText);

        TextView update_username = (TextView)findViewById(R.id.update_UserName);
        Intent i = getIntent();

        name = i.getExtras().getString("name");
        uname = i.getExtras().getString("uname");
        email = i.getExtras().getString("email");

        update_username.setText(""+name);
        id = i.getExtras().getInt("id");
        Toast.makeText(update_activity.this, "id = "+id, Toast.LENGTH_SHORT).show();
        update_EdtiText.setText(""+i.getExtras().getString("detail"));

//        TextView t1 = (TextView)findViewById(R.id.temp);
//
//        Intent i = getIntent();
//
//        t1.setText(i.getExtras().getString("detail")+"\n"+"date: "+i.getExtras().getString("date")+"\nTime:"+i.getExtras().getString("time"));
    }

    public void update_detail()
    {
        EditText eT = (EditText)findViewById(R.id.update_editText);
        detail = eT.getText().toString();
        if (detail.equals("")||detail.equals(" ")||detail==null)
        {
            Toast.makeText(update_activity.this, "Please Enter Your Todo First TO Update..!!", Toast.LENGTH_SHORT).show();
        }
        else {
            SQLiteDatabase db = mHabitDbHelper.getWritableDatabase();
            SimpleDateFormat dfDate_day = new SimpleDateFormat("E, dd/MM/yyyy");
            Calendar cD = Calendar.getInstance();
            String date = dfDate_day.format(cD.getTime());
            SimpleDateFormat dfDate_time = new SimpleDateFormat("HH:mm:ss a");
            Calendar cT = Calendar.getInstance();
            String time = dfDate_time.format(cT.getTime());
            ContentValues values = new ContentValues();
            values.put(habitContract.COLUMN_DETAIL, detail);
            values.put(habitContract.COLUMN_TIME, time);
            values.put(habitContract.COLUMN_DATE, date);
            db.update(habitContract.TABLE_NAME, values, habitContract._Id + "=? AND " + habitContract.COLUMN_UNAME + "=?", new String[]{String.valueOf(id), uname});
            db.close();
            Intent i = new Intent(update_activity.this,MainActivity.class);
            i.putExtra("uniqueID","update_activity");
            i.putExtra("NAME",name);
            i.putExtra("UNAME",uname);
            i.putExtra("EMAIL",email);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
    }

    private void deleteData()
    {
        SQLiteDatabase db = mHabitDbHelper.getWritableDatabase();
//        db.update(habitContract.TABLE_NAME, values, habitContract._Id + "=? AND " + habitContract.COLUMN_UNAME + "=?", new String[]{String.valueOf(id), uname});
        db.delete(habitContract.TABLE_NAME, habitContract._Id+ " =? AND "+habitContract.COLUMN_UNAME+"=?",new String[]{String.valueOf(id),uname});
        db.close();
        Intent i = new Intent(update_activity.this,MainActivity.class);
        i.putExtra("uniqueID","update_activity");
        i.putExtra("NAME",name);
        i.putExtra("UNAME",uname);
        i.putExtra("EMAIL",email);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menuItem)
    {
        getMenuInflater().inflate(R.menu.activity_display_menus,menuItem);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        switch (menuItem.getItemId())
        {
            case R.id.save_display:

                Toast.makeText(update_activity.this, "Display Selected", Toast.LENGTH_SHORT).show();
                update_detail();
                return true;
            case R.id.delete_display:
                Toast.makeText(update_activity.this, "Deleted Selected", Toast.LENGTH_SHORT).show();
                deleteData();
                return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
//    @Override
//    public void dismiss() {
//        super.dismiss();
//        getActivity().finish();
//        Intent i = new Intent(getActivity(), objectActivity.class);  //your class
//        startActivity(i);
//
//    }
@Override
public void onBackPressed() {
    new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
            .setMessage("Are You Sure To Stop Updating?")
            .setPositiveButton("yes", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent i = new Intent(update_activity.this,MainActivity.class);
                    i.putExtra("uniqueID","update_activity");
                    i.putExtra("NAME",name);
                    i.putExtra("UNAME",uname);
                    i.putExtra("EMAIL",email);
                    startActivity(i);
                    finish();
                }
            }).setNegativeButton("no", null).show();
}
//@Override
//public void onBackPressed() {
//    new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
//            .setMessage("Are you sure you want to exit?")
//            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    finish();
//                }
//            }).setNegativeButton("No", null).show();
//}
}

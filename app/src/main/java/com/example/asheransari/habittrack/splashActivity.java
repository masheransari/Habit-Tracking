package com.example.asheransari.habittrack;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asheransari.habittrack.database_material.currentContract;
import com.example.asheransari.habittrack.database_material.habitDbHelper;
import com.example.asheransari.habittrack.database_material.loginContract;

public class splashActivity extends AppCompatActivity implements Animation.AnimationListener {


    ImageView img1;
    Animation animationSlide;
    habitDbHelper mHabitDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mHabitDbHelper = new habitDbHelper(this);

        img1 = (ImageView)findViewById(R.id.imageView);

        animationSlide = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_down);
        animationSlide.setAnimationListener(this);
        animationSlide.setDuration(500);
        img1.setVisibility(View.VISIBLE);
        img1.startAnimation(animationSlide);
        TextView t1,t2;
        t1 = (TextView)findViewById(R.id.power);
        t2 = (TextView)findViewById(R.id.logic);

        t1.setVisibility(View.VISIBLE);
        t2.setVisibility(View.VISIBLE);
        t1.setAnimation(animationSlide);
        t2.setAnimation(animationSlide);
        Thread timer = new Thread() {

            @Override
            public void run()
            {
                try
                {
                    sleep(3000); //some how 3 sec....
                }
                catch(Exception ex)
                {
                    ex.getMessage();
                }
                finally {

//                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
//                    startActivity(i);
                    int tempCurrent = countCurrentTable();
                    if (tempCurrent == 0) {
                        int temp = countLoginTAble();
                        if (temp == 0) {
                            Intent i = new Intent(getApplicationContext(), sign_up.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(i);
//                        Toast.makeText(getApplicationContext(),)
                        } else {
                            Intent j = new Intent(getApplicationContext(), login.class);
                            j.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(j);
                        }
                    }
                    else
                    {
                        Intent j = new Intent(getApplicationContext(), MainActivity.class);
                        SQLiteDatabase sqLiteDatabase = mHabitDbHelper.getReadableDatabase();
                        String Name =null,UName = null,email=null;

                        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+ currentContract.TABLE_NAME,null);
//                        while(cursor!=null0)
                        if (cursor!=null|| cursor.getCount()>1)
                        {
                            int nameIndex = cursor.getColumnIndex(currentContract.COLUMN_CURRENT_NAME);
                            int unameIndex = cursor.getColumnIndex(currentContract.COLUMN_CURRENT_UNAME);
                            int emailIndex = cursor.getColumnIndex(currentContract.COLUMN_CURRENT_EMAIL);
                            while(cursor.moveToNext())
                            {
                                Name = cursor.getString(nameIndex);
                                UName = cursor.getString(unameIndex);
                                email = cursor.getString(emailIndex);
                            }

                        }
//                        yeha pe kam chal rha hai ke hum jab user se login lenge to jo uski personal detail hai wo MainActivity me kis tarha bhej na hai..
//                        yeha hu,m wo define kren ge same as hum yehe kam baki dono me bhe kren ge,,,...
                        j.putExtra("uniqueID","splash_activity");
                        j.putExtra("NAME",Name);
                        j.putExtra("UNAME",UName);
                        j.putExtra("EMAIL",email);
                        j.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(j);

                    }
                }


            }
        };
        timer.start();
    }
    private int countCurrentTable()
    {
        SQLiteDatabase sqLiteDatabase = mHabitDbHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+ currentContract.TABLE_NAME,null);

        int tempCOunt=0;
        if (cursor != null && cursor.getCount()!=0)
        {
            tempCOunt = cursor.getCount();
        }
//        Toast.makeText(splashActivity.this, "count ="+tempCOunt, Toast.LENGTH_SHORT).show();
//        Toast.makeText(login.this,"TEMO COUNT = "+tempCOunt,Toast.LENGTH_SHORT).show();

        return tempCOunt;

    }
    private int countLoginTAble()
    {
        SQLiteDatabase sqLiteDatabase = mHabitDbHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+ loginContract.TABLE_NAME,null);
        int tempCOunt=0;
        if (cursor != null && cursor.getCount()!=0)
        {
            tempCOunt = cursor.getCount();
        }

//        Toast.makeText(login.this,"TEMO COUNT = "+tempCOunt,Toast.LENGTH_SHORT).show();

        return tempCOunt;
    }
    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

        if (animation == animationSlide)
        {

        }

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}

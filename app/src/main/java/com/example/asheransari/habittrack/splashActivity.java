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

        int tempCOunt = cursor.getCount();

//        Toast.makeText(login.this,"TEMO COUNT = "+tempCOunt,Toast.LENGTH_SHORT).show();

        return tempCOunt;

    }
    private int countLoginTAble()
    {
        SQLiteDatabase sqLiteDatabase = mHabitDbHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+ loginContract.TABLE_NAME,null);

        int tempCOunt = cursor.getCount();

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

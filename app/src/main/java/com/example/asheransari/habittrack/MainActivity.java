package com.example.asheransari.habittrack;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressbar;
    Long timerforprogressbar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
////this work is only for ProgressBar <!-- Start From Here-->
        timerforprogressbar = (long) 5000 ;
        new MyProgressBar().execute((Void)null);
//        <!--END HERE -->

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

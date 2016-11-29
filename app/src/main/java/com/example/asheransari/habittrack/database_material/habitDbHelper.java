package com.example.asheransari.habittrack.database_material;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by asher.ansari on 11/28/2016.
 */
public class habitDbHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "habit_todo.db";

    private static final int DATABASE_VERSION = 1;

    public habitDbHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    private static final String SQL_CREATE_HABIT_TABLE = "CREATE TABLE "+habitContract.TABLE_NAME+
            " ("+habitContract._Id + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
            habitContract.COLUMN_DETAIL+ " TEXT NOT NULL, "+
            habitContract.COLUMN_DATE +" TEXT NOT NULL, "+
            habitContract.COLUMN_TIME+" TEXT NOT NULL, "+
            habitContract.COLUMN_UNAME+ " TEXT NOT NULL);";

    private static final String SQL_CREATE_LOGIN_TABLE = "CREATE TABLE "+loginContract.TABLE_NAME+
            " ("+loginContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            loginContract.COLUMN_NAME_LOGIN+ " TEXT NOT NULL, "+
            loginContract.COLUMN_EMAIL_LOGIN+" TEXT NOT NULL, "+
            loginContract.COLUMN_UNAME_LOGIN+" TEXT NOT NULL, "+
            loginContract.COLUMN_PSK_LOGIN+ " TEXT NOT NULL);";

//    private static final String SQL_CREATE_CURRENT_USER = "CREATE TABLE "+

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(SQL_CREATE_HABIT_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_LOGIN_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        onCreate(sqLiteDatabase);
    }
}

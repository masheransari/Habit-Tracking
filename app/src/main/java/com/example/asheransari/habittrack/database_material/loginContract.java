package com.example.asheransari.habittrack.database_material;

import android.provider.BaseColumns;

/**
 * Created by asher.ansari on 11/28/2016.
 */
public class loginContract implements BaseColumns {

    private loginContract()
    {}

    public static final String _ID = BaseColumns._ID;

    public static final String TABLE_NAME = "login";

    public static final String COLUMN_NAME_LOGIN = "name";

    public static final String COLUMN_UNAME_LOGIN = "uname";

    public static final String COLUMN_PSK_LOGIN = "psk";

    public static final String COLUMN_EMAIL_LOGIN = "email";

}

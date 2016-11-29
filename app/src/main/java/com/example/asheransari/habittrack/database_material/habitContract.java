package com.example.asheransari.habittrack.database_material;

import android.provider.BaseColumns;

/**
 * Created by asher.ansari on 11/28/2016.
 */
public class habitContract implements BaseColumns {

    private habitContract()
    {}

    public final static String TABLE_NAME = "details";

    public final static String _Id = BaseColumns._ID;

    public final static String COLUMN_DETAIL = "detail";

    public final static String COLUMN_DATE = "date_detail";

    public final static String COLUMN_TIME = "time_detail";

    public final static String COLUMN_UNAME = "uname";

}

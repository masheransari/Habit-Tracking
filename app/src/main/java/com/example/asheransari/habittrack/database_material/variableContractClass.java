package com.example.asheransari.habittrack.database_material;

/**
 * Created by asher.ansari on 12/3/2016.
 */
public class variableContractClass {

    private String detail;

    private String date;

    private String time;

    public variableContractClass(String detail, String date, String time) {
        this.detail = detail;
        this.date = date;
        this.time = time;
    }

    public String getDetail() {
        return detail;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}

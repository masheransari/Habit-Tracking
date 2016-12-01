package com.example.asheransari.habittrack.database_material;

/**
 * Created by asher.ansari on 12/2/2016.
 */
public class currentVariableClass {

    private String name;

    private String uname;

    private String psk;

    private String email;

    public currentVariableClass(String name, String uname, String psk, String email) {
        this.name = name;
        this.uname = uname;
        this.psk = psk;
        this.email = email;
    }

    public currentVariableClass(String name, String uname, String email) {
        this.name = name;
        this.uname = uname;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getUname() {
        return uname;
    }

    public String getPsk() {
        return psk;
    }

    public String getEmail() {
        return email;
    }
}

package com.example.asheransari.habittrack.database_material.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.asheransari.habittrack.R;
import com.example.asheransari.habittrack.database_material.currentVariableClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asher.ansari on 12/2/2016.
 */
public class currentAdapter extends ArrayAdapter<currentVariableClass>{

    public currentAdapter(Activity activity, ArrayList<currentVariableClass> arrayList)
    {
        super(activity,0,arrayList);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View v = convertView;
        if (v == null)
        {
            v = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);

            currentVariableClass variableClass = getItem(position);

            TextView temp = (TextView)v.findViewById(R.id.temp_new);
            temp.setText("name = "+variableClass.getName()+", User Name = "+variableClass.getUname()+ ", Password = "+variableClass.getPsk()+", email = "+variableClass.getEmail()+"\n");
        }
        return v;
    }
}

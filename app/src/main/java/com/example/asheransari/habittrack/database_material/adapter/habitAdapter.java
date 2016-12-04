package com.example.asheransari.habittrack.database_material.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.asheransari.habittrack.R;
import com.example.asheransari.habittrack.database_material.variableContractClass;

import java.util.ArrayList;

/**
 * Created by asher.ansari on 12/3/2016.
 */
public class habitAdapter extends ArrayAdapter<variableContractClass>{

    public habitAdapter(Activity activity, ArrayList<variableContractClass> arrayList)
    {
        super(activity,0,arrayList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null)
        {
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        variableContractClass variableClass = getItem(position);

        TextView detail = (TextView)view.findViewById(R.id.detail);
        TextView date = (TextView)view.findViewById(R.id.date_detial);
        TextView time = (TextView)view.findViewById(R.id.time_detial);

        detail.setText(variableClass.getDetail());
        date.setText("Date : "+variableClass.getDate());
        time.setText("Time : "+variableClass.getTime());

        return view;
    }

}

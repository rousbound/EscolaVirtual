package com.atelievirtual.escolavirtual;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ItemAdapter extends BaseAdapter {


    LayoutInflater mInflater;
    List nameList;
    List jobList;
    //String[] listItems;

    public ItemAdapter(Context c, List names, List jobs)
    {
        nameList = names;
        jobList = jobs;
        Log.i("Print", "NAME:" + nameList.toString());
        Log.i("Print", "JOB:"+ jobList.toString());
        //listItems = items.toArray();
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return nameList.size();
    }

    @Override
    public Object getItem(int i) {
        return nameList.get(i).toString();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = mInflater.inflate(R.layout.list_model, null);
        TextView nameTextView = (TextView) v.findViewById(R.id.nameView);
        TextView jobView = (TextView) v.findViewById(R.id.jobView);
        String name = nameList.get(i).toString();
        String job = jobList.get(i).toString();
        nameTextView.setText(name);
        jobView.setText(job);
        return v;
    }
}
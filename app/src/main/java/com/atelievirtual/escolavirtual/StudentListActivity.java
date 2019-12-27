package com.atelievirtual.escolavirtual;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;


public class StudentListActivity extends AppCompatActivity {




    DatabaseHelper mDatabaseHelper;
    ListView myListView;
    String RoomName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Print","Entered List Screen");
        setContentView(R.layout.list_items);


        mDatabaseHelper = new DatabaseHelper(this);
        myListView = (ListView) findViewById(R.id.myListView);

        Intent in = getIntent();
        RoomName = in.getStringExtra("RoomName");

        Log.i("Print", "Intent Extra:" + RoomName);
        populateStudentListView();





    }

    private void populateStudentListView()
    {
        Log.i("Print","Entered populate Student List View");
        Cursor data = mDatabaseHelper.getData("StudentData");




        ArrayList<String> listDataName = new ArrayList<>();
        ArrayList<String> listDataJob = new ArrayList<>();
        final ArrayList<Integer> listID = new ArrayList<>();


        if(RoomName == null)
        {
            setTitle("All Students");
            Log.i("Print","Roomname = null");
            while(data.moveToNext()){
                listDataName.add(data.getString(1));
                listDataJob.add(data.getString(4));
                listID.add(data.getInt(0));

            }
        }
        else
        {
            String title = String.format("Room %s", RoomName);
            setTitle(title);
            Log.i("Print","Roomname != null");
            while(data.moveToNext()){
                if(data.getString(3).equals(RoomName))
                {
                    listDataName.add(data.getString(1));
                    listDataJob.add(data.getString(4));
                    listID.add(data.getInt(0));
                }

            }
        }


        for(Object name : listDataName)
            Log.i("Print", "LIST: " + name.toString());


        ItemAdapter itemAdapter = new ItemAdapter(this, listDataName, listDataJob);
        myListView.setAdapter(itemAdapter);


        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent showDetailActivity = new Intent(getApplicationContext(), StudentDetail.class);
                showDetailActivity.putExtra("ITEM_INDEX", i);
                showDetailActivity.putExtra("IDCLICKED",listID.get(i));
                Log.i("Print", "IDCLICKED:" + listID.get(i).toString());

                startActivity(showDetailActivity);

            }
        });
    }





    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

}

package com.atelievirtual.escolavirtual;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;



public class RoomListActivity extends AppCompatActivity {




    DatabaseHelper mDatabaseHelper;
    ListView myListView;
    boolean isLongClick = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Print", "Entered List Screen");
        setContentView(R.layout.list_items);


        mDatabaseHelper = new DatabaseHelper(this);
        myListView = (ListView) findViewById(R.id.myListView);

        Intent in = getIntent();

        String RoomName = in.getStringExtra("Room");

        Log.i("Print", "Entered List View");
        Log.i("Print", "Intent Extra:" + RoomName);
        populateRoomListView();

    }


    private void populateRoomListView()
    {
        final Cursor data = mDatabaseHelper.getData("RoomData");

        ArrayList<String> listDataName = new ArrayList<>();
        ArrayList<String> listDataDate = new ArrayList<>();
        final ArrayList<Integer> listID = new ArrayList<>();

        while(data.moveToNext()){
            listDataName.add(data.getString(1));
            listDataDate.add(data.getString(2));
            listID.add(data.getInt(0));
        }

        ItemAdapter itemAdapter = new ItemAdapter(this, listDataName, listDataDate);
        myListView.setAdapter(itemAdapter);




        myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent showStudentsList = new Intent(getApplicationContext(), StudentListActivity.class);
                data.moveToPosition(i);

                String room = data.getString(1);
                Log.i("Print","ROOM CLICKED:" + room);
                showStudentsList.putExtra("RoomName",room);

                startActivity(showStudentsList);
                isLongClick = true;
                return false;
            }
        });

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent showRoomDetailActivity = new Intent(getApplicationContext(), RoomDetail.class);

                showRoomDetailActivity.putExtra("com.example.listapp.ITEM_INDEX", i);
                showRoomDetailActivity.putExtra("ROOM_ID", listID.get(i));
                Log.i("Print", "IDCLICKED:" + listID.get(i).toString());

                if(isLongClick == false)
                {
                    startActivity(showRoomDetailActivity);
                }
                isLongClick = false;

            }
        });
    }



    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

}

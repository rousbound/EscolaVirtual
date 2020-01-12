package com.atelievirtual.escolavirtual;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.atelievirtual.escolavirtual.R;


public class

MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen_grid);

        Log.i("Print","Entered MainScreen");

        setTitle("Home");
        CardView gotoList = (CardView) findViewById(R.id.allStudentsCardView);
        CardView createProfile = (CardView) findViewById(R.id.createStudentsCardView);
        CardView gotoRoomList = (CardView) findViewById(R.id.allRoomsCardView);
        CardView createNewRoom = (CardView) findViewById(R.id.createRoomCardView);

        gotoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showStudentsList = new Intent(MainActivity.this, StudentListActivity.class);

                startActivity(showStudentsList);
            }
        });

        createProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, StudentDetail.class));
            }
        });

        gotoRoomList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showRoomList = new Intent(MainActivity.this, RoomListActivity.class);

                startActivity(showRoomList);
            }
        });

        createNewRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showRoomDetail = new Intent(MainActivity.this, RoomDetail.class);

                startActivity(showRoomDetail);
            }
        });

    }



}
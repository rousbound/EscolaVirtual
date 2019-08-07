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


        /*Button gotoList = (Button) findViewById(R.id.allStudentsListButton);
        Button createProfile = (Button) findViewById(R.id.createProfileButton);
        Button gotoRoomList = (Button) findViewById(R.id.allRoomsListButton);
        Button createNewRoom = (Button) findViewById(R.id.createRoomButton);

        gotoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showStudentsList = new Intent(MainActivity.this, StudentListActivity.class);
                //showStudentsList.putExtra("WhichList?","Students");
                startActivity(showStudentsList);
            }
        });

        createProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Print", "Goto NewDetail Clicked");
                startActivity(new Intent(MainActivity.this, StudentDetail.class));
            }
        });

        gotoRoomList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showRoomList = new Intent(MainActivity.this, RoomListActivity.class);
                //showRoomList.putExtra("WhichList?","Room");
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
        */

        CardView gotoList = (CardView) findViewById(R.id.allStudentsCardView);
        CardView createProfile = (CardView) findViewById(R.id.createStudentsCardView);
        CardView gotoRoomList = (CardView) findViewById(R.id.allRoomsCardView);
        CardView createNewRoom = (CardView) findViewById(R.id.createRoomCardView);

        gotoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showStudentsList = new Intent(MainActivity.this, StudentListActivity.class);
                //showStudentsList.putExtra("WhichList?","Students");
                startActivity(showStudentsList);
            }
        });

        createProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Print", "Goto NewDetail Clicked");
                startActivity(new Intent(MainActivity.this, StudentDetail.class));
            }
        });

        gotoRoomList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showRoomList = new Intent(MainActivity.this, RoomListActivity.class);
                //showRoomList.putExtra("WhichList?","Room");
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
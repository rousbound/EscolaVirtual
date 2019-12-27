package com.atelievirtual.escolavirtual;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

public class   RoomDetail extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_detail);


        mDatabaseHelper = new DatabaseHelper(this);

        Intent in = getIntent();

        final int index = in.getIntExtra("com.example.listapp.ITEM_INDEX", -1);
        final int ID = in.getIntExtra("ROOM_ID", -1);
        Log.i("Print","ID_RECEIVED:" + Integer.toString(ID));
        Button saveBtn = this.findViewById(R.id.saveButton);


        final EditText nameTextEdit = this.findViewById(R.id.roomNameTextEdit);
        final EditText timeTextEdit = this.findViewById(R.id.timeTextEdit);
        final EditText teacherTextEdit = this.findViewById(R.id.teacherTextEdit);
        final EditText roomNotesTextEdit = this.findViewById(R.id.roomNotesTextEdit);



        if(index > -1)
        {

            setTitle("Room Detail");
            String name,time,teacher;
            final String notes;

            Cursor data = mDatabaseHelper.getDataByID("RoomData",ID);
            //data.moveToPosition(ID);
            data.moveToFirst();
            name = data.getString(1);
            time = data.getString(2);
            teacher = data.getString(3);
            notes = data.getString(4);

            nameTextEdit.setText(name);
            timeTextEdit.setText(time);
            teacherTextEdit.setText(teacher);
            roomNotesTextEdit.setText(notes);


            saveBtn.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   String nameUpdt = nameTextEdit.getText().toString();
                   String timeUpdt = timeTextEdit.getText().toString();
                   String teacherUpdt = teacherTextEdit.getText().toString();
                   String notesUpdt = roomNotesTextEdit.getText().toString();

                   String[] updateInfo = {nameUpdt,timeUpdt,teacherUpdt,notesUpdt};
                   mDatabaseHelper.updateInfo(updateInfo, "RoomData", ID);
                   toastMessage("Saved!");
               }
           });


            Log.i("Print","Greater than One");
        }
        else
        {
            setTitle("Create Room");
            saveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name = nameTextEdit.getText().toString();
                    String time = timeTextEdit.getText().toString();
                    String teacher = teacherTextEdit.getText().toString();
                    String notes = roomNotesTextEdit.getText().toString();

                    if (name.length() != 0) {

                        AddData(name,time,teacher,notes);
                    } else {
                        toastMessage("You must put something in the name text field!");
                    }
                }
            });

            Log.i("Print","Minus One");
        }



        hideKeyboard(this);
    }

    public void AddData(String name,String time, String teacher, String notes) {

        String ColumnData[] = {name,time,teacher,notes};
        boolean insertData = mDatabaseHelper.addData(ColumnData,"RoomData");

        if (insertData) {
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("Something went wrong");
        }
    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}

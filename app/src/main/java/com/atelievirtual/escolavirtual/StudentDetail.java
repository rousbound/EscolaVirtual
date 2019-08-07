package com.atelievirtual.escolavirtual;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StudentDetail extends AppCompatActivity {

    DatabaseHelperNew mDatabaseHelper;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_detail);

        mDatabaseHelper = new DatabaseHelperNew(this);

        Intent in = getIntent();

        final int index = in.getIntExtra("ITEM_INDEX", -1);
        final int ID = in.getIntExtra("IDCLICKED", -1);
        Log.i("Print","ID_RECEIVED:" + Integer.toString(ID));
        Button saveBtn = this.findViewById(R.id.saveButton);


        final EditText nameTextEdit = this.findViewById(R.id.nameTextEdit);
        final EditText ageTextEdit = this.findViewById(R.id.ageTextEdit);
        final EditText roomTextEdit = this.findViewById(R.id.roomTextEdit);
        final EditText jobTextEdit = this.findViewById(R.id.jobTextEdit);
        final EditText keysTextEdit = this.findViewById(R.id.keysTextEdit);
        final EditText notesTextEdit = this.findViewById(R.id.notesTextEdit);


        if(index > -1)
        {
            String name,age,room,job,keys,notes;
            Cursor data = mDatabaseHelper.getStudentDataByID(ID);
            data.moveToFirst();
            Log.i("Print", "Cursor Initialized");
            name = data.getString(1);
            age = data.getString(2);
            room = data.getString(3);
            job = data.getString(4);
            keys = data.getString(5);
            notes = data.getString(6);

            nameTextEdit.setText(name);
            ageTextEdit.setText(age);
            roomTextEdit.setText(room);
            jobTextEdit.setText(job);
            keysTextEdit.setText(keys);
            notesTextEdit.setText(notes);


            saveBtn.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   String nameUpdt = nameTextEdit.getText().toString();
                   String ageUpdt = ageTextEdit.getText().toString();
                   String roomUpdt = roomTextEdit.getText().toString();
                   String jobUpdt = jobTextEdit.getText().toString();
                   String keysUpdt = keysTextEdit.getText().toString();
                   String notesUpdt = notesTextEdit.getText().toString();

                   String[] updateInfo = {nameUpdt,ageUpdt,roomUpdt,jobUpdt,keysUpdt,notesUpdt};
                   mDatabaseHelper.updateInfo(updateInfo, ID, 0);
                   toastMessage("Saved!");
               }
           });

            Log.i("Print","Greater than One");
        }
        else
        {
            saveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name = nameTextEdit.getText().toString();
                    String age = ageTextEdit.getText().toString();
                    String room = roomTextEdit.getText().toString();
                    String job = jobTextEdit.getText().toString();
                    String keys = keysTextEdit.getText().toString();
                    String notes = notesTextEdit.getText().toString();

                    if (name.length() != 0) {

                        AddData(name,age,room,job,keys,notes);
                    } else {
                        toastMessage("You must put something in the name text field!");
                    }
                }
            });

            Log.i("Print","Minus One");
        }




    }

    public void AddData(String name, String age, String room, String job, String keys, String notes) {

        String ColumnData[] = {name,age,room,job,keys,notes};
        boolean insertData = mDatabaseHelper.addData(ColumnData,"StudentData",0);

        if (insertData) {
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("Something went wrong");
        }
    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }


}

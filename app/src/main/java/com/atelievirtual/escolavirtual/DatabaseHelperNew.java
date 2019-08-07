package com.atelievirtual.escolavirtual;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by User on 2/28/2017.
 */

public class DatabaseHelperNew extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";


    private static final String DBNAME = "EscolaVirtualDB";
    private static final String COL_ID = "ID";

    private String[] StudentDataArr = new String[]{"name","age","room","job","keys","notes"};
    private String[] ClassroomDataArr = new String[]{"name","time","teacher","notes"};
    private String[][] StringData = new String[][]{this.StudentDataArr,this.ClassroomDataArr};
    private String[] TABLE_ARRAY = new String[]{"StudentData","ClassroomData"};




    public DatabaseHelperNew(Context context) {
        super(context, DBNAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTable(db, StudentDataArr, TABLE_ARRAY[0]);
        createTable(db, ClassroomDataArr, TABLE_ARRAY[1]);




    }

    private void createTable(SQLiteDatabase db,String[] COLS, String TABLE_NAME) {
        String createTable = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, ";
        for (int i = 0; i < COLS.length; i++) {
            createTable += COLS[i];
            if (i < COLS.length - 1)
                createTable += " TEXT, ";
            else
                createTable += " TEXT)";
        }

        Log.i("Print", "QUERY:" + createTable);

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_ARRAY[0]);
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_ARRAY[1]);
        onCreate(db);
    }

    public boolean addData(String[] stringData, String TABLE_NAME, int TABLE_INDEX) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        for(int i =0;i<stringData.length;i++)
        {
            contentValues.put(StringData[TABLE_INDEX][i],stringData[i]);
            Log.d("SQL", "addData: Adding " + stringData[i] + " to " + TABLE_NAME);
        }



        long result = db.insert(TABLE_NAME, null, contentValues);
        db.close();
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }


    public Cursor getData(int TABLE_INDEX){


        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_ARRAY[TABLE_INDEX] + " ORDER  BY name ASC";
        Log.i("Print", "QUERY:"+ query);
        Cursor data = db.rawQuery(query, null);
        return data;
    }


    public Cursor getStudentDataByID(int ID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_ARRAY[0] + " WHERE ID = " + ID;
        Log.i("Print", "QUERY:"+ query);
        //String id = String.valueOf(ID);
        Cursor data = db.rawQuery(query, null);
        Log.i("Print", "query returned");
        return data;
    }

    public Cursor getRoomDataByID(int ID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_ARRAY[1] + " WHERE ID = " + ID;
        Log.i("Print", "QUERY:"+ query);
        //String id = String.valueOf(ID);
        Cursor data = db.rawQuery(query, null);
        Log.i("Print", "query returned");
        return data;
    }


    public void updateInfo(String[] newInfo, int id, int TABLE_INDEX)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        for(int i=0;i<StringData[TABLE_INDEX].length;i++)
        {
            cv.put(StringData[TABLE_INDEX][i], newInfo[i]);
        }


        String idPass = COL_ID + " = " + (id);
        db.update(TABLE_ARRAY[TABLE_INDEX],cv,idPass,null);
    }



    public ArrayList<String> returnListArrayData(int column,int index)
    {
        Cursor data = this.getData(index);
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext()){
            listData.add(data.getString(column));
        }
        return listData;
    }

}
package com.atelievirtual.escolavirtual;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 2/28/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";


    private static final String DBNAME = "EscolaVirtualDB";
    private static final String COL_ID = "ID";
    private static Map<String, String[]> TABLES_DATA;

    static {
        TABLES_DATA = new HashMap<String, String[]>();
        TABLES_DATA.put("StudentData", new String[]{"name", "age", "room", "job", "keys", "notes"});
        TABLES_DATA.put("RoomData", new String[]{"name", "time", "teacher", "notes"});
    }



    public DatabaseHelper(Context context) {
        super(context, DBNAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTable(db, TABLES_DATA.get("StudentData"), "StudentData");
        createTable(db, TABLES_DATA.get("RoomData"), "RoomData");



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
        db.execSQL("DROP IF TABLE EXISTS " + "ProfileData");
        db.execSQL("DROP IF TABLE EXISTS " + "RoomData");
        onCreate(db);
    }

    public boolean addData(String[] stringData, String TABLE_NAME) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String[] ArrayData = TABLES_DATA.get(TABLE_NAME);

        for(int i =0;i<stringData.length;i++)
        {
            contentValues.put(ArrayData[i], stringData[i]);
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


    public Cursor getData(String TABLE_NAME){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME + " ORDER  BY name ASC";
        Log.i("Print", "QUERY:"+ query);
        Cursor data = db.rawQuery(query, null);
        return data;
    }


    public Cursor getDataByID(String TABLE_NAME, int ID)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE ID = " + ID;
        Log.i("Print", "QUERY:"+ query);
        Cursor data = db.rawQuery(query, null);
        Log.i("Print", "query returned");
        return data;
    }


    public void updateInfo(String[] newInfo, String TABLE_NAME, int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        String[] ArrayData = TABLES_DATA.get(TABLE_NAME);

        for(int i=0;i<ArrayData.length;i++)
        {
            cv.put(ArrayData[i], newInfo[i]);
        }


        String idPass = COL_ID + " = " + (id);
        db.update(TABLE_NAME,cv,idPass,null);
    }



    public ArrayList<String> returnListArrayData(int column, String TABLE_NAME)
    {
        Cursor data = this.getData(TABLE_NAME);
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext()){
            listData.add(data.getString(column));
        }
        return listData;
    }

}
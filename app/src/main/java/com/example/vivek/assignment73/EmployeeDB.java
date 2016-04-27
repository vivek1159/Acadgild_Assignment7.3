package com.example.vivek.assignment73;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;

/**
 * Created by vivek on 27-04-2016.
 */
public class EmployeeDB extends SQLiteOpenHelper{


    SQLiteDatabase db;

    public static String DATABASE_NAME = "Employee.db";
    public static int DATABASE_VERSION = 1;

    /* EMPLOYEE_DETAILS TABLE details */
    public static String TABLE_NAME = "EMPLOYEE_DETAILS";

    public static String FIRST_NAME = "FIRST_NAME";
    public static String LAST_NAME = "LAST_NAME";
    public static String AGE = "AGE";
    public static String EMPLOYEE_PHOTO = "EMPLOYEE_PHOTO";


    String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + FIRST_NAME + " TEXT NOT NULL," +
            AGE + " NUMBER NOT NULL," +LAST_NAME + " TEXT NOT NULL," + EMPLOYEE_PHOTO + " BLOB NOT NULL)";

    public EmployeeDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(EmployeeDB.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);
    }

    public void addEmployee(ContentValues values) {
        db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
    }

    public ContentValues getEmployee() {
        db = this.getReadableDatabase();

        ContentValues values = new ContentValues();

        String query = "SELECT * FROM EMPLOYEE_DETAILS";
        Cursor cursor = db.rawQuery(query,null);
        if (cursor!=null){
            cursor.moveToFirst();
            values.put(FIRST_NAME, cursor.getString(cursor.getColumnIndex(FIRST_NAME)));
            values.put(LAST_NAME, cursor.getString(cursor.getColumnIndex(LAST_NAME)));
            values.put(AGE, cursor.getInt(cursor.getColumnIndex(AGE)));
            values.put(EMPLOYEE_PHOTO,cursor.getBlob(cursor.getColumnIndex(EMPLOYEE_PHOTO)));
            cursor.close();
            return values;
        }
        else {
            return null;
        }
    }
}

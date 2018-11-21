package vn.edu.poly.duan1.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import vn.edu.poly.duan1.Constant;

public class DatabaseHelper extends SQLiteOpenHelper implements Constant{

    public DatabaseHelper(Context context){
        super(context, "patientmanagement", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_PATIENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_PATIENT);
        onCreate(sqLiteDatabase);
    }
}

package vn.edu.poly.duan1.sqlitedao;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import vn.edu.poly.duan1.Constant;
import vn.edu.poly.duan1.PatientManagementActivity;
import vn.edu.poly.duan1.database.DatabaseHelper;
import vn.edu.poly.duan1.model.PatientManagement;

public class PatientDAO implements Constant {
    private DatabaseHelper databaseHelper;
    private Context context;

    public PatientDAO(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public PatientDAO(Context context) {
        this.context = context;
    }

    public void Insert(PatientManagement patientManagement) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, patientManagement.getName());
        contentValues.put(COLUMN_ROOM, patientManagement.getRoom());
        contentValues.put(COLUMN_CODE, patientManagement.getCode());
        contentValues.put(COLUMN_GENDER, patientManagement.getGender());
        contentValues.put(COLUMN_AGE, patientManagement.getAge());
        contentValues.put(COLUMN_BLOOD, patientManagement.getBlood());
        contentValues.put(COLUMN_DOCTOR, patientManagement.getDoctor());
        contentValues.put(COLUMN_NURSE, patientManagement.getNurse());
        contentValues.put(COLUMN_SYMPTOM, patientManagement.getSymptom());
        contentValues.put(COLUMN_DIAGNOSE, patientManagement.getDiagnose());
        contentValues.put(COLUMN_TREATMENT, patientManagement.getTreatment());

        sqLiteDatabase.insert(databaseHelper.TABLE_PATIENT, null, contentValues);
        sqLiteDatabase.close();
    }

    public List<PatientManagement> getAllPatient() {
        List<PatientManagement> listPatient = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        String SELECT_ALL_PATIENTS = "SELECT * FROM " + TABLE_PATIENT;

        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_PATIENTS, null);

        if (cursor.moveToFirst()) {
            do {
                PatientManagement patientManagement = new PatientManagement();

                patientManagement.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                patientManagement.setRoom(cursor.getString(cursor.getColumnIndex(COLUMN_ROOM)));
                patientManagement.setCode(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_CODE))));
                patientManagement.setAge(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_AGE))));
                patientManagement.setBlood(cursor.getString(cursor.getColumnIndex(COLUMN_BLOOD)));
                patientManagement.setSymptom(cursor.getString(cursor.getColumnIndex(COLUMN_SYMPTOM)));
                patientManagement.setDiagnose(cursor.getString(cursor.getColumnIndex(COLUMN_DIAGNOSE)));
                patientManagement.setDoctor(cursor.getString(cursor.getColumnIndex(COLUMN_DOCTOR)));
                patientManagement.setNurse(cursor.getString(cursor.getColumnIndex(COLUMN_NURSE)));
                patientManagement.setTreatment(cursor.getString(cursor.getColumnIndex(COLUMN_TREATMENT)));
                patientManagement.setHealthStatus(cursor.getString(cursor.getColumnIndex(COLUMN_HEALTH_STATUS)));

            } while (cursor.moveToNext());
        }
        return listPatient;
    }

    public boolean deletePatient(PatientManagement patientManagement){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        int check = sqLiteDatabase.delete(TABLE_PATIENT, TABLE_PATIENT + " = " + patientManagement.getCode(), null );
        if (check != 0){
            return true;
        }else {
            return false;
        }
    }

    public long updatePatient(PatientManagement patientManagement){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, patientManagement.getName());
        contentValues.put(COLUMN_ROOM, patientManagement.getRoom());
        contentValues.put(COLUMN_CODE, patientManagement.getCode());
        contentValues.put(COLUMN_GENDER, patientManagement.getGender());
        contentValues.put(COLUMN_AGE, patientManagement.getAge());
        contentValues.put(COLUMN_BLOOD, patientManagement.getBlood());
        contentValues.put(COLUMN_DOCTOR, patientManagement.getDoctor());
        contentValues.put(COLUMN_NURSE, patientManagement.getName());
        contentValues.put(COLUMN_SYMPTOM, patientManagement.getSymptom());
        contentValues.put(COLUMN_DIAGNOSE, patientManagement.getDiagnose());
        contentValues.put(COLUMN_TREATMENT, patientManagement.getTreatment());

        return sqLiteDatabase.update(TABLE_PATIENT, contentValues, TABLE_PATIENT + "=?", new String[]{String.valueOf(patientManagement.getCode())});
    }

}

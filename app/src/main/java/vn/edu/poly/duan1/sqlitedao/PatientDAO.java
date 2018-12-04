package vn.edu.poly.duan1.sqlitedao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.duan1.Constant;
import vn.edu.poly.duan1.database.DatabaseHelper;
import vn.edu.poly.duan1.model.PatientManagement;

public class PatientDAO implements Constant {
    DatabaseHelper databaseHelper;
    Context context;
//    private static final String TAG = "Patient";

    public PatientDAO(Context context) {
        this.context = context;
        databaseHelper = new DatabaseHelper(context);
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
        contentValues.put(COLUMN_HEALTH_STATUS, patientManagement.getHealthStatus());
        sqLiteDatabase.insert(TABLE_PATIENT, null, contentValues);
        sqLiteDatabase.close();

//        try {
//            if (sqLiteDatabase.insert(TABLE_PATIENT, null, contentValues) == -1){
//                return -1;
//            }
//        }catch (Exception ex){
//            Log.e(TAG, ex.toString());
//        }
//        return 1;
    }

    public List<PatientManagement> getAllPatient() {
        List<PatientManagement> listPatient = new ArrayList<PatientManagement>();
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        String SELECT_ALL_PATIENTS = "SELECT * FROM " + databaseHelper.TABLE_PATIENT;

        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_PATIENTS, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            PatientManagement patientManagement = new PatientManagement();

            patientManagement.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
            patientManagement.setRoom(cursor.getString(cursor.getColumnIndex(COLUMN_ROOM)));
            patientManagement.setCode(cursor.getString(cursor.getColumnIndex(COLUMN_CODE)));
            patientManagement.setAge(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_AGE))));
            patientManagement.setBlood(cursor.getString(cursor.getColumnIndex(COLUMN_BLOOD)));
            patientManagement.setSymptom(cursor.getString(cursor.getColumnIndex(COLUMN_SYMPTOM)));
            patientManagement.setDiagnose(cursor.getString(cursor.getColumnIndex(COLUMN_DIAGNOSE)));
            patientManagement.setDoctor(cursor.getString(cursor.getColumnIndex(COLUMN_DOCTOR)));
            patientManagement.setNurse(cursor.getString(cursor.getColumnIndex(COLUMN_NURSE)));
            patientManagement.setTreatment(cursor.getString(cursor.getColumnIndex(COLUMN_TREATMENT)));
            patientManagement.setHealthStatus(cursor.getString(cursor.getColumnIndex(COLUMN_HEALTH_STATUS)));

            listPatient.add(patientManagement);
            cursor.moveToNext();
        }
        return listPatient;
    }

    public boolean deletePatient(PatientManagement patientManagement){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        int check = sqLiteDatabase.delete(TABLE_PATIENT, COLUMN_CODE + " = ?" + patientManagement.getCode(), null);
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
        contentValues.put(COLUMN_DOCTOR, patientManagement.getDoctor());
        contentValues.put(COLUMN_NURSE, patientManagement.getName());
        contentValues.put(COLUMN_SYMPTOM, patientManagement.getSymptom());
        contentValues.put(COLUMN_DIAGNOSE, patientManagement.getDiagnose());
        contentValues.put(COLUMN_TREATMENT, patientManagement.getTreatment());

        return sqLiteDatabase.update(TABLE_PATIENT, contentValues, TABLE_PATIENT + "=?", new String[]{String.valueOf(patientManagement.getCode())});
    }

    public PatientManagement CodePatient(String code){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        String table = "SELECT * FROM " + databaseHelper.TABLE_PATIENT + " WHERE "
                + databaseHelper.TABLE_PATIENT + " AND " + databaseHelper.COLUMN_CODE + " = " + code;
        Cursor cursor = sqLiteDatabase.rawQuery(table, null);
        cursor.moveToFirst();
        PatientManagement patientManagement = new PatientManagement();

        while (!cursor.isAfterLast()){
            patientManagement.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
            patientManagement.setRoom(cursor.getString(cursor.getColumnIndex(COLUMN_ROOM)));
            patientManagement.setCode(cursor.getString(cursor.getColumnIndex(COLUMN_CODE)));
            patientManagement.setAge(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_AGE))));
            patientManagement.setBlood(cursor.getString(cursor.getColumnIndex(COLUMN_BLOOD)));
            patientManagement.setSymptom(cursor.getString(cursor.getColumnIndex(COLUMN_SYMPTOM)));
            patientManagement.setDiagnose(cursor.getString(cursor.getColumnIndex(COLUMN_DIAGNOSE)));
            patientManagement.setDoctor(cursor.getString(cursor.getColumnIndex(COLUMN_DOCTOR)));
            patientManagement.setNurse(cursor.getString(cursor.getColumnIndex(COLUMN_NURSE)));
            patientManagement.setTreatment(cursor.getString(cursor.getColumnIndex(COLUMN_TREATMENT)));
            patientManagement.setHealthStatus(cursor.getString(cursor.getColumnIndex(COLUMN_HEALTH_STATUS)));
        }
        return patientManagement;
    }

}

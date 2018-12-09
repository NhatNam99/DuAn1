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
            patientManagement.setCode(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_CODE))));
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

    public List<PatientManagement> searchPatient(String code) {
        List<PatientManagement> listPatient = new ArrayList<PatientManagement>();
        String[] column = {DatabaseHelper.COLUMN_CODE,DatabaseHelper.COLUMN_HEALTH_STATUS,DatabaseHelper.COLUMN_TREATMENT,DatabaseHelper.COLUMN_SYMPTOM,DatabaseHelper.COLUMN_DIAGNOSE,DatabaseHelper.COLUMN_DOCTOR,DatabaseHelper.COLUMN_BLOOD,DatabaseHelper.COLUMN_AGE,DatabaseHelper.COLUMN_GENDER,DatabaseHelper.COLUMN_NAME,DatabaseHelper.COLUMN_ROOM,DatabaseHelper.COLUMN_NURSE};

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        String SELECT_PATIENTS = "SELECT " + column[0] + "," + column[1] + "," + column[2] + "," + column[3] + "," + column[4] + "," + column[5] + "," + column[6]
        + "," + column[7] + "," + column[8] + "," + column[9] + "," + column[10] + "," + column[11] + " FROM " + databaseHelper.TABLE_PATIENT + " WHERE "
                + databaseHelper.COLUMN_CODE + " LIKE '%" + code + "%'";

        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_PATIENTS, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            PatientManagement patientManagement = new PatientManagement();

            patientManagement.setName(cursor.getString(9));
            patientManagement.setRoom(cursor.getString(10));
            patientManagement.setCode(Integer.parseInt(cursor.getString(0)));
            patientManagement.setGender(cursor.getString(8));
            patientManagement.setAge(Integer.parseInt(cursor.getString(7)));
            patientManagement.setBlood(cursor.getString(6));
            patientManagement.setSymptom(cursor.getString(3));
            patientManagement.setDiagnose(cursor.getString(4));
            patientManagement.setDoctor(cursor.getString(5));
            patientManagement.setNurse(cursor.getString(11));
            patientManagement.setTreatment(cursor.getString(2));
            patientManagement.setHealthStatus(cursor.getString(1));

            listPatient.add(patientManagement);
            cursor.moveToNext();
        }
        return listPatient;
    }

    public boolean deletePatient(PatientManagement patientManagement) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        int check = sqLiteDatabase.delete(TABLE_PATIENT, COLUMN_CODE + " = " + patientManagement.getCode(), null);
        if (check != 0) {
            return true;
        } else {
            return false;
        }
    }

    public long updatePatient(PatientManagement patientManagement) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, patientManagement.getName());
        contentValues.put(COLUMN_ROOM, patientManagement.getRoom());
        contentValues.put(COLUMN_CODE, patientManagement.getCode());
        contentValues.put(COLUMN_GENDER, patientManagement.getGender());
        contentValues.put(COLUMN_AGE, patientManagement.getAge());
        contentValues.put(COLUMN_DOCTOR, patientManagement.getDoctor());
        contentValues.put(COLUMN_NURSE, patientManagement.getNurse());
        contentValues.put(COLUMN_SYMPTOM, patientManagement.getSymptom());
        contentValues.put(COLUMN_DIAGNOSE, patientManagement.getDiagnose());
        contentValues.put(COLUMN_TREATMENT, patientManagement.getTreatment());
        contentValues.put(COLUMN_HEALTH_STATUS, patientManagement.getHealthStatus());

        return sqLiteDatabase.update(TABLE_PATIENT, contentValues, COLUMN_CODE + "=?", new String[]{String.valueOf(patientManagement.getCode())});
    }

    public PatientManagement CodePatient(int code) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
//        Cursor cursor = sqLiteDatabase.query
//                (TABLE_PATIENT, new String[]{COLUMN_NAME, COLUMN_CODE, COLUMN_ROOM, COLUMN_GENDER, COLUMN_AGE, COLUMN_BLOOD, COLUMN_DOCTOR, COLUMN_NURSE, COLUMN_DIAGNOSE, COLUMN_SYMPTOM, COLUMN_TREATMENT, COLUMN_HEALTH_STATUS},
//                        COLUMN_CODE + "=?", new String[]{code},
//                        null, null, null, null);
        String sql = "SELECT * FROM " + databaseHelper.TABLE_PATIENT + " WHERE " + databaseHelper.COLUMN_CODE + " = " + code;
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        cursor.moveToFirst();
        PatientManagement patientManagement = new PatientManagement();

        while (!cursor.isAfterLast()) {
            patientManagement.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
            patientManagement.setRoom(cursor.getString(cursor.getColumnIndex(COLUMN_ROOM)));
            patientManagement.setCode(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_CODE))));
            patientManagement.setAge(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_AGE))));
            patientManagement.setGender(cursor.getString(cursor.getColumnIndex(COLUMN_GENDER)));
            patientManagement.setBlood(cursor.getString(cursor.getColumnIndex(COLUMN_BLOOD)));
            patientManagement.setSymptom(cursor.getString(cursor.getColumnIndex(COLUMN_SYMPTOM)));
            patientManagement.setDiagnose(cursor.getString(cursor.getColumnIndex(COLUMN_DIAGNOSE)));
            patientManagement.setDoctor(cursor.getString(cursor.getColumnIndex(COLUMN_DOCTOR)));
            patientManagement.setNurse(cursor.getString(cursor.getColumnIndex(COLUMN_NURSE)));
            patientManagement.setTreatment(cursor.getString(cursor.getColumnIndex(COLUMN_TREATMENT)));
            patientManagement.setHealthStatus(cursor.getString(cursor.getColumnIndex(COLUMN_HEALTH_STATUS)));
            cursor.moveToNext();
        }
        return patientManagement;
    }

}

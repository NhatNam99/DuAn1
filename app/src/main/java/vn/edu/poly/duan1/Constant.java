package vn.edu.poly.duan1;

public interface Constant {

    String TABLE_PATIENT = "Patient";

    String COLUMN_NAME = "Name";

    String COLUMN_ROOM = "Room";

    String COLUMN_CODE = "Code";

    String COLUMN_GENDER = "Gender";

    String COLUMN_AGE = "Age";

    String COLUMN_BLOOD = "Blood";

    String COLUMN_DOCTOR = "Doctor";

    String COLUMN_NURSE = "NURSE";

    String COLUMN_SYMPTOM = "Symptom";

    String COLUMN_DIAGNOSE = "Diagnose";

    String COLUMN_TREATMENT= "Treatment";

    String COLUMN_HEALTH_STATUS = "HealthStatus";

    String CREATE_TABLE_PATIENT = "CREATE TABLE " + TABLE_PATIENT + " (" +
            "" + COLUMN_NAME + " VARCHAR," +
            "" + COLUMN_ROOM + " INTEGER," +
            "" + COLUMN_CODE + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            "" + COLUMN_GENDER + " VARCHAR," +
            "" + COLUMN_AGE + " INTEGER," +
            "" + COLUMN_BLOOD + "VARCHAR," +
            "" + COLUMN_DOCTOR + " VARCHAR," +
            "" + COLUMN_NURSE + " VARCHAR," +
            "" + COLUMN_SYMPTOM + " VARCHAR," +
            "" + COLUMN_DIAGNOSE + " VARCHAR," +
            "" + COLUMN_TREATMENT + " VARCHAR," +
            "" + COLUMN_HEALTH_STATUS + " VARCHAR" +
            ")";

}

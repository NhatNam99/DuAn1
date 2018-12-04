package vn.edu.poly.duan1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.List;

import vn.edu.poly.duan1.model.PatientManagement;
import vn.edu.poly.duan1.sqlitedao.PatientDAO;

public class UpdatePatientActivity extends AppCompatActivity {
    PatientDAO patientDAO;
    private Spinner spStatus;
    private EditText edtName, edtRoom, edtCode, edtBlood, edtAge, edtDoctor, edtNurse, edtSymptom, edtDiagnose, edtTreatment;
    String Status;
    private Button btnUpdate, btnCancel;
    private RadioGroup rdGender;
    List<PatientManagement> list;
    private int rdgender, vitriStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_patient);

        btnCancel = findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdatePatientActivity.this, PatientManagementActivity.class);
                startActivity(intent);
            }
        });
    }

    public void viewID() {
        edtName = findViewById(R.id.edtName);
        edtRoom = findViewById(R.id.edtRoom);
        edtCode = findViewById(R.id.edtCode);
        rdGender = findViewById(R.id.rdGender);
        edtAge = findViewById(R.id.edtAge);
        edtDoctor = findViewById(R.id.edtDoctor);
        edtNurse = findViewById(R.id.edtNurse);
        edtSymptom = findViewById(R.id.edtSymptom);
        edtDiagnose = findViewById(R.id.edtDiagnose);
        edtTreatment = findViewById(R.id.edtTreatment);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnCancel = findViewById(R.id.btnCancel);
    }


}

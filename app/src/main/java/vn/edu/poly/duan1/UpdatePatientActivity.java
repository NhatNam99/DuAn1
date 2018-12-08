package vn.edu.poly.duan1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.duan1.model.PatientManagement;
import vn.edu.poly.duan1.sqlitedao.PatientDAO;

public class UpdatePatientActivity extends AppCompatActivity {
    PatientDAO patientDAO;
    private Spinner spStatus;
    private EditText edtName, edtRoom, edtAge, edtDoctor, edtNurse, edtSymptom, edtDiagnose, edtTreatment;
    private String spnHealthStatus;
    private Button btnUpdate, btnCancel;
    private RadioGroup rdGender;
    private int rdgender, vitriStatus;
    int codepatient;
    private final List<String> arrayListStatus = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_patient);
        viewID();

        btnCancel = findViewById(R.id.btnCancel);
        spStatus = findViewById(R.id.spStatus);

        patientDAO = new PatientDAO(this);

        arrayListStatus.add("Good");
        arrayListStatus.add("Normal");
        arrayListStatus.add("Bad");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrayListStatus);
        spStatus.setAdapter(arrayAdapter);

        spStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                spnHealthStatus = adapterView.getItemAtPosition(position).toString();
                vitriStatus = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Intent intent = getIntent();
        codepatient = intent.getExtras().getInt("CodePatient");
        this.setResult(RESULT_OK);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rdgender = rdGender.getCheckedRadioButtonId();
                RadioButton rdCheck = findViewById(rdgender);
                String Gender = rdCheck.getText().toString();

                PatientManagement patientManagement = new PatientManagement();
                patientManagement.setCode(codepatient);
                patientManagement.setName(edtName.getText().toString());
                patientManagement.setRoom(edtRoom.getText().toString());
                patientManagement.setAge(Integer.parseInt(edtAge.getText().toString()));
                patientManagement.setDoctor(edtDoctor.getText().toString());
                patientManagement.setNurse(edtNurse.getText().toString());
                patientManagement.setDiagnose(edtRoom.getText().toString());
                patientManagement.setSymptom(edtSymptom.getText().toString());
                patientManagement.setTreatment(edtTreatment.getText().toString());
                patientManagement.setGender(Gender);
                spStatus.setSelection(checkPositionStatus(spnHealthStatus));
                if(patientDAO.updatePatient(patientManagement) != -1){
                    Toast.makeText(UpdatePatientActivity.this, "Update Success", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(UpdatePatientActivity.this, "Update Failed", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(UpdatePatientActivity.this, PatientManagementActivity.class);
                startActivity(intent);
            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdatePatientActivity.this, PatientManagementActivity.class);
                startActivity(intent);
            }
        });
    }
    public void viewID () {
        edtName = findViewById(R.id.edtName);
        edtRoom = findViewById(R.id.edtRoom);
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

    private int checkPositionStatus(String health) {
        for (int i = 0; i < arrayListStatus.size(); i++) {
            if (health.equals(arrayListStatus.get(i))) {
                return i;
            }
        }
        return 0;
    }
}









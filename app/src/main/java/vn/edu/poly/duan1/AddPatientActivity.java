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

public class AddPatientActivity extends AppCompatActivity{

    EditText edtName, edtRoom, edtCode, edtBlood, edtAge, edtDoctor, edtNurse, edtSymptom, edtDiagnose, edtTreatment;
    Spinner spnHealthStatus;
    String Status;
    Button btnAdd, btnCancel;
    RadioGroup rdGender;
    List<PatientManagement> list;
    PatientDAO patientDAO;
    int rdgender, vitriStatus;
    private final List<String> arrayListStatus = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);
        viewID();

        patientDAO = new PatientDAO(this);
        spnHealthStatus = findViewById(R.id.spStatus);
        list = new ArrayList<PatientManagement>();

        List<String> arrayListStatus = new ArrayList<>();
        arrayListStatus.add("Good");
        arrayListStatus.add("Normal");
        arrayListStatus.add("Bad");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrayListStatus);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnHealthStatus.setAdapter(arrayAdapter);
        spnHealthStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                Status = adapterView.getItemAtPosition(position).toString();
                vitriStatus = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rdgender = rdGender.getCheckedRadioButtonId();
                RadioButton rdCheck = findViewById(rdgender);
                String Gender = rdCheck.getText().toString();

                try {
                    PatientManagement patientManagement = new PatientManagement();
                    patientManagement.setName(edtName.getText().toString());
                    patientManagement.setRoom(edtRoom.getText().toString());
                    patientManagement.setCode(Integer.parseInt(edtCode.getText().toString()));
                    patientManagement.setBlood(edtBlood.getText().toString());
                    patientManagement.setAge(Integer.parseInt(edtAge.getText().toString()));
                    patientManagement.setDoctor(edtDoctor.getText().toString());
                    patientManagement.setNurse(edtNurse.getText().toString());
                    patientManagement.setDiagnose(edtRoom.getText().toString());
                    patientManagement.setSymptom(edtSymptom.getText().toString());
                    patientManagement.setTreatment(edtTreatment.getText().toString());
                    patientManagement.setGender(Gender);
                    spnHealthStatus.setSelection(checkPositionStatus(Status));
                    patientDAO.Insert(patientManagement);
                    Toast.makeText(AddPatientActivity.this, "Add Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddPatientActivity.this, PatientManagementActivity.class);
                    startActivity(intent);
                } catch (Exception e){
                    Toast.makeText(AddPatientActivity.this, "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddPatientActivity.this, PatientManagementActivity.class);
                startActivity(intent);
            }
        });
    }

    public void viewID() {
        edtName = findViewById(R.id.edtName);
        edtRoom = findViewById(R.id.edtRoom);
        edtCode = findViewById(R.id.edtCode);
        rdGender = findViewById(R.id.rdGender);
        edtBlood = findViewById(R.id.edtBlood);
        edtAge = findViewById(R.id.edtAge);
        edtDoctor = findViewById(R.id.edtDoctor);
        edtNurse = findViewById(R.id.edtNurse);
        edtSymptom = findViewById(R.id.edtSymptom);
        edtDiagnose = findViewById(R.id.edtDiagnose);
        edtTreatment = findViewById(R.id.edtTreatment);
        btnAdd = findViewById(R.id.btnAdd);
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

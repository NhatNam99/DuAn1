package vn.edu.poly.duan1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import vn.edu.poly.duan1.model.PatientManagement;
import vn.edu.poly.duan1.sqlitedao.PatientDAO;

public class PatientDetailsActivity extends AppCompatActivity {
    TextView TVNAME, TVCODE, TVROOM, TVGENDER, TVAGE, TVBLOOD, TVDOCTOR, TVNURSE, TVSYMPTOM, TVDIAGNOSE, TVTREATMENT, TVSTATUS;
    PatientDAO patientDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);

        TVNAME = findViewById(R.id.Tvname);
        TVROOM = findViewById(R.id.Tvroom);
        TVGENDER = findViewById(R.id.Tvgender);
        TVCODE = findViewById(R.id.Tvcode);
        TVAGE = findViewById(R.id.Tvage);
        TVBLOOD = findViewById(R.id.Tvblood);
        TVDOCTOR = findViewById(R.id.Tvdoctor);
        TVNURSE = findViewById(R.id.Tvnurse);
        TVSYMPTOM = findViewById(R.id.Tvsymptom);
        TVDIAGNOSE =findViewById(R.id.Tvdiagnose);
        TVTREATMENT =findViewById(R.id.Tvtreatment);
        TVSTATUS = findViewById(R.id.TvhealthStatus);

        patientDAO = new PatientDAO(this);
        int code = getIntent().getExtras().getInt("CodePatient");
        PatientManagement patientManagement = new PatientManagement();
        patientManagement = patientDAO.CodePatient(code);

        TVNAME.setText(patientManagement.getName().toString());
        TVCODE .setText(String.valueOf(patientManagement.getCode()));
        TVROOM.setText(patientManagement.getRoom().toString());
        TVGENDER.setText(patientManagement.getGender().toString());
        TVAGE.setText(String.valueOf(patientManagement.getAge()));
        TVBLOOD.setText(patientManagement.getBlood().toString());
        TVDOCTOR.setText(patientManagement.getDoctor().toString());
        TVNURSE.setText(patientManagement.getNurse().toString());
        TVSYMPTOM.setText(patientManagement.getSymptom().toString());
        TVDIAGNOSE.setText(patientManagement.getDiagnose().toString());
        TVTREATMENT.setText(patientManagement.getTreatment().toString());
        TVSTATUS.setText(String.valueOf(patientManagement.getHealthStatus()));
    }
}

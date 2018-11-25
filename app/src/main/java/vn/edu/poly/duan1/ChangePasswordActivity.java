package vn.edu.poly.duan1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import vn.edu.poly.duan1.model.PatientManagement;

public class ChangePasswordActivity extends AppCompatActivity {

    EditText edtpassw, edtpassnew, edtconfirm;
    SharedPreferences sharedPreferences;
    Button btnssave, btnCanCel;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        edtpassw = findViewById(R.id.edtPass);
        edtpassnew = findViewById(R.id.edtPassNew);
        edtconfirm = findViewById(R.id.edtConfirm);

        btnssave = findViewById(R.id.btnSave);
        btnCanCel = findViewById(R.id.btnCancel);
        sharedPreferences = getSharedPreferences("config", Context.MODE_PRIVATE);
        btnssave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass = sharedPreferences.getString("Password", "");
                String Pass = edtpassw.getText().toString();
                String passnew = edtpassnew.getText().toString().trim();
                String confirm = edtconfirm.getText().toString().trim();

                if (Pass.isEmpty() || passnew.isEmpty() || confirm.isEmpty()) {
                    if (Pass.isEmpty())
                        edtpassw.setError(getString(R.string.notify_empty_user));
                    if (pass.isEmpty())
                        edtpassnew.setError(getString(R.string.notify_empty_pass));
                    if (confirm.isEmpty())
                        edtconfirm.setError(getString(R.string.notify_empty_pass));
                }

                if (Pass.equals(pass)) {
                    if (!confirm.equals(passnew)) {
                        Toast.makeText(ChangePasswordActivity.this, "Confirm Failed", Toast.LENGTH_SHORT).show();
                    } else {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("Password", passnew);
                        editor.commit();
                        Toast.makeText(ChangePasswordActivity.this, "Change Password Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ChangePasswordActivity.this, PatientManagementActivity.class);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(ChangePasswordActivity.this, "Password Change Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCanCel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChangePasswordActivity.this, PatientManagementActivity.class);
                startActivity(intent);
            }
        });
    }
}

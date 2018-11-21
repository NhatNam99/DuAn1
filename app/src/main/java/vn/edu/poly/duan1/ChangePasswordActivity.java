package vn.edu.poly.duan1;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePasswordActivity extends AppCompatActivity {

    EditText edtpassw, edtpassnew, edtconfirm;
    SharedPreferences sharedPreferences;
    Button btnssave;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        edtpassw = findViewById(R.id.edtPass);
        edtpassnew = findViewById(R.id.edtPassNew);
        edtconfirm = findViewById(R.id.edtConfirm);

        btnssave = findViewById(R.id.btnSave);
        btnssave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass = sharedPreferences.getString("Password", "");
                String Pass = edtpassw.getText().toString();

                if (Pass.equals(pass)) {
                    String passnew = edtpassnew.getText().toString();
                    String confirm = edtconfirm.getText().toString();
                    if (!confirm.equals(passnew)) {
                        Toast.makeText(ChangePasswordActivity.this, "Confirm Failed", Toast.LENGTH_SHORT).show();
                    } else {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("PassNew", passnew);
                        editor.commit();
                        Toast.makeText(ChangePasswordActivity.this, "Change Password Successfully", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ChangePasswordActivity.this, "Password Change Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        sharedPreferences = getSharedPreferences("config", 0);
    }
}

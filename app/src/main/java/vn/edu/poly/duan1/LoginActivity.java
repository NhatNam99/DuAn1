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

public class LoginActivity extends AppCompatActivity {

    private EditText edtUserName, edtPass;
    String UserName, Password;
    SharedPreferences sharedPreferences;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUserName = findViewById(R.id.edtUserName);
        edtPass = findViewById(R.id.edtPass);

        sharedPreferences = getSharedPreferences("config", Context.MODE_PRIVATE);
        UserName = sharedPreferences.getString("UserName", "NhatNam");
        Password = sharedPreferences.getString("Password", "Nam123");

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String User = edtUserName.getText().toString();
                String Pass = edtPass.getText().toString();
                if (User.equals(UserName) && Pass.equals(Password)){
                    Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, PatientManagementActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

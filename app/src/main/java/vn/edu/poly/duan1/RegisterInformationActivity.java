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

public class RegisterInformationActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    Button btnRegestration, btnCanCel;
    String UserName, PassWord;
    EditText edtUsername, edtPassword, edtConfirmPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_information);

        sharedPreferences = getSharedPreferences("config", Context.MODE_PRIVATE);
        UserName = sharedPreferences.getString("UserName", "");
        PassWord = sharedPreferences.getString("Password", "");

        edtUsername = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassWord);
        edtConfirmPass = findViewById(R.id.edtConfirm);
        btnRegestration = findViewById(R.id.btnRegistration);
        btnCanCel = findViewById(R.id.btnCancel);

        btnRegestration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = edtUsername.getText().toString();
                String pass = edtPassword.getText().toString();
                String confirm = edtConfirmPass.getText().toString();

                if (user.isEmpty() || pass.isEmpty() || confirm.isEmpty()) {
                    if (user.isEmpty())
                        edtUsername.setError(getString(R.string.notify_empty_user));
                    if (pass.isEmpty())
                        edtPassword.setError(getString(R.string.notify_empty_pass));
                    if (confirm.isEmpty())
                        edtConfirmPass.setError(getString(R.string.notify_empty_pass));
                }

                if (!confirm.equals(pass)) {
                    Toast.makeText(RegisterInformationActivity.this, "Confirm Failed", Toast.LENGTH_SHORT).show();
                }

                else {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("UserName", edtUsername.getText().toString());
                    editor.putString("Password", edtPassword.getText().toString());
                    editor.commit();
                    Toast.makeText(getApplicationContext(), "Registration Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterInformationActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

        btnCanCel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterInformationActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}

package fisei.uta.app_004;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUser;
    private EditText editTextPassword;
    private Button buttonOk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUser = findViewById(R.id.editTextUser);
        editTextPassword = findViewById(R.id.editTextPassword);
    }

    public void onClickButtonOk(View view) {
        String user = editTextUser.getText().toString();
        String password = editTextPassword.getText().toString();

        if (!user.matches("") && !password.matches("")) {
            Intent intent = new Intent(this, DataBaseActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Debe ingresar los datos", Toast.LENGTH_SHORT).show();
        }
    }

}
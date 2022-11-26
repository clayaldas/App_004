package uta.fisei.edu.ec.app_004;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText editTextCode;
    private EditText editTextName;
    private EditText editTextLastName;
    private EditText editTextEdge;
    private Button buttonInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextCode = findViewById(R.id.editTextCode);
        editTextName = findViewById(R.id.editTextName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextEdge = findViewById(R.id.editTextEdge);
    }

    public void click_buttonInsert(View view) {
        // crear la BD
        ClientAdminHelper clientAdminHelper = new ClientAdminHelper(this, "CLIENTS_DB",
                null, 1);
        // abrir la BD
        SQLiteDatabase database = clientAdminHelper.getWritableDatabase();

        //String code = editTextCode.getText().toString();
        String name = editTextName.getText().toString();
        String lastName = editTextLastName.getText().toString();
        String edge = editTextEdge.getText().toString();

        // SQL INJECTION
        //database.execSQL("INSERT INTO Clients (Name, LastName, Edge)
        // VALUES ('"+name+"', '"+lastName+"', "+edge+")");

        ContentValues values = new ContentValues();
        values.put("Name", name);
        values.put("LastName", lastName);
        values.put("Edge", edge);

        // enviar a la BD
        database.insert("Clients", null, values);
        // cerrar la BD
        database.close();

        editTextName.setText("");
        editTextLastName.setText("");
        editTextEdge.setText("");
        Toast.makeText(this, "Cliente insertado", Toast.LENGTH_SHORT).show();
    }

    public void click_buttonSearch(View view) {
        // crear la BD
        ClientAdminHelper clientAdminHelper = new ClientAdminHelper(this, "CLIENTS_DB",
                null, 1);
        // abrir la BD
        SQLiteDatabase database = clientAdminHelper.getReadableDatabase();

        String code = editTextCode.getText().toString();
      //  String sql = "SELECT * " +
        //             "FROM Clients";
        String sql = "SELECT Code, Name, LastName, Edge " +
                     "FROM Clients " +
                     "WHERE Code = " + code;

        Cursor cursor = database.rawQuery(sql, null);
        // while (cursor.moveToFirst())
        if (cursor.moveToFirst()) {
            editTextName.setText(cursor.getString(1));
            editTextLastName.setText(cursor.getString(2));
            editTextEdge.setText(cursor.getString(3));
        }
        else
        {
            Toast.makeText(this, "Cliente no existe", Toast.LENGTH_SHORT).show();

            editTextName.setText("");
            editTextLastName.setText("");
            editTextEdge.setText("");
            editTextCode.setText("");
        }

        database.close();
    }

    public void click_buttonUpdate(View view) {
        // crear la BD
        ClientAdminHelper clientAdminHelper = new ClientAdminHelper(this, "CLIENTS_DB",
                null, 1);
        // abrir la BD
        SQLiteDatabase database = clientAdminHelper.getWritableDatabase();
    }

    public void click_buttonDelete(View view) {
        // crear la BD
        ClientAdminHelper clientAdminHelper = new ClientAdminHelper(this, "CLIENTS_DB",
                null, 1);
        // abrir la BD
        SQLiteDatabase database = clientAdminHelper.getWritableDatabase();
    }
}
package fisei.uta.app_004;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import fisei.uta.app_004.dal.DataBaseManager;
import fisei.uta.app_004.entities.Client;
import fisei.uta.app_004.dal.ClientDAL;
//import fisei.uta.app_004.logic.ClientBLL;
public class DataBaseActivity extends AppCompatActivity {

    private EditText editTextCode;
    private EditText editTextName;
    private EditText editTextLastName;
    private EditText editTextPhone;
    private EditText editTextBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base);

        editTextCode = findViewById(R.id.editTextCode);
        editTextName = findViewById(R.id.editTextName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextBalance = findViewById(R.id.editTextBalance);
    }

    public void onClickButtonInsert(View view) {
        DataBaseManager dataBaseManager = new DataBaseManager(this, "SEXTO_DB", null, 1);
        SQLiteDatabase sqLiteDatabase = dataBaseManager.getWritableDatabase();

       // String code = editTextCode.getText().toString();
        String name = editTextName.getText().toString();
        String lastName = editTextLastName.getText().toString();
        String phone = editTextPhone.getText().toString();
        String balance = editTextBalance.getText().toString();

        //sqLiteDatabase.execSQL("INSERT INTO Clients(Name, LastName, Phone, Balance)
        // VALUES ('X', 'Y', '0473847934', 1000)");
       // sqLiteDatabase.execSQL("INSERT INTO Clients(Name, LastName, Phone, Balance) " +
         //       "VALUES ('"+name+"', '"+lastName+"', '"+phone+"', "+balance+")");

        ContentValues values = new ContentValues();
        //values.put("Code", code);
        values.put("Name", name);
        values.put("LastName", lastName);
        values.put("Phone", phone);
        values.put("Balance", balance);

        long count = sqLiteDatabase.insert("Clients", null, values);

        if (count == 0) {
            Toast.makeText(this, "Registro no insertado", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Registro insertado", Toast.LENGTH_SHORT).show();
        }

        ClearFields();

        sqLiteDatabase.close();
    }

    public void onClickButtonInsertWithClass(View view) {

        String name = editTextName.getText().toString();
        String lastName = editTextLastName.getText().toString();
        String phone = editTextPhone.getText().toString();
        String balance = editTextBalance.getText().toString();

        Client client = new Client();
        client.setName(name);
        client.setLastName(lastName);
        client.setPhone(phone);
        client.setBalance(Double.parseDouble(balance));

        ClientDAL clientDAL = new ClientDAL(this);
        long count = clientDAL.insert(client);


        if (count == 0) {
            Toast.makeText(this, "Registro no insertado", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Registro insertado", Toast.LENGTH_SHORT).show();
        }

        ClearFields();
    }

    private void ClearFields() {
        editTextCode.setText("");
        editTextName.setText("");
        editTextLastName.setText("");
        editTextPhone.setText("");
        editTextBalance.setText("");
    }

    public void onClickButtonSearch(View view) {
        DataBaseManager dataBaseManager = new DataBaseManager(this, "SEXTO_DB", null, 1);
        SQLiteDatabase sqLiteDatabase = dataBaseManager.getReadableDatabase();

        String code = editTextCode.getText().toString();

        //String SELECT = "SELECT * FROM CLIENTS WHERE Code = '"+code+"' ";
        String SELECT = "SELECT Name, LastName, Phone, Balance FROM Clients WHERE Code=" + code;

        Cursor cursor = sqLiteDatabase.rawQuery(SELECT, null  );
        cursor.close();

        if (cursor.moveToFirst()) {
            editTextName.setText(cursor.getString(0));
            editTextLastName.setText(cursor.getString(1));
            editTextPhone.setText(cursor.getString(2));
            editTextBalance.setText(cursor.getString(3));
        }
        else {
            Toast.makeText(this,
                    "No existe el registro indicado", Toast.LENGTH_SHORT).show();
            ClearFields();
        }

        sqLiteDatabase.close();
    }

    public void onClickButtonSearchWithClass(View view) {

        String code = editTextCode.getText().toString();

        Client client;
        ClientDAL clientDAL = new ClientDAL(this);

        client = clientDAL.selectByPrimaryKey(Integer.parseInt(code));

                if (client != null ) {
            editTextName.setText(client.getName());
            editTextLastName.setText(client.getLastName());
            editTextPhone.setText(client.getPhone());
            editTextBalance.setText(String.valueOf(client.getBalance()));
        }
        else {
            Toast.makeText(this,
                    "No existe el registro indicado", Toast.LENGTH_SHORT).show();
            ClearFields();
        }


    }
    public void onClickButtonDelete (View view) {
        DataBaseManager dataBaseManager = new DataBaseManager(this, "SEXTO_DB", null, 1);
        SQLiteDatabase sqLiteDatabase = dataBaseManager.getWritableDatabase();

        String code = editTextCode.getText().toString();

        int count  = sqLiteDatabase.delete("Clients", "Code=" + code, null);

        if (count == 0) {
            Toast.makeText(this, "El Registro no se pudo eliminar", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Registro eliminado", Toast.LENGTH_SHORT).show();
        }

        ClearFields();

        sqLiteDatabase.close();
    }

    public void onClickButtonModify (View view) {
        DataBaseManager dataBaseManager = new DataBaseManager(this, "SEXTO_DB", null, 1);
        SQLiteDatabase sqLiteDatabase = dataBaseManager.getWritableDatabase();

        String name = editTextName.getText().toString();
        String lastName = editTextLastName.getText().toString();
        String phone = editTextPhone.getText().toString();
        String balance = editTextBalance.getText().toString();

        String code = editTextCode.getText().toString();


        ContentValues values = new ContentValues();
        values.put("Name", name);
        values.put("LastName", lastName);
        values.put("Phone", phone);
        values.put("Balance", balance);

        //values.put("Code", code);

        long count = sqLiteDatabase.update("Clients", values, "Code=" + code, null );

        if (count == 0) {
            Toast.makeText(this, "Registro no modificado", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Registro modificado", Toast.LENGTH_SHORT).show();
        }

        ClearFields();

        sqLiteDatabase.close();
    }

    public void onClickButtonSelectAll (View view) {
        Intent intent = new Intent(this, ShowAllActivity.class);
        startActivity(intent);
    }
}
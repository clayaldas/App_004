package fisei.uta.app_004;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import fisei.uta.app_004.dal.DataBaseManager;

public class ShowAllActivity extends AppCompatActivity {

    private ListView listviewData;
    private ArrayAdapter adapter;
    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);

        listviewData = findViewById(R.id.listviewData);

        list = getData();

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);

        listviewData.setAdapter(adapter);
    }

    private ArrayList<String> getData() {
        DataBaseManager dataBaseManager = new DataBaseManager(this, "SEXTO_DB", null, 1);
        SQLiteDatabase sqLiteDatabase = dataBaseManager.getReadableDatabase();

        ArrayList<String> listData = new ArrayList<>();

        String select = "SELECT Code, Name, LastName, Phone, Balance " +
                        "FROM Clients";

        Cursor cursor = sqLiteDatabase.rawQuery(select, null);

        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                listData.add(cursor.getString(1));
            }
        }
        else {
            Toast.makeText(this,
                    "No existen registros", Toast.LENGTH_SHORT).show();
        }
        sqLiteDatabase.close();

        return listData;
    }
}
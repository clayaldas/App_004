package uta.fisei.edu.ec.app_004;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ClientAdminHelper extends  SQLiteOpenHelper{

    String createTable_Clients = "CREATE TABLE Clients (Code INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "Name TEXT, LastName TEXT, Edge INTEGER)";

    public ClientAdminHelper(@Nullable Context context, @Nullable String name,
                             @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // crear la estructura de la BD
        sqLiteDatabase.execSQL(createTable_Clients);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // actualizar la estructura de la BD
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Clients");

        sqLiteDatabase.execSQL(createTable_Clients);
    }
}

package fisei.uta.app_004;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseManager extends SQLiteOpenHelper {

    final String CREATE_CLIENTS = "CREATE TABLE Clients (Code Integer PRIMARY KEY AUTOINCREMENT, Name TEXT, " +
                                  "LastName TEXT, Phone TEXT, Balance REAL)";

    public DataBaseManager(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // sql para definir la estructura de la BD.
        db.execSQL(CREATE_CLIENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // sql para realizar cambios en la estructura de la base datos
    }
}

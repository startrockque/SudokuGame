package data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Fabien on 01/03/2018.
 */

public class DataBaseManager extends SQLiteOpenHelper {
    public static final String TABLE_NIVEAUX = "niveaux";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_DIFFICULTE = "difficulte";
    public static final String COLUMN_PLATEAU = "plateau";
    public static final String COLUMN_TEMPS = "meilleur_temps";

    private static final String DATABASE_NAME = "niveaux.db";
    private static final int DATABASE_VERSION = 1;

    public static final String DATABASE_CREATE_IF_NOT_EXISTS = "CREATE TABLE IF NOT EXISTS "
            + TABLE_NIVEAUX + "("
            + COLUMN_ID + " integer primary key, "
            + COLUMN_DIFFICULTE + " integer not null, "
            + COLUMN_PLATEAU + " text not null, "
            + COLUMN_TEMPS + " text not null);";

    public DataBaseManager(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NIVEAUX + ";");
        sqLiteDatabase.execSQL(DATABASE_CREATE_IF_NOT_EXISTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.w(DataBaseManager.class.getName(),
                "Mise à jour de la base de données de la version " + i + " à "
                        + i1 + ", ce qui détruira les données existantes");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NIVEAUX);
        onCreate(sqLiteDatabase);
    }
}

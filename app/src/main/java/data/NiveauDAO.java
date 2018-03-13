package data;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.roume.fabien.sudokugame.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import model.Niveau;
import model.NiveauDTO;
import outils.BoiteAOutils;

/**
 * Created by Fabien on 02/03/2018.
 */

public class NiveauDAO {

    // Champs de la base de données
    private SQLiteDatabase database;
    private DataBaseManager dbHelper;
    private String[] allColumns = { DataBaseManager.COLUMN_ID, DataBaseManager.COLUMN_DIFFICULTE,  DataBaseManager.COLUMN_PLATEAU, DataBaseManager.COLUMN_TEMPS};
    private String[] allColumnsDTO = { DataBaseManager.COLUMN_ID, DataBaseManager.COLUMN_DIFFICULTE, DataBaseManager.COLUMN_TEMPS};

    public NiveauDAO(Context context) {
        dbHelper = new DataBaseManager(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void peuplerLaBase(Context context) throws IOException {
        database.execSQL(DataBaseManager.DATABASE_CREATE_IF_NOT_EXISTS);
        InputStream is = context.getResources().openRawResource(R.raw.peuplement_base);
        BufferedReader br = null;

        String ligne;
        try {
            br = new BufferedReader(new InputStreamReader(is));
            while ((ligne = br.readLine()) != null) {
                database.execSQL(ligne);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void razBase(){
        database.execSQL("DROP TABLE niveaux;");
        database.execSQL(DataBaseManager.DATABASE_CREATE_IF_NOT_EXISTS);
    }

    /**
     * Récupérer une liste de niveaux selon la difficulté sélectionnée
     * @param difficulte
     * @return la liste des NiveauDTO correspodant à la difficulté sélectionnée
     */
    public List<NiveauDTO> getTousNiveauxDTO(Integer difficulte) {
        List<NiveauDTO> niveaux = new ArrayList<NiveauDTO>();

        Cursor cursor = database.query(DataBaseManager.TABLE_NIVEAUX,
                allColumnsDTO, "difficulte = " + difficulte, null, null, null, "_id");

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            NiveauDTO niveau = curseurANiveauDTO(cursor);
            niveaux.add(niveau);
            cursor.moveToNext();
        }
        cursor.close();
        return niveaux;
    }

    /**
     * Récupérer un niveau unique
     * @param id
     * @return le Niveau sélcectionné
     */
    public Niveau getNiveau(Integer id) {
        Niveau niveau;
        Cursor cursor = database.query(DataBaseManager.TABLE_NIVEAUX,
                allColumns, "_id = " + id, null, null, null, null);

        cursor.moveToFirst();
        if (cursor.getCount()>0){
            niveau = curseurANiveau(cursor);
        } else {
            cursor.close();
            return null;
        }
        cursor.moveToNext();
        cursor.close();
        return niveau;
    }

    public void sauvegarderTemps(int id, long temps){
        database.execSQL("UPDATE "+ DataBaseManager.TABLE_NIVEAUX +" SET meilleur_temps = '" + BoiteAOutils.tempsEnString(temps) + "' WHERE _id = " + id + ";");
    }

    /**
     * Transformer un curseur en Niveau
     * @param cursor
     * @return
     */
    private Niveau curseurANiveau(Cursor cursor) {
        Niveau niveau= new Niveau();
        niveau.setId(cursor.getInt(0));
        niveau.setDifficulte(cursor.getInt(1));
        niveau.setPlateau(BoiteAOutils.stringAPlateau(cursor.getString(2)));
        niveau.setMeilleurTemps(cursor.getString(3));
        return niveau;
    }

    /**
     * Transformer un curseur en NiveauDTO
     * @param cursor
     * @return
     */
    private NiveauDTO curseurANiveauDTO(Cursor cursor) {
        NiveauDTO niveau= new NiveauDTO();
        niveau.setId(cursor.getInt(0));
        niveau.setDifficulte(cursor.getInt(1));
        niveau.setMeilleurTemps(cursor.getString(2));
        return niveau;
    }
}
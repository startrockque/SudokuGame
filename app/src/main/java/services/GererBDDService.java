package services;

import android.content.Context;
import android.widget.Toast;

import java.io.IOException;

import data.NiveauDAO;

/**
 * Service de gestion des niveaux
 */
public class GererBDDService {
    private NiveauDAO dao;

    public void initialiserBase(Context context){
        dao = new NiveauDAO(context);
        dao.open();
        try {
            if (baseNonExistante()) {
                dao.peuplerLaBase(context);
            } else {
                //TODO retirer ce else et faire proprement le test et initialisation de la base de données
                dao.razBase();
                dao.peuplerLaBase(context);
            }
        } catch (IOException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Contrôle l'existance de la base ou non
     * @return true si la base n'existe pas, false sinon
     */
    private boolean baseNonExistante() {
        try {
            // si la base existe, on n'aura pas d'erreur
            dao.getNiveau(1);
            return false;
        } catch (Exception e) {
            return true;
        }
    }
}

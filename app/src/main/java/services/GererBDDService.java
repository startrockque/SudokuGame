package services;

import android.content.Context;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import data.NiveauDAO;
import model.Niveau;
import model.NiveauDTO;

/**
 * Service de gestion des niveaux
 */
public class GererBDDService {
    private NiveauDAO dao;

    public void initialiserBase(Context context) {
        dao = new NiveauDAO(context);
        dao.open();
        try {
            if (baseNonExistante()) {
                dao.peuplerLaBase(context);
            } else {
                //TODO retirer ce else et faire proprement le test et initialisation de la base de données
                dao.razBase();
                dao.peuplerLaBase(context);
                dao.close();
            }
        } catch (IOException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            dao.close();
        }
    }

    /**
     * Contrôle l'existance de la base ou non
     *
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

    /**
     * Récupère la liste des niveaux depuis la BDD
     *
     * @param niveauDeDifficulte le niveau de difficulté
     * @return la liste des niveaux
     */
    public List<NiveauDTO> getTousNiveaux(Integer niveauDeDifficulte) {
        dao.open();
        List<NiveauDTO> listeNiveaux = dao.getTousNiveauxDTO(niveauDeDifficulte);
        dao.close();
        return listeNiveaux;
    }

    /**
     * Retourne le niveau sélectionné par le joueur
     * @param idNiveau l'id du niveau sélectionné
     * @return le Niveau
     */
    public Niveau getNiveau(Integer idNiveau) {
        dao.open();
        Niveau niveau = dao.getNiveau(idNiveau);
        dao.close();
        return niveau;
    }

    /**
     * Sauvegarde le temps réalisé par le joueur comme temps du niveau
     * @param idNiveau l'id du niveau
     * @param tempsChrono le temps à sauvegarder
     */
    public void sauvegarderTempsNiveau(Integer idNiveau, int tempsChrono) {
        dao.open();
        dao.sauvegarderTemps(idNiveau, tempsChrono);
        dao.close();
    }
}

package model;

import java.util.List;

/**
 * Created by Fabien on 28/02/2018.
 */

public class Plateau {

    private List<Ligne> lignes;
    private List<Colonne> colonnes;
    private List<SousTableau> sousTableaux;

    public List<Ligne> getLignes() {
        return lignes;
    }

    public void setLignes(List<Ligne> lignes) {
        this.lignes = lignes;
    }

    public List<Colonne> getColonnes() {
        return colonnes;
    }

    public void setColonnes(List<Colonne> colonnes) {
        this.colonnes = colonnes;
    }

    public List<SousTableau> getSousTableau() {
        return sousTableaux;
    }

    public void setSousTableau(List<SousTableau> sousTableaux) {
        this.sousTableaux = sousTableaux;
    }

    @Override
    public String toString() {
        return "Plateau{" +
                "lignes=" + lignes +
                ", colonnes=" + colonnes +
                ", sousTableaux=" + sousTableaux +
                '}';
    }
}

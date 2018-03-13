package model;

/**
 * Created by Fabien on 27/02/2018.
 */

public class Niveau {

    // De la base de données
    private Integer id;
    private Integer difficulte;
    private Plateau plateau;
    private String meilleurTemps;

    // Temps actuel réalisé par le joueur
    private Integer temps;

    public Niveau() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(Integer difficulte) {
        this.difficulte = difficulte;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    public String getMeilleurTemps() {
        return meilleurTemps;
    }

    public void setMeilleurTemps(String meilleurTemps) {
        this.meilleurTemps = meilleurTemps;
    }

    public Integer getTemps() {
        return temps;
    }

    public void setTemps(Integer temps) {
        this.temps = temps;
    }
}

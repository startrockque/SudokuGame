package model;

/**
 * Created by Fabien on 02/03/2018.
 */

public class NiveauDTO {

    private Integer id;
    private Integer difficulte;
    private String meilleurTemps;

    public NiveauDTO() {
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

    public String getMeilleurTemps() {
        return meilleurTemps;
    }

    public void setMeilleurTemps(String meilleurTemps) {
        this.meilleurTemps = meilleurTemps;
    }
}

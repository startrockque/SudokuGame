package outils;

import android.content.Context;

import vues.PlateauDeJeu;

public class GameEngine {
    private static GameEngine instance;

    private PlateauDeJeu plateau = null;

    private GameEngine() {
    }

    public static GameEngine getInstance() {
        if (instance == null)
            instance = new GameEngine();
        return instance;
    }

    public void creerPlateau(Context context) {
        int[][] sudoku = SudokuGenerateur.getInstance().genererGrille();
        plateau = new PlateauDeJeu(context);
        plateau.setPlateau(sudoku);
    }

    public PlateauDeJeu getPlateau() {
        return plateau;
    }
}

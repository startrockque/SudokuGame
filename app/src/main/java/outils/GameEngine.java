package outils;

import android.content.Context;

import vues.PlateauDeJeu;

public class GameEngine {
    private static GameEngine instance;

    private PlateauDeJeu plateau = null;

    private int xSelectionne, ySelectionne = -1;

    private GameEngine() {
    }

    public static GameEngine getInstance() {
        if (instance == null)
            instance = new GameEngine();
        return instance;
    }

    public void creerPlateau(Context context) {
        int[][] sudoku = SudokuGenerateur.getInstance().genererGrille();
        sudoku = SudokuGenerateur.getInstance().cacherCellules(sudoku);
        plateau = new PlateauDeJeu(context);
        plateau.setPlateau(sudoku);
    }

    public PlateauDeJeu getPlateau() {
        return plateau;
    }

    public void setPositionSelectionnee(int x, int y){
        xSelectionne = x;
        ySelectionne = y;
    }

    public void setNombre(int nombre) {
        if (xSelectionne != -1 && ySelectionne != -1)
        plateau.setItem(xSelectionne, ySelectionne, nombre);
    }
}

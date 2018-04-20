package vues;

import android.content.Context;

public class PlateauDeJeu {
    private Cellule[][] sudoku = new Cellule[9][9];

    public PlateauDeJeu(Context context){
        for( int x= 0; x <9; x++){
            for(int y = 0; y < 9; y++){
                sudoku[x][y] = new Cellule(context);
            }
        }
    }

    public void setPlateau(int[][] plateau){
        for( int x= 0; x <9; x++){
            for(int y = 0; y < 9; y++){
                sudoku[x][y].setInitValue(plateau[x][y]);
                if (plateau[x][y] != 0)
                    sudoku[x][y].setNotModifiable();
            }
        }
    }

    public Cellule[][] getPlateau() {
        return sudoku;
    }

    public Cellule getItem(int x, int y) {
        return sudoku[x][y];
    }

    public Cellule getItem(int position) {
        int x = position % 9;
        int y = position / 9;
        return sudoku[x][y];
    }

    public void setItem(int x, int y, int nombre) {
        sudoku[x][y].setValue(nombre);
    }
}

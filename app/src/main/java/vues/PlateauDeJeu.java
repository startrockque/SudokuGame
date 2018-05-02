package vues;

import android.content.Context;
import android.widget.Toast;

import outils.VerificateurDeSudoku;

public class PlateauDeJeu {
    private Cellule[][] sudoku = new Cellule[9][9];

    private Context context;

    public PlateauDeJeu(Context context){
        this.context = context;
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

    public void verifierPartie(){
        int[][] sud = new int[9][9];
        for(int x = 0; x<9; x++){
            for(int y = 0; y<9; y++){
                sud[x][y] = getPlateau()[x][y].getValue();
            }
        }
        if (VerificateurDeSudoku.getInstance().verifierSudoku(sud)){
            Toast.makeText(context, "sudoku terminé", Toast.LENGTH_SHORT).show();
        }
    }
}

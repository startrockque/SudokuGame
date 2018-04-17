package outils;

import java.util.ArrayList;
import java.util.Random;

public class SudokuGenerateur {
    private static SudokuGenerateur instance;

    private ArrayList<ArrayList<Integer>> disponible = new ArrayList<>();

    private Random aleatoire = new Random();

    private SudokuGenerateur(){}

    public static SudokuGenerateur getInstance() {
        if (instance == null)
            instance = new SudokuGenerateur();
        return instance;
    }

    public int[][] genererGrille(){
        int[][] sudoku = new int[9][9];
        int positionActuelle = 0;

        nettoyerGrille(sudoku);

        while (positionActuelle < 81){
            if( disponible.get(positionActuelle).size() != 0){
                int i = aleatoire.nextInt(disponible.get(positionActuelle).size());
                int nombre = disponible.get(positionActuelle).get(i);

                if( !verifierConflits(sudoku, positionActuelle, nombre)){
                    int xPos = positionActuelle % 9;
                    int yPos = positionActuelle / 9;

                    sudoku[xPos][yPos] = nombre;

                    disponible.get(positionActuelle).remove(i);
                    positionActuelle++;
                } else {
                    disponible.get(positionActuelle).remove(i);
                }

            } else {
                for (int i = 1; i<=9; i++){
                    disponible.get(positionActuelle).add(i);
                }
                positionActuelle--;
            }
        }

        return sudoku;
    }

    private void nettoyerGrille(int[][] sudoku){
        for (int y = 0; y < 9; y++){
            for (int x = 0; x < 9; x++){
                sudoku[x][y] = -1;
            }
        }

        for (int x = 0; x < 81; x++ ){
            disponible.add(new ArrayList<Integer>());
            for (int i = 1; i <=9; i++) {
                disponible.get(x).add(i);
            }
        }
    }

    private boolean verifierConflits(int[][] sudoku, int position, final int number){
        int xPos = position % 9;
        int yPos = position / 9;

        if(verifierConflitsHorizontaux(sudoku, xPos, yPos, number) || verifierConflitsVerticaux(sudoku, xPos, yPos, number) || verifierConflitsRegionnaux(sudoku, xPos, yPos, number))
            return true;
        return false;
    }

    private boolean verifierConflitsHorizontaux(final int[][] sudoku, final int xPos, final int yPos, final int number){
        for (int x = xPos-1; x >= 0; x--){
            if (number == sudoku[x][yPos])
                return true;
        }
        return false;
    }

    private boolean verifierConflitsVerticaux(final int[][] sudoku, final int xPos, final int yPos, final int number){
        for (int y = yPos-1; y >= 0; y--){
            if (number == sudoku[xPos][y])
                return true;
        }
        return false;
    }

    private boolean verifierConflitsRegionnaux(final int[][] sudoku, final int xPos, final int yPos, final int number){
        int xRegion = xPos / 3;
        int yRegion = yPos / 3;

        for(int x = xRegion * 3; x < xRegion * 3 + 3; x++){
            for(int y = yRegion * 3; y < yRegion * 3 + 3; y++){
                if ((x != xPos || y != yPos) && (number == sudoku[x][y]))
                    return true;
            }
        }
        return false;
    }
}

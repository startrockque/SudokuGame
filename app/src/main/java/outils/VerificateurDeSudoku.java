package outils;

public class VerificateurDeSudoku {
    private static VerificateurDeSudoku instance;

    private VerificateurDeSudoku(){}

    public static VerificateurDeSudoku getInstance(){
        if (instance == null)
            instance = new VerificateurDeSudoku();
        return instance;
    }

    public boolean verifierSudoku(int [][] sudoku){
        return (verifierLignes(sudoku) && verifierColonnes(sudoku) && verifierCarres(sudoku));
    }
    private boolean verifierLignes(int[][] sudoku) {
        for (int y = 0; y < 9; y++){
            for (int xPos = 0; xPos < 9; xPos++){
                if(sudoku[xPos][y] == 0) {
                    return false;
                } else {
                    for (int x = xPos+1; x < 9; x++){
                        if (sudoku[xPos][y] == sudoku[x][y] || sudoku[x][y] == 0)
                            return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean verifierColonnes(int[][] sudoku) {
        for (int x = 0; x < 9; x++){
            for (int yPos = 0; yPos < 9; yPos++){
                if(sudoku[x][yPos] == 0) {
                    return false;
                } else {
                    for (int y = yPos+1; y < 9; y++){
                        if (sudoku[x][yPos] == sudoku[x][y] || sudoku[x][y] == 0)
                            return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean verifierCarres(int[][] sudoku) {
        for (int xRegion = 0; xRegion < 3; xRegion++){
            for (int yRegion = 0; yRegion < 3; yRegion++){
                if( !verifierCarre(sudoku, xRegion, yRegion))
                    return false;
            }
        }
        return true;
    }

    private boolean verifierCarre(int[][] sudoku, int xRegion, int yRegion){
        for (int xPos = xRegion*3; xPos < xRegion*3+3; xPos++){
            for (int yPos = yRegion*3; yPos < yRegion*3+3; yPos++){
                for (int x = xPos; x < xRegion*3+3; x++){
                    for (int y = yPos; y < yRegion*3+3; y++){
                        if((( x!= xPos || y != yPos) && sudoku[xPos][yPos] == sudoku[x][y]) || sudoku[x][y] == 0){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }


}

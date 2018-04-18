package outils;

public class GameEngine {
    private static GameEngine instance;

    private int[][] sudoku;

    private GameEngine(){}

    public static GameEngine getInstance(){
        if (instance == null)
            instance = new GameEngine();
        return  instance;
    }

    public int[][] getSudoku() {
        return sudoku;
    }

    public void setSudoku(int[][] sudoku) {
        this.sudoku = sudoku;
    }
}

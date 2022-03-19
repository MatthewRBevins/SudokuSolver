import java.util.Arrays;
public class Solver {
    boolean diagonals;
    int sqrt;
    int size;
    public Solver(boolean diagonals, int size){
        this.diagonals = diagonals;
        this.sqrt = (int) Math.sqrt(size);
        this.size = size;
    }
    public int[][] solve(int[][] sudoku) {
        //TODO:ADD GUI
        //TODO:X SUDOKUS
        //TODO:DIFFERENT SIZES
        backtrack(0,-1, sudoku);
        return sudoku;
    }
    private int[] box(int[][] sudoku, int row, int column) {
        int boxRow = Math.round(row/sqrt);
        int boxCol = Math.round(column/sqrt);
        int[] finalArr = new int[100];
        int index = 0;
        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku[i].length; j++) {
                if (Math.round(i/sqrt) == boxRow && Math.round(j/sqrt) == boxCol) {
                    finalArr[index] = sudoku[i][j];
                    index++;
                }
            }
        }
        return finalArr;
    }
    private boolean backtrack(int r, int c, int[][] sudoku) {
        c++;
        if (c > size-1) {
            c = 0;
            r++;
            if (r > size-1) {
                return true;
            }
        }
        if (sudoku[r][c] != 0) {
            return backtrack(r,c, sudoku);
        }
        else {
            for (int i = 1; i < size+1; i++) {
                //if something doesn't work (reaches a "dead end"), it'll return false, ignore those solutions, and go back to where it was when it started
                if (works(i, r, c, sudoku)) {
                    sudoku[r][c] = i;
                    if (backtrack(r, c, sudoku)) {
                        return true;
                    }
                }
            }
            sudoku[r][c] = 0;
            return false;
        }
    }
    //TODO:MAY NEED FOR LATER
    private int[][][] findPossibilities(int[][] sudoku) {
        int[][][] finalArr = new int[size][size][size];
        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku.length; j++) {
                if (sudoku[i][j] == 0) {
                    int index = 0;
                    for (int k = 0; k < size+1; k++) {
                        if (works(k, i, j, sudoku)) {
                            finalArr[i][j][index] = k;
                            index++;
                        }
                    }
                }
                else {
                    finalArr[i][j][0] = sudoku[i][j];
                }
            }
        }
        return finalArr;
    }
    private boolean isIn(int num, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) {
                return true;
            }
        }
        return false;
    }
    private boolean works(int num, int row, int column, int[][] sudoku) {
        if (num < 1 || num > size) {
            return false;
        }
        if (isIn(num,box(sudoku,row,column))) {
            return false;
        }
        for (int i = 0; i < sudoku.length; i++) {
            if (sudoku[row][i] == num || sudoku[i][column] == num) {
                return false;
            }
        }
        if (diagonals) {
            if (row == column) {
                for (int i = 0; i < sudoku.length; i++) {
                    if (sudoku[i][i] == num) {
                        return false;
                    }
                }
            }
            if (row == (size-1) - column) {
                for (int i = 0; i < sudoku.length; i++) {
                    if (sudoku[i][size - (i + 1)] == num) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    private boolean check(int[][] sudoku) {
        for (int[] ints : sudoku) {
            for (int anInt : ints) {
                if (anInt == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}

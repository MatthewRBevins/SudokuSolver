import java.util.Arrays;
public class Main {
    static int[][] sudoku = {
            {0,9,1,0,0,4,8,0,0},
            {0,0,0,1,0,0,0,9,0},
            {5,0,0,0,6,0,1,0,2},
            {0,6,0,0,0,1,3,0,0},
            {4,0,0,0,7,0,0,0,1},
            {0,0,9,2,0,0,0,5,0},
            {9,0,4,0,3,0,0,0,8},
            {0,3,0,0,0,8,0,0,0},
            {0,0,8,6,0,0,2,4,0}
    };
    public static void main(String[] args){
        int timesRun = 0;
        int[][][] prevPossibilities = new int[9][9][9];
        boolean stop = false;
        //TODO:ADD GUI
        //TODO:X SUDOKUS
        //TODO:REMOVE NON-BACKTRACK ALGORITHM
        while (!check(sudoku)) {
            timesRun++;
            int[][][] findPossibilities = find(sudoku);
            for (int i = 0; i < findPossibilities.length; i++) {
                for (int j = 0; j < findPossibilities[i].length; j++) {
                    int p = 0;
                    for (int k = 0; k < findPossibilities[i][j].length; k++) {
                        if (findPossibilities[i][j][k] != 0) {
                            p++;
                        }
                    }
                    if (p == 1) {
                        sudoku[i][j] = findPossibilities[i][j][0];
                    }
                }
            }
            stop = true;
            for (int i = 0; i < prevPossibilities.length; i++) {
                for (int j = 0; j < prevPossibilities[i].length; j++) {
                    for (int k = 0; k < prevPossibilities[i][j].length; k++) {
                        if (prevPossibilities[i][j][k] != findPossibilities[i][j][k]) {
                            stop = false;
                            break;
                        }
                    }
                }
            }
            if (stop) {
                System.out.println("Backtrack necessary");
                backtrack(0,-1);
                break;
            }
            prevPossibilities = findPossibilities;
        }
        if (!stop) {
            System.out.println("Done! Times run: " + timesRun);
        }
        System.out.println(Arrays.deepToString(sudoku));
    }
    private static int[] box(int[][] sudoku, int row, int column) {
        int boxRow = Math.round(row/3);
        int boxCol = Math.round(column/3);
        int[] finalArr = new int[9];
        int index = 0;
        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku[i].length; j++) {
                if (Math.round(i/3) == boxRow && Math.round(j/3) == boxCol) {
                    finalArr[index] = sudoku[i][j];
                    index++;
                }
            }
        }
        return finalArr;
    }
    private static boolean backtrack(int r, int c) {
        c++;
        if (c > 8) {
            c = 0;
            r++;
            if (r >8) {
                return true;
            }
        }
        if (sudoku[r][c] != 0) {
            return backtrack(r,c);
        }
        else {
            for (int i = 1; i < 10; i++) {
                //if something doesn't work (reaches a "dead end"), it'll return false, ignore those solutions, and go back to where it was when it started
                if (works(i, r, c, sudoku)) {
                    sudoku[r][c] = i;
                    if (backtrack(r, c)) {
                        return true;
                    }
                }
            }
            sudoku[r][c] = 0;
            return false;
        }
    }
    private static int[][][] find(int[][] sudoku) {
        int[][][] finalArr = new int[9][9][9];
        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku.length; j++) {
                if (sudoku[i][j] == 0) {
                    int index = 0;
                    for (int k = 0; k < 10; k++) {
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
    private static boolean isIn(int num, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) {
                return true;
            }
        }
        return false;
    }
    private static boolean works(int num, int row, int column, int[][] sudoku) {
        if (num < 1 || num > 9) {
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
        return true;
    }
    private static boolean check(int[][] sudoku) {
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

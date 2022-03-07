import java.util.Arrays;
import java.util.Random;
public class Main {
    static Random r = new Random();
    public static void main(String[] args){
      int[][] sudoku = {
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
      //System.out.println(Arrays.toString(box(sudoku,3, 3)));
      int timesRun = 0;
      int[][][] prevPossibilities = new int[9][9][9];
      while (!check(sudoku)) {
          timesRun++;
          System.out.println(timesRun);
          int[][][] findPossibilities = find(sudoku);
          if (findPossibilities == prevPossibilities) {
              System.out.println("BRUH");
              break;
          }
          for (int i = 0; i < findPossibilities.length; i++) {
              for (int j = 0; j < findPossibilities[i].length; j++) {
                  int p = 0;
                  for (int k = 0; k < findPossibilities[i][j].length; k++) {
                      if (findPossibilities[i][j][k] != 0) {
                          p++;
                          System.out.print(findPossibilities[i][j][k] + ",");
                      }
                  }
                  if (p == 1) {
                      sudoku[i][j] = findPossibilities[i][j][0];
                  }
                  System.out.println("\n");
              }
          }
          boolean stop = true;
          for (int i = 0; i < prevPossibilities.length; i++) {
              for (int j = 0; j < prevPossibilities[i].length; j++) {
                  for (int k = 0; k < prevPossibilities[i][j].length; k++) {
                      if (prevPossibilities[i][j][k] != findPossibilities[i][j][k]) {
                          stop = false;
                          //System.out.println("BRUH MOMENT: " + prevPossibilities[i][j][k] + " || " + findPossibilities[i][j][k]);
                      }
                  }
              }
          }
          if (stop) {
              System.out.println(backtrack(0,-1, sudoku));
              break;
          }
          prevPossibilities = findPossibilities;
          System.out.println("************************************");
      }
      System.out.println("Done! Times run: " + timesRun);
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
    private static boolean backtrack(int r, int c, int[][] sudoku) {
        //DO THIS
        return false;
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
        for (int i : arr) {
            if (i == num) {
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


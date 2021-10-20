import java.util.Random;
import java.util.Arrays;
public class Main {
    public static int[][] puzzle = {{0,0,0}, {0,3,0}, {0,0,0}};
    public static int[] is = new int[9];
    public static int[] js = new int[9];
    public static int iIndex = 0;
    public static int jIndex = 0;
    public static Random generator = new Random();
    public static void main(String[] args) {
        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle[i].length; j++) {
                if (puzzle[i][j] == 0) {
                    is[iIndex] = i;
                    iIndex++;
                    js[jIndex] = j;
                    jIndex++;
                }
            }
        }
        boolean notSolved = true;
        while (notSolved) {
            notSolved = false;
            //loop through things to change
            for (int i = 0; i < is.length; i++) {
                puzzle[is[i]][js[i]] = generator.nextInt(3) + 1;
            }
            //TODO:CHECK COLUMNS
            for (int[] ints : puzzle) {
                for (int j = 0; j < puzzle.length; j++) {
                    if (isIn(ints, ints[j]) != -1 && isIn(ints, ints[j]) != j) {
                        notSolved = true;
                        break;
                    }
                }
            }
        }
    }
    public static int isIn(int[] arr, int val) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == val) {
                return i;
            }
        }
        return -1;
    }
}

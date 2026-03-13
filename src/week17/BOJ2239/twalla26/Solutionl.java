import java.io.*;
import java.util.*;

public class Solutionl {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int[][] puzzle;
    static List<int[]> emptyPos;

    static boolean sudoku(int idx) {
        if (idx == emptyPos.size()) {
            print();
            return true;
        }

        int[] empty = emptyPos.get(idx);
        int r = empty[0];
        int c = empty[1];

        for (int k = 1; k < 10; k++) {
            if (check(r, c, k)) {
                puzzle[r][c] = k;
                if (sudoku(idx + 1)) {
                    return true;
                }
                puzzle[r][c] = 0;
            }
        }

        return false;
    }

    static boolean check(int r, int c, int k) {

        for (int i = 0; i < 9; i++) {
            if (puzzle[r][i] == k) 
                return false;
            if (puzzle[i][c] == k) 
                return false;
        }

        int startRow = (r / 3) * 3;
        int startCol = (c / 3) * 3;

        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (puzzle[i][j] == k)
                    return false;
            }
        }

        return true;
    }

    static void print() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(puzzle[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {

        puzzle = new int[9][9];
        emptyPos = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            String line = br.readLine();
            for (int j = 0; j < 9; j++) {
                int num = line.charAt(j) - '0';
                puzzle[i][j] = num;

                if (num == 0) {
                    emptyPos.add(new int[]{i, j});
                }
            }
        }

        sudoku(0);
    }
}
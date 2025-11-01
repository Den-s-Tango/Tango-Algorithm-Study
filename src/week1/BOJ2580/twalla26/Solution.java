package week1.BOJ2580.twalla26;

import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static boolean complete = false;

    static void solution(int[][] board, int i, int j, int blank) {

        if (complete)
            return;

        if (blank == 0) {
            complete = true;
            printBoard(board);
            return;
        }

        int ni = i;
        int nj = j + 1;
        if (nj == 9) {
            nj = 0;
            ni += 1;
        }

        if (board[i][j] != 0) {
            solution(board, ni, nj, blank);
            return;
        }

        HashSet<Integer> remained = fill(board, i, j);
        for (int elem : remained) {
            board[i][j] = elem;
            solution(board, ni, nj, blank - 1);
            board[i][j] = 0;
        }
    }

    static HashSet<Integer> fill(int[][] board, int i, int j) {

        HashSet<Integer> set = getSet();

        checkHeight(board, i, j, set);

        checkWidth(board, i, j, set);

        checkSquare(board, i, j, set);

        return set;
    }

    static void checkHeight(int[][] board, int i, int j, HashSet<Integer> set) {

        for (int k = 0; k < 9; k++)
            set.remove(board[k][j]);
    }


    static void checkWidth(int[][] board, int i, int j, HashSet<Integer> set) {

        for (int k = 0; k < 9; k++)
            set.remove(board[i][k]);
    }

    static void checkSquare(int[][] board, int i, int j, HashSet<Integer> set) {

        int startI, startJ;

        if (0 <= i && i < 3) startI = 0;
        else if (i < 6) startI = 3;
        else startI = 6;

        if (0 <= j && j < 3) startJ = 0;
        else if (j < 6) startJ = 3;
        else startJ = 6;

        for (int tempI = startI; tempI < startI + 3; tempI++) {
            for (int tempJ = startJ; tempJ < startJ + 3; tempJ++)
                set.remove(board[tempI][tempJ]);
        }
    }

    static HashSet<Integer> getSet() {

        HashSet<Integer> set = new HashSet<>();
        for (int i = 1; i <= 9; i++)
            set.add(i);

        return set;
    }

    static void printBoard(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++)
                sb.append(board[i][j]).append(" ");
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {

        int[][] board = new int[9][9];
        int blank = 0;

        for (int i = 0 ; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                int num = Integer.parseInt(st.nextToken());
                board[i][j] = num;

                if (num == 0)
                    blank += 1;
            }
        }

        solution(board, 0, 0, blank);
    }
}

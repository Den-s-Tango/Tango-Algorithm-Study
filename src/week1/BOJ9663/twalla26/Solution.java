package week1.BOJ9663.twalla26;

import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N, result;
    static boolean[] colUsed;
    static boolean[] diag1;
    static boolean[] diag2;

    static void solution(int row) {

        if (row == N) {
            result += 1;
            return;
        }

        for (int col = 0; col < N; col++) {
            if (isSafe(row, col)) {

                colUsed[col] = true;
                diag1[row + col] = true;
                diag2[row - col + (N - 1)] = true;

                solution(row + 1);

                colUsed[col] = false;
                diag1[row + col] = false;
                diag2[row - col + (N - 1)] = false;
            }
        }
    }

    static boolean isSafe(int row, int col) {
        return (!colUsed[col] && !diag1[row + col]) && !diag2[row - col + (N - 1)];
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        colUsed = new boolean[N];
        diag1 = new boolean[2 * N - 1];
        diag2 = new boolean[2 * N - 1];

        solution(0);
        System.out.println(result);
    }
}

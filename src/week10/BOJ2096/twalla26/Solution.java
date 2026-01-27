import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N;

    static void game(int N, int[][] board) {

        int[] di = new int[]{-1, -1, -1};
        int[] dj = new int[]{-1, 0, 1};

        int[][] maxCache = new int[N][3];
        int[][] minCache = new int[N][3];

        maxCache[0] = new int[]{board[0][0], board[0][1], board[0][2]};
        minCache[0] = new int[]{board[0][0], board[0][1], board[0][2]};

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                int max = Integer.MIN_VALUE;
                int min = Integer.MAX_VALUE;
                for (int d = 0; d < 3; d++) {
                    int ni = i + di[d];
                    int nj = j + dj[d];

                    if (!isBound(ni, nj)) {
                        continue;
                    }

                    max = Math.max(maxCache[ni][nj], max);
                    min = Math.min(minCache[ni][nj], min);
                }
                maxCache[i][j] = board[i][j] + max;
                minCache[i][j] = board[i][j] + min;
            }   
        }

        int maxResult = Math.max(maxCache[N - 1][0], Math.max(maxCache[N - 1][1], maxCache[N - 1][2]));
        int minResult = Math.min(minCache[N - 1][0], Math.min(minCache[N - 1][1], minCache[N - 1][2]));

        System.out.println(maxResult + " " + minResult);
    }

    static boolean isBound(int i, int j) {
        return (0 <= i && i < N) && (0 <= j && j < 3);
    }

    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());

        int[][] board = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) { 
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        game(N, board);
    }
}
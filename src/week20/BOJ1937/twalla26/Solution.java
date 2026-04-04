import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N, max;
    static int[][] forest, cache;
    static int[] di = new int[]{-1, 0, 1, 0};
    static int[] dj = new int[]{0, -1, 0, 1};

    static int dp(int i, int j) {

        if (cache[i][j] != -1) {
            return cache[i][j];
        }

        int cur = forest[i][j];
        boolean smallest = true;
        cache[i][j] = 1;
        for (int d = 0; d < 4; d++) {
            int ni = i + di[d];
            int nj = j + dj[d];

            if (!isBound(ni, nj)) {
                continue;
            }

            int next = forest[ni][nj];

            if (cur <= next) {
                continue;
            }

            cache[i][j] = Math.max(cache[i][j], dp(ni, nj) + 1);
            smallest = false;
        }

        if (smallest) {
            return cache[i][j] = 1;
        }

        return cache[i][j];
    }

    static boolean isBound(int i, int j) {
        return (0 <= i && i < N) && (0 <= j && j < N);
    }

    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());
        forest = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                forest[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cache = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(cache[i], -1);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                max = Math.max(max, dp(i, j));
            }
        }

        System.out.println(max);
    }
}
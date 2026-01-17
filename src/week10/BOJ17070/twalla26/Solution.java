import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N;
    static int[][] house;

    static int dp() {

        int[][][] cache = new int[N][N][3];
        cache[0][1] = new int[]{1, 0, 0};

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (house[i][j] == 1) {
                    continue;
                }

                if (j - 1 >= 0) {
                    cache[i][j][0] += cache[i][j - 1][0] + cache[i][j - 1][1];
                }

                if (i - 1 >= 0) {
                    cache[i][j][2] += cache[i - 1][j][2] + cache[i - 1][j][1];
                }

                if (i - 1 >= 0 && j - 1 >= 0) {
                    if (house[i - 1][j] == 1 || house[i][j - 1] == 1) {
                        continue;
                    }
                    cache[i][j][1] = cache[i - 1][j - 1][0] + cache[i - 1][j - 1][1] + cache[i - 1][j - 1][2];
                }
            }
        }

        return cache[N - 1][N - 1][0] + cache[N - 1][N - 1][1] + cache[N - 1][N - 1][2];
    }

    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());

        house = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                house[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = dp();
        System.out.println(cnt);
    }
}
import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N;
    static int[][] arrs, cache;

    static int solution() {

        cache = new int[N + 1][N + 1];
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                if (i == j) {
                    cache[i][j] = 0;
                } else {
                    cache[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        for (int j = 1; j < N + 1; j++) {
            for (int i = j; i > 0; i--) {
                for (int k = i; k < j; k++) {
                    cache[i][j] = Math.min(cache[i][j], 
                                    cache[i][k] + cache[k + 1][j] + arrs[i][0] * arrs[k + 1][0] * arrs[j][1]);
                }
            }
        }

        return cache[1][N];
    }

    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());
        arrs = new int[N + 1][2];
        
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            arrs[i][0] = Integer.parseInt(st.nextToken());
            arrs[i][1] = Integer.parseInt(st.nextToken());
        }

        int result = solution();
        System.out.println(result);
    }
}
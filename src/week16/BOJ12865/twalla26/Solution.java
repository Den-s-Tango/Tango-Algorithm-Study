import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int dp(int N, int K, int[][] items) {

        int[][] cache = new int[N + 1][K + 1];

        for (int i = 1; i < N + 1; i++) {
            int[] item = items[i - 1];
            int w = item[0];
            int v = item[1];
            for (int j = 1; j < K + 1; j++) {
                if (w <= j) {
                    cache[i][j] = Math.max(cache[i - 1][j], v + cache[i - 1][j - w]);
                } else {
                    cache[i][j] = cache[i - 1][j];
                }
            }
        }
        
        return cache[N][K];
    }

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] items = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            items[i][0] = W;
            items[i][1] = V;
        }

        int result = dp(N, K, items);
        System.out.println(result);

    }
}
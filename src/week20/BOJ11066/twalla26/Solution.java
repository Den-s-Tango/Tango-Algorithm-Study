import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int INF = 5_000_000;
    static int K;
    static int[] files, sum;
    static int[][] cache;

    static int getSum(int s, int e) {
        if (s == 0) {
            return sum[e];
        }

        return sum[e] - sum[s - 1];
    }

    static int dp(int s, int e) {

        if (cache[s][e] != -1) {
            return cache[s][e];
        }

        if (s == e) {
            return 0;
        }
        
        cache[s][e] = INF;
        for (int i = s; i < e; i++) {
            int cost = dp(s, i) + dp(i + 1, e) + getSum(s, e);
            cache[s][e] = Math.min(cache[s][e], cost);
        }

        return cache[s][e];
    }

    public static void main(String[] args) throws Exception {

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            K = Integer.parseInt(br.readLine());
            files = new int[K];
            sum = new int[K];

            st = new StringTokenizer(br.readLine());
            for (int k = 0; k < K; k++) {
                files[k] = Integer.parseInt(st.nextToken());

                if (k == 0) {
                    sum[0] = files[0];
                } else {
                    sum[k] = sum[k - 1] + files[k];
                }
            }

            cache = new int[K][K];
            for (int i = 0; i < K; i++) {
                Arrays.fill(cache[i], -1);
            }

            int result = dp(0, K - 1);
            sb.append(result).append("\n");

        }
        
        System.out.println(sb);
    }
}
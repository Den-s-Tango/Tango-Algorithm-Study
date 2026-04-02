import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int INF = Integer.MAX_VALUE;
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

        for (int len = 2; len <= K; len++) {
            for (int i = 0; i <= K - len; i++) {
                int j = i + len - 1;
                cache[i][j] = INF;

                for (int k = i; k < j; k++) {
                    cache[i][j] = Math.min(cache[i][j], cache[i][k] + cache[k + 1][j] + getSum(i ,j));
                }
            }
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
                cache[i][i] = 0;
            }

            int result = dp(0, K - 1);
            sb.append(result).append("\n");

        }
        
        System.out.println(sb);
    }
}
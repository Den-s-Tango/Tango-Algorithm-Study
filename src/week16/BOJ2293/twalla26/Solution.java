import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int dp(int N, int K, int[] coins) {

        int[][] cache = new int[K + 1][N];
        
        Arrays.fill(cache[0], 1);

        for (int k = 0; k < K + 1; k++) {
            for (int n = 0; n < N; n++) {

                int coin = coins[n];
                if (n == 0) {
                    if (k % coin == 0) {
                        cache[k][n] = 1;
                    }
                    continue;
                } 

                if (k >= coin) {
                    cache[k][n] = cache[k][n - 1] + cache[k - coin][n];
                } else {
                    cache[k][n] = cache[k][n - 1];
                }
            }
        }

        return cache[K][N - 1];
    }

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] coins = new int[N];
        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(coins);

        int result = dp(N, K, coins);
        System.out.println(result);
    }
}
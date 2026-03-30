import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N, K, MOD = 1_000_000_000;

    static int dp() {

        int[][] cache = new int[K + 1][N + 1];
        Arrays.fill(cache[1], 1);

        for (int k = 2; k < K + 1; k++) {
            for (int n = 0; n < N + 1; n++) {
                if (n == 0) {
                    cache[k][n] = 1;
                    continue;
                }

                cache[k][n] = (cache[k - 1][n] + cache[k][n - 1]) % MOD;
            }
        }

        return cache[K][N];
    }

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int result = dp();
        System.out.println(result);
    }
}
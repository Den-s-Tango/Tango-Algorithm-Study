import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int INF = 1_000_000;

    static int dp(int C, int N, int[][] info) {

        int[] cache = new int[C + 1];
        Arrays.fill(cache, INF);
        cache[0] = 0;

        for (int i = 1; i < N + 1; i++) {
            int cost = info[i][0];
            int customer = info[i][1];

            for (int j = 1; j < C + 1; j++) {
                if (j >= customer) {
                    cache[j] = Math.min(cache[j], cache[j - customer] + cost);
                } else {
                    cache[j] = Math.min(cache[j], cost);
                }
            }
        }

        return cache[C];
    }

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[][] info = new int[N + 1][2];
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            info[i][0] = Integer.parseInt(st.nextToken());
            info[i][1] = Integer.parseInt(st.nextToken());
        }

        int result = dp(C, N, info);
        System.out.println(result);
    }
}
import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;
    
    static int N;
    static int[][] W, cache;
    static int INF = 16_000_000;

    static int dfs(int cur, int visited) {

        if (visited == (1 << N) - 1) {
            if (W[cur][0] > 0) {
                return W[cur][0];
            } 
            return INF;
        }

        if (cache[cur][visited] != -1) {
            return cache[cur][visited];
        }

        cache[cur][visited] = INF;

        for (int next = 0; next < N; next++) {

            if (W[cur][next] == 0 || (visited & 1 << next) > 0) {
                continue;
            }

            int cost = dfs(next, visited | 1 << next);
            cache[cur][visited] = Math.min(cache[cur][visited], W[cur][next] + cost);
        }

        return cache[cur][visited];
    }


    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());

        W = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cache = new int[N][1 << N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(cache[i], -1);
        }

        int result = dfs(0, 1);

        System.out.println(result);
    }
}
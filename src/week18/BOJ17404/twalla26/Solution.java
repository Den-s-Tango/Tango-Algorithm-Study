import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N;
    static int[][] costs;
    static int[][] cache;

    static void dp() {
        for (int i = 1; i < N; i++) {
            cache[i][0] = Math.min(cache[i - 1][1], cache[i - 1][2]) + costs[i][0];
            cache[i][1] = Math.min(cache[i - 1][2], cache[i - 1][0]) + costs[i][1];
            cache[i][2] = Math.min(cache[i - 1][0], cache[i - 1][1]) + costs[i][2];
        }
    }

    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());

        costs = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            costs[i][0] = Integer.parseInt(st.nextToken());
            costs[i][1] = Integer.parseInt(st.nextToken());
            costs[i][2] = Integer.parseInt(st.nextToken());
        }

        cache = new int[N][3];

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            Arrays.fill(cache[0], 1000);
            cache[0][i] = costs[0][i];
            dp();

            for (int j = 0; j < 3; j++) {
                if (j != i) {
                    min = Math.min(min, cache[N - 1][j]);
                }
            }
        }
        
        System.out.println(min);
    }
}
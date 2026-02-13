import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int n, m, r, INF = 100_000_000;
    static int[][] graph;
    static int[] items;

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        items = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n + 1; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        graph = new int[n + 1][n + 1];
        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(graph[i], INF);
            graph[i][i] = 0;
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            graph[a][b] = l;
            graph[b][a] = l;
        }

        // 플로이드 워셜
        for (int k = 1; k < n + 1; k++) {
            for (int i = 1; i < n + 1; i++) {
                for (int j = 1; j < n + 1; j++) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        int sum, max = 0;
        for (int i = 1; i < n + 1; i++) {
            sum = 0;
            for (int j = 1; j < n + 1; j++) {
                if (graph[i][j] <= m) {
                    sum += items[j];
                }
            }
            max = Math.max(max, sum);
        }

        System.out.println(max);
    }
}
import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N, M;
    static int[][] graph;
    static int[] plan, parent;

    static boolean trip() {

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (graph[i][j] == 1) {
                    union(i, j);
                }
            }
        }
        
        for (int i = 0; i < M - 1; i++) {
            int cur = plan[i];
            int next = plan[i + 1];

            if (find(cur) != find(next)) {
                return false;
            }
        }

        return true;
    }

    static int find(int u) {
        if (parent[u] == u) {
            return u;
        }

        return parent[u] = find(parent[u]);
    }

    static void union(int u, int v) {
        int fu = find(u);
        int fv = find(v);

        if (fu < fv) {
            parent[fv] = fu;
        } else {
            parent[fu] = fv;
        }
    }

    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        plan = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            plan[i] = Integer.parseInt(st.nextToken());
        }   

        boolean result = trip();
        if (result) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
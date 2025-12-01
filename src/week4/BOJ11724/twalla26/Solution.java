import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N, M;
    static int[] parent;
    static int[][] edges;

    static void solution() {
        int u, v;
        for (int i = 0; i < M; i++) {
            u = edges[i][0];
            v = edges[i][1];

            union(u, v);
        }
    }

    static void union(int u, int v) {
        int fu = find(u);
        int fv = find(v);
        
        if (fu == fv) return;

        parent[fv] = fu;
    }

    static int find(int node) {
        if (parent[node] == node) {
            return node;
        } else {
            return find(parent[node]);
        }
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            parent[i] = i;
        }

        edges = new int[M][2];
        int u, v, small, big;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());

            small = Math.min(u, v);
            big = Math.max(u, v);
            
            edges[i] = new int[]{small, big};
        }

        solution();

        Set<Integer> set = new HashSet<>();
        int fi;
        for (int i = 1; i < N + 1; i++) {
            fi = find(i);
            set.add(fi);
        }

        int result = set.size();
        System.out.println(result);
    }
}
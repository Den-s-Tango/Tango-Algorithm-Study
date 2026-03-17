import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int[] parent;

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
        
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            parent[i] = i;
        }

        for (int i = 1; i < m + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            if (find(u) != find(v)) {
                union(u, v);
            } else {
                System.out.println(i);
                return;
            }
        }

        System.out.println(0);
    }
}
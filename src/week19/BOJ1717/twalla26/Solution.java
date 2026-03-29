import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N, M;
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

        if (fu == fv) {
            return;
        }

        if (fu < fv) {
            parent[fv] = fu;
        } else {
            parent[fu] = fv;
        }
    }

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int operation = Integer.parseInt(st.nextToken());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            if (operation == 0) {
                union(u, v);
            } else {
                if (find(u) == find(v)) {
                    sb.append("YES");
                } else {
                    sb.append("NO");
                }
                sb.append("\n");
            }
        }

        System.out.println(sb);
    }
}
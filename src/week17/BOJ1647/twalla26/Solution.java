import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;
    
    static int N, M;
    static int[] parent;
    static PriorityQueue<Edge> edges;

    static int kruskal() {
        
        parent = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            parent[i] = i;
        }

        int sum = 0, max = 0;
        while (!edges.isEmpty()) {
            Edge cur = edges.poll();
            if (find(cur.u) != find(cur.v)) {
                union(cur.u, cur.v);
                max = Math.max(max, cur.cost);
                sum += cur.cost;
            }
        }

        return sum - max;
    }

    static int find(int u) {
        if (parent[u] == u) {
            return u;
        }

        return parent[u] = find(parent[u]);
    }

    static void union(int u, int v) {
        int pu = find(u);
        int pv = find(v);

        if (pu < pv) {
            parent[pv] = pu;
        } else {
            parent[pu] = pv;
        }
    }

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edges = new PriorityQueue<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            edges.add(new Edge(A, B, C));
        }

        int result = kruskal();
        System.out.println(result);
    }
}

class Edge implements Comparable<Edge> {
    int u;
    int v;
    int cost;

    public Edge(int u, int v, int cost) {
        this.u = u;
        this.v = v;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return this.cost - o.cost;
    }
}
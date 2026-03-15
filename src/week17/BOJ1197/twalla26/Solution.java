import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int V, E;
    static int[] parent;
    static PriorityQueue<Edge> edges;

    static int kruskal() {

        parent = new int[V + 1];
        for (int i = 1; i < V + 1; i++) {
            parent[i] = i;
        }

        int sum = 0;
        while (!edges.isEmpty()) {
            Edge edge = edges.poll();
            if (find(edge.u) != find(edge.v)) {
                union(edge.u, edge.v);
                sum += edge.cost;
            }
        }
        
        return sum;
    }

    static void union(int u, int v) {
        int pu = find(u);
        int pv = find(v);

        if (pu < pv) 
            parent[pv] = pu;
        else
            parent[pu] = pv;
    }

    static int find(int index) {
        if (parent[index] == index) {
            return index;
        }

        return parent[index] = find(parent[index]);
    }

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        edges = new PriorityQueue<>();
        for (int i = 0; i < E; i++) {
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
        this. u = u;
        this.v = v;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return this.cost - o.cost;
    }
}
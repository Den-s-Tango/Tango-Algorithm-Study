import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N, M;
    static PriorityQueue<Edge> pq;
    static int[] parent;

    static int solution() {

        int result = 0;
        parent = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            parent[i] = i;
        }

        while (!pq.isEmpty()) {

            Edge e = pq.poll();

            if (find(e.u) != find(e.v)) {
                union(e.u, e.v);
                result += e.cost;
            }
        }

        return result;
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

    static int find(int u) {
        if (parent[u] == u) {
            return u;
        }

        return parent[u] = find(parent[u]);
    }

    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        pq = new PriorityQueue<>((o1, o2) -> {
            return o1.cost - o2.cost;
        });

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            pq.add(new Edge(a, b, c));
        }

        int result = solution();
        System.out.println(result);
    }
}

class Edge {
    int u;
    int v;
    int cost;

    public Edge(int u, int v, int cost) {
        this.u = u;
        this.v = v;
        this.cost = cost;
    }
}
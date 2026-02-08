import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N;
    static int INF = 100_000_000;
    static int[] dist;

    static boolean bellmanFord(List<Edge> edges, int S) {

        dist = new int[N + 1];
        Arrays.fill(dist, INF);

        dist[S] = 0;
        for (int i = 1; i < N; i++) {
            for (Edge edge : edges) {
                if (dist[edge.v] > dist[edge.u] + edge.weight) {
                    dist[edge.v] = dist[edge.u] + edge.weight;
                }
            }
        }

        for (Edge edge : edges) {
            if (dist[edge.u] != INF && dist[edge.v] > dist[edge.u] + edge.weight) {
                dist[edge.v] = dist[edge.u] + edge.weight;
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        
        int TC = Integer.parseInt(br.readLine());

        int M, W, S, E, T;
        List<Edge> edges;

        for (int tc = 0; tc < TC; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            edges = new ArrayList<>();

            for (int m = 0; m < M; m++) {
                st = new StringTokenizer(br.readLine());
                S = Integer.parseInt(st.nextToken());
                E = Integer.parseInt(st.nextToken());
                T = Integer.parseInt(st.nextToken());

                edges.add(new Edge(S, E, T));
                edges.add(new Edge(E, S, T));
            }

            for (int w = 0; w < W; w++) {
                st = new StringTokenizer(br.readLine());
                S = Integer.parseInt(st.nextToken());
                E = Integer.parseInt(st.nextToken());
                T = Integer.parseInt(st.nextToken());

                edges.add(new Edge(S, E, -T));
            }
            
            if (bellmanFord(edges, 0)) {
                sb.append("NO").append("\n");
            } else {
                sb.append("YES").append("\n");
            }
        }

        System.out.println(sb);
    }
}

class Edge {
    int u;
    int v;
    int weight;

    public Edge(int u, int v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }
}
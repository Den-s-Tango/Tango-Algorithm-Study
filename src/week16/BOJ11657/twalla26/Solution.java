import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N, M;
    static List<Node> buses;
    static long[] dist;
    static int INF = 100_000_000;

    static boolean bellmanFord() {

        dist = new long[N + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;

        for (int i = 1; i < N; i++) {
            for (Node bus : buses) {
                if (dist[bus.start] != INF && dist[bus.end] > dist[bus.start] + bus.time) {
                    dist[bus.end] = dist[bus.start] + bus.time;
                }
            }
        }

        for (Node bus : buses) {
            if (dist[bus.start] != INF && dist[bus.end] > dist[bus.start] + bus.time) {
                return true;
            }
        }

        for (int i = 2; i < N + 1; i++) {
            if (dist[i] == INF) {
                dist[i] = -1;
            }
        }

        return false;
    }

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        buses = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            buses.add(new Node(A, B, C));
        }

        if (bellmanFord()) {
            System.out.println(-1);
            return;
        }

        for (int i = 2; i < N + 1; i++) {
            sb.append(dist[i]).append("\n");
        }
        System.out.println(sb);
    }
}

class Node {
    int start;
    int end;
    int time;

    public Node(int start, int end, int time) {
        this.start = start;
        this.end = end;
        this.time = time;
    }
}
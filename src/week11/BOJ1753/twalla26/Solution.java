import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int V, E;
    static int INF = 3000_000;
    static List<List<Node>> graph;

    static int[] dijkstra(int start) {

        int[] dist = new int[V + 1];
        Arrays.fill(dist, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
            return o1.cost - o2.cost;
        });
        pq.add(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int curIdx = cur.index;
            int curCost = cur.cost;

            if (curCost > dist[curIdx]) {
                continue;
            }

            for (Node next : graph.get(curIdx)) {
                if (next.cost + curCost < dist[next.index]) {
                    dist[next.index] = next.cost + curCost;
                    pq.add(new Node(next.index, dist[next.index]));
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) throws Exception {
        
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        int start = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        for (int i = 0; i < V + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(u).add(new Node(v, w));
        }

        int[] dist = dijkstra(start);

        for (int i = 1; i < V + 1; i++) {
            if (dist[i] == INF) {
                sb.append("INF").append("\n");
            } else {
                sb.append(dist[i]).append("\n");
            }
        }

        System.out.println(sb);
    }
}

class Node {
    int index;
    int cost;

    public Node(int index, int cost) {
        this.index = index;
        this.cost = cost;
    }
}
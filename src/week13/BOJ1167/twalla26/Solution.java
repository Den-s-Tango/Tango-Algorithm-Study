import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int V, INF = 1_000_000_000;
    static long[] distance;
    static List<List<Node>> graph;

    static int dijkstra(int S) {

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
            return o1.cost - o2.cost;
        });
        distance = new long[V + 1];
        Arrays.fill(distance, INF);
        
        pq.add(new Node(S, 0));
        distance[S] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int curIndex = cur.index;
            int curCost = cur.cost;

            if (distance[curIndex] < curCost) {
                continue;
            }

            for (Node next : graph.get(curIndex)) {
                if (distance[next.index] > curCost + next.cost) {
                    distance[next.index] = curCost + next.cost;
                    pq.add(new Node(next.index, curCost + next.cost));
                }
            }
        }

        long max = 0;
        int maxI = 0;
        for (int i = 1; i < V + 1; i++) {
            if (max < distance[i]) {
                max = distance[i];
                maxI = i;
            }
        }

        return maxI;
    }

    public static void main(String[] args) throws Exception {

        V = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        for (int i = 0; i < V + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < V; i++) {

            st = new StringTokenizer(br.readLine());
            int vertex = Integer.parseInt(st.nextToken());

            while (true) {

                int index = Integer.parseInt(st.nextToken());
                if (index == -1) {
                    break;
                }
                int cost = Integer.parseInt(st.nextToken());

                graph.get(vertex).add(new Node(index, cost));
            }
        }

        int A = dijkstra(1);
        int B = dijkstra(A);

        System.out.println(distance[B]);
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
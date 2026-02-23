import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int n;
    static List<List<Node>> graph;

    static void dijkstra(int start, int end) {

        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        int[] parent = new int[n + 1];

        Node cur = new Node(start, 0);
        pq.add(cur);
        distance[start] = 0;

        while (!pq.isEmpty()) {

            cur = pq.poll();

            if (cur.index == end) {
                break;
            }

            if (cur.cost > distance[cur.index]) {
                continue;
            }

            for (Node next : graph.get(cur.index)) {
                if (distance[next.index] > cur.cost + next.cost) {
                    distance[next.index] = cur.cost + next.cost;
                    parent[next.index] = cur.index;
                    pq.add(new Node(next.index, cur.cost + next.cost));
                }
            }
        }

        int totalMinCost = distance[end];
        sb.append(totalMinCost).append("\n");

        List<Integer> path = new ArrayList<>();
        path.add(cur.index);

        int prevIndex = parent[cur.index];
        while (prevIndex != 0) {
            path.add(prevIndex);
            prevIndex = parent[prevIndex];
        }

        sb.append(path.size()).append("\n");

        for (int i = path.size() - 1; i >= 0; i--) {
            sb.append(path.get(i)).append(" ");
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {

        n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(u).add(new Node(v, cost));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dijkstra(start, end);
    }
}

class Node implements Comparable<Node> {
    int index;
    int cost;

    public Node(int index, int cost) {
        this.index = index;
        this.cost = cost;
    }   

    @Override
    public int compareTo(Node o) {
        return this.cost - o.cost;
    }
}
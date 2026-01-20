import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int dijkstra(int N, int M, List<List<Node>> graph, int startCity, int endCity) {

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
            return o1.cost - o2.cost;
        });
        pq.add(new Node(startCity, 0));

        int[] distance = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        while (!pq.isEmpty()) {

            Node curNode = pq.poll();
            int curIndex = curNode.index;
            int curCost = curNode.cost;

            if (curCost > distance[curIndex]) {
                continue;
            }

            for (Node neighbor : graph.get(curIndex)) {
                if (neighbor.cost + curCost < distance[neighbor.index]) {
                    distance[neighbor.index] = neighbor.cost + curCost;
                    pq.add(new Node(neighbor.index, distance[neighbor.index]));
                }
            }
        }

        return distance[endCity];
    }

    public static void main(String[] args) throws Exception {

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(start).add(new Node(end, cost));
        }

        st = new StringTokenizer(br.readLine());
        int startCity = Integer.parseInt(st.nextToken());
        int endCity = Integer.parseInt(st.nextToken());

        int result = dijkstra(N, M, graph, startCity, endCity);
        System.out.println(result);
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
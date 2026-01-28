import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N, E;
    static int INF = 200_000_000;
    static List<List<Node>> graph;

    static int dijkstra(int start, int end) {

        PriorityQueue<Node> q = new PriorityQueue<>((o1, o2) -> {
            return o1.cost - o2.cost;
        });

        int[] distance = new int[N + 1];
        Arrays.fill(distance, INF);

        distance[start] = 0;
        q.add(new Node(start, 0));

        Node cur;
        while (!q.isEmpty()) {

            cur = q.poll();
            int curIdx = cur.index;
            int curCost = cur.cost;

            if (curCost > distance[curIdx]) {
                continue;
            }

            for (Node next : graph.get(curIdx)) {

                if (distance[next.index] > distance[curIdx] + next.cost) {
                    distance[next.index] = distance[curIdx] + next.cost;
                    q.add(new Node(next.index, distance[next.index])); 
                }
            }
        }

        return distance[end];
    }

    public static void main(String[] args) throws Exception {
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            Node node1 = new Node(n2, cost);
            Node node2 = new Node(n1, cost);

            graph.get(n1).add(node1);
            graph.get(n2).add(node2);
        }

        st = new StringTokenizer(br.readLine());
        int stop1 = Integer.parseInt(st.nextToken());
        int stop2 = Integer.parseInt(st.nextToken());

        int startToStop1 = dijkstra(1, stop1);
        int startToStop2 = dijkstra(1, stop2);

        int stop1ToStop2 = dijkstra(stop1, stop2);

        int stop1ToEnd = dijkstra(stop1, N);
        int stop2ToEnd = dijkstra(stop2, N);

        int min1 = startToStop1 + stop1ToStop2 + stop2ToEnd;
        int min2 = startToStop2 + stop1ToStop2 + stop1ToEnd;

        int result = Math.min(min1, min2);
        result = (result >= INF) ? -1 : result;
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
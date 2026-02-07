import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N;
    static List<List<Node>> graph, reverseGraph;

    static int[] dijkstra(int start, List<List<Node>> graph) {

        Queue<Node> q = new PriorityQueue<>((o1, o2) -> {
            return o1.time - o2.time;
        });

        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        q.add(new Node(start, 0));
        dist[start] = 0;

        while (!q.isEmpty()) {

            Node cur = q.poll();
            int curIndex = cur.index;
            int curTime = cur.time;

            if (curTime > dist[curIndex]) {
                continue;
            }

            for (Node next : graph.get(curIndex)) {
                if (curTime + next.time < dist[next.index]) {
                    dist[next.index] = curTime + next.time;
                    q.add(new Node(next.index, curTime + next.time));
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) throws Exception {
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        reverseGraph = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
            reverseGraph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            graph.get(start).add(new Node(end, time));
            reverseGraph.get(end).add(new Node(start, time));
        }
        
        int[] back = dijkstra(X, graph);
        int[] go = dijkstra(X, reverseGraph);

        int max = 0;
        for (int i = 1; i < N + 1; i++) {
            max = Math.max(max, back[i] + go[i]);
        }

        System.out.println(max);
    }
}

class Node {
    int index;
    int time;

    public Node(int index, int time) {
        this.index = index;
        this.time = time;
    }
}
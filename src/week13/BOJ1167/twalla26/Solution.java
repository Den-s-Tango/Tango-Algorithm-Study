import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int V;
    static List<List<Node>> graph;
    static int farthestNode = 0, maxDist = 0;
    static boolean[] visited;

    static void dfs(int index, int dist) {

        visited[index] = true;

        if (dist > maxDist) {
            maxDist = dist;
            farthestNode = index;
        }

        for (Node next : graph.get(index)) {
            if (!visited[next.index]) {
                dfs(next.index, dist + next.cost);
            }
        }
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

        visited = new boolean[V + 1];
        dfs(1, 0);

        maxDist = 0;

        visited = new boolean[V + 1];
        dfs(farthestNode, 0);

        System.out.println(maxDist);
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
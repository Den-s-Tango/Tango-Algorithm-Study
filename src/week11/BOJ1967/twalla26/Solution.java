import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int n;
    static List<List<Node>> tree;

    static Node getFarthestNode(Node node) {

        Queue<Node> q = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];

        q.add(node);
        visited[node.index] = true;

        int max = 0;
        Node farthestNode = node;

        while (!q.isEmpty()) {

            Node cur = q.poll();

            for (Node next : tree.get(cur.index)) {

                if (visited[next.index]) {
                    continue;
                }

                q.add(new Node(next.index, cur.cost + next.cost));
                visited[next.index] = true;
            }

            if (max < cur.cost) {
                max = cur.cost;
                farthestNode = cur;
            }
        }

        return farthestNode;
    }

    public static void main(String[] args) throws Exception {

        n = Integer.parseInt(br.readLine());

        tree = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            tree.add(new ArrayList<>());
        }

        int parent, child, cost;
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());

            parent = Integer.parseInt(st.nextToken());
            child = Integer.parseInt(st.nextToken());
            cost = Integer.parseInt(st.nextToken());

            tree.get(parent).add(new Node(child, cost));
            tree.get(child).add(new Node(parent, cost));
        }

        Node A = getFarthestNode(new Node(1, 0));
        Node B = getFarthestNode(new Node(A.index, 0));

        System.out.println(B.cost);
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
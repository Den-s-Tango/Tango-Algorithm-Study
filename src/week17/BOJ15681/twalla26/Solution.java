import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N, R, Q, U, V;
    static List<List<Integer>> edges;
    static Node tree;
    static int[] size;
    
    static void makeTree(Node cur) {
        for (int v : edges.get(cur.index)) {
            if (v != cur.parent) {
                Node node = new Node(v, cur.index);
                cur.children.add(node);
                makeTree(node);
            }
        }
    }

    static void countChildren(Node cur) {
        size[cur.index] = 1;
        for (Node child : cur.children) {
            countChildren(child);
            size[cur.index] += size[child.index];
        }
    }

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        edges = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            edges.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            U = Integer.parseInt(st.nextToken());
            V = Integer.parseInt(st.nextToken());

            edges.get(U).add(V);
            edges.get(V).add(U);
        }

        int[] queries = new int[Q];
        for (int i = 0; i < Q; i++) {
            queries[i] = Integer.parseInt(br.readLine());
        }

        tree = new Node(R, -1);
        makeTree(tree);

        size = new int[N + 1];
        countChildren(tree);

        for (int i = 0; i < Q; i++) {
            sb.append(size[queries[i]]).append("\n");
        }
        
        System.out.println(sb);
    }
}

class Node {
    int index;
    int parent;
    List<Node> children = new ArrayList<>();

    public Node(int index, int parent) {
        this.index = index;
        this.parent = parent;
    }
}
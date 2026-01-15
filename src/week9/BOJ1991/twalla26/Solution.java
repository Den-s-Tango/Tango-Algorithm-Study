import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static Node makeTree(int N, String[][] tree) {

        Node root = new Node("A");
        Queue<Node> q = new LinkedList<>();
        q.add(root);

        String left, right;
        Node leftNode, rightNode;

        while (!q.isEmpty()) {

            Node current = q.poll();

            for (int i = 0; i < N; i++) {
                if (tree[i][0].equals(current.value)) {
                    left = tree[i][1];
                    right = tree[i][2];

                    leftNode = new Node(left);
                    current.leftNode = leftNode;
                    q.add(leftNode);

                    rightNode = new Node(right);
                    current.rightNode = rightNode;
                    q.add(rightNode);
                }
            }
        }

        return root;
    }

    static void preorder(Node node) {

        if (node.value.equals(".")) {
            return;
        }

        sb.append(node.value);
        preorder(node.leftNode);
        preorder(node.rightNode);
    }

    static void inorder(Node node) {

        if (node.value.equals(".")) {
            return;
        }

        inorder(node.leftNode);
        sb.append(node.value);
        inorder(node.rightNode);
    }

    static void postorder(Node node) {

        if (node.value.equals(".")) {
            return;
        }

        postorder(node.leftNode);
        postorder(node.rightNode);
        sb.append(node.value); 
    }

    public static void main(String[] args) throws Exception {

        int N = Integer.parseInt(br.readLine());

        String[][] tree = new String[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            tree[i][0] = st.nextToken();
            tree[i][1] = st.nextToken();
            tree[i][2] = st.nextToken();
        }

        Node root = makeTree(N, tree);

        preorder(root);
        sb.append("\n");

        inorder(root);
        sb.append("\n");

        postorder(root);

        System.out.println(sb);
    }
}

class Node {

    String value;
    Node leftNode, rightNode;

    Node(String value) {
        this.value = value;
    }
}
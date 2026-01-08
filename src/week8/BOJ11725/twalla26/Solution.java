import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N;
    static int[] parents;
    static List<Integer>[] adjacent;

    static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(1);

        boolean[] visited = new boolean[N + 1];
        visited[1] = true;

        parents = new int[N + 1];

        while (!q.isEmpty()) {
            int parent = q.poll();

            for (int i : adjacent[parent]) {
                if (visited[i]) {
                    continue;
                }

                q.add(i);
                parents[i] = parent;
                visited[i] = true;
            }
        }
    }

    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());

        adjacent = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            adjacent[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            adjacent[node1].add(node2);
            adjacent[node2].add(node1);
        }

        bfs();

        for (int i = 2; i < N + 1; i++) {
            sb.append(parents[i]).append("\n");
        }
        System.out.println(sb);
    }
}
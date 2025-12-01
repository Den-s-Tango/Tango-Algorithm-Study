import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N, M, V;
    static boolean[] visited;
    static int[][] edges;

    static void dfs(int node) {
        visited[node] = true;
        sb.append(node).append(" ");

        for (int i = 0; i < 2 * M; i++) {
            if (edges[i][0] == node && !visited[edges[i][1]]) {
                dfs(edges[i][1]);
            }
        }
    }

    static void bfs() {
        Queue<Integer> q = new LinkedList<>();

        q.add(V);

        while (!q.isEmpty()) {
            int cur = q.poll();

            if (visited[cur]) {
                continue;
            }

            visited[cur] = true;
            sb.append(cur).append(" ");

            for (int i = 0; i < 2 * M; i++) {
                if (edges[i][0] == cur) {
                    q.add(edges[i][1]);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        edges = new int[2 * M][2];
        int start, dest;
        for (int i = 0; i < 2 * M; i += 2) {
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            dest = Integer.parseInt(st.nextToken());
            edges[i][0] = start;
            edges[i][1] = dest;
            edges[i + 1][0] = dest;
            edges[i + 1][1] = start;
        }

        Arrays.sort(edges, (o1, o2) -> {
            if (o1[0] != o2[0]) return o1[0] - o2[0];
            else return o1[1] - o2[1];
        });

        visited = new boolean[N + 1];
        dfs(V);
        sb.append("\n");

        visited = new boolean[N + 1];
        bfs();

        System.out.println(sb);
    }
}
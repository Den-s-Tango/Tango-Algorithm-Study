import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N;
    static int[][] graph;

    static void bfs(int start) {

        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N];

        q.add(start);

        int[] result = new int[N];
        while (!q.isEmpty()) {

            int current = q.poll();
            for (int j = 0; j < N; j++) {
                if (graph[current][j] == 0) {
                    continue;
                }

                if (visited[j] == true) {
                    continue;
                }

                result[j] = 1;
                visited[j] = true;
                q.add(j);
            }
        }

        for (int j = 0; j < N; j++) {
            sb.append(result[j]).append(" ");
        }
        sb.append("\n");
    }

    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());

        graph = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            bfs(i);
        }

        System.out.println(sb);
    }
}
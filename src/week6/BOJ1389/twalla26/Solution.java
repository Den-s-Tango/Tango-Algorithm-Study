import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N, M;
    static List<Integer>[] graph;
    static Queue<int[]> q;
    static boolean[] visited;

    static void solution() {

        int result = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < N + 1; i++) {
            int num = bfs(i);
            if (num < min) {
                min = num;
                result = i;
            }
        }

        System.out.println(result);
    }

    static int bfs(int i) {

        q = new LinkedList<>();
        visited = new boolean[N + 1];

        q.add(new int[]{i, 0});

        int result = 0;
        int curNum, length;
        while (!q.isEmpty()) {

            int[] node = q.poll();
            curNum = node[0];
            length = node[1];

            result += length;

            for (int next : graph[curNum]) {
                if (!visited[next]) {
                    visited[next] = true;
                    q.add(new int[]{next, length + 1});
                }
            }
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        int u1, u2;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            u1 = Integer.parseInt(st.nextToken());
            u2 = Integer.parseInt(st.nextToken());

            graph[u1].add(u2);
            graph[u2].add(u1);
        }

        solution();
    }
}
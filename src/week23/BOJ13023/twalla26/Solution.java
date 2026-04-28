import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N, M;
    static boolean result = false;
    static boolean[] visited;
    static List<List<Integer>> graph;

    static int solution() {

        for (int i = 1; i < N + 1; i++) {

            visited = new boolean[N + 1];
            visited[i] = true;
            dfs(i, 1);

            if (result) {
                return 1;
            }
        }

        return 0;
    }

    static void dfs(int cur, int cnt) {

        if (cnt == 5) {
            result = true;
            return;
        }

        for (int next : graph.get(cur)) {
            if (visited[next]) {
                continue;
            }

            visited[next] = true;
            dfs(next, cnt + 1);
            visited[next] = false;
        }
    }

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        int result = solution();
        System.out.println(result);
        
    }
}

class Node {
    int index;
    int dist;

    public Node(int index, int dist) {
        this.index = index;
        this.dist = dist;
    }
}
import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int V, E;
    static List<List<Integer>> graph;

    static boolean solution() {

        int[] colors = new int[V + 1];

        for (int i = 1; i < V + 1; i++) {

            if (colors[i] != 0) {
                continue;
            }

            Queue<Integer> q = new LinkedList<>();
            q.add(i);
            colors[i] = 1;

            while (!q.isEmpty()) {
                int cur = q.poll();
                int curColor = colors[cur];

                for (int next : graph.get(cur)) {
                    if (colors[next] == curColor) {
                        return false;
                    }

                    if (colors[next] == -curColor) {
                        continue;
                    }

                    colors[next] = -curColor;
                    q.add(next);
                }
            }
        }

        return true;
    }

    public static void main(String[] args) throws Exception {

        int K = Integer.parseInt(br.readLine());

        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();
            for (int i = 0; i < V + 1; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            boolean result = solution();
            if (result) {
                sb.append("YES");
            } else {
                sb.append("NO");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
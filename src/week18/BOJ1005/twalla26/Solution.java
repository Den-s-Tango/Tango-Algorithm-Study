import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;
    
    static int topologySort(int N, int W, int[] time, int[] indegree, List<List<Integer>> graph) {

        Queue<Integer> q = new LinkedList<>();
        int[] cache = new int[N + 1];
        
        for (int i = 1; i < N + 1; i++) {
            cache[i] = time[i];
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        while (!q.isEmpty()) {

            int cur = q.poll();

            for (int next: graph.get(cur)) {
                cache[next] = Math.max(cache[next], cache[cur] + time[next]);

                indegree[next] -= 1;
                if (indegree[next] == 0) {
                    q.add(next);
                }
            }
        }

        return cache[W];
    }

    public static void main(String[] args) throws Exception {

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[] time = new int[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i < N + 1; i++) {
                time[i] = Integer.parseInt(st.nextToken());
            }

            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i < N + 1; i++) {
                graph.add(new ArrayList<>());
            }

            int[] indegree = new int[N + 1];
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());

                graph.get(X).add(Y);
                indegree[Y] += 1;
            }

            int W = Integer.parseInt(br.readLine());

            int result = topologySort(N, W, time, indegree, graph);

            sb.append(result).append("\n");
        }
        
        System.out.println(sb);
    }
}
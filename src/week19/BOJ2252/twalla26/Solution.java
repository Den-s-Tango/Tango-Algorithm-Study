import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N, M;
    static List<List<Integer>> graph;
    static int[] indegree;

    static List<Integer> topologySort() {

        List<Integer> result = new ArrayList<>();

        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i < N + 1; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        while (!q.isEmpty()) {

            int cur = q.poll();
            result.add(cur);
            for (int next : graph.get(cur)) {
                indegree[next] -= 1;

                if (indegree[next] == 0) {
                    q.add(next);
                }
            }
        }
        
        return result;
    }

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        indegree = new int[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            graph.get(A).add(B);
            indegree[B] += 1;
        }

        List<Integer> result = topologySort();
        for (int elem : result) {
            sb.append(elem).append(" ");
        }
        sb.append("\n");

        System.out.println(sb);
    }
}
import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N, M;
    static int[] indegree;
    static List<List<Integer>> graph;

    static int[] topologySort() {
        int[] order = new int[N];

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                pq.add(i);
            }
        }

        int index = 0;
        while (!pq.isEmpty()) {
            int cur = pq.poll();
            order[index] = cur;
            index += 1;

            for (int next : graph.get(cur)) {
                indegree[next] -= 1;

                if (indegree[next] == 0) {
                    pq.add(next);
                }
            }
        }

        return order;
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

        int[] order = topologySort();
        for (int i = 0; i < N; i++) {
            sb.append(order[i]).append(" ");
        }
        System.out.println(sb);
    }
}
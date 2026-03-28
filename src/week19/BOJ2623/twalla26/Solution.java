import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N, M;
    static int[] indegree;
    static List<List<Integer>> graph;

    static int[] solution() {
        int[] result = new int[N + 1];

        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i < N + 1; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        int index = 1;
        while (!q.isEmpty()) {

            int cur = q.poll();
            result[index] = cur;
            index += 1;

            for (int next : graph.get(cur)) {
                indegree[next] -= 1;

                if (indegree[next] == 0) {
                    q.add(next);
                }
            }
        }

        if (index < N + 1) {
            result[1] = 0;
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
            int numOfSinger = Integer.parseInt(st.nextToken());
            int prev = 0;
            for (int j = 0; j < numOfSinger; j++) {
                int cur = Integer.parseInt(st.nextToken());
                if (j > 0) {
                    graph.get(prev).add(cur);
                    indegree[cur] += 1;
                }
                prev = cur;
            }
        }

        int[] result = solution();
        if (result[1] == 0) {
            System.out.println(0);
            return;
        }

        for (int i = 1; i < N + 1; i++) {
            System.out.println(result[i]);
        }
        
    }
}
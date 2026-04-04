import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N;
    static int[] arr;
    static boolean[] isCycle, visited, finished;

    static int solution() {

        isCycle = new boolean[N + 1];
        visited = new boolean[N + 1];
        finished = new boolean[N + 1];

        for (int i = 1; i < N + 1; i++) {
            dfs(i);
        }

        int cnt = 0;
        for (int i = 1; i < N + 1; i++) {
            if (!isCycle[i]) {
                cnt += 1;
            }
        }

        return cnt;
    }

    static void dfs(int cur) {

        visited[cur] = true;
        int next = arr[cur];

        if (!visited[next]) {
            dfs(next);
        } else if (!finished[next]) {
            isCycle[cur] = true;
            int temp = next;
            while (temp != cur) {
                isCycle[temp] = true;
                temp = arr[temp];
            }
        }

        finished[cur] = true;
    }


    public static void main(String[] args) throws Exception {

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i < N + 1; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int result = solution();
            sb.append(result).append("\n");
        }

        System.out.println(sb);
    }
}
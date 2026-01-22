import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int bfs(int N, int K) {

        int[] time = new int[100001];
        Arrays.fill(time, -1);

        time[N] = 0;

        Queue<Integer> q = new LinkedList<>();
        q.add(N);

        int cur, next;
        while (!q.isEmpty()) {

            cur = q.poll();

            if (cur == K) {
                break;
            }

            next = cur - 1;
            if (isBound(next)) {
                if (time[next] == -1) {
                    time[next] = time[cur] + 1;
                    q.add(next);
                } else {
                    if (time[next] > time[cur] + 1) {
                        time[next] = time[cur] + 1;
                        q.add(next);
                    }
                }
            }

            next = cur + 1;
            if (isBound(next)) {
                if (time[next] == -1) {
                    time[next] = time[cur] + 1;
                    q.add(next);
                } else {
                    if (time[next] > time[cur] + 1) {
                        time[next] = time[cur] + 1;
                        q.add(next);
                    }
                }
            }

            next = cur * 2;
            if (isBound(next)) {
                if (time[next] == -1) {
                    time[next] = time[cur];
                    q.add(next);
                } else {
                    if (time[next] > time[cur]) {
                        time[next] = time[cur];
                        q.add(next);
                    }
                }
            }    
        }

        return time[K];
    }

    static boolean isBound(int i) {
        return 0 <= i && i < 100001;
    }

    public static void main(String[] args) throws Exception {
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int result = bfs(N, K);
        System.out.println(result);
    }
}
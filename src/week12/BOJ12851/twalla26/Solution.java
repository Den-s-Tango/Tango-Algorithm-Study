import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int min, cnt;

    static void bfs(int N, int K) {
        
        Queue<Integer> q = new ArrayDeque<>();

        int[] dist = new int[100_001];
        Arrays.fill(dist, Integer.MAX_VALUE);

        min = Integer.MAX_VALUE;
        cnt = 0;

        q.add(N);
        dist[N] = 0;

        while (!q.isEmpty()) {

            int cur = q.poll();
            int curTime = dist[cur];

            if (cur == K) {
                if (curTime < min) {
                    min = curTime;
                    cnt = 1;
                    continue;
                } else if (curTime == min) {
                    cnt += 1;
                    continue;
                } else {
                    break;
                }
            }

            int[] nextArr = new int[]{cur - 1, cur + 1, cur * 2};
            for (int i = 0; i < nextArr.length; i++) {
                int next = nextArr[i];

                if (!isBound(next)) {
                    continue;
                }

                if (curTime + 1 > dist[next]) {
                    continue;
                }

                dist[next] = curTime + 1;
                q.add(next);
            }
        }
    }

    static boolean isBound(int x) {
        return (0 <= x && x < 100_001);
    }

    public static void main(String[] args) throws Exception {
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        bfs(N, K);

        System.out.println(min);
        System.out.println(cnt);
    }
}
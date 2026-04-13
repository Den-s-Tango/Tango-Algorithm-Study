import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N, C;
    static int[] routers;
    static int min = 1_000_000_000, max = 0;

    static int solution() {

        int l = 1;
        int r = max - min;
        int m = 0;

        int answer = 0;
        while (l <= r) {

            m = (l + r) / 2;
            
            if (install(m)) {
                l = m + 1;
                answer = m;
            } else {
                r = m - 1;
            }
        }

        return answer;
    }

    static boolean install(int d) {

        int cnt = 1;
        int prev = routers[0];
        for (int i = 1; i < N; i++) {

            int cur = routers[i];
            if (cur - prev >= d) {
                cnt += 1;
                prev = cur;
            }

            if (cnt == C) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        routers = new int[N];
        for (int i = 0; i < N; i++) {
            int router = Integer.parseInt(br.readLine());
            routers[i] = router;

            if (router < min)
                min = router;
            if (router > max)
                max = router;
        }

        Arrays.sort(routers);

        int result = solution();
        System.out.println(result);
        
    }
}
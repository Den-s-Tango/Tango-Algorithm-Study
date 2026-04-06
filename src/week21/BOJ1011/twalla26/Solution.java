import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int solution(int dist) {

        double dn = Math.sqrt(dist);
        int n = (int) dn;

        if (dist == n * n) {
            return 2 * n - 1;
        } else if (dist <= n * n + n) {
            return 2 * n;
        } else {
            return 2 * n + 1;
        }
    }

    public static void main(String[] args) throws Exception {

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int result = solution(y - x);
            sb.append(result).append("\n");
        }

        System.out.println(sb);
    }
}

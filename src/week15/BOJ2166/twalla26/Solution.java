import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    public static void main(String[] args) throws Exception {

        int N = Integer.parseInt(br.readLine());

        long[][] dots = new long[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            dots[i][0] = Long.parseLong(st.nextToken());
            dots[i][1] = Long.parseLong(st.nextToken());
        }

        long S1 = 0, S2 = 0;
        for (int i = 0; i < N; i++) {

            if (i == N - 1) {
                S1 += dots[i][0] * dots[0][1];
                S2 -= dots[i][1] * dots[0][0];
                break;
            }

            S1 += dots[i][0] * dots[i + 1][1];
            S2 -= dots[i][1] * dots[i + 1][0];
        }

        double area = Math.abs(S1 + S2) / 2.0;
        System.out.println(String.format("%.1f", area));
    }
}
import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N;
    static int[] arr;
    static boolean[][] dp;

    static void makePalindrome() {

        dp = new boolean[N + 1][N + 1];

        for (int i = 1; i < N + 1; i++) {
            dp[i][i] = true;
        }

        for (int i = 1; i < N; i++) {
            if (arr[i] == arr[i + 1]) {
                dp[i][i + 1] = true;
            }
        }

        for (int len = 3; len < N + 1; len++) {
            for (int start = 1; start <= N - len + 1; start++) {
                int end = start + len - 1;
                dp[start][end] = arr[start] == arr[end] && dp[start + 1][end - 1];
            }
        }
    }

    public static void main(String[] args) throws Exception {
        
        N = Integer.parseInt(br.readLine());

        arr = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        makePalindrome();

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            if (dp[S][E]) {
                sb.append(1).append("\n");
            } else {
                sb.append(0).append("\n");
            }
        }

        System.out.println(sb);
    }
}
import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    public static void main(String[] args) throws Exception {

        int N = Integer.parseInt(br.readLine());

        int[] times = new int[N + 1];
        int[] profits = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            times[i] = Integer.parseInt(st.nextToken()); 
            profits[i] = Integer.parseInt(st.nextToken());
        }

        int[] cache = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            cache[i] = Math.max(cache[i], cache[i - 1]);

            int payday = i + times[i] - 1;
            if (payday > N) {
                continue;
            }

            cache[payday] = Math.max(cache[payday], cache[i - 1] + profits[i]);
        }

        System.out.println(cache[N]);
    }
}
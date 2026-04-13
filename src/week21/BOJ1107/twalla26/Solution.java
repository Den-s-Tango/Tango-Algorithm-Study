import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N, M;
    static List<Integer> buttons;

    static int solution() {

        int min = Math.abs(N - 100);

        for (int i = 0; i < 1_000_000; i++) {

            if (i == 0) {
                if (buttons.contains(0)) {
                    min = Math.min(min, 1 + Math.abs(N - 0));
                } 
                continue;
            }

            int cur = i;
            boolean possible = true;

            int cnt = 0;
            while (cur > 0) {
                int m = cur % 10;
                if (!buttons.contains(m)) {
                    possible = false;
                    break;
                }
                cur /= 10;
                cnt += 1;
            }

            if (possible) {
                min = Math.min(min, cnt + Math.abs(N - i));
            }
        }
       
        return min;
    }

    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        buttons = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            buttons.add(i);
        }

        if (M > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                int broken = Integer.parseInt(st.nextToken());
                buttons.remove(Integer.valueOf(broken));
            }
        } 

        int result = solution();
        System.out.println(result);
    }
}
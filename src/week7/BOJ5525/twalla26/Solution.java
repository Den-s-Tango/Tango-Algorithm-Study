import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N, M;
    static String S;

    static int solution() {

        int cnt, left, n;
        cnt = left = n = 0;
  
        char first, second, third;
        while (left < M - 2) {

            first = S.charAt(left);
            second = S.charAt(left + 1);
            third = S.charAt(left + 2);

            if (first == 'I' && second == 'O' && third == 'I'){
                n += 1;
                left += 2;
            } else {
                n = 0;
                left += 1;
            }

            if (n == N) {
                cnt += 1;
                n -= 1;
            }
        }

        return cnt;
    }

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        S = br.readLine();

        int result = solution();
        System.out.println(result);
    }
}
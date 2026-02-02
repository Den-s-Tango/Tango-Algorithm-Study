import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int C = 1_000_000_007;
    static long sum;

    static long pow(long N, int exp) {

        long result = 1;
        while (exp > 0) {
            if (exp % 2 == 1) {
                result = (result * N) % C;
            } 

            N = (N * N) % C;
            exp /= 2;
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        
        int M = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int S = Integer.parseInt(st.nextToken());

            sum += (S * pow(N, C - 2)) % C;
        }

        sum %= C;

        System.out.println(sum);
    }
}
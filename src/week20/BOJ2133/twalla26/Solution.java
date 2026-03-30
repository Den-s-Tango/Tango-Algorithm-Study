import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    public static void main(String[] args) throws Exception {

        int N = Integer.parseInt(br.readLine());

        if (N % 2 == 1) {
            System.out.println(0);
            return;
        }

        int[] cache = new int[N + 1];
        cache[2] = 3;

        for (int i = 4; i < N + 1; i++) {
            for (int j = 2; j < i; j += 2) {
                if (j == 2) {
                    cache[i] += cache[j] * cache[i - j];
                    continue;
                }
                cache[i] += 2 * cache[i - j];
            }
            cache[i] += 2;
        }

        System.out.println(cache[N]);   
    }
}
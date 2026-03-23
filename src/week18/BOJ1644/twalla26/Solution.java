import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    public static void main(String[] args) throws Exception {

        int N = Integer.parseInt(br.readLine());
        boolean[] isPrime = new boolean[N + 1];
        Arrays.fill(isPrime, true);

        isPrime[0] = isPrime[1] = false;

        for (int i = 2; i <= N; i++) {
            if (isPrime[i]) {
                for (int j = 2; i * j <= N; j++) {
                    isPrime[i * j] = false;
                }
            }
        }

        int i = 2, cnt = 0;
        while (i <= N) {
            
            if (!isPrime[i]) {
                i += 1;
                continue;
            }

            int sum = 0;
            int j = i;
        
            while (j <= N) {

                if (!isPrime[j]) {
                    j += 1;
                    continue;
                }

                sum += j;
                if (sum < N) {
                    j += 1;
                } else if (sum == N) {
                    cnt += 1;
                    break;
                } else {
                    break;
                }
            }
            i += 1;
        }

        System.out.println(cnt);

    }
}
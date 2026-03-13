import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int solution(int N, int S, int[] arr) {

        int minLength = 1_000_000;

        int left = 0, right, sum = 0;
        
        for (right = 0; right < N; right++) {
            sum += arr[right];
            while (sum >= S) {
                minLength = Math.min(minLength, right - left + 1);
                sum -= arr[left];
                left += 1;
            }
        }

        if (minLength == 1_000_000) 
            return 0;

        return minLength;
    }

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int result = solution(N, S, arr);
        System.out.println(result);
    }
}
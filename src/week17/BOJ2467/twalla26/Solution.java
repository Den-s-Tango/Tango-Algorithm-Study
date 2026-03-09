import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int[] mix(int N, int[] arr) {

        int min = Integer.MAX_VALUE;
        int result[] = new int[2];

        int left = 0, right = N - 1;
        while (left < right) {

            int sum = arr[left] + arr[right];

            if (min > Math.abs(sum)) {
                min = Math.abs(sum);
                result[0] = arr[left];
                result[1] = arr[right];
            }

            if (sum == 0) {
                break;
            } else if (sum > 0) {
                right -= 1;
            } else {
                left += 1;
            }
        }

        return result;
    }

    public static void main(String[] args) throws Exception {

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] result = mix(N, arr);
        System.out.println(result[0] + " " + result[1]);
        
    }
}
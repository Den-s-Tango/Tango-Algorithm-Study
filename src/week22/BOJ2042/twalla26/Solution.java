import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long[] arr = new long[N + 1];
        long[] sumArr = new long[N + 1];
        for (int i = 1; i < N + 1; i++) {
            arr[i] = Long.parseLong(br.readLine());
            sumArr[i] = sumArr[i - 1] + arr[i];
        }

        HashMap<Integer, Long> map = new HashMap<>();
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) {
                long amt = c - arr[b];
                map.put(b, amt);
            } else {
                long sum = sumArr[(int) c] - sumArr[b - 1];
                for (int index : map.keySet()) {
                    if (index <= c) {
                        sum += map.get(index);
                    } 
                    
                    if (index <= b - 1) {
                        sum -= map.get(index);
                    }
                }

                sb.append(sum).append("\n");
            }
        }

        System.out.println(sb);
        
    }
}
import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N;
    static int[] tang, window;

    static void solution() {

        window = new int[10];

        int left, max, curKind;
        left = max = curKind = 0;

        for (int right = 0; right < N; right++) {
            int fruit = tang[right];
            
            if (window[fruit] > 0) {
                window[fruit] += 1;
            } else {
                window[fruit] = 1;
                curKind += 1;
            }

            while (curKind > 2) {
                int leftFruit = tang[left];
                
                window[leftFruit] -= 1;
                if (window[leftFruit] == 0) {
                    curKind -= 1;
                }

                left += 1;
            }

            max = Math.max(max, right - left + 1);
        }

        System.out.println(max);
    }

    public static void main(String[] args) throws Exception {
        
        N = Integer.parseInt(br.readLine());
        tang = new int[N];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            tang[i] = Integer.parseInt(st.nextToken());
        }

        solution();
    }
}
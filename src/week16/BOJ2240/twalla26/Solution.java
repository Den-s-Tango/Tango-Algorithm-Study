import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int catchPlum(int T, int W, int[] plums) {

        int[][] cache = new int[T + 1][W + 1];

        for (int t = 1; t < T + 1; t++) {
            for (int w = 0; w < W + 1; w++) {

                if (w == 0) {
                    if (plums[t] == 1) {
                        cache[t][0] = cache[t - 1][0] + 1;
                    } else {
                        cache[t][0] = cache[t - 1][0];
                    }
                    continue;
                } 

                int pos = (w % 2 == 0 ? 1 : 2);
                if (plums[t] == pos) {
                    cache[t][w] = Math.max(cache[t - 1][w - 1], cache[t - 1][w]) + 1;
                } else {
                    cache[t][w] = Math.max(cache[t - 1][w - 1], cache[t - 1][w]);
                }
            }
        }

        int max = 0;
        for (int j = 0; j < W + 1; j++) {
            max = Math.max(max, cache[T][j]);
        }

        return max;
    }

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int[] plums = new int[T + 1];
        for (int i = 1; i < T + 1; i++) {
            plums[i] = Integer.parseInt(br.readLine());
        }        

        int max = catchPlum(T, W, plums);
        System.out.println(max);
    }
}
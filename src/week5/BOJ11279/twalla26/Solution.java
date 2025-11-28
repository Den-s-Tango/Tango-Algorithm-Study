import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < N; i++) {
            
            int x = Integer.parseInt(br.readLine());

            if (x == 0) {
                if (maxHeap.isEmpty()) {
                    sb.append(0).append("\n");
                } else {
                    sb.append(maxHeap.poll()).append("\n");
                }
            } else {
                maxHeap.add(x);
            }
        }

        System.out.println(sb);
    }
}
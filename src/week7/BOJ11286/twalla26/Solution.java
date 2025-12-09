import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N;
    static PriorityQueue<Integer> absHeap;

    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());

        absHeap = new PriorityQueue<>((o1, o2) -> {
            if (Math.abs(o1) == Math.abs(o2)) {
                return o1 - o2;
            } else {
                return Math.abs(o1) - Math.abs(o2);
            }
        });

        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());
            if (x == 0) {
                if (absHeap.isEmpty()) {
                    sb.append(0).append("\n");
                } else {
                    int first = absHeap.poll();
                    sb.append(first).append("\n");
                }
            } else {
                absHeap.add(x);
            }
        }
        
        System.out.println(sb);
    }
}
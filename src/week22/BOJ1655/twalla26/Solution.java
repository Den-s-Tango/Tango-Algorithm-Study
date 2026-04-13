import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    public static void main(String[] args) throws Exception {

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> low = new PriorityQueue<>((o1, o2) -> {
            return o2 - o1;
        });

        PriorityQueue<Integer> high = new PriorityQueue<>((o1, o2) -> {
            return o1 - o2;
        });   

        for (int i = 0; i < N; i++) {
            int target = Integer.parseInt(br.readLine());

            if (low.size() == high.size()) {
                low.add(target);
            } else {
                high.add(target);
            }

            if (!low.isEmpty() && !high.isEmpty()) {
                if (low.peek() > high.peek()) {
                    int temp = low.poll();
                    low.add(high.poll());
                    high.add(temp);
                }
            }

            sb.append(low.peek()).append("\n");
        }

        System.out.print(sb);
    }
}
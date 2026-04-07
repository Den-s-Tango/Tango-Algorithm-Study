import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N;
    static PriorityQueue<Integer> pq;

    static int solution() {

        int result = 0;

        for (int i = 1; i < N; i++) {
            int A = pq.poll();
            int B = pq.poll();

            result += A + B;
            pq.add(A + B);
        }

        return result;
    }

    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());

        pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }

        int result = solution();
        System.out.println(result);
    }
}
import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static long bfs(long A, long B) {
        Queue<long[]> q = new LinkedList<>();
        q.add(new long[]{A, 1});

        while (!q.isEmpty()) {
            long[] cur = q.poll();

            long num = cur[0];
            long level = cur[1];

            long next1 = num * 2;
            long next2 = num * 10 + 1;

            if (next1 == B || next2 == B) {
                return level + 1;
            }

            if (next1 < B) {
                q.add(new long[]{next1, level + 1});
            } 

            if (next2 < B) {
                q.add(new long[]{next2, level + 1});
            }
        }

        return -1;
    }

    public static void main(String[] args) throws Exception {
        
        st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        long result = bfs(A, B);
        System.out.println(result);
    }
}
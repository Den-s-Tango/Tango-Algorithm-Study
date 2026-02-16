import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int findMaxCommon(List<Integer> A, List<Integer> B) {
        int max = 0;
        for (int numA : A) {
            if (B.contains(numA)) {
                max = Math.max(max, numA);
            }
        }
        return max;
    }

    public static void main(String[] args) throws Exception {

        int N = Integer.parseInt(br.readLine());
        List<Integer> A = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A.add(Integer.parseInt(st.nextToken()));
        }

        int M = Integer.parseInt(br.readLine());
        List<Integer> B = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            B.add(Integer.parseInt(st.nextToken()));
        }

        List<Integer> result = new ArrayList<>();
        while (true) {

            int max = findMaxCommon(A, B);
            if (max == 0) {
                break;
            }

            result.add(max);

            A = A.subList(A.indexOf(max) + 1, A.size());
            B = B.subList(B.indexOf(max) + 1, B.size());
        }
        
        int size = result.size();
        sb.append(size).append("\n");

        for (int i = 0; i < size; i++) {
            sb.append(result.get(i)).append(" ");
        }

        System.out.println(sb);
    }
}
import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N, M;
    static int[] result;
    static Integer[] arr;

    static void solution(int length) {

        if (length == M) {
            printResult();
            return;
        }

        for (int i = 0; i < arr.length; i++) {

            int num = arr[i];
            if (length > 0 && result[length - 1] > num) {
                continue;
            }
            
            result[length] = num;
            solution(length + 1);
        }
    }

    static void printResult() {
        for (int i = 0; i < M; i++) {
            sb.append(result[i]).append(" ");
        }
        sb.append("\n");
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        HashSet<Integer> set = new HashSet<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            set.add(Integer.valueOf(st.nextToken()));
        }

        arr = set.toArray(new Integer[0]);
        Arrays.sort(arr);

        result = new int[M];
        solution(0);
        System.out.println(sb);
    }
}
import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int[] solution(int N, int[] input) {

        int[] result = new int[N];
        Arrays.fill(result, -1);

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {

            while (!stack.isEmpty() && input[stack.peek()] < input[i]) {
                int prevIndex = stack.pop();
                result[prevIndex] = input[i];
            }
            stack.push(i);
        }

        return result;
    }

    public static void main(String[] args) throws Exception {

        int N = Integer.parseInt(br.readLine());

        int[] input = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        int[] result = solution(N, input);
        for (int i = 0; i < N; i++) {
            sb.append(result[i]).append(" ");
        }
        System.out.println(sb);
    }
}
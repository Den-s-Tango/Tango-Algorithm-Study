import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int[] solution(int N, int[] input) {

        int[] result = new int[N];

        Stack<Node> stack = new Stack<>();
        for (int i = 0; i < N; i++) {

            Node cur = new Node(input[i], i);
            if (stack.isEmpty() || stack.peek().num >= cur.num) {
                stack.push(cur);
                continue;
            }

            while (!stack.isEmpty() && stack.peek().num < cur.num) {
                Node prev = stack.pop();
                result[prev.index] = cur.num;
            }
            stack.push(cur);
        }

        while (!stack.isEmpty()) {
            Node prev = stack.pop();
            result[prev.index] = -1;
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

class Node {
    int num;
    int index;

    public Node(int num, int index) {
        this.num = num;
        this.index = index;
    }
}
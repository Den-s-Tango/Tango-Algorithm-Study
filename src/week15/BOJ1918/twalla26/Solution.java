import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int getPriority(char target) {

        if (target == '(' || target == ')') {
            return 0;
        }

        if (target == '+' || target == '-') {
            return 1;
        }

        if (target == '*' || target == '/') {
            return 2;
        }

        return -1; // 알파벳
    }

    public static void main(String[] args) throws Exception {

        String line = br.readLine();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < line.length(); i++) {

            char target = line.charAt(i);
            int priority = getPriority(target);

            if (Character.isLetter(target)) {
                sb.append(target);
            } else if (target == '(') {
                stack.push(target);
            } else if (target == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    sb.append(stack.pop());
                }
                stack.pop();
            } else {
                while (!stack.isEmpty() && getPriority(stack.peek()) >= priority) {
                    sb.append(stack.pop());
                }
                stack.push(target);
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        System.out.println(sb);
    }
}
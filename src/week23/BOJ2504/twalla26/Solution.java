import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int solution(String[] line) {

        int N = line.length;
        Stack<String> stack = new Stack<>();

        for (int i = 0; i < N; i++) {

            String cur = line[i];

            if (cur.equals("(") || cur.equals("[")) {
                stack.push(cur);
                continue;
            }

            String target, nonTarget;
            int score;
            if (cur.equals(")")) {
                target = "(";
                nonTarget = "[";
                score = 2;
            } else {
                target = "[";
                nonTarget = "(";
                score = 3;
            }

            int temp = 0;
            boolean matched = false;
            while (!stack.isEmpty()) {

                String prev = stack.pop();

                // 괄호 탈출
                if (prev.equals(target)) {
                    if (temp > 0) {
                        temp *= score;
                    } else {
                        temp = score;
                    }
                    
                    stack.push(Integer.toString(temp));
                    matched = true;
                    break;
                }
                
                // 올바르지 못한 괄호
                if (prev.equals(nonTarget)) {
                    return 0;
                }

                // 숫자
                temp += Integer.parseInt(prev);
            }

            if (!matched) {
                return 0;
            }
        }

        int total = 0;
        while (!stack.isEmpty()) {

            String num = stack.pop();

            if (num.equals("(") || num.equals("[")) {
                return 0;
            }

            total += Integer.parseInt(num);

        }

        return total;
    }

    public static void main(String[] args) throws Exception {

        String[] line = br.readLine().split("");

        int score = solution(line);
        System.out.println(score);
        
    }
}
import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int A, B;

    static void solution() {
        char[] instructions = new char[]{'D', 'S', 'L', 'R'};
        Queue<String> insQ = new LinkedList<>();
        Queue<Integer> numQ = new LinkedList<>();
        boolean[] visited = new boolean[10000];

        insQ.add("");
        numQ.add(A);
        visited[A] = true;

        while (!insQ.isEmpty()) {
            String prevInsQ = insQ.poll();
            int prevNum = numQ.poll();
            
            String curIns;
            int curNum;
            for (int i = 0; i < 4; i++) {
                curIns = prevInsQ + instructions[i];

                if (i == 0) {
                    curNum = D(prevNum);
                } else if (i == 1) {
                    curNum = S(prevNum);
                } else if (i == 2) {
                    curNum = L(prevNum);
                } else {
                    curNum = R(prevNum);
                }

                if (visited[curNum]) {
                    continue;
                }

                if (curNum == B) {
                    sb.append(curIns).append("\n");
                    return;
                }

                insQ.add(curIns);
                numQ.add(curNum);
                visited[curNum] = true;
            }
        }
    }

    static int D(int num) {
        num *= 2;
        num %= 10000;

        return num;
    }

    static int S(int num) {
        if (num == 0) {
            return 9999;
        } else {
            return num - 1;
        }
    }

    static int L(int num) {
        String str = num + "";
        int length = str.length();

        if (length < 4) {
            str = "0".repeat(4 - length) + str;
        }
        
        char first = str.charAt(0);
        str = str.substring(1);
        str += first;

        return Integer.parseInt(str);
    }

    static int R(int num) {
        String str = num + "";
        int length = str.length();
        
        if (length < 4) {
            str = "0".repeat(4 - length) + str;
        }

        char last = str.charAt(3);
        str = str.substring(0, 3);
        str = last + str;

        return Integer.parseInt(str);
    }

    public static void main(String[] args) throws Exception {

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            solution();
        }

        System.out.println(sb);
    }
}
import java.util.*;

public class Solution {

    static Reader rd = new Reader();
    static StringBuilder ANSWER = new StringBuilder();
    static int[] tens = {0, 1, 10, 100, 1000};
    static boolean[] vst;

    public static class Node {
        int num;
        String cmds;

        Node(int num, String cmds) {
            this.num = num;
            this.cmds = cmds;
        }
    }

    public static void solution(int start, int target) {
        vst = new boolean[10000];
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(start, ""));
        vst[start] = true;

        while(!que.isEmpty()) {
            Node curr = que.poll();
            start = curr.num;

            int nextNum; String nextCmds;
            // D
            nextNum = (start * 2) % 10000;
            if (!vst[nextNum]) {
                vst[nextNum] = true;
                nextCmds = curr.cmds + "D";
                if (nextNum == target) {
                    ANSWER.append(nextCmds).append("\n");
                    break;
                }
                que.add(new Node(nextNum, nextCmds));
            }


            // S
            nextNum = (start == 0) ? 9999 : start - 1;
            if (!vst[nextNum]) {
                vst[nextNum] = true;
                nextCmds = curr.cmds + "S";
                if (nextNum == target) {
                    ANSWER.append(nextCmds).append("\n");
                    break;
                }
                que.add(new Node(nextNum, nextCmds));
            }


            int digit = (start < 10) ? 1 : (start < 100) ? 2 : (start < 1000) ? 3 : 4;;
            // L
            int leftest = start / 1000;
            nextNum = ((start * 10) % 10000) + leftest;
            if (!vst[nextNum]) {
                vst[nextNum] = true;
                nextCmds = curr.cmds + "L";
                if (nextNum == target) {
                    ANSWER.append(nextCmds).append("\n");
                    break;
                }
                que.add(new Node(nextNum, nextCmds));
            }


            // R
            int rightest = start % 10;
            nextNum = ((start / 10)) + (rightest * 1000);
            if (!vst[nextNum]) {
                vst[nextNum] = true;
                nextCmds = curr.cmds + "R";
                if (nextNum == target) {
                    ANSWER.append(nextCmds).append("\n");
                    break;
                }
                que.add(new Node(nextNum, nextCmds));
            }

        }
    }

    public static void main(String[] args) throws Exception {
        int TEST_CASE = rd.nextInt();
        while (TEST_CASE-- > 0) {
            int start = rd.nextInt();
            int target = rd.nextInt();

            solution(start, target);
        }
        System.out.println(ANSWER);
    }

    static class Reader {
        final int SIZE = 1 << 15;
        byte[] buffer = new byte[SIZE];
        int index, size;

        int nextInt() throws Exception {
            int n = 0;
            byte c;
            while ((c = read()) <= 32);
            boolean neg = c == '-' ? true : false;
            if (neg)
                c = read();
            do
                n = (n << 3) + (n << 1) + (c & 15);
            while (isNumber(c = read()));
            return neg ? -n : n;
        }
        
        char nextChar() throws Exception {
            byte c;
            while ((c = read()) <= 32) ;
            return (char) c;
        }

        boolean isNumber(byte c) {
            return 47 < c && c < 58;
        }

        byte read() throws Exception {
            if (index == size) {
                size = System.in.read(buffer, index = 0, SIZE);
                if (size < 0)
                    buffer[0] = -1;
            }
            return buffer[index++];
        }
    }
}
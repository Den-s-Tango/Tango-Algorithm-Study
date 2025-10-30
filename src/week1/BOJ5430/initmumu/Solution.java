
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Solution {

    public static StringBuilder ANSWER = new StringBuilder();

    public static void solution(String command, int N, int[] arr) {
        boolean isReversed = false;
        int start = 0;
        int end = arr.length - 1;

        for (int f = 0; f < command.length(); f++) {
            char cmd = command.charAt(f);

            switch (cmd) {
                case 'R':
                    isReversed = !isReversed;
                    break;
                case 'D':
                    if (start > end) {
                        ANSWER.append("error\n");
                        return;
                    }

                    if (isReversed) end--;
                    else start++;
            }
        }

        ANSWER.append("[");
        if (isReversed) {
            for (int i = end; i >= start; i--) {
                ANSWER.append(arr[i]);
                if (i != start) ANSWER.append(",");
            }
        } else {
            for (int i = start; i <= end; i++) {
                ANSWER.append(arr[i]);
                if (i != end) ANSWER.append(",");
            }
        }
        ANSWER.append("]\n");
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TEST_CASE = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < TEST_CASE; tc++) {
            String command = br.readLine();
            int N = Integer.parseInt(br.readLine());
            int[] arr = readArr(br.readLine());

            solution(command, N, arr);
        }
        System.out.println(ANSWER);
    }

    public static int[] readArr(String s) throws Exception {
        s = s.substring(1, s.length() - 1);
        if (s.length() == 0) return new int[0];
        String[] numbers = s.split(",");
        int[] res = new int[numbers.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = Integer.parseInt(numbers[i]);
        }

        return res;
    }
}
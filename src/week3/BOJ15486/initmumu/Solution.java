
public class Solution {

    static Reader rd = new Reader();

    public static void main(String[] args) throws Exception {
        int N = rd.nextInt();

        int[] T = new int[N + 1];
        int[] P = new int[N + 1];
        
        int[] dp = new int[N + 2]; 

        for (int i = 1; i <= N; i++) {
            T[i] = rd.nextInt();
            P[i] = rd.nextInt();
        }

        int maxVal = 0;
        
        for (int i = 1; i <= N + 1; i++) {
            
            maxVal = Math.max(maxVal, dp[i]);

            if (i > N) {
                continue;
            }
            
            int nextDay = i + T[i]; 

            if (nextDay <= N + 1) {
                dp[nextDay] = Math.max(dp[nextDay], maxVal + P[i]);
            }
        }
        
        System.out.println(maxVal);
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
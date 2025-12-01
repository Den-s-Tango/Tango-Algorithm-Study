
public class Solution {

    static Reader rd = new Reader();

    public static void main(String[] args) throws Exception {
        
        int N = rd.nextInt();

        int[] d = new int[N + 1];

        for (int i = 0; i < N; i++) {
            d[i] = rd.nextInt();
            d[i+1] = rd.nextInt();
        }

        long[][] dp = new long[N + 1][N + 1];

        for (int len = 2; len <= N; len++) {
            for (int i = 1; i <= N - len + 1; i++) {
                int j = i + len - 1;
                
                dp[i][j] = Long.MAX_VALUE; 

                for (int k = i; k < j; k++) {
                    
                    long cost = dp[i][k] + dp[k + 1][j] + (long)d[i - 1] * d[k] * d[j];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }

        System.out.println(dp[1][N]);
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
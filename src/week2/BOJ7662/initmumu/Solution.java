import java.util.*;


public class Solution {
    static Reader rd = new Reader();
    static StringBuilder sb = new StringBuilder();

    public static void solution() throws Exception {
        TreeMap<Integer, Integer> map = new TreeMap<>();

        int CMD_CNT = rd.nextInt();

        while (CMD_CNT-- > 0) {
            char cmd = rd.nextChar();
            int value = rd.nextInt();

            switch (cmd) {
                case 'I':
                    if (map.containsKey(value)) map.put(value, map.get(value) + 1);
                    else map.put(value, 1);

                    break;
                case 'D':
                    if (map.isEmpty()) continue;
                    
                    int key, amt;
                    if (value == -1) key = map.firstKey();
                    else key = map.lastKey();

                    amt = map.get(key);
                    if (amt == 1) map.remove(key);
                    else map.put(key, amt - 1);
            }
        }
        
        if (map.isEmpty()) sb.append("EMPTY").append("\n");
        else sb.append(map.lastKey()).append(" ").append(map.firstKey()).append("\n");
    }

    public static void main(String[] args) throws Exception {
        int TEST_CASE = rd.nextInt();

        while (TEST_CASE-- > 0) {
            solution();
        }

        System.out.println(sb);
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
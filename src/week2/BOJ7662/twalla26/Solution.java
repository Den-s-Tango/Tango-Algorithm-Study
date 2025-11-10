import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    public static void main(String[] args) throws Exception {

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int K = Integer.parseInt(br.readLine());

            TreeMap<Integer, Integer> map = new TreeMap<>();

            for (int k = 0; k < K; k++) {

                String[] instruction = br.readLine().split(" ");
                String method = instruction[0];
                int arg = Integer.parseInt(instruction[1]);

                if (method.equals("I")) {
                    if (map.containsKey(arg)) {
                        map.put(arg, map.get(arg) + 1);
                    } else {
                        map.put(arg, 1);
                    }
                } else {

                    if (map.isEmpty()) {
                        continue;
                    }

                    int key;
                    if (arg == -1) {
                        key = map.firstKey();
                        int amt = map.get(key);
                        if (amt == 1) {
                            map.pollFirstEntry();
                        } else {
                            map.put(key, map.get(key) - 1);
                        }
                    } else {
                        key = map.lastKey();
                        int amt = map.get(key);
                        if (amt == 1) {
                            map.pollLastEntry();
                        } else {
                            map.put(key, map.get(key) - 1);
                        }
                    }
                }
            }

            if (map.isEmpty()) {
                sb.append("EMPTY\n");
            } else {
                sb.append(map.lastKey()).append(" ").append(map.firstKey()).append("\n");
            }

        }

        System.out.println(sb);
        
        return;
    }
}
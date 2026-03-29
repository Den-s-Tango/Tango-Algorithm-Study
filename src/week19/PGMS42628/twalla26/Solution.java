import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        
        TreeMap<Integer, Integer> map = new TreeMap<>();
        
        for (int i = 0; i < operations.length; i++) {
            String[] operation = operations[i].split(" ");
            
            if (operation[0].equals("I")) {
                int num = Integer.parseInt(operation[1]);
                map.put(num, map.getOrDefault(num, 0) + 1);
            } else {
                if (map.isEmpty()) {
                    continue;
                }
                if (operation[1].equals("-1")) {
                    int minKey = map.firstKey();
                    if (map.get(minKey) > 1) {
                        map.put(minKey, map.get(minKey) - 1);
                    } else 
                        map.pollFirstEntry();
                } else {
                    int maxKey = map.lastKey();
                    if (map.get(maxKey) > 1) {
                        map.put(maxKey, map.get(maxKey) - 1);
                    } else 
                        map.pollLastEntry();
                }
            }
        }
        
        int[] answer;
        if (map.isEmpty()) {
            answer = new int[]{0, 0};
        } else {
            answer = new int[]{map.lastKey(), map.firstKey()};
        }
        
        return answer;
    }
}
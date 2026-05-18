import java.util.*;

class Solution {
    
    public int solution(String[][] clothes) {

        Map<String, Integer> map = new HashMap<>();
        
        for (String[] cloth : clothes) {
            String type = cloth[1];
            map.put(type, map.getOrDefault(type, 0) + 1);
        }
        
        int result = 1;
        for (String type: map.keySet()) {
            result *= map.get(type) + 1;
        }

        return result - 1;
    }
}
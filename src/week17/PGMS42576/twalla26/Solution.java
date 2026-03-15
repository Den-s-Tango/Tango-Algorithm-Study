import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < participant.length; i++) {
            String cur = participant[i];
            if (map.containsKey(cur)) {
                map.put(cur, map.get(cur) + 1);
            } else {
                map.put(cur, 1);
            }
        }
        
        for (int i = 0; i < completion.length; i++) {
            String cur = completion[i];
            if (map.containsKey(cur)) {
                if (map.get(cur) > 1) {
                    map.put(cur, map.get(cur) - 1);
                } else {
                    map.remove(cur);
                }
            }
        }
        
        Iterator<String> keys = map.keySet().iterator();
        while(keys.hasNext()){
            String key = keys.next();
            answer = key;
        }
        
        return answer;
    }
}
import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        
        int N = phone_book.length;
        Set<String> set = new HashSet<>();
        
        for (int i = 0; i < N; i++) {
            set.add(phone_book[i]);
        }
        
        for (int i = 0; i < N; i++) {
            
            String cur = phone_book[i];
            
            for (int j = 0; j < cur.length(); j++) {
                if (!set.contains(cur.substring(0, j + 1))) {
                    continue;
                }
                                    
                if (j < cur.length() - 1) {
                    return false;
                }
            }
        }
        
        return true;
    }
}
import java.util.*;

class Solution {
    public int solution(int[] citations) {
        
        int N = citations.length;   
        Arrays.sort(citations);

        
        for (int i = 0; i < N; i++) {
            int h = N - i;
            
            if (citations[i] >= h) {
                return h;
            }
        }

        return 0;
    }
}
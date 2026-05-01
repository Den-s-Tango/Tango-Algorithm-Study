import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        
        int N = scoville.length;
        PriorityQueue<Long> pq = new PriorityQueue<>();
        
        for (int i = 0; i < N; i++) {
            pq.add((long) scoville[i]);
        }
        
        int answer = 0;
        while (pq.size() >= 2) {
            Long first = pq.poll();
            
            if (first >= K) {
                return answer;
            }
            
            Long second = pq.poll();
            
            pq.add(first + second * 2);
            answer += 1;
        }
        
        if (pq.size() == 1 && pq.peek() >= K) {
            return answer;
        }
        
        return -1;
    }
}
import java.util.*;

class Solution {
    static boolean isHighest(Queue<int[]> q) {
        int[] cur = q.peek();
        int curPriority = cur[0];
        
        Iterator<int[]> iter = q.iterator();
        while (iter.hasNext()) {
            int[] next = iter.next();
            int nextPriority = next[0];
            int nextPos = next[1];

            if (curPriority < nextPriority) {
                return false;
            }
        }
        
        return true;
    }
    
    public int solution(int[] priorities, int location) {
        
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < priorities.length; i++) {
            q.add(new int[]{priorities[i], i});
        }
        
        int order = 1;
        while (!q.isEmpty()) {

            int[] cur = q.peek();
            int curPriority = cur[0];
            int curPos = cur[1];
            
            if (isHighest(q)) {
                if (curPos == location) {
                    break;
                } 
                
                q.poll();
                order += 1;
                continue;
            }
            
            q.add(q.poll());
        }
        
        return order;
    }
}
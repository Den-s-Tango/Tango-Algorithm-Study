import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        
        int N = progresses.length;
        int[] complete = new int[N];
        
        for (int i = 0; i < N; i++) {
            complete[i] = (100 - progresses[i]) / speeds[i];
            if ((100 - progresses[i]) % speeds[i] > 0) {
                complete[i] += 1;
            }
        }
        
        List<Integer> list = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        
        int max = complete[0], cnt = 0;
        for (int i = 0; i < N; i++) {
            if (max < complete[i]) {
                list.add(cnt);
                max = complete[i];
                cnt = 1;
                continue;
            } 
            
            cnt += 1;
        }
        
        if (cnt > 0) {
            list.add(cnt);
        }
        
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}
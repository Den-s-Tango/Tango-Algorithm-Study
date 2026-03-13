import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            
            if (stack.isEmpty()) {
                stack.push(arr[i]);
                continue;
            }
            
            if (stack.peek() == arr[i]) {
                continue;
            }
            
            stack.push(arr[i]);
            
        }
        
        int[] answer = new int[stack.size()];
        int idx = stack.size() - 1;
        while (!stack.isEmpty()) {
            answer[idx] = stack.pop();
            idx -= 1;
        }

        return answer;
    }
}
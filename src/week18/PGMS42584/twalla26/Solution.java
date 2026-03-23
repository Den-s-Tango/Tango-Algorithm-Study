import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        
        int N = prices.length;
        
        int[] answer = new int[N];        
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < N; i++) {
            int curIdx = i;
            int curPrice = prices[i];
            
            while (!stack.isEmpty()) {
                if (prices[stack.peek()] > curPrice) {
                    int prev = stack.pop();
                    answer[prev] = curIdx - prev;
                } else {
                    break;
                }
            }
            
            stack.push(curIdx);
        }
        
        for (int index : stack) {
            answer[index] = N - index - 1;
        }
        
        return answer;
    }
}
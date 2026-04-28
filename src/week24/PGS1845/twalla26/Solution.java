import java.util.*;

class Solution {
    public int solution(int[] nums) {
        
        int N = nums.length;
        
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < N; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        
        if (N / 2 >= map.size()) {
            return map.size();
        } else {
            return N / 2;
        }
  
    }
}
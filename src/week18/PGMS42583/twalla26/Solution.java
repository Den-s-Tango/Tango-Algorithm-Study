import java.util.*;

class Solution {
    
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        
        Queue<Integer> bridge = new LinkedList<>();
        for (int i = 0; i < bridge_length; i++) {
            bridge.add(0);
        }
        
        int complete = 0, truckIdx = 0, sec = 0;
        int numOfTrucks = truck_weights.length;
        while (complete < numOfTrucks) {
            sec += 1;
            
            if (bridge.poll() > 0) {
                complete += 1;
            }
            
            int curWeight = 0;
            for (int truck : bridge) {
                curWeight += truck;
            }
            
            if (truckIdx < numOfTrucks && curWeight + truck_weights[truckIdx] <= weight) {
                bridge.add(truck_weights[truckIdx]);
                truckIdx += 1;
            } else {
                bridge.add(0);
            }
        }
        
        return sec;
    }
}
import java.util.*;

class Solution {
    
    int N;
    
    int[] work(HashMap<Integer, ArrayList<Node>> map) {
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] ret = new int[N];
        
        Node cur = null;
        int done = 0;
        
        int sec = -1;
        while (true) {
            sec += 1;
            
            if (map.containsKey(sec)) {
                for (Node elem : map.get(sec)) {
                    pq.add(elem);
                }
            }
            
            if (cur != null) {
                cur.time -= 1;
                if (cur.time == 0) {
                    ret[cur.index] = sec;
                    done += 1;
                    cur = null;
                }
            } 
            
            if (cur == null) {
                if (!pq.isEmpty()) {
                    cur = pq.poll();
                } else {
                    if (done == N) {
                        break;
                    }
                }
            }
        }
        
        return ret;
    }
    
    public int solution(int[][] jobs) {
        
        N = jobs.length;
        
        HashMap<Integer, ArrayList<Node>> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            
            if (!map.containsKey(jobs[i][0])) {
                map.put(jobs[i][0], new ArrayList<>());
            }
            
            Node nNode = new Node(i, jobs[i][0], jobs[i][1]);
            map.get(jobs[i][0]).add(nNode);
        }
        
        int[] ret = work(map);
        
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += ret[i] - jobs[i][0];
        }
        
        int answer = sum / N;
        return answer;
    }
}

class Node implements Comparable<Node>{
    int index;
    int request;
    int time;
    
    public Node(int index, int request, int time) {
        this.index = index;
        this.request = request;
        this.time = time;
    }
    
    @Override
    public int compareTo(Node o) {
        if (this.time == o.time) {
            if (this.request == o.request) {
                return this.index - o.index;
            }
            return this.request - o.request;
        }
        return this.time - o.time;
    }
}
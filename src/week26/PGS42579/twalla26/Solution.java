import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        
        int N = genres.length;
        
        HashMap<String, Integer> totalCntMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String genre = genres[i];
            totalCntMap.put(genre, totalCntMap.getOrDefault(genre, 0) + plays[i]);
        }
        
        PriorityQueue<Genre> gpq = new PriorityQueue<>((o1, o2) -> {
            return o2.totalCnt - o1.totalCnt;
        });
        
        for (String key : totalCntMap.keySet()) {
            gpq.add(new Genre(key, totalCntMap.get(key)));
        }
        
        HashMap<String, ArrayList<Song>> songMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            if (!songMap.containsKey(genres[i])) { 
                ArrayList<Song> songList = new ArrayList<>();
                songMap.put(genres[i], songList);   
            }
            songMap.get(genres[i]).add(new Song(i, plays[i]));
        }
        
        ArrayList<Integer> result = new ArrayList<>();
        
        while (!gpq.isEmpty()) {
            
            String genre = gpq.poll().name;
            
            ArrayList<Song> songList = songMap.get(genre);
            
            if (songList.size() == 1) {
                result.add(songList.get(0).index);
                continue;
            } 
            
            songList.sort((o1, o2) -> {
                if (o1.cnt == o2.cnt) {
                    return o1.index - o2.index;
                }
                return o2.cnt - o1.cnt;
            });
            
            result.add(songList.get(0).index);
            result.add(songList.get(1).index);
        }
  
        int[] answer = new int[result.size()];
        
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        
        return answer;
    }
}

class Song {
    int index;
    int cnt;
    
    public Song(int index, int cnt) {
        this.index = index;
        this.cnt = cnt;
    }
}

class Genre {
    String name;
    int totalCnt;
    
    public Genre(String name, int totalCnt) {
        this.name = name;
        this.totalCnt = totalCnt;
    }
}
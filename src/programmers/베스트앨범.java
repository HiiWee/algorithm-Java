package programmers;

import java.util.*;

class 베스트앨범 {

    static class Song implements Comparable<Song> {
        int play, number;

        public Song(int play, int number) {
            this.play = play;
            this.number = number;
        }

        @Override
        public int compareTo(Song s) {
            if (play == s.play) {
                return number - s.number;
            }
            return s.play - play;
        }

        @Override
        public String toString() {
            return "number=" + number + ", play=" + play;
        }
    }

    int n;
    Map<String, Integer> genreCount = new HashMap<>();
    Map<String, List<Song>> songs = new HashMap<>();

    public Integer[] solution(String[] genres, int[] plays) {
        this.n = genres.length;

        // 장르마다 전체 재생수 카운팅, 장르마다 각 노래의 재생수 저장
        for (int i = 0; i < n; i++) {
            genreCount.put(genres[i], genreCount.getOrDefault(genres[i], 0) + plays[i]);
            if (songs.containsKey(genres[i])) {
                songs.get(genres[i]).add(new Song(plays[i], i));
            } else {
                List<Song> song = new ArrayList<>();
                song.add(new Song(plays[i], i));
                songs.put(genres[i], song);
            }
        }

        // 1. 속한 노래가 많이 재생된 장르를 먼저 수록해야 하므로 내림차순 정렬
        List<String> keys = new ArrayList<>(genreCount.keySet());
        keys.sort((k1, k2) -> genreCount.get(k2) - genreCount.get(k1));

        // 장르별 갯수가 1개일수 있으므로 List로 크기를 동적으로 조절할 수 있게함
        List<Integer> answers = new ArrayList<>();

        int index = 0;
        for (String key : keys) {
            // 각 장르에 해당되는 노래들을 재생수 내림차순, 고유번호 오름차순으로 정렬
            songs.get(key).sort(Comparator.naturalOrder());
            List<Song> song = songs.get(key);
            // 딱 최대 2개까지만 추가할 수 있어야 함
            for (int i = 0; i < Math.min(2, song.size()); i++) {
                answers.add(song.get(i).number);
            }
        }

        return answers.toArray(new Integer[0]);
    }

    public void println(Object o) {
        System.out.println(o);
    }
}
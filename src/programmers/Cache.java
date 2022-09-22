package programmers;

/*
    먼저 도시를 담으면서 마지막으로 사용된 도시를 구분하기 위해 다음 도시를 방문할때마다
    기존 도시의 카운트를 1씩 증가시켜준다.

    각 도시를 방문하면서 다음을 확인한다.
    1. 캐시 적중인 경우: 해당 도시의 카운트 초기화, +1의 실행 시간 증가
    2. 캐시 미스인 경우
        1-1. 캐시가 여유 있는 경우:
        1-2. 캐시가 가득 찬 경우

    생각해야할 예외 캐시의 사이즈가 0이라면?
    0이라면 모든 도시를 방문할때 +5의 실행시간을 증가시켜야 한다.
*/

import java.util.*;

public class Cache {
    public int solution(int cacheSize, String[] cities) {
        Map<String, Integer> cache = new HashMap<>();

        int time = 0;
        for (String city : cities) {
            city = city.toLowerCase();
            // 캐시 적중인 경우, 1 실행 시간 증가 + 캐시의 카운트 초기화
            if (cache.containsKey(city)) {
                time += 1;
                cache.put(city, 0);
                // 캐시에 존재하지 않고 캐시의 여유공간이 있다면 경우 일단 추가하고 5 실행시간 증가
            } else if (cache.size() < cacheSize) {
                cache.put(city, 0);
                time += 5;
                // 캐시가 가득 찬 경우, 제일 사용하지 않은 도시를 지워준다.
                // 그리고 현재 도시를 넣고, 5 실행시간 증가
            } else if (cache.size() >= cacheSize) {
                doLRU(cache, city);
                time += 5;
            }
            // 하나의 도시를 방문했으므로 방문한 도시를 제외하고 카운팅 수 1씩 증가
            increaseCount(cache, city);
        }
        return time;
    }

    public void doLRU(Map<String, Integer> cache, String city) {
        // cacheSize가 0이라면 캐시에 추가할 수 없다.
        if (cache.size() == 0) {
            return;
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<>(cache.entrySet());
        // Value 기준 내림차순 정렬
        list.sort((o1, o2) -> {
            return cache.get(o2.getKey()) - cache.get(o1.getKey());
        });
        cache.remove(list.get(0).getKey());
        cache.put(city, 0);
    }

    public void increaseCount(Map<String, Integer> cache, String currentCity) {
        for (String key : cache.keySet()) {
            if (key.equals(currentCity)) {
                continue;
            }
            cache.put(key, cache.get(key) + 1);
        }
    }

    public static void main(String[] args) {
        Cache cache = new Cache();
        int[] cacheSizes = {3, 3, 2, 5, 2, 0};
        String[][] cities = {{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"},
                {"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"},
                {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"},
                {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"},
                {"Jeju", "Pangyo", "NewYork", "newyork"},
                {"Jeju", "Pangyo", "Seoul", "NewYork", "LA"}};
        int[] runtime = {50, 21, 60, 52, 16, 25};

        for (int i = 0; i < cacheSizes.length; i++) {
            int result = cache.solution(cacheSizes[i], cities[i]);
            if (result == runtime[i]) {
                System.out.println("Test " + (i + 1) + " is passed");
            }

        }
    }
}

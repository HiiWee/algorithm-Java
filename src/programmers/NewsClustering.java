package programmers;

/*
    1. 받아온 문자열은 대소문자를 가리지 않는다 -> 통일시킴

    2. 2개의 문자열을 2개씩 잘라가며 2개의 map1, map2에 담으며 카운팅한다.
       2-1. 담는 도중에 특수문자를 포함하고 있다면 해당 문자열은 담지 않는다.
            결국 2개씩 자른 문자열들을 담은 2개의 map이 생성된다.

    3. map1을 기준으로 원소를 뽑아서 해당 원소가 map2에 존재하면
       2개의 map중 더 작은 카운트 값을 더해가며 교집합을 구한다.

    4. 합집합은 두 개의 map에서 같은 원소가 있다면 더 큰수를 선택하고
       하나의 map에만 존재하는 값도 선택해야 한다.
       따라서 3번 과정에서 map1의 원소가 map2에 존재할때 더 큰 카운트 값을 더하고
       만약 map2에 map1의 원소가 존재하지 않더라도 map1 원소의 카운트 값을 더한다.

    5. 이후 map2를 기준으로 map2의 원소가 map1에 존재하지 않을 경우에 카운트 값을 합집합의 수에 더해준다.

*/
import java.util.*;

class NewsClustering {
    public int solution(String str1, String str2) {
        int answer = 0;

        // 1. 받아온 문자열은 대소문자를 가리지 않음
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        // 2. 문자열을 검사하고 통과하면 각 map에 카운팅
        for (int i = 0; i < str1.length() - 1; i++) {
            String split = str1.substring(i, i + 2);
            if (check(split)) {
                map1.put(split, map1.getOrDefault(split, 0) + 1);
            }
        }
        for (int i = 0; i < str2.length() - 1; i++) {
            String split = str2.substring(i, i + 2);
            if (check(split)) {
                map2.put(split, map2.getOrDefault(split, 0) + 1);
            }
        }

        // 두 리스트 모두 비어있다면 1이므로 1 * 65536 반환
        if (map1.isEmpty() && map2.isEmpty()) {
            return 65536;
        }

        int intersection = 0;
        int union = 0;

        for (String key1 : map1.keySet()) {
            // map1의 원소가 map2에 존재하면 더 작은 카운팅 값을 교집합의 수에 더하고
            // 더 큰 값을 합집합의 수에 더한다.
            if (map2.containsKey(key1)) {
                intersection += Math.min(map1.get(key1), map2.get(key1));
                union += Math.max(map1.get(key1), map2.get(key1));
            } else {
                // 만약 map1의 원소가 map2에 없더라고 합집합은 카운팅해야 하므로 더해준다.
                union += map1.get(key1);
            }
        }
        // 이후 map2의 원소중 map1에 없는 원소의 카운팅 값을 합집합의 수에 더해준다.
        for (String key2 : map2.keySet()) {
            if (!map1.containsKey(key2)) {
                union += map2.get(key2);
            }
        }

        answer = (int) (65536 * ((double) intersection / (double) union));
        return answer;
    }

    public boolean check(String str) {
        char c1 = str.charAt(0);
        char c2 = str.charAt(1);
        return (c1 >= 97 && c1 <= 122) && (c2 >= 97 && c2 <= 122);
    }

    public static void main(String[] args) {
        NewsClustering newsClustering = new NewsClustering();
        String[] str1 = {"FRANCE", "handshake", "aa1+aa2", "E=M*C^2"};
        String[] str2 = {"french", "shake hands", "AAAA12", "e=m*c^2"};
        int[] answer = {16384, 65536, 43690, 65536};

        for (int i = 0; i < str1.length; i++) {
            int solution = newsClustering.solution(str1[i], str2[i]);
            if (answer[i] == solution) {
                System.out.println("Test " + (i + 1) + " is passed");
            }
        }
    }
}
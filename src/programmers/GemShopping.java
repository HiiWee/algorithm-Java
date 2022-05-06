package programmers;

import java.util.*;

/*
    0. 0번째 보석이 시작보석으로 기준을 둔다.
    1. 해쉬셋을 이용해 중복 제거한 모든 품목을 저장하고
    2. 0번부터 인덱스를 돌며 새로운 해쉬셋을 만들어 저장하고 기존의 해쉬셋과 길이를 비교
    3. 길이가 충족되면
*/
class GemShopping_1 {
    public static int[] solution(String[] gems) {
        int[] answer = new int[2];
        Set<String> categories = new HashSet<>();
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < gems.length; i++) {
            categories.add(gems[i]);
        }

        int numOfGems = categories.size();
        int left = 0;
        int right = 0;
        int length = 99999;
        while (true) {

            // 만약 right가 끝까지 탐색하고 left값도 끝까지 증가되어 더이상의 탐색이 무의미하면 종료
            if (right == gems.length && right - left < numOfGems) {
                break;
            }
            if (map.size() < numOfGems) {
                // 초기에 보석을 전부 찾을때까지 탐색
                if (left == 0 && right < gems.length) {
                    map.put(gems[right], map.getOrDefault(gems[right], 0) + 1);
                    right++;
                // 모든 보석 찾았지만 더 짧은 경우를 찾기위해 left를 증가한 경우의 right 탐색
                } else if (left > 0 && right < gems.length) {
                    map.put(gems[right], map.getOrDefault(gems[right], 0) + 1);
                    right++;
                // left값 증가시켰을때 보석이 하나 탈락되고, right값이 이미 끝까지 탐색해 더이상 탐색할 수 없으면 종료
                } else {
                    break;
                }
            // 사이즈 동일하면 length 비교후 좌표 초기화
            } else if (map.size() == numOfGems) {
                if (length > right - left) {
                    length = right - left;
                    answer[0] = left + 1;
                    answer[1] = right;
                }
                // 만약 value인 보석의 개수가 0개면 탈락
                map.put(gems[left], map.get(gems[left]) - 1);
                if (map.get(gems[left]) == 0) {
                    map.remove(gems[left]);
                }
                left++;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        String[][] gems = {{"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"},
                {"AA", "AB", "AC", "AA", "AC"},
                {"XYZ", "XYZ", "XYZ"},
                {"ZZZ", "YYY", "NNNN", "YYY", "BBB"}};

        for (int i = 0; i < gems.length; i++) {
            int[] answer = solution(gems[i]);
            for (int j = 0; j < answer.length; j++) {
                System.out.print(answer[j] + " ");
            }
            System.out.println();

        }
    }
}

// 정석적인 방법
class GemShopping_2 {

    public static int[] solution(String[] gems) {
        int[] answer = new int[2];
        Set<String> set = new HashSet<>();
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < gems.length; i++) {
            set.add(gems[i]);
        }

        int numOfGems = set.size();
        int left = 0;
        int right = 0;
        int length = 99999;
        while (true) {
            // 3. 만약 길이가 동일한 경우, left를 증가시켜 구매 보석의 길이를 감소할 수 있는지 확인
            if (map.size() == numOfGems) {
                map.put(gems[left], map.get(gems[left]) - 1);
                // 보석의 개수가 0이면 탈락
                if (map.get(gems[left]) == 0) {
                    map.remove(gems[left]);
                }
                left++;
            // end : right값이 끝까지 탐색했다면 종료
            } else if (right == gems.length) {
                break;
            // 1. 보석을 하나씩 추가, 4. left를 줄였을때 보석이 탈락된 경우 right를 증가시킬수 있다면 더 탐색함
            } else if (right < gems.length) {
                map.put(gems[right], map.getOrDefault(gems[right], 0) + 1);
                right++;
            }
            // 2. 보석을 추가했을때 만약 map사이즈와 보석의 종류수가 동일하면 길이 비교 및 좌표 초기화
            if (map.size() == numOfGems) {
                if (length > right - left) {
                    length = right - left;
                    answer[0] = left + 1;
                    answer[1] = right;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        String[][] gems = {{"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"},
                {"AA", "AB", "AC", "AA", "AC"},
                {"XYZ", "XYZ", "XYZ"},
                {"ZZZ", "YYY", "NNNN", "YYY", "BBB"}};

        for (int i = 0; i < gems.length; i++) {
            int[] answer = solution(gems[i]);
            for (int j = 0; j < answer.length; j++) {
                System.out.print(answer[j] + " ");
            }
            System.out.println();

        }
    }

}



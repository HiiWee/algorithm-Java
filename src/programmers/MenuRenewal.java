package programmers;
/*
    1. 손님이 주문한 메뉴를 n(i)(i == orders.length)라 하고
        각 course를 r이라둔다.
    2. 나올 수 있는 조합들을 n(i) C r을 구하고 이들을 Map을 이용해 카운트한다.
    3. 각 course길이의 Max값을 구하고 max값과 같은것들은 모두 메뉴로 추가
    4. 오름차순으로 정렬후 반환
*/

import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

class MenuRenewal {
    static Map<String, Integer> map;
    static StringBuilder sb;
    static boolean[] visited;

    public static String[] solution(String[] orders, int[] course) {
        // 2. 나올 수 있는 조합들을 n(i) C r을 구하고 이들을 Map을 이용해 카운트한다.
        map = new HashMap<>();
        sb = new StringBuilder();
        for (int i = 0; i < course.length; i++) {
            for (int j = 0; j < orders.length; j++) {
                visited = new boolean[orders[j].length()];
                combination(orders[j], visited, 0, course[i]);
            }
        }
        put();
        sb.setLength(0);
        // 3. 각 course길이의 Max값을 구하고 max값과 크거나 같은것들은 모두 메뉴로 추가
        for (int i = 0; i < course.length; i++) {
            int max = 2;
            for (String key : map.keySet()) {
                if (key.length() == course[i]) {
                    if (max <= map.get(key)) {
                        max = map.get(key);
                    }
                }
            }
            for (String key : map.keySet()) {
                if (key.length() == course[i]) {
                    if (map.get(key) == max) {
                        sb.append(key).append(" ");
                    }
                }
            }

        }
        String[] answer = sb.toString().split(" ");
        Arrays.sort(answer);
        return answer;
    }

    public static void combination(String arr, boolean[] visited, int depth, int r) {
        if (r == 0) {
            appendCombination(arr, visited);
            return;
        }
        if (depth == arr.length()) {
            return;
        } else {
            visited[depth] = true;
            combination(arr, visited, depth + 1, r - 1);
            visited[depth] = false;
            combination(arr, visited, depth + 1, r);
        }
    }

    public static void appendCombination(String arr, boolean[] visited) {
        StringBuilder tempSb = new StringBuilder();
        for (int i = 0; i < arr.length(); i++) {
            if (visited[i]) {
                tempSb.append(arr.charAt(i));
            }
        }
        // 각 조합들은 순서의 영향을 받지 않지만 문자의 순서가 다르면 다른값으로 취급되므로 정렬해서 저장
        String str = tempSb.toString();
        char[] strToChar = str.toCharArray();
        Arrays.sort(strToChar);
        sb.append(strToChar);
        sb.append(" ");
    }

    public static void put() {
        String[] allCombi = sb.toString().split(" ");
        for (int i = 0; i < allCombi.length; i++) {
            map.put(allCombi[i], map.getOrDefault(allCombi[i], 0) + 1);
        }
    }

    public static void main(String[] args) {
        String[][] orders = {{"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"},
                {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"},
                {"XYZ", "XWY", "WXA"}};

        int[][] course = {{2, 3, 4}, {2, 3, 5}, {2, 3, 4}};
        String[][] results = {{"AC", "ACDE", "BCFG", "CDE"}, {"ACD", "AD", "ADE", "CD", "XYZ"}, {"WX", "XY"}};

        for (int i = 0; i < orders.length; i++) {
            String[] answer = solution(orders[i], course[i]);
            int flag = 0;
            for (int j = 0; j < answer.length; j++) {
                if (!answer[j].equals(results[i][j])) {
                    flag++;
                }
            }
            if (flag == 0) {
                System.out.println("Test " + (i + 1) + " is Passed");
            }
        }
    }

}
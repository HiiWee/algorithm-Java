package programmers;

import java.util.*;


/*
* 각 옷의 종류를 카운트함 (A : 3, B : 2, C : 1)
* 옷의 종류에 대해 1개를 고를때부터 모든 옷을 고를 수 있는 조합을 모두 생성 (A, B, C, AB, AC, BC, ABC)
* 이후 조합 하나에서 나온 옷의 종류에 해당되는 옷의 수를 각각 곱함, 모든 조합에 대해 행하면서 더해간다. (3 + 2 + 1 + 6 + 3 + 2 + 6 == 23)
* 하지만, 옷의 종류가 늘어날수록 연산의 수가 기하급수적으로 늘어남 --> TEST CASE 1 메모리초과 발생
* */
class Camouflage_1 {
    static boolean[] visited;
    static Map<String, Integer> map;
    static String[] keys;
    static List<String> combinationList;

    public static int solution(String[][] clothes) {
        int answer = 0;
        combinationList = new ArrayList<>();
        map = new HashMap<>();
        // 카테고리별 옷의 종류를 카운트
        for (int i = 0; i < clothes.length; i++) {
            map.put(clothes[i][1], map.getOrDefault(clothes[i][1], 0) + 1);
        }

        // 중복을 제거한 키에대해 조합을 생성
        visited = new boolean[map.size()];
        keys = map.keySet().toArray(new String[map.size()]);
        for (int i = 1; i <= map.size(); i++) {
            combination(0, map.size(), i);
        }

        for (String eachCombi : combinationList) {
            StringTokenizer st = new StringTokenizer(eachCombi);
            int sum = 1;
            while (st.hasMoreTokens()) {
                sum *= map.get(st.nextToken());
            }
            answer += sum;
        }
        return answer;
    }

    public static void combination(int depth, int n, int r) {
        if (r == 0) {
            append();
            return;
        }
        if (depth == n) {
            return;
        }
        visited[depth] = true;
        combination(depth + 1, n, r - 1);
        visited[depth] = false;
        combination(depth + 1, n, r);
    }

    public static void append() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < visited.length; i++) {
            if (visited[i]) {
                sb.append(keys[i]).append(" ");
            }
        }
        combinationList.add(sb.toString());
    }

    public static void main(String[] args) {
        String[][][] clothes = {{{"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}},
                {{"crowmask", "face"}, {"bluesunglasses", "face"}, {"smoky_makeup", "face"}}};
        int[] results = {5, 3};

        for (int i = 0; i < results.length; i++) {
            if (solution(clothes[i]) == results[i]) {
                System.out.println("Test " + (i + 1) + " is passed");
            }
        }
    }
}
/* 애초에 카테고리에 입지않는 경우도 옷으로 생각하고 추가
* A = {a, aa, aaa}, B = {b, bb}, C = {c} 이라면 (0은 아무것도 입지 않음으로 간주)
* A는 (0, a, aa, aaa)의 경우가 존재
* B는 (0, b, bb)의 경우 존재
* C는 (0, c)의 경우 존재
* 따라서 4 * 3 * 2 = 24에서 A, B, C가 아무것도 입지 않는 경우가 선택된 경우 1을 빼주면 됨
* 정답 : 24 - 1 = 3
 */
class Camouflage_2 {
    static Map<String, Integer> map;
    public static int solution(String[][] clothes) {
        int answer = 1;
        map = new HashMap<>();
        // 카테고리별 옷의 종류를 카운트
        for (int i = 0; i < clothes.length; i++) {
            map.put(clothes[i][1], map.getOrDefault(clothes[i][1], 0) + 1);
        }
        // 옷을 입지 않는 경우도 하나의 옷으로 생각하고 모든 경우의 수를 구한다.
        for (String key : map.keySet()) {
            answer *= (map.get(key) + 1);
        }
        return answer - 1;
    }

    public static void main(String[] args) {
        String[][][] clothes = {{{"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}},
                {{"crowmask", "face"}, {"bluesunglasses", "face"}, {"smoky_makeup", "face"}}};
        int[] results = {5, 3};

        for (int i = 0; i < results.length; i++) {
            if (solution(clothes[i]) == results[i]) {
                System.out.println("Test " + (i + 1) + " is passed");
            }
        }
    }
}
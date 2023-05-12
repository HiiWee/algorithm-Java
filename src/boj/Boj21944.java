package boj;

/*
    문제번호, 난이도, 알고리즘 종류를 가진 Problem 클래스를 생성한다.

    - recommend G x 추천 방식
    우선 각 알고리즘을 key로두고 value를 TreeSet으로 두어 분류한다.

    TreeSet을 구성하는데 정렬 조건은 문제 난이도 기준 오름차순 정렬을 하고, 동일 난이도에 대해 문제 번호기준 오름차순 정렬을한다.
    이렇게 되면 최소난이도를 구할때, 동일 난이도라면 더 낮은 번호가 출력되고, 최대 난이도를 구할때 동일 난이도라면 더 큰 번호나 나타난다.

    - recommend2 x 추천 방식
    모든 문제를 하나의 TreeSet에 담고 난이도 오름차순, 번호 오름차순으로 정렬하여 뽑는다.

    - recommend3
    floor, ceiling 메소드를 활용

    equals and hashcode를 구현하지 않아도 compareTo 메소드를 구현했으므로 동일한 값을 가진 Problem 객체라면 TreeSet에서 remove시 지워진다.(contains메소드에서 compareTo로 비교하므로)

    문제 solved를 편리하게 하기 위해 문제 번호를 키로두고 난이도와, 알고리즘 종류를 벨류로 두는 2개의 Map도 선언하여 저장한다.
    문제를 삭제해야할때 number를 이용해 난이도와 알고리즘을 찾아오고 Problem 객체를 만들어서 TreeSet에 저장되어있는 Problem 객체를 remove하면 된다.
*/

import java.io.*;
import java.util.*;

class Boj21944 {

    static class Problem implements Comparable<Problem> {
        int number, level, type;

        public Problem(int number, int level, int type) {
            this.number = number;
            this.level = level;
            this.type = type;
        }

        @Override
        public int compareTo(Problem o) {
            if (level == o.level) {
                return number - o.number;
            }
            return level - o.level;

        }
    }
    // 키: 알고리즘, 밸류: 문제
    private static Map<Integer, TreeSet<Problem>> seperateAlgos = new HashMap<>();
    // 문제번호: 알고리즘 종류
    private static Map<Integer, Integer> types = new HashMap<>();
    // 문제번호: 난이도
    private static Map<Integer, Integer> levels = new HashMap<>();
    // 모든 문제
    private static TreeSet<Problem> sortedProblems = new TreeSet<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        while (n-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int number = Integer.parseInt(st.nextToken());
            int level = Integer.parseInt(st.nextToken());
            int type = Integer.parseInt(st.nextToken());
            add(number, level, type);
        }

        StringBuilder result = new StringBuilder();
        int m = Integer.parseInt(br.readLine());
        while (m-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            switch(command) {
                case "recommend":
                    result.append(recommend(Integer.parseInt(st.nextToken()), st.nextToken())).append("\n");
                    break;
                case "recommend2":
                    result.append(recommend2(st.nextToken())).append("\n");
                    break;
                case "recommend3":
                    result.append(recommend3(st.nextToken(), Integer.parseInt(st.nextToken()))).append("\n");
                    break;
                case "add":
                    add(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                    break;
                case "solved":
                    remove(Integer.parseInt(st.nextToken()));
            }
        }

        bw.write(result.toString());
        bw.flush();
        bw.close();
    }

    public static void remove(int number) {
        int level = levels.get(number);
        int type = types.get(number);
        Problem problem = new Problem(number, level, type);
        levels.remove(number);
        types.remove(number);
        sortedProblems.remove(problem);
        TreeSet<Problem> set = seperateAlgos.get(type);
        set.remove(problem);
        if (set.isEmpty()) {
            seperateAlgos.remove(type);
        }
    }

    public static int recommend3(String symbol, int level) {
        Problem problem = new Problem(0, level, 0);
        Problem findProblem;
        if ("1".equals(symbol)) {
            findProblem = sortedProblems.ceiling(problem);
            if (findProblem == null) {
                return -1;
            }
            return findProblem.number;
        }
        findProblem = sortedProblems.floor(problem);
        if (findProblem == null) {
            return -1;
        }
        return findProblem.number;
    }

    public static void add(int number, int level, int type) {
        Problem problem = new Problem(number, level, type);
        types.put(number, type);
        levels.put(number, level);
        sortedProblems.add(problem);
        if (!seperateAlgos.containsKey(type)) {
            TreeSet<Problem> set = new TreeSet<>();
            set.add(problem);
            seperateAlgos.put(type, set);
        } else {
            seperateAlgos.get(type).add(problem);
        }
    }

    public static int recommend(int type, String symbol) {
        TreeSet<Problem> set = seperateAlgos.get(type);
        if ("1".equals(symbol)) {
            return set.last().number;
        }
        return set.first().number;
    }

    public static String recommend2(String symbol) {
        if ("1".equals(symbol)) {
            return String.valueOf(sortedProblems.last().number);
        }
        return String.valueOf(sortedProblems.first().number);
    }
}
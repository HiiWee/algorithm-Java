package boj;

/*
    문제 번호는 중복이 없다, 하지만 이전에 solved 됐던 문제가 다시 들어올 수 있다.

    recommend x
      - x가 1이라면 가장 난이도가 높은 문제를 출력한다. 동일한 난이도를 가진다면 높은 문제번호를 우선적으로 출력한다.
      - x가 -1이라면 가장 난이도가 낮은 문제를 출력한다. 동일한 난이도를 가진다면 낮은 문제번호를 우선적으로 출력한다.
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


class Boj21939 {

    static class Problem {
        int number, level;

        Problem(int number, int level) {
            this.number = number;
            this.level = level;
        }
    }

    private static Map<Integer, Problem> problems = new HashMap<>();
    private static PriorityQueue<Problem> highLevels;
    private static PriorityQueue<Problem> lowLevels;
    private static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        highLevels = new PriorityQueue<>((p1, p2) -> {
            if (p1.level == p2.level) {
                return p2.number - p1.number;
            }
            return p2.level - p1.level;
        });
        lowLevels = new PriorityQueue<>((p1, p2) -> {
            if (p1.level == p2.level) {
                return p1.number - p2.number;
            }
            return p1.level - p2.level;
        });

        int n = Integer.parseInt(br.readLine());
        while (n-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int number = Integer.parseInt(st.nextToken());
            int level = Integer.parseInt(st.nextToken());
            addProblem(number, level);
        }

        int m = Integer.parseInt(br.readLine());
        while (m-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            switch (command) {
                case "add":
                    addProblem(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                    break;
                case "recommend":
                    result.append(recommendProblem(Integer.parseInt(st.nextToken()))).append("\n");
                    break;
                case "solved":
                    solveProblem(Integer.parseInt(st.nextToken()));
                    break;
            }
        }
        bw.write(result.toString());
        bw.flush();
        bw.close();
    }

    public static void solveProblem(int number) {
        problems.remove(number);
    }

    public static int recommendProblem(int selectedLevel) {
        if (selectedLevel == 1) {
            return peekProblem(highLevels);
        }
        return peekProblem(lowLevels);
    }

    public static int peekProblem(PriorityQueue<Problem> queue) {
        int selectedNumber = -1;
        while (!queue.isEmpty()) {
            Problem problem = queue.peek();
            if (problems.containsKey(problem.number) && problems.get(problem.number).level == problem.level) {
                selectedNumber = problem.number;
                break;
            } else {
                queue.poll();
            }
        }
        return selectedNumber;
    }

    public static void addProblem(int number, int level) {
        highLevels.offer(new Problem(number, level));
        lowLevels.offer(new Problem(number, level));
        problems.put(number, new Problem(number, level));
    }
}
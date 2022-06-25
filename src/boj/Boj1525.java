package boj;

import java.util.*;
import java.io.*;

class Boj1525 {
    static Map<String, Integer> map;
    static StringBuilder sb;
    static int[] row = {-1, 0, 1, 0};
    static int[] col = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        map = new HashMap<>();
        sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                sb.append(st.nextToken());
            }
        }
        if (sb.toString().equals("123456780")) {
            bw.write(0 + "\n");
        } else {
            map.put(sb.toString(), 0);
            bw.write(BFS() + "\n");
        }
        bw.flush();
        bw.close();

    }

    public static int BFS() {
        Queue<String> que = new LinkedList<>();
        que.add(sb.toString());

        while (!que.isEmpty()) {
            String str = que.poll();
            int index = str.indexOf("0");
            int r = index / 3;
            int c = index % 3;
            for (int i = 0; i < 4; i++) {
                int nextR = r + row[i];
                int nextC = c + col[i];

                if (nextR < 0 || nextR >= 3 || nextC < 0 || nextC >= 3) {
                    continue;
                }
                // 현재 0의 위치와 다음 탐색위치를 변경함
                StringBuilder puzzle = new StringBuilder(str);
                swap(index, nextR, nextC, puzzle);

                if (puzzle.toString().equals("123456780")) {
                    return map.get(str) + 1;
                }
                // 만약 새로운 유형의 퍼즐의 형태라면 기존의 유형의 카운트수 + 1을해 새로 저장
                // HashMap을 이용하면 각 이동마다 Map의 카운트값을 독립적으로 이용할 수 있다.
                if (!map.containsKey(puzzle.toString())) {
                    que.add(puzzle.toString());
                    map.put(puzzle.toString(), map.get(str) + 1);
                }
            }
        }
        return -1;
    }

    private static void swap(int index, int nextR, int nextC, StringBuilder puzzle) {
        int swapIndex = nextR * 3 + nextC;
        char target = puzzle.charAt(swapIndex);
        puzzle.setCharAt(swapIndex, '0');
        puzzle.setCharAt(index, target);
    }
}
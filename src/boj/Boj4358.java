package boj;

import java.util.*;
import java.io.*;

/*
    BufferedReader 는 readLine메소드로 읽어온 값이 null이라면 eof 처리를 할 수 있다.

    우선 Map 자료형을 이용해서 eof를 만날때까지 모든 종을 카운팅 한다.
    별개로 전체 종의 개수를 카운팅하여 비율을 구할때 사용할 수 있도록 한다.
*/

class Boj4358 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int totalCount = 0;
        Map<String, Integer> trees = new HashMap<>();

        String tree = null;
        while ((tree = br.readLine()) != null) {
            trees.put(tree, trees.getOrDefault(tree, 0) + 1);
            totalCount++;
        }

        if (trees.isEmpty()) {
            return;
        }

        List<String> treeKeys = new ArrayList<>(trees.keySet());
        treeKeys.sort(Comparator.naturalOrder());

        for (String key : treeKeys) {
            bw.write(key + " " + String.format("%.4f\n", (double) trees.get(key) / (double) totalCount * 100));
        }
        bw.flush();
        bw.close();
    }
}
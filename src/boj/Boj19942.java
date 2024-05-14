package boj;

import java.io.*;
import java.util.*;

class Boj19942 {

    static int minCost = Integer.MAX_VALUE;
    static int n, p, f, s, v;
    static int[][] foods;
    static List<String> answers = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        p = Integer.parseInt(st.nextToken());
        f = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());
        foods = new int[n][5];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            foods[i][0] = Integer.parseInt(st.nextToken());
            foods[i][1] = Integer.parseInt(st.nextToken());
            foods[i][2] = Integer.parseInt(st.nextToken());
            foods[i][3] = Integer.parseInt(st.nextToken());
            foods[i][4] = Integer.parseInt(st.nextToken());
        }

        search(0, new ArrayList<>(), new int[5]);

        if (minCost == Integer.MAX_VALUE) {
            bw.write("-1");
        } else {
            answers.sort(Comparator.naturalOrder());
            bw.write(minCost + "\n" + answers.get(0));
        }
        bw.flush();
        bw.close();
    }

    public static void search(int index, List<Integer> numbers, int[] save) {
        if (index == n) {
            if (isPossible(save)) {
                if (minCost >= save[4]) {
                    if (minCost > save[4]) {
                        answers.clear();
                    }
                    String result = "";
                    for (int number : numbers) {
                        result += number + " ";
                    }

                    answers.add(result);
                    minCost = save[4];
                }
            }
            return;
        }

        numbers.add(index + 1);
        save[0] += foods[index][0];
        save[1] += foods[index][1];
        save[2] += foods[index][2];
        save[3] += foods[index][3];
        save[4] += foods[index][4];
        search(index + 1, numbers, save);
        numbers.remove(numbers.size() - 1);
        save[0] -= foods[index][0];
        save[1] -= foods[index][1];
        save[2] -= foods[index][2];
        save[3] -= foods[index][3];
        save[4] -= foods[index][4];
        search(index + 1, numbers, save);
    }

    public static boolean isPossible(int[] save) {
        return save[0] >= p && save[1] >= f && save[2] >= s && save[3] >= v;
    }
}

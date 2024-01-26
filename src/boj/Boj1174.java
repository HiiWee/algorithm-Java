package boj;

import java.io.*;
import java.util.*;

class Boj1174 {

    static int n;
    static final int[] numbers = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
    static List<Long> findNumbers = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        find(0, 0);

        findNumbers.sort(Comparator.naturalOrder());

        if (findNumbers.size() < n) {
            bw.write("-1");
        } else {
            bw.write(findNumbers.get(n - 1) + "");
        }
        bw.flush();
        bw.close();
    }

    public static void find(int index, long number) {
        if (!findNumbers.contains(number)) {
            findNumbers.add(number);
        }
        if (index == 10) {
            return;
        }

        find(index + 1, number * 10 + numbers[index]);
        find(index + 1, number);
    }
}
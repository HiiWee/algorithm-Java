package boj;

import java.io.*;
import java.util.*;

class Boj1038 {

    static final String limit = "9876543210";
    static final List<Long> numbers = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        combinations(0, 0);
        numbers.sort(Comparator.naturalOrder());

        if (n < numbers.size() - 1) {
            bw.write(Long.toString(numbers.get(n + 1)));
        } else {
            bw.write("-1");
        }
        bw.flush();
        bw.close();
    }

    public static void combinations(int index, long number) {
        if (index == 10) {
            numbers.add(number);
            return;
        }

        combinations(index + 1, number * 10 + limit.charAt(index) - '0');
        combinations(index + 1, number);
    }
}

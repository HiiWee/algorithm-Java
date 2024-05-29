package boj;

import java.io.*;
import java.util.*;

class Boj1469 {

    static int n;
    static int[] numbers;
    static int[] indexCheck = new int[17];
    static int[] countCheck = new int[17];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        numbers = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(numbers);
        Arrays.fill(indexCheck, -1);

        search(0, new int[n * 2]);

        bw.write("-1");
        bw.flush();
        bw.close();
    }

    public static void search(int index, int[] out) {
        if (index == 2 * n) {
            StringBuilder answer = new StringBuilder();
            for (int number : out) {
                answer.append(number).append(" ");
            }
            System.out.println(answer);
            System.exit(0);
        }

        for (int i = 0; i < n; i++) {
            if (isPossible(index, numbers[i])) {
                int preIndex = indexCheck[numbers[i]];
                indexCheck[numbers[i]] = index;
                countCheck[numbers[i]]++;
                out[index] = numbers[i];
                search(index + 1, out);
                indexCheck[numbers[i]] = preIndex;
                countCheck[numbers[i]]--;
            }
        }
    }

    public static boolean isPossible(int index, int number) {
        return indexCheck[number] == -1 || countCheck[number] == 1 && index - indexCheck[number] - 1 == number;
    }
}

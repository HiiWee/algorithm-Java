package boj;

import java.io.*;
import java.util.*;

class Boj1920 {

    static int n;
    static int[] numbers;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        numbers = new int[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numbers);

        StringBuilder answer = new StringBuilder();
        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        while(m-- > 0) {
            answer.append(searchByLowerBound(Integer.parseInt(st.nextToken()))).append("\n");
        }

        bw.write(answer.toString());
        bw.flush();
        bw.close();
    }

    public static int searchByLowerBound(int target) {
        int l = 0;
        int r = n - 1;
        while(l < r) {
            int mid = (l + r) / 2;

            if (numbers[mid] >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        if (numbers[l] == target) {
            return 1;
        }
        return 0;
    }
}
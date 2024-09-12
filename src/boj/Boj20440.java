package boj;

import java.io.*;
import java.util.*;

class Boj20440 {

    static class Pos {
        int s, e;

        public Pos(int s, int e) {
            this.s = s;
            this.e = e;
        }
    }

    static int n;
    static Set<Integer> set = new HashSet<>();
    static int[] arr;
    static Pos[] poses;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        poses = new Pos[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            poses[i] = new Pos(s, e);
            set.add(s);
            set.add(e);
        }
        arr = new int[set.size()];
        int index = 0;
        for (int number : set) {
            arr[index++] = number;
        }
        Arrays.sort(arr);

        int[] sum = new int[arr.length];

        for (Pos pos : poses) {
            int start = lowerBoundSearch(pos.s);
            int end = lowerBoundSearch(pos.e);

            for (int i = start; i < end; i++) {
                sum[i]++;
            }
        }

        int max = 0;
        int leftIdx = 0;
        int rightIdx = 0;
        for (int i = 0; i < arr.length; i++) {
            if (sum[i] > max) {
                max = sum[i];
                leftIdx = i;
                rightIdx = i;
            }
            if (sum[i] == max && rightIdx == i - 1) {
                rightIdx = i;
            }
        }

        bw.write(max + "\n");
        bw.write(arr[leftIdx] + " " + arr[rightIdx + 1]);
        bw.flush();
        bw.close();
    }

    public static int lowerBoundSearch(int number) {
        int l = 0;
        int r = arr.length;

        while (l < r) {
            int mid = (l + r) / 2;

            if (arr[mid] >= number) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return l;
    }
}


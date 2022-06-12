package boj;

import java.util.*;
import java.io.*;

// 병합정렬 Top-Down
class Boj1517_1 {
    static long[] sorted;
    static long count = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        sorted = new long[n];
        long[] arr = new long[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        divide(arr, 0, n - 1);
        bw.write(count + "\n");
        bw.flush();
        bw.close();
    }

    public static void divide(long[] arr, int left, int right) {
        if (left != right) {
            int mid = (left + right) / 2;
            divide(arr, left, mid);
            divide(arr, mid + 1, right);

            merge(arr, left, mid, right);
        }
    }

    public static void merge(long[] arr, int left, int mid, int right) {
        int l = left;
        int r = mid + 1;
        int idx = left;

        while (l <= mid && r <= right) {
            if (arr[l] <= arr[r]) {
                sorted[idx++] = arr[l++];
            } else {
                sorted[idx++] = arr[r++];
                count += (mid + 1 - l);
            }
        }

        while (r <= right) {
            sorted[idx++] = arr[r++];
        }
        while (l <= mid) {
            sorted[idx++] = arr[l++];
        }


        for (int i = left; i <= right; i++) {
            arr[i] = sorted[i];
        }
    }
}

// 병합정렬 Bottom-Up
class Boj1517_2 {
    static long[] sorted;
    static long count = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        sorted = new long[n];
        long[] arr = new long[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        mergeSort(arr, 0, arr.length - 1);

        bw.write(count + "\n");
        bw.flush();
        bw.close();
    }
    public static void mergeSort(long[] arr, int left, int right) {
        for (int size = 1; size <= right; size += size) {
            for (int l = 0; l <= right - size; l += (size * 2)) {
                int low = l;
                int mid = l + size - 1;
                int high = Math.min(l + (size * 2) - 1, right);
                merge(arr, low, mid, high);
            }
        }
    }

    public static void merge(long[] arr, int left, int mid, int right) {
        int l = left;
        int r = mid + 1;
        int idx = left;

        while (l <= mid && r <= right) {

            if (arr[l] <= arr[r]) {
                sorted[idx++] = arr[l++];
            } else {
                sorted[idx++] = arr[r++];
                count += mid + 1 - l;
            }
        }

        if (l > mid) {
            while (r <= right) {
                sorted[idx++] = arr[r++];
            }
        } else {
            while (l <= mid) {
                sorted[idx++] = arr[l++];
            }
        }

        for (int i = left; i <= right; i++) {
            arr[i] = sorted[i];
        }
    }
}
package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 카운팅을 이용해 풀이
class Boj11652_1 {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        long[] arr = new long[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(bf.readLine());
        }
        Arrays.sort(arr);

        int maxCnt = 1;
        long number = arr[0];
        int count = 1;
        for (int i = 1; i < n; i++) {
            if (arr[i] == arr[i - 1]) {
                count++;
            } else {
                count = 1;
            }
            if (maxCnt < count) {
                maxCnt = count;
                number = arr[i];
            }
        }
        System.out.println(number);
    }
}

// HashMap을 이용한 풀이
class Boj11652_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        long[] arr = new long[n];
        Map<Long, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(bf.readLine());
        }
        Arrays.sort(arr);

        for (int i = 0; i < n; i++) {
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], 1);
            } else {
                int num = map.get(arr[i]);
                num++;
                map.put(arr[i], num);
            }
        }

        int max = map.get(arr[0]);
        long key = arr[0];
        for (int i = 1; i < n; i++) {
            if (max < map.get(arr[i])) {
                max = map.get(arr[i]);
                key = arr[i];
            }
        }
        System.out.println(key);
    }
}
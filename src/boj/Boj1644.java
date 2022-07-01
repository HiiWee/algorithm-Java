package boj;

import java.util.*;
import java.io.*;

// 더미 데이터 추가를 이용해 경계값 오류 방지 풀이
class Boj1644_1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        List<Integer> list = getPrimeList(N);
        // 더미 값 추가
        list.add(0);

        int start = 0;
        int end = 0;
        int total = 0;
        int count = 0;
        // 항상 end - 1의 위치에 있는 값까지 검사하므로 end는 그것보다 하나 큰 경계값이다.
        // 따라서 list에 더미 데이터 하나를 추가하여 IndexOutOfBounds를 방지하고 탐색은 size() - 1 까지 둔다.(end는 최대 size()까지 증가됨)
        while (end <= list.size() - 1) {
            if (total >= N) {
                total -= list.get(start++);
            } else if (total < N) {
                total += list.get(end++);
            }
            if (total == N) {
                count++;
            }
        }

        bw.write(count + "\n");
        bw.flush();
        bw.close();
    }

    public static List<Integer> getPrimeList(int num) {
        List<Integer> list = new ArrayList<>();
        boolean[] arr = new boolean[num + 1];

        for (int i = 2; i <= num; i++) {
            if (arr[i]) {
                continue;
            } else {
                list.add(i);
            }
            for (int j = 2 * i; j <= num; j += i) {
                arr[j] = true;
            }
        }
        return list;
    }
}

// 내부의 else if문으로 끝 인덱스 + 1의 위치의 탐방을 애초에 막는 풀이
class Boj1644_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        List<Integer> list = getPrimeArray(N);

        int start = 0;
        int end = 0;
        int total = 0;
        int count = 0;
        while (true) {
            if (total >= N) {
                total -= list.get(start++);
            } else if (end == list.size()) {
                break;
            } else {
                total += list.get(end++);
            }
            if (total == N) {
                count++;
            }
        }

        bw.write(count + "\n");
        bw.flush();
        bw.close();
    }

    public static List<Integer> getPrimeArray(int num) {
        List<Integer> list = new ArrayList<>();
        boolean[] arr = new boolean[num + 1];

        for (int i = 2; i <= num; i++) {
            if (arr[i]) {
                continue;
            } else {
                list.add(i);
            }
            for (int j = 2 * i; j <= num; j += i) {
                arr[j] = true;
            }
        }
        return list;
    }
}
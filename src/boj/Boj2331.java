package boj;

import java.io.*;
import java.util.*;

// 반복되는 구간을 찾는 문제 : DFS의 방식을 이요하는것이 효율적이다.
class Boj2331 {
    static List<Integer> list;
    static int num;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        list = new ArrayList<>();

        int A = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        list.add(A);
        DFS(A, P);

        int count = 0;
        for (int n : list) {
            if (num == n) {
                break;
            } else {
                count++;
            }
        }
        bw.write(count + "\n");
        bw.flush();
        bw.close();
    }

    public static void DFS(int A, int P) {
        int nextA = getNextNum(A, P);
        if (!list.contains(nextA)) {
            list.add(nextA);
            DFS(nextA, P);
        } else {
            num = nextA;
        }
    }

    public static int getNextNum(int A, int P) {
        int nextNum = 0;
        while (A > 0) {
            nextNum += Math.pow(A % 10, P);
            A /= 10;
        }
        return nextNum;
    }
}
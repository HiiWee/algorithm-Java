package boj;

import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.Queue;
import java.io.*;

class Boj1697 {
    static int[] arr;
    static int N;
    static int K;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        if (N == K) {
            bw.write("0");
            bw.flush();
            bw.close();
            return;
        }
        arr = new int[100001];

        BFS(N);

        bw.write(arr[K] + "\n");
        bw.flush();
        bw.close();
    }

    public static void BFS(int n) {
        Queue<Integer> que = new LinkedList<>();
        que.add(n);

        while (!que.isEmpty()) {
            int direct = que.poll();
            int[] nextMove = getNextMove(direct);
            for (int index : nextMove) {
                if (index > 100000 || index < 0 || arr[index] != 0) {
                    continue;
                }
                que.add(index);
                arr[index] = arr[direct] + 1;
                if (index == K) {
                    return;
                }
            }
        }
    }

    public static int[] getNextMove(int direct) {
        return new int[]{direct - 1, direct + 1, direct * 2};
    }
}
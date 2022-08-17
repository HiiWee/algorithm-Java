package boj;

import java.io.*;

class Boj15721 {
    public static final int LIMIT = 10000;
    public static int A, T, S;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        A = Integer.parseInt(br.readLine());
        T = Integer.parseInt(br.readLine());
        S = Integer.parseInt(br.readLine());


        int result = getResult();

        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }

    private static int getResult() {
        int round = 0;
        int[] bbundegi = new int[2];    // 0: 뻔, 1: 데기
        while (true) {
            for (int i = 0; i < 4; i++) {
                if (i % 2 == 0) {
                    bbundegi[0]++;
                } else {
                    bbundegi[1]++;
                }

                if (bbundegi[S] == T) {
                    return (bbundegi[0] + bbundegi[1] - 1) % A;
                }
            }

            round++;
            for (int i = 0; i < 1 + round; i++) {
                bbundegi[0]++;
                if (bbundegi[S] == T) {
                    return (bbundegi[0] + bbundegi[1] - 1) % A;
                }
            }
            for (int i = 0; i < 1 + round; i++) {
                bbundegi[1]++;
                if (bbundegi[S] == T) {
                    return (bbundegi[0] + bbundegi[1] - 1) % A;
                }
            }
            if (bbundegi[0] + bbundegi[1] > LIMIT) {
                break;
            }
        }
        return -1;
    }
}
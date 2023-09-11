package boj;

import java.io.*;
import java.util.*;

// Array
class Boj15787_1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] trains = new int[n + 1][21];

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int select = Integer.parseInt(st.nextToken());

            if (command == 1) {
                int seat = Integer.parseInt(st.nextToken());
                trains[select][seat] = 1;
            } else if (command == 2) {
                int seat = Integer.parseInt(st.nextToken());
                trains[select][seat] = 0;
            } else if (command == 3) {
                moveBack(trains[select]);
            } else {
                moveForward(trains[select]);
            }
        }

        Set<String> seatPositions = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            StringBuilder seatPosition = new StringBuilder();
            for (int j = 1; j <= 20; j++) {
                seatPosition.append(trains[i][j]);
            }
            seatPositions.add(seatPosition.toString());
        }

        bw.write(seatPositions.size() + "");
        bw.flush();
        bw.close();
    }

    public static void moveBack(int[] train) {
        for (int i = 20; i >= 2; i--) {
            train[i] = train[i - 1];
        }
        train[1] = 0;
    }

    public static void moveForward(int[] train) {
        for (int i = 1; i <= 19; i++) {
            train[i] = train[i + 1];
        }
        train[20] = 0;
    }
}


// Bit Masking
class Boj15787_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] trains = new int[n + 1];

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int select = Integer.parseInt(st.nextToken());

            if (command == 1) {
                int seat = Integer.parseInt(st.nextToken()) - 1;
                trains[select] = trains[select] | (1 << seat);
            } else if (command == 2) {
                int seat = Integer.parseInt(st.nextToken()) - 1;
                trains[select] = trains[select] & ~(1 << seat);
            } else if (command == 3) {
                trains[select] = trains[select] << 1;
                trains[select] = trains[select] & (1 << 20) - 1;
            } else {
                trains[select] = trains[select] >> 1;
            }
        }

        Set<Integer> seatPositions = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            seatPositions.add(trains[i]);
        }

        bw.write(seatPositions.size() + "");
        bw.flush();
        bw.close();
    }
}
package boj;

import java.io.*;
import java.util.*;

class Boj12908 {

    static long xs, ys, xe, ye;
    static long minDistance;
    static long[][] pos = new long[6][2];
    static boolean[] visited = new boolean[3];
    static int[] out = new int[3];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        xs = Integer.parseInt(st.nextToken());
        ys = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        xe = Integer.parseInt(st.nextToken());
        ye = Integer.parseInt(st.nextToken());
        minDistance = Math.abs(xs - xe) + Math.abs(ys - ye);

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            pos[i * 2][0] = x1;
            pos[i * 2][1] = y1;
            pos[i * 2 + 1][0] = x2;
            pos[i * 2 + 1][1] = y2;
        }

        Arrays.fill(out, -1);
        search(0, out);

        bw.write(Long.toString(minDistance));
        bw.flush();
        bw.close();
    }

    public static void search(int count, int[] out) {
        if (count > 0) {
            minDistance = Math.min(getDistance(out), minDistance);
            if (count == 3) {
                return;
            }
        }

        for (int i = 0; i < 6; i += 2) {
            if (!visited[i / 2]) {
                out[count] = i;
                visited[i / 2] = true;
                search(count + 1, out);
                out[count] = i + 1;
                search(count + 1, out);
                out[count] = -1;
                visited[i / 2] = false;
            }
        }
    }

    private static long getDistance(final int[] out) {
        long distance = 0;
        long startX = xs;
        long startY = ys;

        for (int i = 0; i < 3; i++) {
            if (out[i] == -1) {
                break;
            }
            distance += Math.abs(startX - pos[out[i]][0]) + Math.abs(startY - pos[out[i]][1]);
            distance += 10;
            if (out[i] % 2 == 1) {
                startX = pos[out[i] - 1][0];
                startY = pos[out[i] - 1][1];
            } else {
                startX = pos[out[i] + 1][0];
                startY = pos[out[i] + 1][1];
            }
        }
        distance += Math.abs(startX - xe) + Math.abs(startY - ye);

        return distance;
    }
}

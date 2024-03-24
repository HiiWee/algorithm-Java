package boj;

import java.io.*;
import java.util.*;

class Boj11256 {

    static class Box implements Comparable<Box> {
        int h, w;

        public Box(int h, int w) {
            this.h = h;
            this.w = w;
        }

        public int getSize() {
            return h * w;
        }

        @Override
        public int compareTo(Box b) {
            return b.h * b.w - h * w;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());

        StringBuilder answer = new StringBuilder();
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int j = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            Box[] boxes = new Box[n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                boxes[i] = new Box(
                        Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken())
                );
            }

            Arrays.sort(boxes);
            int count = 0;
            for (int i = 0; i < n; i++) {
                count++;
                j -= boxes[i].getSize();
                if (j <= 0) {
                    break;
                }
            }
            answer.append(count).append("\n");
        }

        bw.write(answer.toString());
        bw.flush();
        bw.close();
    }
}
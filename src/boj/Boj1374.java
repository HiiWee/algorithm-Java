package boj;

import java.io.*;
import java.util.*;

class Boj1374 {

    static class Lecture implements Comparable<Lecture> {
        int number, start, end;

        public Lecture(int number, int start, int end) {
            this.number = number;
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Lecture lecture) {
            return start - lecture.start;
        }
    }

    static int n;
    static Lecture[] lectures;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        lectures = new Lecture[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            lectures[i] = new Lecture(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );
        }

        Arrays.sort(lectures, Comparator.naturalOrder());

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(0);
        for (int i = 0; i < n; i++) {
            if (pq.peek() <= lectures[i].start) {
                pq.poll();
            }
            pq.offer(lectures[i].end);
        }

        bw.write(Integer.toString(pq.size()));
        bw.flush();
        bw.close();
    }
}

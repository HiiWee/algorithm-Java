package boj;

/*
    사진은 추천횟수와, 게시된 시간을 갖습니다.
    사진을 추천하면 추천횟수가 증가합니다.

    1. 사진틀에 빈공간이 있다면 사진을 즉시 걸 수 있습니다.
        - 사진을 걸고 추천횟수가 증가함
    2. 사진틀에 빈공간이 없다면 가장 추천수가 적은 사진을 내립니다.
        - 가장 추천수가 적은 사진이 여러개라면 가장 오래 걸려있는 사진을 내립니다.

    사진을 내리게 되면 해당 사진의 추천 횟수는 0이 됩니다.
*/
import java.io.*;
import java.util.*;

class Boj1713 {

    static class Frame {
        int size;
        PriorityQueue<Picture> pq = new PriorityQueue<>((p1, p2) -> {
            if (p1.count == p2.count) {
                return p1.time - p2.time;
            }
            return p1.count - p2.count;
        });

        public Frame(int size) {
            this.size = size;
        }

        public void recommend(Picture picture, int time) {
            if (pq.contains(picture)) {
                pq.remove(picture);
                picture.count++;
                pq.offer(picture);
                return;
            }
            if (pq.size() >= size) {
                Picture removePicture = pq.poll();
                removePicture.reset();
            }
            picture.recommend(time);
            pq.offer(picture);
        }
    }

    static class Picture {
        int number, count, time;
        boolean isRecommended;

        public Picture(final int number, int count, int time) {
            this.number = number;
            this.count = count;
            this.time = time;
        }

        public void reset() {
            count = 0;
            time = 0;
            isRecommended = false;
        }

        public void increaseTime() {
            time++;
        }

        public void recommend(final int time) {
            isRecommended = true;
            this.time = time;
            count++;
        }
    }

    static final Picture[] pictures = new Picture[101];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= 100; i++) {
            pictures[i] = new Picture(i,0, 0);
        }
        Frame frame = new Frame(n);
        for (int i = 0; i < m; i++) {
            int number = Integer.parseInt(st.nextToken());
            frame.recommend(pictures[number], i);

        }
        StringBuilder result = new StringBuilder();
        List<Integer> results = new ArrayList<>();
        while(!frame.pq.isEmpty()) {
            results.add(frame.pq.poll().number);
        }
        results.sort(Comparator.naturalOrder());
        for (int number : results) {
            result.append(number).append(" ");
        }

        bw.write(result.toString());
        bw.flush();
        bw.close();
    }
}
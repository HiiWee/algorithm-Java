package boj;

/*
    1. 받아온 좌표를 입력받고 x 좌표 기준으로 정렬한다.
    2. 이후 중간값을 구하고 좌, 우로 계속 분할한다.(재귀)
        이때 3이하의 값을 가진다면(3을 나누면 2, 1이고 1은 버려지는걸 방지)
        brute force한 방법으로 거리를 구하고 최소값을 구한다.
    3. mid좌표에 걸치는 수가 있을 수 있음
    4. 따라서 좌, 우에서 구한 최소 거리중 더 작은 거리를 기준으로 mid에서 x좌표의 거리가
       최소 거리 기준안에 들어오는 x좌표를 구함 이때 구한 값들을 모두 담아놓고
    5. 모든 y값을 가지고 비교하면 --> 효율 낭비
       따라서 4에서 구한 최소 거리 이하에 들어오는 y값이 존재한다면 거리를 계산하고, 최소값이라면 최소거리 갱신 후
       계속 값을 계산해간다.
    6. 최종 최소값 도출
    와 빡시다.. 이게 플레..?
*/

import java.util.*;
import java.io.*;

class Boj2261 {
    static class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static Pos[] P;
    static int n;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        P = new Pos[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            P[i] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        // mid값 계산위한 x좌표 기준 정렬
        Arrays.sort(P, (o1, o2) -> {
            return o1.x - o2.x;
        });

        bw.write(getMinDistance(0, n - 1) + "\n");
        bw.flush();
        bw.close();
    }

    public static int getMinDistance(int start, int end) {
        /*
            3이하라면 무조건
        */
        if (end - start < 3) {
            return brute(start, end);
        }
        int mid = (start + end) / 2;

        // mid 기준 좌측면의 최소 거리
        int leftMin = getMinDistance(start, mid);
        // mid 기준 우측면의 최소 거리
        int rightMin = getMinDistance(mid + 1, end);
        // 좌측면, 우측면 중 더 짧은 거리
        int minDist = Math.min(leftMin, rightMin);
        // 좌측면, 우측면이 아닌 mid에 걸치는 점들의 최소거리 탐방
        minDist = middleBand(minDist, start, mid, end);
        return minDist;
    }

    public static int middleBand(int minDist, int start, int mid, int end) {
        List<Pos> list = new ArrayList<>();
        /*
            기준거리 만들기 만약 start ~ end사이에 있는 점의 x좌표와 mid의 x좌표 사이의 거리가
            minDist보다 짧다면 리스트에 저장
        */
        for (int i = start; i <= end; i++) {
            int xDist = P[i].x - P[mid].x;
            if (xDist * xDist < minDist) {
                list.add(P[i]);
            }
        }
        // y좌표 기준 오름차순(차례대로 탐방하며 계산해야하므로)
        Collections.sort(list, (o1, o2) -> {
            return o1.y - o2.y;
        });

        /*
            만약 두 점 사이의 거리가 minDist보다 짧다면 거리 계산후 최소값이라면 갱신
            그렇지 않다면 break로 다음 y값을 탐방함
            (y값은 오름차순 되어있으므로 그 다음값은 어차피 더 큰 거리를 가짐)
        */
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                Pos p1 = list.get(i);
                Pos p2 = list.get(j);
                int yDist = p1.y - p2.y;
                if(yDist * yDist < minDist) {
                    minDist = Math.min(dist(p1, p2), minDist);
                } else {
                    break;
                }
            }
        }
        return minDist;

    }

    public static int brute(int start, int end) {
        int minDist = Integer.MAX_VALUE;

        for (int i = start; i <= end; i++) {
            for (int j = i + 1; j <= end; j++) {
                minDist = Math.min(dist(P[i], P[j]), minDist);
            }
        }
        return minDist;
    }

    public static int dist(Pos p1, Pos p2) {
        // 어차피 제곱의 값이므로 굳이 루트를 씌우진 않음
        return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
    }
}
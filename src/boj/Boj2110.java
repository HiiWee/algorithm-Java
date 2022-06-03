package boj;

/*
    공유기 설치 최소간격을 정하고, 마지막으로 설치한 집의 좌표 + 거리를 이 값 이상의 좌표에 집이 존재하면
    공유기를 설치할 수 있다. 이 때 집의 수를 count한다.

    위에서 count한 값이 공유기의 개수보다 작다면, 최소거리를 좁히고
    공유기의 개수보다 큰 값이라면, 최소거리를 늘린다.

    가장 인접한 공유기의 거리가 최대가 되야 하므로, 최대로 가질 수 있는 최소 거리를 찾아야 한다.
    --> 따라서 Upper Bound 개념이 이용됨.
*/

import java.io.*;
import java.util.*;

class Boj2110 {
    static int[] home;
    static int n;
    static int c;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 집의 개수
        n = Integer.parseInt(st.nextToken());
        // 공유기 개수
        c = Integer.parseInt(st.nextToken());
        home = new int[n];
        for (int i = 0; i < n; i++) {
            home[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(home);

        // 최소 거리는 0이 될 수 없다.
        int low = 1;
        // Upper bound는 찾고자 하는 값을 초과하는 첫 번째 인덱스를 의미하기 때문에
        // Upper bound가 최대로 가질 수 있는 값은 +1이 되어야 한다.
        int high = home[home.length - 1] - home[0] + 1;
        int mid;
        while (low < high) {
            mid = (low + high) / 2;

            int homeCount = getHomeCount(mid);

            // homeCount 수가 적다 -> 공유기 사이의 거리가 멀다. -> 거리의 최대를 줄임
            // homeCount 수가 크다 -> 공유기 사이의 거리가 너무 짧다. -> 거리의 최소를 증가
            if (homeCount < c) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        bw.write((low - 1) + "\n");
        bw.flush();
        bw.close();
    }

    // 임의의 거리 기준으로 공유기를 설치할 수 있는 집의 개수를 카운트한다.
    public static int getHomeCount(int distance) {
        // 첫번째 집은 무조건 방문
        int count = 1;
        int lastLocate = home[0];

        for (int i = 1; i < home.length; i++) {
            if (home[i] - lastLocate >= distance) {
                count++;
                // 해당집을 계산했으면 마지막 집을 직전에 탐색한 집으로 갱신
                lastLocate = home[i];
            }
        }
        return count;
    }
}
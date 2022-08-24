package boj;

/*
    백트래킹으로 조합을 구하여 모든 경우를 계산하고 그 중 최소의 값을 구해보자
    for문을 이용해 NCr개를 뽑는 조합을 1 ~ N까지 반복함 --> 효율이 떨어진다.
*/
import java.util.*;
import java.io.*;

class Boj2961_1 {
    static int[][] food;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        food = new int[n][2];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            food[i][0] = Integer.parseInt(st.nextToken());
            food[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n; i++) {
            combination(new boolean[n], n, i, 0);
        }

        bw.write(min + "\n");
        bw.flush();
        bw.close();
    }

    public static void combination(boolean[] visited, int n, int r, int depth) {
        if (r == 0) {
            updateMinValue(visited);
            return;
        }

        for (int i = depth; i < n; i++) {
            visited[i] = true;
            combination(visited, n, r - 1, depth + 1);
            visited[i] = false;
        }
    }

    public static void updateMinValue(boolean[] visited) {
        int sumSour = 1;
        int sumBitter = 0;
        for (int i = 0; i < visited.length; i++) {
            if (visited[i]) {
                sumSour *= food[i][0];
                sumBitter += food[i][1];
            }
        }
        min = Math.min(min, Math.abs(sumSour - sumBitter));
    }
}

/*
    N개의 숫자에서 NC1, NC2, NC3 ... NCN의 모든 조합을 구하는 좀 더 효율적인 재귀 탐색을 이용
*/

class Boj2961_2 {
    static int n;
    static int[][] food;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        food = new int[n][2];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            food[i][0] = Integer.parseInt(st.nextToken());
            food[i][1] = Integer.parseInt(st.nextToken());
        }

        combination(0, 1, 0);

        bw.write(min + "\n");
        bw.flush();
        bw.close();
    }

    public static void combination(int index, int sour, int bitter) {
        if (index == n) {
            if (bitter == 0) {
                return;
            }
            min = Math.min(min, Math.abs(sour - bitter));
            return;
        }

        combination(index + 1, sour * food[index][0], bitter + food[index][1]);
        combination(index + 1, sour, bitter);
    }
}



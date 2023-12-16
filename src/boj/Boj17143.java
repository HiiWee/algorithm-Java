package boj;

/*
    상어가 벽을 만나서 반대로 이동하기 위해서는 상 좌 하 우 순서로 설정하고 +2 % 4로 방향 조정
*/
import java.io.*;
import java.util.*;

class Boj17143 {

    static final int[] rows = {-1, 0, 1, 0};
    static final int[] cols = {0, -1, 0, 1};

    // 상 하 우 좌
    static final int[] dirIndex = {0, 2, 3, 1};

    static class Shark {
        int r, c, speed, dir, size, number;
        boolean isOut;

        Shark(int number, int r, int c, int speed, int dir, int size) {
            this.number = number;
            this.r = r;
            this.c = c;
            this.speed = speed;
            this.dir = dir;
            this.size = size;
        }
    }

    static int r, c, m;
    static int[][] map;
    static final List<Shark> sharks = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[r][c];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());
            map[r][c] = i + 1;
            sharks.add(new Shark(i + 1, r, c, s, dirIndex[d], z));
        }

        int score = 0;
        int position = -1;
        while (position < c - 1 && !sharks.isEmpty()) {
            // 낚시 하기
            position++;
            score += startFishing(position);
            // 상어 이동
            moveSharks();
        }

        bw.write(score + "");
        bw.flush();
        bw.close();
    }

    public static void moveSharks() {
        int[][] copyMap = new int[r][c];
        for (Shark shark : sharks) {
            if (!shark.isOut) {
                moveShark(shark, copyMap);
            }
        }
        map = copyMap;
    }

    public static void moveShark(Shark shark, int[][] copyMap) {
        int newR = shark.r;
        int newC = shark.c;
        int amount;
        if (shark.dir % 2 == 0) {
            amount = shark.speed % ((r - 1) * 2);
        } else {
            amount = shark.speed % ((c - 1) * 2);
        }
        for (int i = 0; i < amount; i++) {
            newR += rows[shark.dir];
            newC += cols[shark.dir];
            if (newR < 0 || newR >= r || newC < 0 || newC >= c) {
                shark.dir = (shark.dir + 2) % 4;
                newR += rows[shark.dir] * 2;
                newC += cols[shark.dir] * 2;
            }
        }
        // 기존 상어보다 현재 상어의 사이즈가 더 크다면 교체 및 아웃처리
        if (copyMap[newR][newC] > 0 && sharks.get(copyMap[newR][newC] - 1).size < shark.size) {
            sharks.get(copyMap[newR][newC] - 1).isOut = true;
            copyMap[newR][newC] = shark.number;
            // 빈칸이라면 현재 상어 위치시킴
        } else if (copyMap[newR][newC] == 0) {
            copyMap[newR][newC] = shark.number;
            // 기존 상어가 현재 상어보다 더 작다면 현재 상어 아웃처리
        } else {
            shark.isOut = true;
        }
        shark.r = newR;
        shark.c = newC;
    }

    public static int startFishing(int col) {
        int score = 0;
        for (int i = 0; i < r; i++) {
            // 가장 가까이 있는 상어를 발견하면
            if (map[i][col] > 0 && !sharks.get(map[i][col] - 1).isOut) {
                Shark shark = sharks.get(map[i][col] - 1);
                // 사이즈를 구하고
                score = shark.size;
                // out처리
                shark.isOut = true;
                // 상어를 맵에서 삭제함
                map[i][col] = 0;
                break;
            }
        }
        return score;
    }
}
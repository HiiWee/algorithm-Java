package boj;

import java.io.*;
import java.util.*;

class Boj12100 {

    // 상우하좌
    static final int[] rows = {-1, 0, 1, 0};
    static final int[] cols = {0, 1, 0, -1};

    static int[] OUT;
    static int n;
    static int[][] map;
    static int max;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        permutations(new int[5], 0);

        bw.write(Integer.toString(max));
        bw.flush();
        bw.close();
    }


    public static void permutations(int[] out, int count) {
        if (count == 5) {
            OUT = out;
            int[][] copyMap = copyMap(map);
            moveBlock(out, copyMap);
            max = Math.max(max, findMaxValue(copyMap));
            return;
        }

        for (int i = 0; i < 4; i++) {
            out[count] = i;
            permutations(out, count + 1);
        }
    }

    public static int[][] copyMap(int[][] map) {
        int[][] copyMap = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                copyMap[i][j] = map[i][j];
            }
        }
        return copyMap;
    }

    public static int findMaxValue(int[][] map) {
        int value = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                value = Math.max(value, map[i][j]);
            }
        }
        return value;
    }

    public static void moveBlock(int[] directions, int[][] map) {
        for (int direction : directions) {
            if (direction == 0) {
                up(map);
            } else if (direction == 1) {
                right(map);
            } else if (direction == 2) {
                down(map);
            } else {
                left(map);
            }
        }
    }

    public static void up(int[][] map) {
        boolean[][] isDone = new boolean[n][n];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 0) {
                    continue;
                }
                // 현재 칸이 숫자라면 위로 올라가야하므로
                int row = getRow(i, j, 0, map);
                // 위로 갔을때 만나는 첫번째 숫자와 동일하다면 합치고, 그렇지 않다면 한칸 아래까지 옮김
                if (!isDone[row][j] && map[i][j] == map[row][j]) {
                    isDone[row][j] = true;
                    map[row][j] *= 2;
                    map[i][j] = 0;
                } else if (map[row][j] == 0) {
                    // 그냥 0이라면 숫자만 그 자리에 올려줌
                    map[row][j] = map[i][j];
                    map[i][j] = 0;
                } else {
                    map[row + 1][j] = map[i][j];
                    if (row + 1 != i) {
                        map[i][j] = 0;
                    }
                }
            }
        }
    }

    public static void right(int[][] map) {
        boolean[][] isDone = new boolean[n][n];
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                if (map[j][i] == 0) {
                    continue;
                }
                // 현재 칸이 숫자라면 우측으로 가야하므로 열을 찾음
                int col = getColumn(j, i, 1, map);
                // 우측으로 갔을때 만나는 첫번째 숫자와 동일하다면 합치고, 그렇지 않다면 한칸 왼쪽까지 옮김
                if (!isDone[j][col] && map[j][i] == map[j][col]) {
                    isDone[j][col] = true;
                    map[j][col] *= 2;
                    map[j][i] = 0;
                } else if (map[j][col] == 0) {
                    map[j][col] = map[j][i];
                    map[j][i] = 0;
                } else {
                    map[j][col - 1] = map[j][i];
                    if (col - 1 != i) {
                        map[j][i] = 0;
                    }
                }
            }
        }
    }

    public static void down(int[][] map) {
        boolean[][] isDone = new boolean[n][n];
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 0) {
                    continue;
                }
                // 현재 칸이 숫자라면 아래로 내려가야함
                int row = getRow(i, j, 2, map);
                // 아래로 갔을때 만나는 첫번째 숫자와 동일하다면 합치고, 그렇지 않다면 한칸 위까지 옮김
                if (!isDone[row][j] && map[i][j] == map[row][j]) {
                    isDone[row][j] = true;
                    map[row][j] *= 2;
                    map[i][j] = 0;
                } else if (map[row][j] == 0) {
                    map[row][j] = map[i][j];
                    map[i][j] = 0;
                } else {
                    map[row - 1][j] = map[i][j];
                    if (row - 1 != i) {
                        map[i][j] = 0;
                    }
                }
            }
        }
    }

    public static void left(int[][] map) {
        boolean[][] isDone = new boolean[n][n];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[j][i] == 0) {
                    continue;
                }
                // 현재 칸이 숫자라면 왼쪽으로 가야하므로 0이아닌 첫번째 열을 찾음
                int col = getColumn(j, i, 3, map);
                // 왼쪽으로 갔을때 만나는 첫번째 숫자와 동일하다면 합치고, 그렇지 않다면 한칸 오른쪽까지 옮김
                if (!isDone[j][col] && map[j][i] == map[j][col]) {
                    isDone[j][col] = true;
                    map[j][col] *= 2;
                    map[j][i] = 0;
                } else if (map[j][col] == 0) {
                    map[j][col] = map[j][i];
                    map[j][i] = 0;
                } else {
                    map[j][col + 1] = map[j][i];
                    if (col + 1 != i) {
                        map[j][i] = 0;
                    }
                }
            }
        }
    }

    public static int getColumn(int r, int c, int dir, int[][] map) {
        int findC;
        while (true) {
            r += rows[dir];
            c += cols[dir];

            if (c < 0) {
                findC = 0;
                break;
            } else if (c >= n) {
                findC = n - 1;
                break;
            }
            if (map[r][c] != 0) {
                findC = c;
                break;
            }
        }

        return findC;
    }

    public static int getRow(int r, int c, int dir, int[][] map) {
        int findR;
        while (true) {
            r += rows[dir];
            c += cols[dir];
            if (r < 0) {

                findR = 0;
                break;
            } else if (r >= n) {
                findR = n - 1;
                break;
            }
            if (map[r][c] != 0) {
                findR = r;
                break;
            }
        }
        return findR;
    }
}
package programmers;

import java.util.*;

class CheckingDistance {
    static class Pos {
        int r;
        int c;
        int dis;

        public Pos(int r, int c, int dis) {
            this.r = r;
            this.c = c;
            this.dis = dis;
        }
    }

    public static int[] solution(String[][] places) {
        int[] answer = new int[5];
        for (int i = 0; i < 5; i++) {
            if (bfs(places[i])) {
                answer[i] = 1;
            } else {
                answer[i] = 0;
            }
        }
        return answer;
    }

    public static boolean bfs(String[] places) {
        Queue<Pos> que = new LinkedList<>();
        int[] dr = {1, 0, -1, 0, -1, 1, 1, -1};
        int[] dc = {0, 1, 0, -1, 1, 1, -1, -1};
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                char sit = places[i].charAt(j);
                if (sit == 'P') {
                    que.add(new Pos(i, j, 0));
                }
            }
        }
        while (!que.isEmpty()) {
            Pos cur = que.poll();
            for (int i = 0; i < dr.length; i++) {
                int nextR = cur.r + dr[i];
                int nextC = cur.c + dc[i];

                if (nextR < 0 || nextC < 0 || nextC > 4 || nextR > 4) {
                    continue;
                }
                if (i < 4 && places[nextR].charAt(nextC) == 'P') {
                    return false;
                } else if (i < 4 && places[nextR].charAt(nextC) == 'O') {
                    // 상 우 하 좌 순서로 탐방
                    if (i == 0) {
                        if (nextC - 1 > -1 && places[nextR].charAt(nextC - 1) == 'P') {
                            return false;
                        } else if (nextC + 1 < 5 && places[nextR].charAt(nextC + 1) == 'P') {
                            return false;
                        } else if (nextR + 1 < 5 && places[nextR + 1].charAt(nextC) == 'P') {
                            return false;
                        }
                    } else if (i == 2) {
                        if (nextC - 1 > -1 && places[nextR].charAt(nextC - 1) == 'P') {
                            return false;
                        } else if (nextC + 1 < 5 && places[nextR].charAt(nextC + 1) == 'P') {
                            return false;
                        } else if (nextR - 1 > -1 && places[nextR - 1].charAt(nextC) == 'P') {
                            return false;
                        }
                    } else if (i == 1) {
                        if (nextR - 1 > -1 && places[nextR - 1].charAt(nextC) == 'P') {
                            return false;
                        } else if (nextR + 1 < 5 && places[nextR + 1].charAt(nextC) == 'P') {
                            return false;
                        } else if (nextC + 1 < 5 && places[nextR].charAt(nextC + 1) == 'P') {
                            return false;
                        }
                    } else {
                        if (nextR - 1 > -1 && places[nextR - 1].charAt(nextC) == 'P') {
                            return false;
                        } else if (nextR + 1 < 5 && places[nextR + 1].charAt(nextC + 1) == 'P') {
                            return false;
                        } else if (nextC - 1 > -1 && places[nextR].charAt(nextC - 1) == 'P') {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[][] places = {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
                {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};
        int[] results = {1, 0, 1, 1, 1};
        int[] answer = solution(places);
        for (int i = 0; i < answer.length; i++) {
            if (results[i] == answer[i]) {
                System.out.println("Test Pass");
            }
        }
    }
}
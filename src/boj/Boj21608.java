package boj;

import java.io.*;
import java.util.*;

class Boj21608 {

    static class Seat {
        int r, c, like, empty; // 행, 열, 좋아하는사람수, 빈 칸

        Seat(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static int[] row = {-1, 0, 1, 0}; // 상 우 하 좌
    private static int[] col = {0, 1, 0, -1};
    private static int[] scores = {0, 1, 10, 100, 1000};
    private static Map<Integer, List<Integer>> likedStudents = new HashMap<>();
    private static int n;
    private static int[][] map;
    private static int[] studentOrder;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        studentOrder = new int[n * n];
        map = new int[n][n];

        for (int i = 0; i < n * n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int who = Integer.parseInt(st.nextToken());
            studentOrder[i] = who;

            List<Integer> likes = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                likes.add(Integer.parseInt(st.nextToken()));
            }
            likedStudents.put(who, likes);
        }

        for (int who : studentOrder) {
            List<Seat> emptySeatInfos = getAllEmptySeats(who);
            Seat bestSeat = findBestSeat(emptySeatInfos);
            map[bestSeat.r][bestSeat.c] = who;
        }

        int totalValue = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                totalValue += scores[getLikedPersonCounts(r, c)];
            }
        }

        bw.write(totalValue + "");
        bw.flush();
        bw.close();
    }

    private static List<Seat> getAllEmptySeats(int who) {
        List<Seat> emptySeatInfos = new ArrayList<>();
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (map[r][c] != 0) {
                    continue;
                }
                Seat seat = new Seat(r, c);
                int[] nearSeatInfos = getNearSeatInfos(r, c, who);
                seat.like = nearSeatInfos[0];
                seat.empty = nearSeatInfos[1];
                emptySeatInfos.add(seat);
            }
        }
        return emptySeatInfos;
    }

    public static boolean isWrongPosition(int r, int c) {
        return r >= n || r < 0 || c >= n || c < 0;
    }

    public static int[] getNearSeatInfos(int r, int c, int who) {
        int[] infos = new int[2];

        List<Integer> likes = likedStudents.get(who);
        int likeStudentCounts = 0;
        int emptyCounts = 0;
        for (int i = 0; i < 4; i++) {
            int nextR = r + row[i];
            int nextC = c + col[i];

            if (isWrongPosition(nextR, nextC)) {
                continue;
            }
            if (map[nextR][nextC] == 0) {
                emptyCounts++;
            } else if (likes.contains(map[nextR][nextC])) {
                likeStudentCounts++;
            }
        }
        infos[0] = likeStudentCounts;
        infos[1] = emptyCounts;

        return infos;
    }

    public static Seat findBestSeat(List<Seat> emptySeatInfos) {
        emptySeatInfos.sort((s1, s2) -> {
            if (s1.like != s2.like) {
                return s2.like - s1.like;
            } else if (s1.empty != s2.empty) {
                return s2.empty - s1.empty;
            } else if (s1.r != s2.r) {
                return s1.r - s2.r;
            } else {
                return s1.c - s2.c;
            }
        });
        return emptySeatInfos.get(0);
    }

    public static int getLikedPersonCounts(int r, int c) {
        int who = map[r][c];
        List<Integer> likes = likedStudents.get(who);
        int count = 0;

        for (int i = 0; i < 4; i++) {
            int nextR = r + row[i];
            int nextC = c + col[i];
            if (isWrongPosition(nextR, nextC)) {
                continue;
            }

            if (likes.contains(map[nextR][nextC])) {
                count++;
            }
        }
        return count;
    }
}
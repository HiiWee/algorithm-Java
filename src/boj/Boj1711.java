package boj;

import java.io.*;
import java.util.*;

class Boj1711 {
    static int n;

    static List<Pos> list = new ArrayList<>();

    static class Pos {
        int x, y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.add(new Pos(x, y));
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            count += solve(i);
        }

        bw.write(count + "\n");
        bw.flush();
        bw.close();
    }

    public static int solve(int start) {
        Map<String, Integer> map = new HashMap<>();
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (i == start) {
                continue;
            }
            int x = list.get(start).x - list.get(i).x;
            int y = list.get(start).y - list.get(i).y;
            int g = gcd(x, y);
            if (g < 0) {
                g = -g;
            }
            if (g != 0) {
                x /= g;
                y /= g;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(x).append(" ").append(y);
            map.put(sb.toString(), map.getOrDefault(sb.toString(), 0) + 1);
        }

        for (String key : map.keySet()) {
            StringTokenizer st = new StringTokenizer(key);
            int nx = Integer.parseInt(st.nextToken());
            int ny = Integer.parseInt(st.nextToken());
            StringBuilder sb = new StringBuilder();
            sb.append(-ny).append(" ").append(nx);
            if (map.containsKey(sb.toString())) {
                count += map.get(key) * map.get(sb.toString());
            }
        }
        return count;
    }

    public static int gcd(int x, int y) {
        if (y == 0) {
            return x;
        }
        return gcd(y, x % y);
    }
}

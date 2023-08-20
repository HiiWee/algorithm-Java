package boj;

import java.io.*;
import java.util.*;

class Boj15961_1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] plates = new int[n];
        for (int i = 0; i < n; i++) {
            plates[i] = Integer.parseInt(br.readLine());
        }
        Map<Integer, Integer> plateCounts = new HashMap<>();
        for (int i = 0 ; i < k; i++) {
            put(plateCounts, plates[i]);
        }

        int max = 0;
        int l = 0;
        int r = k - 1;
        while (r != k - 2) {
            if (plateCounts.containsKey(c)) {
                max = Math.max(plateCounts.size(), max);
            } else {
                max = Math.max(plateCounts.size() + 1, max);
            }
            remove(plateCounts, plates[l]);
            l = (l + 1) % n;
            r = (r + 1) % n;
            put(plateCounts, plates[r]);
        }

        if (plateCounts.containsKey(c)) {
            max = Math.max(plateCounts.size(), max);
        } else {
            max = Math.max(plateCounts.size() + 1, max);
        }

        bw.write(max + "");
        bw.flush();
        bw.close();
    }

    public static void remove(Map<Integer, Integer> plateCounts, int plate) {
        int preCount = plateCounts.put(plate, plateCounts.get(plate) - 1);
        if (preCount == 1) {
            plateCounts.remove(plate);
        }
    }

    public static void put(Map<Integer, Integer> plateCounts, int plate) {
        plateCounts.put(plate, plateCounts.getOrDefault(plate, 0) + 1);
    }
}

class Boj15961_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] visited = new int[d + 1];
        int[] plates = new int[n];
        for (int i = 0; i < n; i++) {
            plates[i] = Integer.parseInt(br.readLine());
        }

        int currentCount = 0;
        for (int i = 0 ; i < k; i++) {
            if (visited[plates[i]] == 0) {
                currentCount++;
            }
            visited[plates[i]]++;
        }
        int maxCount = 0;
        for (int i = 1; i <= n; i++) {
            if (visited[c] == 0) {
                maxCount = Math.max(currentCount + 1, maxCount);
            } else {
                maxCount = Math.max(currentCount, maxCount);
            }

            visited[plates[i - 1]]--;
            if (visited[plates[i - 1]] == 0) {
                currentCount--;
            }
            if (visited[plates[(i + k - 1) % n]] == 0) {
                currentCount++;
            }
            visited[plates[(i + k - 1) % n]]++;
        }

        bw.write(maxCount + "");
        bw.flush();
        bw.close();
    }
}
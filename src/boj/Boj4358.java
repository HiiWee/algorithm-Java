package boj;

import java.util.*;
import java.io.*;

class Boj4358 {
    static Map<String, Integer> map = new HashMap<>();
    static int totalCount;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String line = "";

        while ((line = br.readLine()) != null) {
            map.put(line, map.getOrDefault(line, 0) + 1);
            totalCount++;
        }
        String[] keys = map.keySet().toArray(new String[map.keySet().size()]);
        Arrays.sort(keys);

        StringBuilder sb = new StringBuilder();
        for (String key : keys) {
            int count = map.get(key);
            double percent = getPercentage((double) count, (double) totalCount);
            sb.append(key).append(" ").append(String.format("%.4f", percent)).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    public static double getPercentage(double count, double total) {
        return Math.round(count / total * 100 * 10000) / 10000.0;
    }
}
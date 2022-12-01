package boj;

import java.io.*;
import java.util.*;

class Boj9733 {
    static final List<String> WORK = List.of("Re", "Pt", "Cc", "Ea", "Tb", "Cm", "Ex");
    static Map<String, Integer> workTotalCounts;
    static int workCount;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        workTotalCounts = new HashMap<>();

        String data;
        while ((data = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(data);
            countInputWork(st);
        }

        StringBuilder sb = new StringBuilder();
        for (String sequence : WORK) {
            int count = workTotalCounts.getOrDefault(sequence, 0);
            double ratio = calcRatio(workCount, count);
            sb.append(getResult(sequence, count, ratio));
        }
        sb.append("Total ")
                .append(workCount)
                .append(" 1.00");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void countInputWork(final StringTokenizer st) {
        String work;
        while (st.hasMoreTokens()) {
            workCount++;
            work = st.nextToken();
            if (WORK.contains(work)) {
                workTotalCounts.put(work, workTotalCounts.getOrDefault(work, 0) + 1);
            }
        }
    }

    private static String getResult(final String sequence, final int count, final double ratio) {
        StringBuilder sb = new StringBuilder();
        sb.append(sequence)
                .append(" ")
                .append(count)
                .append(" ")
                .append(String.format("%.2f", ratio))
                .append("\n");
        return sb.toString();
    }

    private static double calcRatio(int workCount, int certainCount) {
        return (double) certainCount / workCount;
    }
}
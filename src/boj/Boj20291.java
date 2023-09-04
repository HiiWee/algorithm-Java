package boj;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class Boj20291 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        Map<String, Long> extensionCount = br.lines()
                .map(line -> line.substring(line.lastIndexOf('.') + 1))
                .collect(Collectors.groupingBy(extension -> extension, Collectors.counting()));

        StringBuilder result = new StringBuilder();
        extensionCount.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> result.append(entry.getKey()).append(" ").append(entry.getValue()).append("\n"));

        bw.write(result.toString());
        bw.flush();
        bw.close();
    }
}

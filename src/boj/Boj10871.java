package boj;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        Arrays.stream(br.readLine()
                .split(" "))
                .map(Integer::parseInt)
                .filter(num -> num < x)
                .forEach(num -> System.out.print(num + " "));
    }
}

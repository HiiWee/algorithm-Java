package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Boj2571 {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(bf.readLine());
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            list.add(Integer.parseInt(bf.readLine()));
        }
        // TimSort g
        Collections.sort(list);

        for (int num : list) {
            sb.append(num).append("\n");
        }
        System.out.println(sb);
    }
}
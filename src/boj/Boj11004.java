package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Boj11004 {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = bf.readLine().split(" ");
        int n = Integer.parseInt(temp[0]);
        int k = Integer.parseInt(temp[1]);
        List<Integer> list = new ArrayList<>();
        temp = bf.readLine().split(" ");

        for (int i = 0; i < n; i++) {
            list.add(Integer.parseInt(temp[i]));
        }

        Collections.sort(list);
        System.out.println(list.get(k - 1));
    }
}
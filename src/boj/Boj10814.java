package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

class Boj10814 {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        String[][] arr = new String[n][2];
        for (int i = 0; i < n; i++) {
            String[] temp = bf.readLine().split(" ");
            arr[i][0] = temp[0];
            arr[i][1] = temp[1];
        }

        Arrays.sort(arr, (e1, e2) -> {
            return Integer.parseInt(e1[0]) - Integer.parseInt(e2[현]);
        });

        // 람다식의 원래 표
//        Arrays.sort(arr, new Comparator<String[]>() {
//            @Override
//            public int compare(String[] o1, String[] o2) {
//                return Integer.parseInt(o1[0]) - Integer.parseInt(o2[0]);
//            }
//        });

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(arr[i][0] + " " + arr[i][1]).append("\n");
        }
        System.out.print(sb.toString());
    }
}

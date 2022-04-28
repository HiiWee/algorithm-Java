package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

class Boj11650 {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            String[] temp = bf.readLine().split(" ");
            arr[i][0] = Integer.parseInt(temp[0]);
            arr[i][1] = Integer.parseInt(temp[1]);
        }

//        Arrays.sort(arr, new Comparator<int[]>() {
//            public int compare(int[] e1, int[] e2) {
//                if (e1[0] == e2[0]) {
//                    return e1[1] - e2[1];
//                } else {
//                    return e1[0] - e2[0];
//                }
//            }
//        });
        // Comparator의 람다식 변경
        Arrays.sort(arr, (e1, e2) -> {
            if (e1[0] == e2[0]) {
                return e1[1] - e2[1];
            } else {
                return e1[0] - e2[0];
            }
        });

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(arr[i][0] + " " + arr[i][1]).append("\n");
        }
        System.out.println(sb.toString());
    }
}

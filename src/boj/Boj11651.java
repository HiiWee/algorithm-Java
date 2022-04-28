package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            String[] temp = bf.readLine().split(" ");
            arr[i][0] = Integer.parseInt(temp[0]);
            arr[i][1] = Integer.parseInt(temp[1]);
        }

        Arrays.sort(arr, (e1, e2) -> {
            if (e1[1] == e2[1]) {
                return e1[0] - e2[0];
            } else {
                return e1[1] - e2[1];
            }
        });

//        Arrays.sort(arr, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                if (o1[1] == o2[1]) {
//                    return o1[0] - o2[0];
//                } else {
//                    return o1[1] - o2[1];
//                }
//            }
//        });

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(arr[i][0] + " " + arr[i][1]).append("\n");
        }
        System.out.println(sb.toString());
    }
}

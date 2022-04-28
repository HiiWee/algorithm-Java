package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Boj10825 {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        String arr[][] = new String[n][4];
        for (int i = 0; i < n; i++) {
            String[] temp = bf.readLine().split(" ");
            arr[i][0] = temp[0];
            arr[i][1] = temp[1];
            arr[i][2] = temp[2];
            arr[i][3] = temp[3];
        }
        // 이름, 국어, 영어, 수학
        Arrays.sort(arr, (e1, e2) -> {
            if (Integer.parseInt(e1[1]) != Integer.parseInt(e2[1])) {
                // 국어 점수 비교(내림차순)
                return (Integer.parseInt(e1[1]) - Integer.parseInt(e2[1])) * -1;
            } else if (Integer.parseInt(e1[2]) != Integer.parseInt(e2[2])) {
                // 영어 점수 비교(오름차순)
                return Integer.parseInt(e1[2]) - Integer.parseInt(e2[2]);
            } else if (Integer.parseInt(e1[3]) != Integer.parseInt(e2[3])) {
                // 수학 점수 비교(내림차순)
                return (Integer.parseInt(e1[3]) - Integer.parseInt(e2[3])) * -1;
            } else {
                // 이름의 아스키코드 값을 통한 비교
                return e1[0].compareTo(e2[0]);
            }
        });

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(arr[i][0]).append("\n");
        }
        System.out.println(sb.toString());
    }
}
package boj;
/*
    조건을 1, 2를 살펴보면 박스를 트럭에 실으면 반드시 받는 마을에서 내리되, 지나온 마을은 되돌아가지 않음
    즉 박스를 내리는 마을의 크기가 작을수록 결과적으로 경로가 짧기때문에 많은 박스를 담을 수 있다.

    따라서, 도착하는 지점의 오름차순으로 정렬을 하면된다.

    이후 가중치를 위한 배열을 준비하고,
    정렬한 정보들을 하나씩 꺼내어
    보내는 마을번호 ~ 받는 마을번호 - 1(받는 부분은 어차피 박스를 내리므로)까지 실어야 하는 박스를 더한다.
    위 말의 의미는 결국 해당 마을에서 트럭이 들고 옮기는 중량이므로 트럭의 한계 중량은 넘을 수 없다.

    따라서 한계중량을 넘게되지 않고 딱 맞아떨어질 수 있개만 더한다.
    각 가중치를 위한 배열에 값을 더해갈때 가중치를 계산한 변수에도 해당 값을 더해줘야 트럭 한대로
    배송할 수 있는 최대 박스 수를 구할 수 있다.
*/

import java.util.*;
import java.io.*;

class Boj8980 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(br.readLine());
        int[] town = new int[n + 1];
        int[][] arr = new int[m + 1][3];
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            arr[i][2] = Integer.parseInt(st.nextToken());
        }

        // 도착지점 기준 오름차순 정렬
        Arrays.sort(arr, (o1, o2) -> {
            if (o1[1] == o2[1]) {
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });

        int total = 0;
        for (int i = 1; i <= m; i++) {
            int box = arr[i][2];
            if (box > c) {
                box = c;
            }
            for (int j = arr[i][0]; j < arr[i][1]; j++) {
                if (town[j] == c) {
                    box = 0;
                    break;
                }
                box = Math.min(box, c - town[j]);
            }
            if (box != 0) {
                total += box;
                for (int j = arr[i][0]; j < arr[i][1]; j++) {
                    town[j] += box;
                }
            }
        }

        bw.write(total + "\n");
        bw.flush();
        bw.close();
    }
}
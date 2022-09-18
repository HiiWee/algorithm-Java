package boj;

/*
    특정 사람을 고르고 다른사람과 모두 비교하여 자신보다 덩치가 큰(키, 몸무게 둘 다 나보다 큼) 사람의 수를 카운팅하고
    해당 수가 k일때 k+1의 값을 자신의 등수로 정한다.

    이를 모든 사람에 대해 반복하면 된다.
*/
import java.io.*;
import java.util.*;

class Boj7568 {
    public static void main(String[] arsg) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][2];
        int[] results = new int[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (arr[i][0] < arr[j][0] && arr[i][1] < arr[j][1]) {
                    count++;
                }
            }
            sb.append(count + 1).append(" ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}

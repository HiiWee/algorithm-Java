package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj20116 {
    /*
        맨 위에 쌓인 상자의 무게중심을 구하고 바로 아래에 존재하는 상자의 구간에 속하는지 판단한다.
        맞다면 그 두개의 상자의 평균 무게중심을 세번쨰 상자에 구간에 속하는지 판단한다.
        이렇게 1번째 상자까지 누적하여 무게중심이 구간에 속하는지 판단이 된다면 stable한 상태이다.
    */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        int[] boxes = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            boxes[i] = Integer.parseInt(st.nextToken());
        }

        double sum = boxes[n - 1];
        int count = 1;
        for (int i = n - 2; i >= 0; i--) {
            int left = boxes[i] - l;
            int right = boxes[i] + l;
            if ((sum / count) > left && (sum / count) < right) {
                count++;
                sum += boxes[i];
            } else {
                bw.write("unstable");
                bw.flush();
                bw.close();
                return;
            }
        }
        bw.write("stable");
        bw.flush();
        bw.close();
    }
}

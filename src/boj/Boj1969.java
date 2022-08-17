package boj;

/*
    1. 각 DNA를 한줄씩 받고 한글자씩 쪼개면서 해당 자릿수와, 알파벳에 맞는 위치를 카운트한다.
    2. 카운트가 전부 끝나면 1 ~ N개의 문자열을 돌면서 가장 큰 알파벳을 선택하고 해당 알파벳의 개수를 제외한
       나머지의 수들을 Hamming Distance값으로 취급
*/

import java.util.*;
import java.io.*;

class Boj1969 {
    public static char[] dna = {'A', 'C', 'G', 'T'};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] arr = new int[m][4];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();

            for (int j = 0; j < m; j++) {
                char c = line.charAt(j);
                if (c == 'A') {
                    arr[j][0]++;
                } else if (c == 'C') {
                    arr[j][1]++;
                } else if (c == 'G') {
                    arr[j][2]++;
                } else {
                    arr[j][3]++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int hammingDistance = 0;
        for (int i = 0; i < m; i++) {
            int maxIdx = -1;
            int maxCount = -1;
            for (int j = 0; j < 4; j++) {
                if (maxCount < arr[i][j]) {
                    maxIdx = j;
                    maxCount = arr[i][j];
                }
            }
            hammingDistance += n - maxCount;
            sb.append(dna[maxIdx]);
        }
        bw.write(sb.toString() + "\n");
        bw.write(hammingDistance + "\n");
        bw.flush();
        bw.close();
    }
}

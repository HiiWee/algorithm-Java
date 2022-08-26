package boj;

/*
    3 개의 수 a, b, c중에서 가장 큰 수가 c일때 a + b > c가 성립되면 삼각관계가 됨을 알 수 있다.
    예를 들어 1, 2, 3의 경우 1 + 2 > 3이 아니므로 삼각관계가 성립되지 않음

    따라서 먼저 수열을 오름차순 정렬하여 임의의 값을 뽑았을때 a + b > c가 성립하게 되면
    a ~ c사이에 있는 수는 모두 삼각관계에 있다.
    따라서 a, b가 정렬 후 제일 작은수 2개를 고르고 c의 수는 제일 큰 수부터 비교하여 제일 긴 수열을 찾아간다.
    만약 a + b > c가 성립된다면 a ~ c사이에 있는 숫자는 어떤 수를 선택하든 삼각관계가 성립된다.
*/


import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        int maxLength = 1;

        for (int i = 0; i < n - 2; i++) {
            for (int j = n - 1; j > i + 1; j--) {
                if (arr[i] + arr[i + 1] > arr[j]) {
                    maxLength = Math.max(maxLength, j - i + 1);
                }
            }
        }
        if (n >= 2 && maxLength == 1) {
            bw.write(2 + "\n");
        } else {
            bw.write(maxLength + "\n");
        }
        bw.flush();
        bw.close();
    }
}
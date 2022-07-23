package boj;

/*
    1. 문제의 입력은 반드시 키의 오름차순으로 주어진다. 따라서 `x < y`일때
        비용은 `arr[y] - arr[x]`을 반드시 성립한다. 식을 풀어 쓴다면 아래와 같이 나타낼 수 있다.
        `arr[y] + (-arr[y - 1] + arr[y - 1]) + (-arr[y - 2] + arr[y - 2]) ... + (-arr[x + 1] + arr[x + 1]) - arr[x]`
        ex) 만약 `[1, 2, 3, 4]`가 하나의 조라면
        `4 - 1` = 4 - 3 + 3 - 2 + 2 - 1 = 4 + (-3 + 3) + (-2 + 2) - 1이 됨

    2. 따라서 각 배열의 diff를 저장한 배열을 오름차순으로 정렬하고 0 ~ n - k 까지 더하면 된다.
        그런데 왜 n - k일까?
        1 3 5 6 10의 diff 배열을 오름차순 정렬한 `diff = [1, 2, 2, 4]`를 예시로 보자
        위의 diff를 모두 더하면 10 - 1 = 9와 동일하므로 더 할 수록 낱개의 그룹들이 하나씩 인접한 그룹들과 하나씩 합쳐진다.

    3. 위의 diff배열의 앞에서부터 2개를 더해가면 1 + 2 == (6 - 5) + (3 - 1)즉, 그룹을 다음과 같이(1,3), (5,6), (10)로 보겠다는 의미이다.
        이를 다시 생각해보면, 1번 더하게 되면, n - 1개의 그룹이, 2번 더하게 되면, n - 2개의 그룹이 생성되므로
        결국 n - k번을 더하면, k개의 그룹을 구하는 것이고, 최소값부터 더하며 왔으므로 해당 값이 그룹 k로 나눴을때의 최소 비용이 된다.
*/

import java.util.*;
import java.io.*;

// Greedy 넘모 어렵다..
class Boj13164 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[] diff = new int[n - 1];
        // 각 배열의 diff 구하기
        for (int i = 0; i < n - 1; i++) {
            diff[i] = arr[i + 1] - arr[i];
        }
        Arrays.sort(diff);

        // 0 ~ n - k 까지의 diff를 더하여 최소 비용 구하기
        int total = 0;
        for (int i = 0; i < n - k; i++) {
            total += diff[i];
        }

        bw.write(total + "\n");
        bw.flush();
        bw.close();
    }
}
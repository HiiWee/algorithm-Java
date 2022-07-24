package boj;

/*
    하나의 기지국이 수신 가능한 영역은 다음과 같이 정의할 수 있다.
        집중국에 포함된 가장 우측의 센서의 위치 - 집중국에 포함된 가장 좌측 센서의 위치
    따라서 결국 해당 집중국에서의 최대 - 최소의 값이 영역의 길이가 된다.

    x, y가 하나의 집중국에 포함되고, x가 제일왼쪽, y가 제일 오른쪽의 센서일 경우
    arr[y] - arr[x]이 수신 가능 영역이다. 이는 다음과 같이 표현되기도 한다.
    arr[y] - arr[x] = arr[y] + (arr[y - 1] - arr[y - 1]) + ... (arr[x + 1] - arr[x + 1]) - arr[x]
    만약 하나의 집중국이 [1, 2, 3, 4] 센서를 포함한다면 수신 가능 영역의 길이는 다음과 같다.
    4 - 1 = 3, 이를 길게 풀면 4 - 1 = 4 - 3 + 3 - 2 + 2 - 1 = 4 + (-3 + 3) + (-2 + 2) - 1 = 3 으로
    결국 하나의 집중국에서 포함된 각 영역의 모든 diff의 합은 최대 - 최소의 값과 동일하다.

    우선 계산하기전에 각 모든 센서에 집중국 존재한다고 생각하고 이를 합치며 줄여나가보자
    b = 6, k = 2, 센서 = 1 6 9 3 6 7일때 오름차순으로 정렬하면
    1 3 6 6 7 9이고 diff는 2 3 1 0 2가 된다. 이를 다시 오름차순 정렬하면
    0 1 2 2 3이 된다. 최소의 수신 가능 영역 길이의 합을 구해야 하기때문에 낮은 값부터 더해가보자

    > 0 + 1 == 이것의 의미는 센서 [6, 6](2개이므로) 을 포함하는 집중국 한 개와 센서 [6, 7]를 포함하는 집중국 하나를 만든다는 의미다
             즉, [6, 6, 7]을 포함하는 집중국을 세운다는 의미이므로 구간의 값은 1이 된다.
             이렇게되면 집중국은 다음과같이 센서를 포함한다. [1][3][6, 6, 7][9] 집중국의 수가 4개이므로 더 줄여야 한다.
    > 0 + 1 + 2 == 기존 0 + 1은 이미 완료됐고, 2는 [1, 3]을 포함하는 집중국을 세운다 따라서
                   집중국은 다음과 같이 센서를 포함한다. [1, 3] [6, 6, 7], [9] 집중국의 수가 3개이므로 더 줄여야 한다.

    > 0 + 1 + 2 + 2 == 0 + 1 + 2는 위에서 완료했고 새로운 2는 [7, 9]를 포함하는 집중국을 세운다는 의미이다.
                       하지만 잘 보면 기존에 [6, 6, 7]이 존재하므로 [7, 9]와 합쳐진다.
                       집중국은 다음과 같이 센서를 포함한다. [1, 3], [6, 6, 7, 9] 집중국의 수가 2개이므로 k와 같고 종료

     참고로 1 + 2의 의미는 [1], [3], [6, 7, 9]으로 집중국을 세운다는 의미도 되지만, [1, 3], [6, 7], [9]도 된다.

     결국 [0 ~ n - k]의 diff 구간을 더하게 되면 최종적으로 k개의 집중국을 설치했을때
     최소의 영역값이 나온다.
*/


import java.util.*;
import java.io.*;

// 그리디
class Boj2212 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        int[] diff = new int[arr.length - 1];
        for (int i = 0; i < n - 1; i++) {
            diff[i] = arr[i + 1] - arr[i];
        }

        Arrays.sort(diff);

        int total = 0;
        for (int i = 0; i < arr.length - k; i++) {
            total += diff[i];
        }

        bw.write(total + "\n");
        bw.flush();
        bw.close();
    }
}
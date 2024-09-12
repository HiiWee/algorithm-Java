package boj;

// 좌표를 재정렬하고 이분탐색으로 실제 좌표의 순번을 출력함
import java.io.*;
import java.util.*;

class Boj18870_1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        Set<Integer> set = new HashSet<>();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            set.add(arr[i]);
        }

        int[] copyArr = new int[set.size()];
        int index = 0;
        for (int number : set) {
            copyArr[index++] = number;
        }
        Arrays.sort(copyArr);

        StringBuilder answer = new StringBuilder();
        for (int number : arr) {
            answer.append(lowerBoundSearch(number, copyArr)).append(" ");
        }

        bw.write(answer.toString());
        bw.flush();
        bw.close();
    }

    public static int lowerBoundSearch(int number, int[] arr) {
        int l = 0;
        int r = arr.length - 1;

        while (l < r) {
            int mid = (l + r) / 2;

            if (arr[mid] >= number) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return l;
    }
}


// Map을 이용한 좌표 압축
class Boj18870_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];
        int[] copyArr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            copyArr[i] = arr[i];
        }
        Arrays.sort(copyArr);
        Map<Integer, Integer> ranks = new HashMap<>();

        int rank = 0;
        for (int i = 0; i < n; i++) {
            if (!ranks.containsKey(copyArr[i])) {
                ranks.put(copyArr[i], rank++);
            }
        }

        StringBuilder answer = new StringBuilder();
        for (int number : arr) {
            answer.append(ranks.get(number)).append(" ");
        }

        bw.write(answer.toString());
        bw.flush();
        bw.close();
    }
}

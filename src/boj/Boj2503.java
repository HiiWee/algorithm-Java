package boj;

/*
    숫자는 1 ~ 9이며 3자리를 골라야 하므로 나올 수 있는 범위는 111 ~ 999가 된다.
    따라서 질문지를 2차원 배열에 저장하고
    111 ~ 999까지의 임의의 수와 질문지에 물어본 수를 비교해 스트라이크, 볼이 모든 질문에서 일치하면
    영수가 생각하고 있을 가능성이 있는 답이므로 카운트한다.
*/
import java.util.*;
import java.io.*;

class Boj2503 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][3];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            arr[i][2] = Integer.parseInt(st.nextToken());
        }

        int count = 0;
        for (int i = 111; i <= 999; i++) {
            if (!isPossible(i)) {
                continue;
            }
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                int strike = getStrikeCount(i, arr[j][0]);
                int ball = getBallCount(i, arr[j][0], strike);

                if (strike != arr[j][1] || ball != arr[j][2]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                count++;
            }
        }

        bw.write(count + "\n");
        bw.flush();
        bw.close();
    }

    public static int getStrikeCount(int num, int guess) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            if (num % 10 == guess % 10) {
                count++;
            }
            num /= 10;
            guess /= 10;
        }
        return count;
    }

    public static int getBallCount(int num, int guess, int strike) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            map.put(num % 10, 1);
            num /= 10;
        }
        int count = 0;
        for (int i = 0; i < 3; i++) {
            if (map.containsKey(guess % 10)) {
                count++;
            }
            guess /= 10;
        }

        // 일치하는 수를 전부 카운트 했으므로 strike 횟수 뺴줘야함
        return count - strike;
    }

    public static boolean isPossible(int number) {
        String num = String.valueOf(number);

        // 2개의 자릿수가 동일하면 안됨
        if (num.charAt(0) == num.charAt(1)
                || num.charAt(0) == num.charAt(2)
                || num.charAt(1) == num.charAt(2)) {
            return false;
        }

        // 0이 포함되어 있으면 안됨
        if (num.charAt(0) == '0' || num.charAt(1) == '0' || num.charAt(2) == '0') {
            return false;
        }
        return true;
    }
}

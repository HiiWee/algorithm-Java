package boj;

/**
 * @Greedy
 * 4개 또는 2개를 선택하여 채워야 하므로 홀수라면 채울 수 없음 (짝수 + 짝수는 반드시 짝수)
 * 먼저 x의 수를 카운트 해가다가 .을만나면 개수 측정 후 붙여넣기
 * .도 연속될 수 있으므로 계속 개수를 세면서 붙여넣음
 */

import java.io.*;

class Boj1343 {
    static final String A = "AAAA";
    static final String B = "BB";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();

        int count = 0;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (c == '.') {
                if (count != 0) {
                    if (!append(count, sb)) {
                        sb = new StringBuilder("-1");
                        break;
                    }
                    count = 0;
                }
                sb.append(".");
            } else {
                count++;
            }
            if (i == line.length() - 1) {
                if (!append(count, sb)) {
                    sb = new StringBuilder("-1");
                }
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static boolean append(int count, StringBuilder sb) {
        if (count % 2 == 1) {
            return false;
        }
        int countA = count / 4;
        int countB = count % 4 / 2;

        for (int i = 0; i < countA; i++) {
            sb.append(A);
        }
        for (int i = 0; i < countB; i++) {
            sb.append(B);
        }
        return true;
    }
}
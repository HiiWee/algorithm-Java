package boj;

/*
    단순하게 Map하나를 이용해 숫자: 이름, 이름: 숫자로 저장하면 된다.
*/

import java.io.*;
import java.util.*;

class Boj1620 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Map<String, String> pocketmon = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            String number = String.valueOf(i);
            String name = br.readLine();
            pocketmon.put(number, name);
            pocketmon.put(name, number);
        }

        StringBuilder result = new StringBuilder();
        while (m-- > 0) {
            result.append(pocketmon.get(br.readLine())).append("\n");
        }

        bw.write(result.toString());
        bw.flush();
        bw.close();
    }
}
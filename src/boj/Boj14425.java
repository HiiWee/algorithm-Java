package boj;

/*
    중복으로 주어지는 경우가 없으므로 Set을 이용해 저장하고 contains를 통해 비교하여 존재여부를 확인한다.
*/

import java.io.*;
import java.util.*;

class Boj14425 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Set<String> set = new HashSet<>();

        while (n-- > 0) {
            set.add(br.readLine());
        }

        int count = 0;
        while (m-- > 0) {
            if (set.contains(br.readLine())) {
                count++;
            }
        }

        bw.write(count + "");
        bw.flush();
        bw.close();
    }
}

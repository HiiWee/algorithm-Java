package boj;

/**
 * 1. 크레인의 중량, 박스의 크기를 ArrayList에 담고 내림차순으로 정렬한다.
 * 2. 이후 크레인에서 하나씩 값을 꺼내며 박스의 값과 비교해 크레인의 값이 더 크면 박스를 하나씩 지워준다.
 * 3. 만약 박스의 값이 더 큰 경우라면, 현재 크레인의 인덱스는 그대로 두고, 박스의 인덱스만 다음으로 진행해야 크레인 한 사이클간에 모든 박스를 지울 수 있다.
 * 이후 위의 2, 3이 한 번 끝날때마다 1초씩 증가한다.
 */

import java.io.*;
import java.util.*;

class Boj1092 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Integer> crane = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            crane.add(Integer.parseInt(st.nextToken()));
        }

        int m = Integer.parseInt(br.readLine());
        List<Integer> load = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            load.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(crane, Collections.reverseOrder());
        Collections.sort(load, Collections.reverseOrder());

        if (crane.get(0) < load.get(0)) {
            bw.write("-1");
            bw.flush();
            bw.close();
            return;
        }

        int count = 0;

        while (!load.isEmpty()) {
            int idx = 0;
            int tmp = 0;
            while (idx < n) {
                if (tmp == load.size()) {
                    break;
                }
                if (crane.get(idx) >= load.get(tmp)) {
                    load.remove(tmp);
                    idx++;
                } else {
                    tmp++;
                }
            }
            count++;
        }

        bw.write(count + "\n");
        bw.flush();
        bw.close();
    }
}
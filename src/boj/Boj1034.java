package boj;

/*
    불이 전부 켜진 최대 행의 개수를 찾아야 하는 문제
    특정열의 스위치를 키게되면 해당 열 전체가 토글작용을 한다.
    1. 따라서 애초에 초기에 켜져있는 전구가 동일한 형태인 행들만 서로 같아질 수 있음을 알 수 있다.
    따라서 해당 켜져있는 전구가 동일한 형태의 최대 개수를 구하면 된다.
    하지만, 위의 조건만 적용하려니 문제가 발생한다. 두번쨰로 생각할 부분은

    2. k와 최대 동일 행의 0의 개수를 고려해야 한다.
    만약 001 이라는 행이 3개가 존재해 최대 개수인데 k값이 1이라면 전구를 1개 키지 못하게 되어 문제의 조건에
    부합하지 못하게된다.

    이제 이를통해 값을 구하려고 해봤지만, 위의 2 조건 외에도 문제가 발생할 수 있는 소지가 있다.
    첫번쨰로 0의 홀수, 짝수 여부와 k의 홀수, 짝수여부가 동일해야 한다는 점이다.
    만약 001이 최대 행의 개수이고 k값이 3이라면 반드시 하나의 열은 0이되어 111로 만들 수 없게 된다.
    반대로 101이 최대 행의 개수이고, k값이 2여도 반드시 하나의 하나의 열은 0이된다.

    따라서 k와 고른 행의 0의 개수의 홀, 짝 여부는 같아야 한다.
*/

import java.io.*;
import java.util.*;

class Boj1034 {
    static Map<String, Integer> map = new HashMap<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        String[] lines = new String[n];
        for (int i = 0; i < n; i++) {
            lines[i] = br.readLine();
        }
        int k = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String line = lines[i];
            int count = 0;
            for (int j = 0; j < m; j++) {
                if (line.charAt(j) == '0') {
                    count++;
                }
            }
            if (count <= k && count % 2 == k % 2) {
                map.put(line, map.getOrDefault(line, 0) + 1);
            }
        }
        List<Integer> list = new ArrayList<>(map.values());
        Collections.sort(list);
        if (list.size() == 0) {
            bw.write("0\n");
        } else {
            bw.write(list.get(list.size() - 1) + "\n");
        }
        bw.flush();
        bw.close();
    }
}
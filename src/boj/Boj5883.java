package boj;

/*
    입력받은 숫자를 하나씩 제거하면서 다른 숫자들의 연속성을 파악한다.
    연속성을 파악하기 위해 직전에 방문했던 숫자가 무엇인지 파악하는것 중요하고
    방문한 숫자가 제거한 숫자라면 연속성에 영향을 미치게 하면 안된다.
*/
import java.io.*;
import java.util.*;

class Boj5883 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] storages = new int[n];
        Set<Integer> uniqueStorages = new HashSet<>();
        for (int i = 0; i < n; i++) {
            storages[i] = Integer.parseInt(br.readLine());
            uniqueStorages.add(storages[i]);
        }

        int maxCount = 1;
        for (int unique : uniqueStorages) {
            int count = 1;
            int preStorage = -1;
            for (int i = 0; i < n; i++) {
                if (storages[i] == unique) {
                    continue;
                }
                if (preStorage == storages[i]) {
                    count++;
                } else {
                    preStorage = storages[i];
                    maxCount = Math.max(count, maxCount);
                    count = 1;
                }
            }
            maxCount = Math.max(count, maxCount);
        }
        bw.write(maxCount + "");
        bw.flush();
        bw.close();
    }
}
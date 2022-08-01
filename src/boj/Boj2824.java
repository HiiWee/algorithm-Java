package boj;
/*
    N개의 수들과 M개의 수들을 소인수 분해함
    소인수 분해를 하고 각 A, B에 해당되는 map에 카운트를 해줌

    이후 A, B 맵에 겹치게 되는 값들을 곱하면서 최대공약수를 계산한다.
    그러나 long을 벗어날 수 있으므로 10^9자리를 넘어서면 10^9로 한번 나누어주며 계속 곱해간다.
    (혹은 BigInteger를 사용)
*/

import java.util.*;
import java.io.*;

class Boj2824 {
    static boolean[] prime = new boolean[40001];
    static List<Integer> primeList = new ArrayList<>();
    static boolean flag;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] nArr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nArr[i] = Integer.parseInt(st.nextToken());
        }
        int m = Integer.parseInt(br.readLine());
        int[] mArr = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            mArr[i] = Integer.parseInt(st.nextToken());
        }

        Map<Integer, Integer> aMap = new HashMap<>();
        Map<Integer, Integer> bMap = new HashMap<>();

        calcPrime();

        for (int num : nArr) {
            makeFactorization(num, aMap);
        }
        for (int num : mArr) {
            makeFactorization(num, bMap);
        }

        // 중복되는 키만 담기
        List<Integer> keyList = new ArrayList<>();
        for (int key : aMap.keySet()) {
            if (bMap.containsKey(key)) {
                keyList.add(key);
            }
        }
        long result = 1;
        for (int key : keyList) {
            int countA = aMap.get(key);
            int countB = bMap.get(key);
            int min = Math.min(countA, countB);
            // 어차피 9자리까지만 출력해야하므로 10^9으로
            // 값을 나눠주면서 계산하여도 10^8 ~ 10^0자리의 변화는 없다
            for (int i = 0; i < min; i++) {
                result *= key;
                if (result >= 1000000000) {
                    result %= 1000000000;
                    flag = true;
                }

            }
        }


        String line = String.valueOf(result % 1000000000);
        if (flag) {
            for (int i = 0; i < 9 - line.length(); i++) {
                bw.write("0");
            }
            bw.write(line);
        } else {
            bw.write(line);
        }
        bw.flush();
        bw.close();
    }

    public static void calcPrime() {
        for (int i = 2; i < 40001; i++) {
            if (prime[i]) {
                continue;
            }
            primeList.add(i);
            for (int j = i * 2; j < 40001; j += i) {
                prime[j] = true;
            }
        }
    }

    public static void makeFactorization(int num, Map<Integer, Integer> map) {
        for (int prime : primeList) {
            if (num % prime == 0) {
                map.put(prime, map.getOrDefault(prime, 0) + 1);
                makeFactorization(num / prime, map);
                return;
            }
        }
        // 40000 보다 큰 수이며 해당 수 자체가 소수인 경우는 자기 자신을 추가해야함
        if (num != 1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
    }
}
package boj;

import java.util.*;
import java.io.*;

/*
    양수 : 음수,0을 서로 다른 리스트로 묶어서 보관
    이후 값을 정렬하고 앞에순서부터 짝으로 꺼내어 곱하여 상쇄시킴
    하지만 양수에서 가져온 2개의 값에서 1이 하나라도 있으면 곱하는것보다 더하는것이 더 큰수를 갖게함
*/
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        List<Integer> plusList = new ArrayList<>();
        List<Integer> minusList = new ArrayList<>();
        int n = Integer.parseInt(br.readLine());
        if (n == 1) {
            bw.write(br.readLine());
            bw.flush();
            bw.close();
            return;
        }
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num > 0) {
                plusList.add(num);
            } else {
                minusList.add(num);
            }
        }
        Collections.sort(plusList, (o1, o2) -> {
            return o2 - o1;
        });
        // 절대값이 큰 순서대로 정렬하기 위해서 음수는 오름차순 정렬
        Collections.sort(minusList);

        int total = 0;
        total += getTotal(plusList);
        total += isRemain(plusList);
        total += getTotal(minusList);
        total += isRemain(minusList);

        bw.write(total + "\n");
        bw.flush();
        bw.close();
    }

    public static int isRemain(List<Integer> list) {
        if (list.size() % 2 == 1) {
            return list.get(list.size() - 1);
        }
        return 0;
    }

    public static int getTotal(List<Integer> list) {
        int total = 0;
        for (int i = 2; i <= list.size(); i += 2) {
            int num1 = list.get(i - 1);
            int num2 = list.get(i - 2);
            if (num1 != 1 && num2 != 1) {
                total += num1 * num2;
            } else {
                total += num1 + num2;
            }
        }
        return total;
    }
}
package boj;

import java.io.*;
import java.util.*;

class Boj20546 {
    private static int bnpCount;
    private static int timingCount;
    private static int downCount, upCount;
    private static int prePrice;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int bnpMoney = Integer.parseInt(br.readLine());
        int timingMoney = bnpMoney;
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] stockPrice = new int[15];
        for (int i = 1; i <= 14; i++) {
            stockPrice[i] = Integer.parseInt(st.nextToken());
        }

        bnpMoney = buyWithBnpStrategy(bnpMoney, stockPrice[1]);
        prePrice = stockPrice[1];
        for (int i = 2; i <= 14; i++) {
            bnpMoney = buyWithBnpStrategy(bnpMoney, stockPrice[i]);
            timingMoney = buyWithTimingStrategy(timingMoney, stockPrice[i]);
            prePrice = stockPrice[i];
        }

        // 1월 14일의 자산은 (현금 + 1월 14일의 주가 × 주식 수)로 계산
        int finalBnpMoney = bnpMoney + (stockPrice[14] * bnpCount);
        int finalTimingMoney = timingMoney + (stockPrice[14] * timingCount);

        if (finalBnpMoney > finalTimingMoney) {
            bw.write("BNP");
        } else if (finalBnpMoney < finalTimingMoney) {
            bw.write("TIMING");
        } else {
            bw.write("SAMESAME");
        }
        bw.flush();
        bw.close();
    }

    public static int buyWithBnpStrategy(int bnpMoney, int stockPrice) {
        if (bnpMoney >= stockPrice) {
            bnpCount += bnpMoney / stockPrice;
            return bnpMoney % stockPrice;
        }
        return bnpMoney;
    }

    public static int buyWithTimingStrategy(int timingMoney, int stockPrice) {
        countStockChanging(stockPrice);

        if (downCount >= 3) {
            if (timingMoney >= stockPrice) {
                timingCount += timingMoney / stockPrice;
                return timingMoney % stockPrice;
            }
            return timingMoney;
        }
        if (upCount >= 3) {
            int sellingStockMoney = timingMoney + (timingCount * stockPrice);
            timingCount = 0;
            return sellingStockMoney;
        }
        return timingMoney;
    }

    private static void countStockChanging(int stockPrice) {
        if (prePrice < stockPrice) {
            downCount = 0;
            upCount++;
        } else if (prePrice > stockPrice) {
            upCount = 0;
            downCount++;
        }
    }
}
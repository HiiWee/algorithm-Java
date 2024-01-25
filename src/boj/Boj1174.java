package boj;

import java.io.*;
import java.util.*;

class Boj1174 {

    static int n;
    static int findNumber = -1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());

        find(0, 0);

        bw.write(findNumber + "");
        bw.flush();
        bw.close();
    }

    public static void find(int count, int curNumber) {
        if (isDescendingNumber(curNumber)) {
            count++;
        }
        if (count == n) {
            findNumber = curNumber;
            return;
        }
        int nextNumber = getNextNumber(curNumber);
        find(count, nextNumber);
    }

    public static int getNextNumber(int number) {
        while (number % 100 != 10) {
            number++;
        }
        return number;
    }

    public static boolean isDescendingNumber(int number) {
        if (number <= 10) {
            return true;
        }

        int curNumber = -1;
        while (number > 0) {
            int sep = number % 10;
            number = number / 10;

            if (sep <= curNumber) {
                return false;
            }
            curNumber = sep;
        }
        return true;
    }
}
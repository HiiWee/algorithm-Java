package boj;

import java.util.Scanner;

class Boj1924 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int month, day;
        month = sc.nextInt();
        day = sc.nextInt();
        int[] lastDay = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String[] weekDay = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};

        for (int i = 0; i < 12; i++) {
            if (month - 1 > i) {
                day += lastDay[i];
            } else {
                break;
            }
        }
        System.out.println(weekDay[day % 7]);
    }
}

package programmers;

public class YearOf2016 {
    public static String solution(int a, int b) {
        int[] lastDays = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String[] months = {"THU", "FRI", "SAT", "SUN", "MON", "TUE", "WED"};
        int dayCnt = 0;
        for (int i = 0; i < 12; i++) {
            if (a - 1 > i) {
                dayCnt += lastDays[i];
            }
        }
        dayCnt += b;

        return months[dayCnt % 7];
    }

    public static void main(String[] args) {
        int a = 5;
        int b = 24;

        String result = "TUE";

        if (solution(a, b).equals(result)) {
            System.out.println("Pass");
        }
    }
}

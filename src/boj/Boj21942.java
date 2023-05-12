package boj;

/*
    2021년의 모든 날짜의 수를 저장한다.
    1. 입력받은 정보의 개수, 대여 기간, 벌금을 저장한다.
    2. 정보의 개수를 입력받는다.

    사용자가 부품을 빌리게 되면 기록을 해놓는다.
    이때 같은 부품이 아닌 다른 부품을 동일한 사용자가 빌릴 수 있다.

    사용자가 동일한 부품에 대해 다시 입력이 들어오면 반납의 의미이므로 빌린 날짜와 시간을 계산하고 벌금의 여부를 판단한다.

    맵에 저장되는 형태는 다음과 같다.
    key: "부품이름 사용자", value: "날짜와시간"

    동일한 key를 만난다면 반납의 의미로 간주한다.
*/
import java.io.*;
import java.util.*;

class Boj21942 {

    private static final int[] monthDate = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static Map<String, String> borrowList = new HashMap<>();
    private static Map<String, Long> fines = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int listCount = Integer.parseInt(st.nextToken());
        int rentalMinute = dayAndTimeToMinute(st.nextToken());
        int money = Integer.parseInt(st.nextToken());
        while (listCount-- > 0) {
            st = new StringTokenizer(br.readLine());
            String yearMonthDayStr = st.nextToken();
            String hourMinuteStr = st.nextToken();
            String item = st.nextToken();
            String user = st.nextToken();
            String key = item + " " + user;
            String value = yearMonthDayStr + " " + hourMinuteStr;

            if (borrowList.containsKey(key)) {
                String[] values = borrowList.get(key).split(" ");
                int startMinute = yearMonthDayToMinute(values[0], values[1]);
                int endMinute = yearMonthDayToMinute(yearMonthDayStr, hourMinuteStr);
                if (endMinute - startMinute > rentalMinute) {
                    fines.put(user, fines.getOrDefault(user, 0L) + Math.abs(rentalMinute - (endMinute - startMinute)) * (long) money);
                }
                borrowList.remove(key);
            } else {
                borrowList.put(key, value);
            }
        }
        if (fines.isEmpty()) {
            bw.write("-1");
            bw.flush();
            bw.close();
            return;
        }
        List<String> users = new ArrayList<>(fines.keySet());
        users.sort(Comparator.naturalOrder());
        for (String user : users) {
            bw.write(user + " " + fines.get(user) + "\n");
        }
        bw.flush();
        bw.close();
    }

    public static int yearMonthDayToMinute(String yearMonthDayStr, String hourMinuteStr) {
        int totalMinute = 0;
        String[] yearMonthDay = yearMonthDayStr.split("-");
        int month = Integer.parseInt(yearMonthDay[1]) - 1;
        int day = Integer.parseInt(yearMonthDay[2]) - 1;
        int monthToDay = 0;
        for (int i = 0; i <= month; i++) {
            monthToDay += monthDate[i];
        }
        totalMinute += (monthToDay + day) * 24 * 60;
        String[] hourMinute = hourMinuteStr.split(":");
        int hour = Integer.parseInt(hourMinute[0]);
        int minute = Integer.parseInt(hourMinute[1]);
        totalMinute += hour * 60 + minute;
        return totalMinute;
    }

    public static int dayAndTimeToMinute(String format) {
        String[] dayAndTime = format.split("/");
        int day = Integer.parseInt(dayAndTime[0]);
        String[] hourAndMin = dayAndTime[1].split(":");
        int hour = Integer.parseInt(hourAndMin[0]);
        int min = Integer.parseInt(hourAndMin[1]);
        return day * 24 * 60 + hour * 60 + min;
    }
}

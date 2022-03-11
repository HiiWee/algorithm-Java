package programmers;

import java.util.ArrayList;
import java.util.List;

import java.util.*;

class ParkingFeeCalculation {
    public static int[] solution(int[] fees, String[] records) {
        // key : 차량번호, value : 해당 차량번호의 입출차 기록(시간(시:분), IN or OUT)
        Map<String, List<String>> sepRecords = new HashMap<>();

        for (String record : records) {
            // records 배열 " " 기준 분리
            String[] splitRecords = record.split(" ");
            // 차량번호 저장
            String number = splitRecords[1];
            // 입출차 시간, 입출차 저장
            String other = splitRecords[0] + " " + splitRecords[2];

            // value가 null면 새로운 List만들어서 추가 그렇지 않으면 기존 List에 추가
            if (sepRecords.get(number) == null) {
                List<String> newList = new ArrayList<>();
                newList.add(other);
                sepRecords.put(number, newList);
            } else {
                List<String> existList = sepRecords.get(number);
                existList.add(other);
                sepRecords.put(number, existList);
            }
        }

        // HashMap keySet 순서대로 sorting하기
        Object[] mapKey = sepRecords.keySet().toArray();
        Arrays.sort(mapKey);

        // 반환할 정수 배열 정의
        int mapLength = mapKey.length;
        int[] answer = new int[mapLength];

        for (int i = 0; i < mapLength; i++) {
            // 총 시간(분)
            int totalMin = 0;
            // 반복할 list의 길이 저장 변수
            int listLength = sepRecords.get(mapKey[i]).size();
            // IN일 경우 시간, 분 저장 변수
            int preHour = 0;
            int preMin = 0;
            // 낮은 차량번호의 입출차 기록부터 하나씩 받아옴
            List<String> list = sepRecords.get(mapKey[i]);

            for (int j = 0; j < listLength; j++) {
                // 시간(시:분), 내역(IN, OUT) 분리
                String[] splitRecord = list.get(j).split(" ");
                // IN일 경우
                if (splitRecord[1].equals("IN")) {
                    // 시간, 분 나누고 정수로 변환
                    preHour = Integer.parseInt(splitRecord[0].split(":")[0]);
                    preMin = Integer.parseInt(splitRecord[0].split(":")[1]);

                    // 만약 IN인데 for문의 마지막 반복이라면 OUT기록이 없는것 따라서 23:59를 출차로 두고 계산
                    if (j == listLength - 1) {
                        totalMin += (23 - preHour) * 60;
                        totalMin += 59 - preMin;
                    }
                }
                // OUT일 경우
                else {
                    // 시간, 분 나누고 정수 변환
                    int hour = Integer.parseInt(splitRecord[0].split(":")[0]);
                    int min = Integer.parseInt(splitRecord[0].split(":")[1]);

                    // 자리내림이 필요한 경우(23분 - 40분)와 그렇지 않은경우의 연산 분리
                    if (min - preMin < 0) {
                        totalMin += (hour - preHour - 1) * 60;
                        totalMin += min - preMin + 60;
                    } else {
                        totalMin += (hour - preHour) * 60;
                        totalMin += min - preMin;
                    }
                }

            }
            // 최종 시간(분)이 기본 시간(분)보다 큰 경우만 계산
            if (totalMin > fees[0]) {
                answer[i] = fees[1] + (int)Math.ceil((double)(totalMin - fees[0]) / fees[2]) * fees[3];
            } else {
                // 기본 시간이 더 크므로 기본요금 적용
                answer[i] = fees[1];
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] fees1 = {180, 5000, 10, 600};
        String[] records1 = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};

        int[] answer = solution(fees1, records1);
        for (int won: answer) {
            System.out.printf("%d ", won);
        }
        System.out.println();

        int[] fees2 = {120, 0, 60, 591};
        String[] records2 = {"16:00 3961 IN","16:00 0202 IN","18:00 3961 OUT","18:00 0202 OUT","23:58 3961 IN"};
        answer = solution(fees2, records2);
        for (int won: answer) {
            System.out.printf("%d ", won);
        }
        System.out.println();

        int[] fees3 = {1, 461, 1, 10};
        String[] records3 = {"00:00 1234 IN"};
        answer = solution(fees3, records3);
        for (int won: answer) {
            System.out.printf("%d ", won);
        }
        System.out.println();



    }
}

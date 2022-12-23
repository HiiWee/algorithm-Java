package algorithm.codingtest.kakao2022blind;

/*
    - 오늘 날짜를 년 월 일로 나누어 저장
    - terms를 Map에서 key: 약관종류, value: 유효기간으로 담음
    - 이후 각 pricacies를 돌며 약관종류를 파악하고 약관종류의 달을 주어진 날짜에 더한다.
        - 더할때 달은 그대로 더하되, 현재 일에서 -1을 해준다.
        - 만약 현재일이 0이라면 이전달의 28일로 변경해주어야 함
        - today와 비교해 today보다 이전의 날이라면 파기하는 것에 추가 아니라면 다음것을 진행한다.
*/
import java.util.*;
class Solution1 {
    public Integer[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> list = new ArrayList<>();

        // 오늘 날짜를 년, 월, 일로 나누어 저장
        String[] tokens = today.split("\\.");
        int curYear = Integer.parseInt(tokens[0]);
        int curMonth = Integer.parseInt(tokens[1]);
        int curDay = Integer.parseInt(tokens[2]);

        // - terms를 Map에서 key: 약관종류, value: 유효기간으로 담음
        Map<String, Integer> map = new HashMap<>();
        for (String term : terms) {
            String[] split = term.split(" ");
            map.put(split[0], Integer.parseInt(split[1]));
        }

        // 이후 각 pricacies를 돌며 약관종류를 파악하고 약관종류의 달을 주어진 날짜에 더한다.
        int index = 1;
        for (String privacy : privacies) {
            String[] split = privacy.split(" ");
            String[] times = split[0].split("\\.");

            int year = Integer.parseInt(times[0]);
            int month = Integer.parseInt(times[1]);
            int day = Integer.parseInt(times[2]);

            int addMonth = map.get(split[1]);

            // 12달 넘을 경우 일단 년도에 더해줌
            year += addMonth / 12;
            addMonth %= 12;

            // 달을 더했을떄 12월이 넘어가면 년도를 올려줌
            month += addMonth;
            if (month > 12) {
                year++;
                month -= 12;
            }

            // day에서 하루 뺐을때 0일이면 이전달로 변경하고 28일로 설정
            day--;
            if (day == 0) {
                day = 28;
                month--;
            }

            // 년도 비교
            if (curYear > year) {
                list.add(index++);
                continue;
            } else if (curYear < year) {
                index++;
                continue;
            }
            // 달 비교
            if (curMonth > month) {
                list.add(index++);
                continue;
            } else if (curMonth < month) {
                index++;
                continue;
            }

            // 날 비교
            if (curDay > day) {
                list.add(index++);
                continue;
            } else if (curDay < day) {
                index++;
                continue;
            }

            index++;
        }



        return list.toArray(new Integer[list.size()]);
    }
}

package programmers;

/*
    1. 각 작업일이 걸리는 일수를 구하고
    2. i번째 남은일수보다 i+1이 더 오래걸리면 pass
       i + 1이 더 금방끝난다면 pivot을 i번째 남은일수로 정함,
       이후 count를 하며 만약 pivot보다 큰 수가 나온다면 List에 저장
    3. 이후 배열로 변환하여 리턴
*/

import java.util.List;
import java.util.ArrayList;

public class ProgressDevelopment {
    static List<Integer> list;

    public static Integer[] solution(int[] progresses, int[] speeds) {
        Integer[] answer;
        list = new ArrayList<>();
        setRemainDay(progresses, speeds);
        answer = getDoneProgress();
        return answer;
    }

    public static void setRemainDay(int[] progresses, int[] speeds) {
        for (int i = 0; i < speeds.length; i++) {
            int leftProgress = 100 - progresses[i];
            if (leftProgress % speeds[i] != 0) {
                list.add(leftProgress / speeds[i] + 1);
            } else {
                list.add(leftProgress / speeds[i]);
            }
        }
    }

    public static Integer[] getDoneProgress() {
        List<Integer> temp = new ArrayList<>();
        int count = 1;
        int pivotDay = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            int curDay = list.get(i - 1);
            int afterDay = list.get(i);
            if (curDay < afterDay) {
                if (pivotDay < afterDay) {
                    temp.add(count);
                    count = 1;
                    pivotDay = afterDay;
                } else {
                    count++;
                }
            } else {
                count++;
            }
            if (i == list.size() - 1) {
                temp.add(count);
            }
        }
        return temp.toArray(new Integer[temp.size()]);
    }

    public static void main(String[] args) {
        int[][] progresses = {{93, 30, 55}, {95, 90, 99, 99, 80, 99}};
        int[][] speeds = {{1, 30, 5}, {1, 1, 1, 1, 1, 1}};
        int[][] results = {{2, 1}, {1, 3, 2}};

        for (int i = 0; i < progresses.length; i++) {
            Integer[] answer = solution(progresses[i], speeds[i]);
            int count = 0;
            for (int j = 0; j < results[i].length; j++) {
                if (results[i][j] == answer[j]) {
                    count++;
                }
                if (count == results[i].length) {
                    System.out.println("Test " + (i + 1) + " is Passed");
                }
            }
        }
    }
}
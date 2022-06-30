package programmers;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class OpenChat {
    /*
    HashMap을 이용 key(UID) : value (nickName)
    추가로 들어온 순서를 기억하기 위해 List에 "[Enter or Leave] [UID]"를 저장한다. 이후 파싱해서 메시지 출력
    */

    static class Solution {
        String[] output = {"님이 들어왔습니다.", "님이 나갔습니다."};

        public String[] solution(String[] record) {
            Map<String, String> map = new HashMap<>();
            List<String> list = new ArrayList<>();

            for (int i = 0; i < record.length; i++) {
                String[] message = record[i].split(" ");
                int length = message.length;
                if (length == 3) {
                    map.put(message[1], message[2]);
                    list.add(message[0] + " " + message[1]);
                } else if (length == 2) {
                    list.add(message[0] + " " + message[1]);
                }
            }

            StringBuilder sb = new StringBuilder();

            for (String message : list) {
                String[] sepMessage = message.split(" ");
                // Enter 라면
                if (sepMessage[0].equals("Enter")) {
                    sb.append(map.get(sepMessage[1])).append(output[0]).append(",");
                    // Leave 라면
                } else if (sepMessage[0].equals("Leave")) {
                    sb.append(map.get(sepMessage[1])).append(output[1]).append(",");
                }
            }

            return sb.toString().split(",");
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"};
        String[] result = {"Prodo님이 들어왔습니다.", "Ryan님이 들어왔습니다.", "Prodo님이 나갔습니다.", "Prodo님이 들어왔습니다."};

        String[] answer = solution.solution(record);
        int check = 0;
        for (int i = 0; i < answer.length; i++) {
            if (result[i].equals(answer[i])) {
                check++;
            }
        }
        if (check == result.length) {
            System.out.println("Test is passed");
        }
    }
}

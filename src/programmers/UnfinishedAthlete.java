package programmers;

import java.util.*;

class UnfinishedAthlete {
    public static String solution(String[] participant, String[] completion) {
        String answer = "";
        // key : 참가자, value : 완주 여부 카운팅
        Map<String, Integer> hash = new HashMap<>();

        // 참여한 사람은 value에 1씩 더해줌
        for (int i = 0; i < participant.length; i++) {
            int count = 0;
            if (hash.get(participant[i]) != null) {
                count = hash.get(participant[i]);
                hash.put(participant[i], ++count);
            } else {
                hash.put(participant[i], ++count);
            }
        }
        // 완주한 사람은 value에 1씩 빼줌
        for (int i = 0; i < completion.length; i++) {
            int count = hash.get(completion[i]);
            hash.put(completion[i], --count);
        }

        // value가 0보다 큰 경우는 완주하지 못한 경우
        for (int i = 0; i < participant.length; i++) {
            if (hash.get(participant[i]) > 0) {
                answer = participant[i];
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        String[][] participants = {{"leo", "kiki", "eden"}, {"marina", "josipa", "nikola", "vinko", "filipa"}, {"mislav", "stanko", "mislav", "ana"}};
        String[][] completions = {{"eden", "kiki"}, {"josipa", "filipa", "marina", "nikola"}, {"stanko", "ana", "mislav"}};
        String[] results = {"leo", "vinko", "mislav"};

        for (int i = 0; i < 3; i++) {
            String result = solution(participants[i], completions[i]);
            if (result.equals(results[i])) {
                System.out.println("pass");
            }
        }
    }
}


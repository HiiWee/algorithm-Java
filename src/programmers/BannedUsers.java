package programmers;

/*
    1. 정규식을 이용해 ban에 해당되는 user list 생성
    2. 생성된 ban list를 이용해 DFS탐색 (만약 중복 있다면 백트래킹으로 가지치기)
*/

import java.util.*;
import java.util.regex.Pattern;

public class BannedUsers {
    private static Set<Set<String>> set;
    private static List<List<String>> bannedUsers;

    public static int solution(String[] user_id, String[] banned_id) {
        set = new HashSet<>();
        bannedUsers = new ArrayList<>();
        for (String banUser : banned_id) {
            bannedUsers.add(getMatchedId(banUser, user_id));
        }

        dfs(0, new HashSet<String>());
        return set.size();
    }

    // 정규식을 이용한 해당되는 ID매칭 필드에 담기
    public static List<String> getMatchedId(String bannedUser, String[] user_id) {
        // '.'은 임의의 어떤 문자열도 가능하므로 알아서 알맞은 문자열을 찾을 수 있다.
        String pattern = bannedUser.replace('*', '.');
        List<String> list = new ArrayList<>();
        for (String userId : user_id) {
            // 패턴과 아이디 비교시 적용된다면 true
            boolean isMatch = Pattern.matches(pattern, userId);
            if (isMatch) {
                list.add(userId);
            }
        }
        return list;
    }

    // 순서 상관 없는 Set을 이용해 담음 만약 중복되는
    public static void dfs(int depth, Set<String> perm) {
        if (depth == bannedUsers.size()) {
            set.add(perm);
        } else {
            for (String banUser : bannedUsers.get(depth)) {
                // 만약 뒷 노드가 이미 방문했던 노드와 동일하면 가지치기해 탐색하지 않음
                if (!perm.contains(banUser)) {
                    perm.add(banUser);
                    dfs(depth + 1, new HashSet<String>(perm));
                    // 현재 방문한 노드를 다음 동일 레벨의 노드가 방문할 수 있게 삭제
                    perm.remove(banUser);
                }
            }
        }
    }

    // Test Code
    public static void main(String[] args) {
        String[][] user_id = {{"frodo", "fradi", "crodo", "abc123", "frodoc"},
                {"frodo", "fradi", "crodo", "abc123", "frodoc"},
                {"frodo", "fradi", "crodo", "abc123", "frodoc"}};
        String[][] banned_id = {{"fr*d*", "abc1**"},
                {"*rodo", "*rodo", "******"},
                {"fr*d*", "*rodo", "******", "******"}};

        int[] results = {2, 2, 3};

        for (int i = 0; i < user_id.length; i++) {
            if (results[i] == solution(user_id[i], banned_id[i])) {
                System.out.println("Test[" + (i + 1) + "] is passed");
            }
        }
    }

}
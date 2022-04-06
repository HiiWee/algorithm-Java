package programmers;

/*
    1. 재귀함수를 통해 나올 수 있는 수들의 조합을 모두 생성 (DFS)
       이미 지나온 수들은 체크하고 넘김(백트래킹)
    2. 각각의 수가 소수인지 판별
    3. 소수라면 담는다. (다만 중복된 소수를 체크하면서 담음)
*/
import java.util.*;

class FindDecimals {

    // 지나온 수들인지 판별하기 위한 배열
    static boolean[] visited;
    // 동일 소수를 제거하고 모든 소수들을 담음
    static Set<Integer> set;

    public static int solution(String numbers) {
        // 초기화 작업
        set = new HashSet<>();
        visited = new boolean[numbers.length()];
        for (int i = 0; i < numbers.length(); i++) {
            visited[i] = false;
        }

        // 트리의 깊이(0), 현재 번호(""), 전체 번호(ex: "123")
        backTracking(0, "", numbers);

        return set.size();
    }

    public static void backTracking(int depth, String current, String numbers) {
        // 만약 탐색 깊이가 numbers의 길이와 동일하다면 종료
        if (depth == numbers.length()) {
            return;
        }
        for (int i = 0; i < numbers.length(); i++) {
            // 이미 방문한 번호라면 패스
            if (!visited[i]) {
                // 현재 번호 + numbers에서 i번째 숫자 1개 (ex: "" + "1" == "1")
                String number = current + numbers.charAt(i);
                // 위에서 조합한 번호가 소수인지 판별 소수라면 set에 담음
                if (isDecimals(number)) set.add(Integer.valueOf(number));
                // 지금 방문한 번호는 탐색하지 못하도록 막음
                visited[i] = true;
                // 깊이+1, 현재번호==조합한번호, 전체 번호
                backTracking(depth + 1, number, numbers);
                // numbers = [1, 2, 3]에서 1로 시작하는 숫자들을 모두 조합완료하면
                // 2로 시작하는 숫자를 조합할때 1을 이용해야함 따라서 방문할 수 있게 열어둔다.
                visited[i] = false;
            }
        }
    }

    public static boolean isDecimals(String number) {
        int n = Integer.parseInt(number);
        if (n == 0 || n == 1) {
            return false;
        }
        // 중요!! 시간줄이기 : 전체 for문을 돌며 count하기 보단 소수가 아님이 판별되면 false
        for (int i = 1; i <= n / 2; i++) {
            if (n % i == 0 && i > 1) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] numbers = {"17", "011"};
        int[] results = {3, 2};

        for (int i = 0; i < numbers.length; i++) {
            if (results[i] == solution(numbers[i])) {
                System.out.printf("pass test case [%d]\n", i + 1);
            }
        }
    }
}
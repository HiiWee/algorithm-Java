package programmers;

class SportsWear {
    public static int solution(int n, int[] lost, int[] reserve) {
        int answer = n;
        int[] students = new int[n + 1];

        // 여분있는 학생 추가
        for (int i = 0; i < reserve.length; i++) {
            students[reserve[i] - 1]++;
        }
        // 도난당한 학생 제외
        for (int i = 0; i < lost.length; i++) {
            students[lost[i] - 1]--;
        }

        for (int i = 0; i < n; i++) {
            // 만약 도난당한 학생 찾을경우
            if (students[i] == -1) {
                // 1번 학생이면 바로 뒷 사람이 여분이 있는지 확인
                if (i == 0 && students[i + 1] == 1) {
                    students[i]++;
                    students[i + 1]--;
                }
                // 앞사람 체육복을 먼저 빌려야 최대한의 체육복을 학생들이 입을 수 있다.
                else if (i > 0 && students[i - 1] == 1) {
                    students[i]++;
                    students[i - 1]--;
                }
                else if (i > 0 && students[i + 1] == 1) {
                    students[i]++;
                    students[i + 1]--;
                }
                // 여분이 없는경우 제외함
                else {
                    answer--;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] n = {5, 5, 3};
        int[][] lost = {{2, 4}, {2, 4}, {3}};
        int[][] reserve = {{1, 3, 5}, {3}, {1}};
        int[] returnValue = {5, 4, 2};
        
        for (int i = 0; i < n.length; i++) {
            int result = solution(n[i], lost[i], reserve[i]);
            if (result == returnValue[i]) {
                System.out.println("result = " + result);
            }
        }
    }
}

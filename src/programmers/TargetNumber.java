package programmers;

/**
 * 첫 번째 방법
 * 1. numbers를 전부 더한 수를 구함
 * 2. 재귀를 돌며 더한 수 - (numbers의 수 * 2)를 함
 * 뺀 값 > target -> sumNum에서 제외하고 다음 인덱스 탐방
 * (현재 수를 제외하고 다른 방법이 존재할 수 있으므로 기존의 sumNum유지하고 재귀호출 한번 더 함)
 * 뺀 값 < target -> 해당 수는 포함될 수 없음 다음인덱스 탐방
 * 뺀 값 == target count증가, 방문처리후 다음 인덱스 탐방
 */
class TargetNumber_1 {
    static int count = 0;

    public static int solution(int[] numbers, int target) {
        int sumNum = 0;
        for (int i = 0; i < numbers.length; i++) {
            sumNum += numbers[i];
        }
        searchSubNum(numbers, sumNum, target, 0);
        return count;
    }

    public static void searchSubNum(int[] numbers, int sumNum, int target, int index) {
        if (index == numbers.length) {
            return;
        }
        int resultNum = sumNum - 2 * numbers[index];
        if (resultNum == target) {
            count++;
            searchSubNum(numbers, sumNum, target, index + 1);

        } else if (resultNum > target) {
            searchSubNum(numbers, resultNum, target, index + 1);
            searchSubNum(numbers, sumNum, target, index + 1);
        } else {
            searchSubNum(numbers, sumNum, target, index + 1);
        }
    }

    public static void main(String[] args) {
        int[][] numbers = {{1, 1, 1, 1, 1}, {4, 1, 2, 1}};
        int[] targets = {3, 4};
        int[] results = {5, 2};

        for (int i = 0; i < targets.length; i++) {
            int solution = solution(numbers[i], targets[i]);
            count = 0;
            if (results[i] == solution) {
                System.out.println("Test " + (i + 1) + " is passed");
            }
        }
    }
}

/**
 * 두 번째 방법 정석 DFS풀이
 * 재귀함수로 현재 인덱스의 수를 더하는것, 빼는것을 모두 진행하며
 * 결과적으로 모든 경우를 탐방하며 최종 배열의 끝 인덱스에 도달함
 * 최종 sum의 값이 target과 동일하다면 1을 반환 아니라면 0 반환
 */
class TargetNumber_2 {
    public static int solution(int[] numbers, int target) {
        int answer = 0;
        answer = DFS(numbers, 0, 0, target);
        return answer;
    }

    public static int DFS(int[] numbers, int sum, int index, int target) {
        if (index == numbers.length) {
            if (sum == target) {
                return 1;
            }
            return 0;
        }
        return DFS(numbers, sum + numbers[index], index + 1, target) + DFS(numbers, sum - numbers[index], index + 1, target);
    }

    public static void main(String[] args) {
        int[][] numbers = {{1, 1, 1, 1, 1}, {4, 1, 2, 1}};
        int[] targets = {3, 4};
        int[] results = {5, 2};

        for (int i = 0; i < targets.length; i++) {
            int solution = solution(numbers[i], targets[i]);

            if (results[i] == solution) {
                System.out.println("Test " + (i + 1) + " is passed");
            }
        }
    }
}

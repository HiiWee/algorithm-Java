package programmers;

// 이분탐색을 이용한 징검다리 건너기
class SteppingStones_1 {
    public int solution(int[] stones, int k) {
        int answer = 0;
        int min = 1;
        int max = 1;
        for (int i = 0; i < stones.length; i++) {
            max = Math.max(max, stones[i]);
        }

        while (min <= max) {
            int mid = (min + max) / 2;

            int count = 0;
            for (int i = 0; i < stones.length; i++) {
                if (stones[i] - mid <= 0) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == k) {
                    max = mid - 1;
                    break;
                }
            }
            if (count < k) {
                min = mid + 1;
                answer = mid + 1;
            }

        }
        return answer;
    }
}

// 메소드를 나눠서 작성한 이분탐색법
class SteppingStones_2 {
    public int solution(int[] stones, int k) {
        int answer = 0;
        int min = 1;
        int max = 1;
        for (int i = 0; i < stones.length; i++) {
            max = Math.max(max, stones[i]);
        }

        while (min <= max) {
            int mid = (min + max) / 2;

            if (calc(stones, mid, k)) {
                min = mid + 1;
                answer = mid + 1;
            } else {
                max = mid - 1;
            }

        }
        return answer;
    }

    public boolean calc(int[] stones, int num, int k) {
        int count = 0;

        for (int i = 0; i < stones.length; i++) {
            if (stones[i] - num <= 0) {
                count++;
            } else {
                count = 0;
            }
            if (count == k) {
                return false;
            }
        }
        return true;
    }
}

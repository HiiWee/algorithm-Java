package programmers;

class 입국심사 {
    public long solution(int n, int[] times) {

        long l = 1;
        long r = (long) Math.pow(10, 18) + 1L;
        while (l < r) {
            // mid 시간내에 n명의 검사를 완료할 수 있는지 검사해야함
            long mid = (l + r) / 2;

            long count = 0;
            for (int time : times) {
                count += mid / time;
                if (count >= n) {
                    break;
                }
            }

            if (count >= n) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
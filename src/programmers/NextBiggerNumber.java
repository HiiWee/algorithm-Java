package programmers;

class NextBiggerNumber {
    public static int solution(int n) {
        int answer = 0;
        // 2진수 변환후 1의 개수 반환
        int nCount = getOneCount(n);
        int nextN = n + 1;
        while (true) {
            // 값을 증가시키면서 1의 개수가 동일하다면 loop 종료
            if (getOneCount(nextN) == nCount) {
                answer = nextN;
                break;
            }
            nextN++;
        }
        return answer;
    }

    public static int getOneCount(int n) {
        int count = 0;
        int num = n;
        int mod;
        while (true) {
            mod = num % 2;
            num /= 2;
            if (mod == 1) {
                count++;
            }
            if (num < 2) {
                if (num == 1) {
                    count++;
                }
                break;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] n = {78, 15};
        int[] result = {83, 23};

        for (int i = 0; i < n.length; i++) {
            int answer = solution(n[i]);
            if (result[i] == answer) {
                System.out.println("Test " + (i + 1) + " is passed");
            }
        }
    }
}
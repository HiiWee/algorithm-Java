package programmers;

public class PushingKeypad {
    // 행, 열 순서
    static int[] keypad = {11, 12, 13, 21, 22, 23, 31, 32, 33, 41, 42, 43};
    static int[] left = {3, 0};
    static int[] right = {3, 2};

    public static String solution(int[] numbers, String hand) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < numbers.length; i++) {
            int num = numbers[i];
            if (num == 1 || num == 4 || num == 7) {
                sb.append("L");
            } else if (num == 3 || num == 6 || num == 9) {
                sb.append("R");
            } else {
                int flag = findCloseHand(num);
                // 왼손이 가까움
                if (flag < 0) {
                    sb.append("L");
                    // 오른손이 가까움
                } else if (flag > 0) {
                    sb.append("R");
                    // 동일거리라면 자신의 주 손으로 결정
                } else {
                    if (hand.equals("left")) {
                        sb.append("L");
                    } else {
                        sb.append("R");
                    }
                }
            }
            fixCoordinate(sb.charAt(sb.length() - 1), num);
        }
        return sb.toString();
    }

    // (왼손거리 - 오른손거리) 계산을 통해 양수, 0, 음수로 더 가까운 거리를 판단한다.
    public static int findCloseHand(int num) {
        if (num == 0) {
            num = 11;
        }
        int x = keypad[num - 1] / 10 - 1;
        int y = keypad[num - 1] % 10 - 1;

        int lDistance = Math.abs(left[0] - x) + Math.abs(left[1] - y);
        int rDistance = Math.abs(right[0] - x) + Math.abs(right[1] - y);

        return lDistance - rDistance;
    }

    public static void fixCoordinate(char hand, int num) {
        if (num == 0) {
            num = 11;
        }
        if (hand == 'L') {
            left[0] = keypad[num - 1] / 10 - 1;
            left[1] = keypad[num - 1] % 10 - 1;
        } else {
            right[0] = keypad[num - 1] / 10 - 1;
            right[1] = keypad[num - 1] % 10 - 1;
        }
    }

    public static void init() {
        left[0] = 3;
        left[1] = 0;
        right[0] = 3;
        right[1] = 2;
    }

    public static void main(String[] args) {
        int[][] numbers = {{1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5},
                {7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2},
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 0}};
        String[] hand = {"right", "left", "right"};
        String[] results = {"LRLLLRLLRRL", "LRLLRRLLLRR", "LLRLLRLLRL"};

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numbers.length; i++) {
            init();
            sb.append(solution(numbers[i], hand[i]));
            if (results[i].equals(sb.toString())) {
                System.out.println("Test Pass " + sb.toString());
            } else {
                System.out.println("Test Fail");
            }
            sb.setLength(0);
        }
    }

}

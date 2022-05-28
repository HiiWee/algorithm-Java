package programmers;

class HighestAndLowestOfLotto {
    public static int[] solution(int[] lottos, int[] win_nums) {
        int zeroCount = 0;
        int equalCount = 0;
        for (int select : lottos) {
            if (select == 0) {
                zeroCount++;
                continue;
            }
            for (int num : win_nums) {
                if (num == select) {
                    equalCount++;
                    break;
                }
            }
        }
        int min = 7 - (equalCount + zeroCount);
        int max = 7 - equalCount;

        int[] answer = {Math.min(6, min), Math.min(6, max)};
        return answer;
    }

    public static void main(String[] args) {
        int[][] lottos = {{44, 1, 0, 0, 31, 25}, {0, 0, 0, 0, 0, 0}, {45, 4, 35, 20, 3, 9}};
        int[][] win_nums = {{31, 10, 45, 1, 6, 19}, {38, 19, 20, 40, 15, 25}, {20, 9, 3, 45, 4, 35}};
        int[][] results = {{3, 5}, {1, 6}, {1, 1}};

        for (int i = 0; i < lottos.length; i++) {
            int[] answer = solution(lottos[i], win_nums[i]);
            if (answer[0] == results[i][0] && answer[1] == results[i][1]) {
                System.out.println("Test " + (i + 1) + " is passed");
            }
        }
    }
}
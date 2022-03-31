package programmers;

public class Hopscotch {

    /* 좋은 DP 예시 문제 */
/*
    1 <= i <= land.length 일 때,
    i행일때 각 4개의 열에 직전 i - 1행에서 동일 인덱스 제외 3개의 열 중에서 가장 큰 값을 더한다.
*/
    static int solution(int[][] land) {
        int answer = 0;
        for (int i = 1; i < land.length; i++) {
            land[i][0] += getMax(land[i - 1][1], land[i - 1][2], land[i - 1][3]);
            land[i][1] += getMax(land[i - 1][0], land[i - 1][2], land[i - 1][3]);
            land[i][2] += getMax(land[i - 1][0], land[i - 1][1], land[i - 1][3]);
            land[i][3] += getMax(land[i - 1][0], land[i - 1][1], land[i - 1][2]);
        }

        answer = getMaxRow(land);
        return answer;
    }

    static int getMax(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }

    static int getMaxRow(int arr[][]) {
        int rowMax1 = Math.max(arr[arr.length - 1][0], arr[arr.length - 1][1]);
        int rowMax2 = Math.max(arr[arr.length - 1][2], arr[arr.length - 1][3]);
        return Math.max(rowMax1, rowMax2);
    }


    public static void main(String[] args) {
        int[][][] param1 = {
                            {
                                {1, 2, 3, 5},
                                {5, 6, 7, 8},
                                {4, 3, 2, 1}
                            }, {
                                {3, 3, 3, 4},
                                {3, 3, 3, 4},
                                {3, 3, 3, 4},
                                {3, 3, 3, 4}
                            }, {
                                {1, 1, 1, 1},
                                {2, 2, 2, 3},
                                {3, 3, 3, 6},
                                {4, 4, 4, 7}}
                            };

        int[] result = {16, 14, 14};

        for (int i = 0; i < param1.length; i++) {
            if (result[i] == solution(param1[i])) {
                System.out.println("result[" + i + "] = " + result[i]);
            }
        }
    }
}

package leetcode;

class LeetCode6 {

    int[] rows = {1, -1};
    int[] cols = {0, 1};

    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        char[][] map = new char[numRows][1000];

        int r = 0;
        int c = 0;
        int index = 0;
        int vector = 0;
        // 3, 6, 9, 12
        // 2 4 6 8 10
        while (index < s.length()) {
            if (index != 0 && index % (numRows - 1) == 0) {
                vector = (vector + 1) % 2;
            }

            map[r][c] = s.charAt(index++);

            r += rows[vector];
            c += cols[vector];
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < 1000; j++) {
                if (map[i][j] != '\0') {
                    answer.append(map[i][j]);
                }
            }
        }

        return answer.toString();
    }
}

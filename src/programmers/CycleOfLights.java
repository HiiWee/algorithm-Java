package programmers;

import java.util.ArrayList;
import java.util.List;

public class CycleOfLights {

    // 상우하좌
    final int[] row = {-1, 0, 1, 0};
    final int[] col = {0, 1, 0, -1};
    boolean[][][] cycle;

    public int[] solution(String[] grid) {
        cycle = new boolean[grid.length][grid[0].length()][4];
        List<Integer> cycleCounts = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length(); j++) {
                for (int k = 0; k < 4; k++) {
                    if (!cycle[i][j][k]) {
                        int cycleCount = searchCycle(grid, i, j, k);
                        cycleCounts.add(cycleCount);
                    }
                }
            }
        }
        return cycleCounts.stream()
                .sorted()
                .mapToInt(i -> i)
                .toArray();
    }

    public int searchCycle(String[] grid, int r, int c, int k) {
        int count = 0;

        while (true) {
            if (cycle[r][c][k]) {
                break;
            }

            // 현재 노드 방문 처리
            cycle[r][c][k] = true;
            char symbol = grid[r].charAt(c);
            if (symbol == 'L') {
                k = (k + 1) % 4;
            } else if (symbol == 'R') {
                k = (k + 3) % 4;
            }
            count++;
            r = (row[k] + r + grid.length) % grid.length;
            c = (col[k] + c + grid[0].length()) % grid[0].length();
        }
        return count;
    }
}

package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class makeStarInIntersection {
    /*
    완탐을 통해 모든 교점에 대한 접점을 구하는데 AD - BC = 0인경우는 교점에 추가하지 않는다.
    교점을 구하고 나서 좌표로 만들어지는 사각형 기준으로 좌측 하단 꼭짓점의 좌표를 0,0으로 옮긴다.
    이때 발생하는 편차를 모든 좌표에 적용하여 일괄적으로 옮겨준다.

    이후 해당 좌표에서 최대 행과 열값을 구하여 해당 크기에 맞는 배열을 생성한다.

*/

    int minX = Integer.MAX_VALUE;
    int minY = Integer.MAX_VALUE;
    int maxX = Integer.MIN_VALUE;
    int maxY = Integer.MIN_VALUE;

    static class Pos {
        int x, y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public String[] solution(int[][] line) {
        List<Pos> poses = new ArrayList<>();
        for (int i = 0; i < line.length - 1; i++) {
            for (int j = i + 1; j < line.length; j++) {
                Pos pos = createIntersection(line[i], line[j]);
                if (pos != null) {
                    poses.add(pos);
                }
            }
        }
        setVertices(poses);
        int movedMaxX = Integer.MIN_VALUE;
        int movedMaxY = Integer.MIN_VALUE;
        for (Pos pos : poses) {
            pos.x = pos.x - minX;
            pos.y = maxY - pos.y;
            movedMaxX = Math.max(movedMaxX, pos.x);
            movedMaxY = Math.max(movedMaxY, pos.y);
        }
        char[][] answer = new char[movedMaxY + 1][movedMaxX + 1];
        for (int i = 0; i < movedMaxY + 1; i++) {
            Arrays.fill(answer[i], '.');
        }
        for (Pos pos : poses) {
            answer[pos.y][pos.x] = '*';
        }

        String[] result = new String[movedMaxY + 1];

        for (int i = 0; i < movedMaxY + 1; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < movedMaxX + 1; j++) {
                sb.append(answer[i][j]);
            }
            result[i] = sb.toString();
        }
        return result;
    }

    void setVertices(List<Pos> poses) {
        for (Pos pos : poses) {
            maxX = Math.max(maxX, pos.x);
            maxY = Math.max(maxY, pos.y);
            minX = Math.min(minX, pos.x);
            minY = Math.min(minY, pos.y);
        }
    }

    Pos createIntersection(int[] line1, int[] line2) {
        long adbc = (((long) line1[0] * (long) line2[1]) - ((long) line1[1] * (long) line2[0]));
        if (adbc == 0) {
            return null;
        }
        if ((((long) line1[1] * (long) line2[2]) - ((long) line1[2] * (long) line2[1])) % adbc != 0
                || (((long) line1[2] * (long) line2[0]) - ((long) line1[0] * (long) line2[2])) % adbc != 0) {
            return null;
        }
        int x = (int) ((((long) line1[1] * (long) line2[2]) - ((long) line1[2] * (long) line2[1])) / adbc);
        int y = (int) ((((long) line1[2] * (long) line2[0]) - ((long) line1[0] * (long) line2[2])) / adbc);

        return new Pos(x, y);
    }

}

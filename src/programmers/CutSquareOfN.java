package programmers;

import java.util.ArrayList;
import java.util.List;

public class CutSquareOfN {

    static class Pair {
        long r, c;

        Pair(long r, long c) {
            this.r = r;
            this.c = c;
        }

        static Pair from(long num, int n) {
            long row = num / n;
            num %= n;
            long col = num;
            return new Pair(row, col);
        }

        long getMaxPos() {
            return Math.max(r, c);
        }
    }

    public Long[] solution(int n, long left, long right) {
        List<Long> answers = new ArrayList<>();
        for (long i = left; i <= right; i++) {
            Pair pair = Pair.from(i, n);
            long maxPos = pair.getMaxPos();
            answers.add(maxPos + 1);
        }
        return answers.toArray(new Long[answers.size()]);
    }
}

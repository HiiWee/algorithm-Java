package leetcode;

// Solved with O(N)
import java.util.*;

class LeetCode1276_1 {
    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        if (tomatoSlices % 2 == 1 || cheeseSlices > tomatoSlices) {
            return Collections.emptyList();
        }
        if (tomatoSlices == 0 && cheeseSlices == 0) {
            return List.of(0, 0);
        }

        for (int i = 0; i <= cheeseSlices; i++) {
            int jumboCount = cheeseSlices - i;
            int smallCount = i;

            int tomatoCount = 4 * jumboCount;
            tomatoCount += 2 * smallCount;

            if (tomatoCount == tomatoSlices) {
                return List.of(jumboCount, smallCount);
            }
        }

        return Collections.emptyList();
    }
}

// Solved with O(logN)
class LeetCode1276_2 {
    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        if (tomatoSlices % 2 == 1 || cheeseSlices > tomatoSlices) {
            return Collections.emptyList();
        }
        if (tomatoSlices == 0 && cheeseSlices == 0) {
            return List.of(0, 0);
        }

        int l = 0;
        int r = cheeseSlices;

        while (l < r) {
            int mid = (l + r) / 2;

            int count = mid * 4;
            count += (cheeseSlices - mid) * 2;

            if (count >= tomatoSlices) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        if (l * 4 + (cheeseSlices - l) * 2 == tomatoSlices) {
            return List.of(l, cheeseSlices - l);
        }
        return Collections.emptyList();
    }
}

// Solved with O(1)
class LeetCode1276_3 {
    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        // 4x + 2y = ts
        // x + y = cs, 2x + 2y = 2cs
        // 2x = ts - 2cs
        // x = ts / 2 - 2cs / 2

        int x = tomatoSlices / 2 - (2 * cheeseSlices) / 2;

        if (x >= 0 && cheeseSlices - x >= 0 && x * 4 + (cheeseSlices - x) * 2 == tomatoSlices) {
            return List.of(x, cheeseSlices - x);
        }

        return Collections.emptyList();
    }
}

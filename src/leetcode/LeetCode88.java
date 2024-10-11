package leetcode;

// Original version
class LeetCode88_1 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) {
            return;
        }

        int[] nums1Copy = new int[m];
        for (int i = 0; i < m; i++) {
            nums1Copy[i] = nums1[i];
        }

        int indexOne = 0;
        int indexTwo = 0;
        int currentIndex = 0;

        while (currentIndex < m + n) {

            if (indexOne == m) {
                while (currentIndex < m + n) {
                    nums1[currentIndex++] = nums2[indexTwo++];
                }
                break;
            } else if (indexTwo == n) {
                while (currentIndex < m + n) {
                    nums1[currentIndex++] = nums1Copy[indexOne++];
                }
                break;
            }

            if (indexOne < m && indexTwo < n && nums1Copy[indexOne] < nums2[indexTwo]) {
                nums1[currentIndex] = nums1Copy[indexOne];
                indexOne++;
                currentIndex++;
            } else {
                nums1[currentIndex] = nums2[indexTwo];
                indexTwo++;
                currentIndex++;
            }
        }
    }
}

// Better version
class LeetCode88_2 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] nums1Copy = new int[m];
        for (int i = 0; i < m; i++) {
            nums1Copy[i] = nums1[i];
        }

        int i1 = 0;
        int i2 = 0;
        for (int i = 0; i < n + m; i++) {
            int numberOne;
            int numberTwo;
            if (i1 < m) {
                numberOne = nums1Copy[i1];
            } else {
                numberOne = Integer.MAX_VALUE;
            }
            if (i2 < n) {
                numberTwo = nums2[i2];
            } else {
                numberTwo = Integer.MAX_VALUE;
            }

            if (numberOne > numberTwo) {
                nums1[i] = numberTwo;
                i2++;
            } else {
                nums1[i] = numberOne;
                i1++;
            }
        }
    }
}

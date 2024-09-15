package boj;

class LeetCode274 {
    public int hIndex(int[] citations) {
        int n = citations.length;
        int[] buckets = new int[n + 1];

        for (int citation : citations) {
            if (citation >= n) {
                buckets[n]++;
            } else {
                buckets[citation]++;
            }
        }

        for (int bucket : buckets) {
            System.out.print(bucket + " ");
        }

        int answer = 0;
        int count = 0;
        for (int i = n; i >= 0; i--) {
            count += buckets[i];

            if (count >= i) {
                answer = i;
                break;
            }
        }

        return answer;
    }
}

package programmers;

class 풍선_터트리기 {

    int n;
    public int solution(int[] a) {
        if (a.length < 2) {
            return a.length;
        }
        n = a.length;
        int answer = 0;

        int[] rightMin = new int[n];
        rightMin[n - 1] = a[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMin[i] = Math.min(rightMin[i + 1], a[i]);
        }

        int leftMin = a[0];
        for (int i = 0; i < n; i++) {
            if (!(leftMin < a[i] && rightMin[i] < a[i])) {
                answer++;
                leftMin = Math.min(a[i], leftMin);
            }
        }
        return answer;
    }
}
package structure;

public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 13, 29, 29, 29, 30, 34, 35, 39, 40, 50, 565};
        int targetNum = 29;

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("Target Number is [" + targetNum + "]");
        System.out.println("Upper Bound BinarySearch");
        upperBoundBinarySearch(arr, targetNum);

        System.out.println();

        System.out.println("Lower Bound BinarySearch");
        lowerBoundBinarySearch(arr, targetNum);

    }

    public static void upperBoundBinarySearch(int[] arr, int targetNum) {
        int low = 0;
        int high = arr.length;
        int mid;
        while (low <= high) {
            mid = (low + high) / 2;

            System.out.println("LOW: " + low + " " + "HIGH: " + high + " MID: " + mid);
            if (arr[mid] > targetNum) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        // 16번째 줄의 > 조건으로 인해 Upper_bound로 계산됨
        // targetNum과 동일한 수가 여러개 있다면 low의 값은 targetNum보다 큰 수가 처음 나타나는 인덱스를 가진다.
        System.out.println("[RESULT]\nLOW: " + low + " HIGH: " + high);
    }

    public static void lowerBoundBinarySearch(int[] arr, int targetNum) {
        int low = 0;
        int high = arr.length;
        int mid;
        while (low <= high) {
            mid = (low + high) / 2;

            System.out.println("LOW: " + low + " " + "HIGH: " + high + " MID: " + mid);
            if (arr[mid] >= targetNum) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        // 16번째 줄의 > 조건으로 인해 Lower_bound로 계산됨
        // targetNum과 동일한 수가 여러개 있다면 low의 값은 targetNum이 처음 나타나는 곳의 인덱스를 가진다.
        System.out.println("[RESULT]\nLOW: " + low + " HIGH: " + high);    }
}

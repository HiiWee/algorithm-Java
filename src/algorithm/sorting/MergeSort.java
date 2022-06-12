package algorithm.sorting;

// Merge Sorting Bottom-Up
class MergeSort_1 {
    static int[] sorted;
    static int count = 0;

    public static void main(String[] args) {
        int[] arr = {6, 4, 3, 5, 2, 1};
        sorted = new int[arr.length];

        // before
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        mergeSort(arr, 0, arr.length - 1);

        // after
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        System.out.println(count);
    }

    // bottom-up
    public static void mergeSort(int[] arr, int left, int right) {
        for (int size = 1; size <= right; size += size) {

            for (int l = 0; l <= right - size; l += (size * 2)) {
                int low = l;
                int mid = l + size - 1;
                int high = Math.min(l + (2 * size) - 1, right);
                merge(arr, low, mid, high);
            }

        }

    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int l = left;
        int r = mid + 1;
        int idx = left;

        while (l <= mid && r <= right) {

            if (arr[l] <= arr[r]) {
                sorted[idx++] = arr[l++];
            } else {
                sorted[idx++] = arr[r++];
            }
        }

        if (l > mid) {
            while (r <= right) {
                sorted[idx++] = arr[r++];
            }
        } else {
            while (l <= mid) {
                sorted[idx++] = arr[l++];
            }
        }

        for (int i = left; i <= right; i++) {
            arr[i] = sorted[i];
        }
    }
}

// Merge Sorting Top-Down
class MergeSort_2 {
    static int[] sorted;
    static int count = 0;

    public static void main(String[] args) {
        int[] arr = {6, 4, 3, 5, 2, 1};
        sorted = new int[arr.length];

        // before
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        divide(arr, 0, arr.length - 1);

        // after
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        System.out.println(count);
    }

    // Top-Down
    public static void divide(int[] arr, int left, int right) {
        if (left != right) {
            int mid = (left + right) / 2;
            divide(arr, left, mid);
            divide(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int l = left;
        int r = mid + 1;
        int idx = left;

        while (l <= mid && r <= right) {

            if (arr[l] <= arr[r]) {
                sorted[idx++] = arr[l++];
            } else {
                sorted[idx++] = arr[r++];
            }
        }

        if (l > mid) {
            while (r <= right) {
                sorted[idx++] = arr[r++];
            }
        } else {
            while (l <= mid) {
                sorted[idx++] = arr[l++];
            }
        }

        for (int i = left; i <= right; i++) {
            arr[i] = sorted[i];
        }
    }
}
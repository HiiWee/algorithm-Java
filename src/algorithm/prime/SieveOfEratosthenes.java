package algorithm.prime;

public class SieveOfEratosthenes {

    public static void main(String[] args) {
        int[] arr = new int[100];
        arr[1] = 1;
        for (int i = 2; i < arr.length; i++) {
            if (arr[i] == 1) {
                continue;
            }
            for (int j = 2 * i; j < arr.length; j += i) {
                arr[j] = 1;
            }
        }

        System.out.println("if value is 0, that is prime number");
        for (int i = 1; i < arr.length; i++) {
            System.out.println("num: " + i + " prime: " + arr[i]);
        }
    }
}

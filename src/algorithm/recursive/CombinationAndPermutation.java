package algorithm.recursive;

public class CombinationAndPermutation {

    public static void main(String[] args) {

        /*
         * [순열]
         * 먼저 순열은 서로다른 n개 중에 r개를 선택하는 방법으로 순서를 정해서 나열한다.
         * n P r 과 같은 기호로 나타낸다.(p == permutation)
         * 따라서 순서에 영향을 받는다.
         *
         * [조합]
         * 조합은 서로 다른 n 개중에 r개를 선택하지만 순서를 고려하지 않고 뽑는다.
         * n C r과 같은 기호론 나타낸다. (C = Combination)
         * 따라서 순서에 영향을 받지 않는다.
         * */

        /*
         * 아래 배열의 원소에서 2개, 3개, 4개를 뽑는 순열과 조합을 구해보자
         */
        int[] arr = new int[]{1, 2, 3, 4};

        Combination combination = new Combination();
        Permutation permutation = new Permutation();
        StringBuilder sb = new StringBuilder();

        System.out.println("Combination with backtracking");
        combination.combi1(arr, new boolean[arr.length], 0, arr.length, 3);
        System.out.println();

        System.out.println("Combination with recursive");
        combination.combi2(arr, new boolean[arr.length], 0, arr.length, 3);
        System.out.println();

        System.out.println("Permutation with recursive");
        permutation.perm(new int[3], arr, new boolean[arr.length], 0, arr.length, 3);
        System.out.println();

    }

}

class Combination {

    // 백트래킹 방식을 이용한 조합 구하기
    public void combi1(int[] arr, boolean[] visited, int depth, int n, int r) {
        // 모두 뽑았다면 출력하고 종료
        if (r == 0) {
            print(arr, visited);
            return;
        }

        /*
         * 동작 원리
         * 1 방문(깊이 1), 재귀 -> 2방문(깊이 2), 재귀 -> 3방문(깊이 3), 출력 -> 2미방문처리(깊이 2), i증가 -> 3방문(깊이 3), 출력
         * 이런 순서로 모든 조합들이 하나씩 구해진다
         */
        for (int i = depth; i < n; i++) {
            visited[i] = true;
            combi1(arr, visited, i + 1, n, r - 1);
            visited[i] = false;
        }
    }

    // 재귀방식을 이용한 조합 구하기
    public void combi2(int[] arr, boolean[] visited, int depth, int n, int r) {
        if (r == 0) {
            print(arr, visited);
            return;
        }
        if (depth == n) {
            return;
        }

        // 뽑는 경우와, 뽑지 않는 경우를 재귀를 돌린다.
        visited[depth] = true;
        combi2(arr, visited, depth + 1, n, r - 1);
        visited[depth] = false;
        combi2(arr, visited, depth + 1, n, r);
    }

    public void print(int[] arr, boolean[] visited) {
        System.out.print("[ ");
        for (int i = 0; i < arr.length; i++) {
            if (visited[i]) {
                System.out.print(arr[i] + " ");
            }
        }
        System.out.println("]");
    }

}

class Permutation {

    // DFS를 이용한 순열 구하기
    public void perm(int[] out, int[] arr, boolean[] visited, int depth, int n, int r) {
        if (depth == r) {
            print(out);
            return;
        }
        // 순열은 순서가 다르다면 다른 순열(조합은 123, 132가 같은 취급 하지만, 순열은 아님)
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                // 방문할때마다 바로바로 찍어줘야 순서를 나타낼 수 있다.
                out[depth] = arr[i];
                perm(out, arr, visited, depth + 1, n, r);
                visited[i] = false;
            }
        }

    }

    private void print(int[] out) {
        System.out.print("[ ");
        for (int num : out) {
            System.out.print(num + " ");
        }
        System.out.println("]");
    }


}

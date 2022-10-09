package boj;

/*
    DFS + 메모제이션을 이용해서 푼다.

    1. 먼저 i번돌을 기점으로 시작하게 되면
    2. j(i + 1) ~ N번 돌을 반복문으로 검사하면서 만약 i번돌에서 해당돌(j)로 갈 수 있다면 dp에 j 돌을 true로 해당돌을 갈 수 있다고 표시한다.
        2-1. 2번 과정에서 이미 dp[j]가 이미 true로 표시되어 있다면 상위 재귀에서 이미 탐색했으므로, 굳이 해당 돌을 또 방문할 필요가 없다 따라서, 다음 j + 1의 돌을 탐색한다.
    3. j돌의 인덱스로 다시 DFS 탐색을 해간다.

*/
import java.util.*;
import java.io.*;

class Boj22869_1 {
    static int n, k;
    static boolean[] dp;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[n + 1];
        dp = new boolean[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp[1] = true;
        dfs(1);

        if (dp[n]) {
            bw.write("YES");
        } else {
            bw.write("NO");
        }
        bw.flush();
        bw.close();

    }
    public static void dfs(int index) {
        for (int i = index + 1; i <= n; i++) {
            if (check(index, i)) {
                if (dp[i]) {
                    continue;
                }
                dp[i] = true;
                dfs(i);
            }
        }
    }

    public static boolean check(int from, int to) {
        return ((to - from) * (1 + Math.abs(arr[from] - arr[to]))) <= k;
    }
}

/*
    bottom up 방식
    첫번째 for문은 단순히 1 ~ n - 1의 돌을 돌고, 두번째 for문은 i + 1 ~ n까지 돈다.
    따라서 j돌은 i돌보다 최소 한 칸 뒤의 돌을 가리킨다.

    0. 먼저 dp[1]은 갈 수 있으므로 true로 두고 시작한다.
    1. 만약 i -> j돌로 갈 수 있다면 j돌은 갈 수 있으므로 dp[j] = true가 된다.
       1-1. 여기서 만약 이미 j돌이 true라면 continue 한다.
    2. 이렇게 1번돌을 시작으로 갈 수 있는 돌을 모두 체크하고 나면 i를 증가시킨다.
    3. 다음 i를 선택할때 dp값이 true인 i를 선택해야 갈 수 없는 돌의 검사를 막을 수 있다.
 */
class Boj22869_2 {
    static int n, k;
    static boolean[] dp;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[n + 1];
        dp = new boolean[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        dp[1] = true;
        for (int i = 1; i <= n - 1; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (check(i, j) && !dp[j]) {
                    dp[j] = true;
                }
            }
        }
        if (dp[n]) {
            bw.write("YES");
        } else {
            bw.write("NO");
        }
        bw.flush();
        bw.close();

    }

    public static boolean check(int from, int to) {
        return ((to - from) * (1 + Math.abs(arr[from] - arr[to]))) <= k;
    }
}
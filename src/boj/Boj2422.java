package boj;

/*
    3개의 조합을 모두 확인하는데 M개의 섞으면 안되는 조합이 pair로 주어진다.
    따라서 2차원 check배열을 이용해 check[num1][num2] = true로 체크해 조합을 카운트하는 것을 막는다.
    (단 num1 < num2)

    3중 for문을 돌지만 제일 바깥 for문을 제외한 for문의 시작 지점은 직전의 상위 for문의 변수값 + 1로 지정하여
    서로 숫자 중복되는 조합이나, 이미 체크했던 조합을 다시 체크하지 않도록 한다.
    (따라서 초기에 check[num1][num2]는 num1 < num2로 되게 두어도 상관이 없다.)
*/
import java.util.*;
import java.io.*;

// brute force
class Boj2422_1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        boolean[][] check = new boolean[n + 1][n + 1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (a > b) {
                check[b][a] = true;
            } else {
                check[a][b] = true;
            }
        }

        int count = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (check[i][j]) {
                    continue;
                }
                for (int k = j + 1; k <= n; k++) {
                    if (check[i][k] || check[j][k]) {
                        continue;
                    }
                    count++;
                }
            }
        }

        bw.write(count + "\n");
        bw.flush();
        bw.close();
    }

}
/*
    섞어먹으면 안되는 조합을 하나씩 받으면서 해당 조합을 포함한 모든 경우의 수를 구하여
    Set에 담는다. 순서는 상관이 없으므로 일관성을 위해 정렬을 하여 담아, 중복이 발생하는것을 막는다.
    이후 전제 조합의 경우의 수 - set의 사이즈를 빼면된다.
    이 방법은 메모리 및 시간이 꽤나 걸리는 방법이다.
*/

// combination 이용
class Boj2422_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int count = getCombinationCount(n, 3);
        Set<String> set = new HashSet<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            for (int j = 1; j <= n; j++) {
                if (a != j && b != j) {
                    char[] arr = new char[3];
                    arr[0] = (char)(a + '0');
                    arr[1] = (char)(b + '0');
                    arr[2] = (char)(j + '0');
                    Arrays.sort(arr);
                    set.add(new String(arr));
                }
            }
        }

        bw.write(count - set.size() + "\n");
        bw.flush();
        bw.close();
    }

    public static int getCombinationCount(int n, int r) {
        if (n == r || r == 0) {
            return 1;
        }
        return getCombinationCount(n - 1, r - 1) + getCombinationCount(n - 1, r);
    }
}



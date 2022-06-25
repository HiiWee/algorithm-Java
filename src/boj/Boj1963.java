package boj;
import java.io.*;
import java.util.*;
/*
   먼저 0 ~ 9999까지의 배열을 만들어 소수인지 아닌지 판별한다 (에라토네스의 체)
   이후 한자리씩 수를 변경하면서 소수인지 판별하는 과정을 BFS를 통해 진행

*/
class Boj1963 {
    static boolean[] primeArr;
    static int[] arr;
    static boolean[] visited;
    static int end;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        primeArr = new boolean[10000];

        setPrimeNum();

        for (int i = 0; i < T; i++) {
            visited = new boolean[10000];
            arr = new int[10000];

            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            if (start == end) {
                bw.write("0\n");
                continue;
            }
            if (BFS(start)) {
                bw.write(arr[end] + "\n");
            } else {
                bw.write("Impossible\n");
            }
        }
        bw.flush();
        bw.close();
    }

    private static void setPrimeNum() {
        for (int i = 2; i < 10000; i++) {
            if (primeArr[i]) {
                continue;
            }
            for (int j = 2 * i; j < 10000; j += i) {
                primeArr[j] = true;
            }
        }
    }

    public static boolean BFS(int start) {
        Queue<Integer> que = new LinkedList<>();
        que.add(start);
        visited[start] = true;

        while (!que.isEmpty()) {
            String prime = String.valueOf(que.poll());
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 10; j++) {
                    // 1000의 자리는 0이 되면 안됨
                    if (i == 0 && j == 0 || i == 0 && j == 3) {
                        continue;
                    }
                    int nextNumber = getChangeNum(prime, i, j);
                    if (!visited[nextNumber] && !primeArr[nextNumber]) {
                        que.add(nextNumber);
                        visited[nextNumber] = true;
                        arr[nextNumber] = arr[Integer.parseInt(prime)] + 1;
                    }
                    if (nextNumber == end) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static int getChangeNum(String prime, int index, int target) {
        StringBuilder sb = new StringBuilder(prime);
        sb.replace(index, index + 1, String.valueOf(target));
        return Integer.parseInt(sb.toString());
    }
}

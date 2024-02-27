package boj;

// 그래프 이론 - 유니온 파인드 적용
import java.io.*;

class Boj15886_1 {

    static int n;
    static int[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        parents = new int[n];
        String line = br.readLine();
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
        for (int i = 0; i < n; i++) {
            if (line.charAt(i) == 'E') {
                if (i == n - 1)  {
                    break;
                }
                union(i, i + 1);
            } else {
                if (i == 0) {
                    continue;
                }
                union(i, i - 1);
            }
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (parents[i] == i) {
                count++;
            }
        }

        bw.write(Integer.toString(count));
        bw.flush();
        bw.close();
    }

    public static int find(int a) {
        if (parents[a] == a) {
            return a;
        }
        return parents[a] = find(parents[a]);
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) {
            return;
        }
        if (a < b) {
            parents[a] = b;
        } else {
            parents[b] = a;
        }
    }
}

// 간단한 풀이 - 문제의 핵심인 맵을 벗어나지 않는 특성을 이용
class Boj15886_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        String line = br.readLine();

        int count = 0;
        for (int i = 0; i < n - 1; i++) {
            if (line.charAt(i) == 'E' && line.charAt(i + 1) == 'W') {
                count++;
            }
        }

        bw.write(Integer.toString(count));
        bw.flush();
        bw.close();
    }
}
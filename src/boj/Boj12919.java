package boj;
/*
     문자열 S에 'A'와 'B'를 하나씩 추가하여 T를 만들어내도록 확인하는 것은 불가능합니다.
    연산의 최대 가짓수가 2^50이 되므로, 시간초과가 발생하기 때문입니다.

    따라서 반대로 T -> S로 조건에 맞으면 변경하는 과정이 제일 효율적인 방식입니다.

    이를 구현할 수 있는 방식은 DFS, BFS모두 가능합니다.
    T -> S로 변환해야 한다는것을 인지하게 되면 풀이 자체는 간단한 편이다.
*/
import java.util.*;
import java.io.*;

// BFS 풀이
class Boj12919 {
    static String s;
    static String t;
    static int possible = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        s = br.readLine();
        t = br.readLine();

        BFS();

        bw.write(possible + "\n");
        bw.flush();
        bw.close();
    }

    public static void BFS() {
        Queue<String> que = new LinkedList<>();
        que.add(t);

        while (!que.isEmpty()) {
            String cur = que.poll();

            if (cur.length() == 0) {
                return;
            }

            if (cur.length() == s.length()) {
                if (cur.equals(s)) {
                    possible = 1;
                    return;
                }
            }
            if (cur.charAt(cur.length() - 1)== 'A') {
                String next = getRemoveA(cur);
                que.add(next);
            }
            if (cur.charAt(0) == 'B') {
                String next = getRemoveBAndReverse(cur);
                que.add(next);
            }

        }
    }

    public static String getRemoveA(String line) {
        StringBuilder sb = new StringBuilder(line);
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    public static String getRemoveBAndReverse(String line) {
        StringBuilder sb = new StringBuilder(line);
        return sb.deleteCharAt(0).reverse().toString();
    }
}

// DFS 풀이
class Boj12919_2 {
    static String s;
    static String t;
    static int possible = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        s = br.readLine();
        t = br.readLine();

        DFS(t);

        bw.write(possible + "\n");
        bw.flush();
        bw.close();
    }

    public static void DFS(String line) {
        if (line.length() == s.length()) {
            if (line.equals(s)) {
                possible = 1;
            }
            return;
        }

        if (line.charAt(line.length() - 1) == 'A') {
            DFS(getRemoveA(line));
        }
        if (line.charAt(0) == 'B') {
            DFS(getRemoveBAndReverse(line));
        }
    }

    public static String getRemoveA(String line) {
        StringBuilder sb = new StringBuilder(line);
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    public static String getRemoveBAndReverse(String line) {
        StringBuilder sb = new StringBuilder(line);
        return sb.deleteCharAt(0).reverse().toString();
    }
}

package boj;

/*
    각 책을 뽑는 모든 조합을 구하는데 1개 뽑는경우 부터 ~ N개를 뽑는 경우를 전부 구한다.
    이후 구해진 모든 조합을 각 조합마다 찾는 단어의 알파벳이 모두 포함되어 있다면 가격을 측정하고,
    포함되어 있지 않다면 다음 조합을 검사한다.
*/

import java.util.*;
import java.io.*;

// 조합 + 비효율적 풀이
class Boj16508_1 {
    static List<String> list = new ArrayList<>();
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        char[] word = br.readLine().toCharArray();
        int n = Integer.parseInt(br.readLine());
        String[] books = new String[n];
        int[] price = new int[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            price[i] = Integer.parseInt(st.nextToken());
            books[i] = st.nextToken();
        }

        for (int i = 1; i <= n; i++) {
            combination(new boolean[n], n, i, 0);
        }

        // 하나의 조합에 해당되는 각 책의 알파벳 카운팅
        for (String combi : list) {
            StringTokenizer st = new StringTokenizer(combi);
            // 해당 조합의 책의 알파벳의 개수를 카운트할 배열
            int[][] alpha = new int[st.countTokens()][26];
            int count = st.countTokens();
            int priceOfCombi = 0;
            for (int i = 0; i < count; i++) {
                int idx = Integer.parseInt(st.nextToken());
                // 조합의 책을 꺼냄
                String book = books[idx];
                // 해당 조합의 책의 값을 구함
                priceOfCombi += price[idx];
                // 알파벳 카운팅
                for (int j = 0; j < book.length(); j++) {
                    int alphaIdx = book.charAt(j) - 'A';
                    alpha[i][alphaIdx]++;
                }
            }

            boolean[] check = new boolean[word.length];
            for (int i = 0; i < count; i++) {
                for (int j = 0; j < word.length; j++) {
                    if (check[j]) {
                        continue;
                    }
                    int alphaIdx = word[j] - 'A';
                    if (alpha[i][alphaIdx] > 0) {
                        alpha[i][alphaIdx]--;
                        check[j] = true;
                    }
                }
            }

            boolean flag = true;
            for (int i = 0; i < check.length; i++) {
                if (!check[i]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                min = Math.min(min, priceOfCombi);
            }
        }
        if (min == Integer.MAX_VALUE) {
            bw.write("-1\n");
        } else {
            bw.write(min + "\n");
        }
        bw.flush();
        bw.close();
    }

    public static void combination(boolean[] visited, int n, int r, int depth) {
        if (r == 0) {
            append(visited);
            return;
        }

        for (int i = depth; i < n; i++) {
            visited[i] = true;
            combination(visited, n, r - 1, i + 1);
            visited[i] = false;
        }
    }

    public static void append(boolean[] visited) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < visited.length; i++) {
            if (visited[i]) {
                sb.append(i).append(" ");
            }
        }
        list.add(sb.toString());
    }
}

/*
   단어에 대한 알파벳 카운트 배열을 만들고, 단어의 알파벳의 개수를 카운트한다.
   각 책에대한 모든 조합을 돌면서 선택된 책의 알파벳에 대한 누적 카운트 배열을 만들어 카운팅을 누적해간다.

   앞의 책을 선택했든 선택하지 않았든 n번째 책까지 도달하게 되면
   단어 알파벳 카운트 값과 누적 책의 알파벳 카운트의 값 26자리를 비교한다.
   만일 그 중 하나라도 단어 알파벳 카운트 값이 더 크게되면 지금 선택된 조합의 책들로는 해당 단어를 만들 수 없고,
   누적 책의 알파벳 카운트 값이 크거나 같다면 단어를 완성시킬 수 있으므로 최소값을 갱신한다.
*/

// 효율적인 조합 이용 풀이
class Boj16508_2 {
    static int[] word = new int[26];
    static int[] total = new int[26];
    static int[] price;
    static String[] books;
    static int n, min = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String line = br.readLine();
        for (int i = 0; i < line.length(); i++) {
            word[line.charAt(i) - 'A']++;
        }
        n = Integer.parseInt(br.readLine());
        price = new int[n];
        books = new String[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            price[i] = Integer.parseInt(st.nextToken());
            books[i] = st.nextToken();
        }
        combination(0, 0);

        if (min == Integer.MAX_VALUE) {
            bw.write("-1\n");
        } else {
            bw.write(min + "\n");
        }
        bw.flush();
        bw.close();
    }

    // 각 책에 대한 모든 경우의 수를 돌며 알파벳을 완성시킬 수 있는지 확인 및 최소값 갱신
    public static void combination(int index, int sum) {
        // 만약 지금까지 선택한 책으로 완성시킬 수 있다면 최소값 갱신
        if (index == n) {
            if (check()) {
                min = Math.min(sum, min);
            }
            return;
        }

        for (int i = 0; i < books[index].length(); i++) {
            total[books[index].charAt(i) - 'A']++;
        }
        // 현재 책을 선택함
        combination(index + 1, sum + price[index]);
        for (int i = 0; i < books[index].length(); i++) {
            total[books[index].charAt(i) - 'A']--;
        }
        // 현재 책을 선택하지 않음
        combination(index + 1, sum);

    }

    public static boolean check() {
        for (int i = 0; i < 26; i++) {
            if (word[i] > total[i]) {
                return false;
            }
        }
        return true;
    }
}

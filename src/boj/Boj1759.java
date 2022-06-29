package boj;

import java.io.*;
import java.util.*;

/*
    1. 각 수에서 모음의 수, 자음의 수를 구한다.
    2. 모음의 수가 최소 1개부터 최대의 수까지 뽑았을때 나올 수 있는 조합을 구함
    3. 자음의 수가 최소 2개부터 최대의 수까지 뽑았을때 나올 수 있는 조합을 구함
    4. 위 2개의 조합된 목록을 다시 2중for를 돌며 합치면서 모음 + 자음의 모든 조합을
       생성할때 길이수가 맞는 조합만 생성
*/
class Boj1759 {
    static char[] alpha;

    static StringBuilder vowelCombi = new StringBuilder();
    static StringBuilder consonantCombi = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int L = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        alpha = new char[C];

        st = new StringTokenizer(br.readLine());
        StringBuilder vowelSb = new StringBuilder();
        StringBuilder consonantSb = new StringBuilder();

        // 1. 각 수에서 모음의 수, 자음의 수 구함
        for (int i = 0; i < C; i++) {
            char ch = st.nextToken().charAt(0);
            if (isVowel(ch)) {
                vowelSb.append(ch);
            } else {
                consonantSb.append(ch);
            }
        }

        // 2. 모음 조합 생성
        char[] chars = vowelSb.toString().toCharArray();
        for (int i = 1; i <= vowelSb.length(); i++) {
            combination(vowelCombi, new boolean[chars.length], chars, 0, i);
        }

        // 3. 자음 조합 생성
        chars = consonantSb.toString().toCharArray();
        for (int i = 2; i <= consonantSb.length(); i++) {
            combination(consonantCombi, new boolean[chars.length], chars, 0, i);
        }

        // 두 조합을 합치며 만드는 모든 조합의 합의 수에서 길이가 L인 암호만 선택
        String[] vowelArr = vowelCombi.toString().split(" ");
        String[] consonantArr = consonantCombi.toString().split(" ");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < vowelArr.length; i++) {
            for (int j = 0; j < consonantArr.length; j++) {
                if (vowelArr[i].length() + consonantArr[j].length() == L) {
                    String password = vowelArr[i] + consonantArr[j];
                    // 패스워드의 알파벳도 오름차순으로 정렬해야함
                    char[] temp = password.toCharArray();
                    Arrays.sort(temp);
                    password = new String(temp);
                    sb.append(password).append(" ");
                }
            }
        }

        // 각 패스워드 배열도 오름차순 정렬되어있어야 한다.
        String[] results = sb.toString().split(" ");
        Arrays.sort(results);

        // 출력
        for (String result : results) {
            bw.write(result + "\n");
        }
        bw.flush();
        bw.close();
    }

    // 모음이 맞는지 판단
    public static boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }

    // 조합 생성
    public static void combination(StringBuilder appendSb, boolean[] visited, char[] arr, int depth, int r) {
        if (r == 0) {
            append(appendSb, arr, visited);
            return;
        }

        for (int i = depth; i < arr.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                combination(appendSb, visited, arr, i + 1, r - 1);
                visited[i] = false;
            }
        }
    }

    // StringBuilder에 조합의 결과를 붙여줌
    public static void append(StringBuilder sb, char[] arr, boolean[] visited) {
        for (int i = 0; i < arr.length; i++) {
            if (visited[i]) {
                sb.append(arr[i]);
            }
        }
        sb.append(" ");
    }
}
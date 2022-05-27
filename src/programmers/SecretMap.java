package programmers;

class SecretMap {
    public static String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        long[] code = new long[n];

        for (int i = 0; i < n; i++) {
            code[i] = getBinary(arr1[i] | arr2[i], n);
        }
        for (int i = 0; i < n; i++) {
            answer[i] = getDecode(code[i], n);
        }

        return answer;
    }

    public static long getBinary(long num, int n) {
        StringBuilder sb = new StringBuilder();
        while (n-- > 0) {
            sb.append(num % 2);
            num /= 2;
        }
        return Long.parseLong(sb.reverse().toString());
    }

    public static String getDecode(long lineOfMap, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            long num = lineOfMap % 10;
            lineOfMap /= 10;
            if (num > 0) {
                sb.append("#");
            } else {
                sb.append(" ");
            }
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        int[] n = {5, 6};
        int[][] arr1 = {{9, 20, 28, 18, 11}, {46, 33, 33, 22, 31, 50}};
        int[][] arr2 = {{30, 1, 21, 17, 28}, {27, 56, 19, 14, 14, 10}};
        int flag = 0;
        String[][] results = {{"#####", "# # #", "### #", "#  ##", "#####"}, {"######", "###  #", "##  ##", " #### ", " #####", "### # "}};
        for (int i = 0; i < n.length; i++) {
            String[] result = solution(n[i], arr1[i], arr2[i]);
            for (int j = 0; j < results[i].length; j++) {
                if (results[i][j].equals(result[j])) {
                        flag++;
                }
            }
            if (flag == n[i]) {
                System.out.println("Test " + (i + 1) + " is passed");
            } else {
                System.out.println("Test " + (i + 1) + " is failed");
            }
            flag = 0;
        }
    }
}
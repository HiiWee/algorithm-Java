package programmers;

import java.util.*;

public class Tuple {
    public static Object[] solution(String s) {
        String[] arr = s.replaceAll("\\}\\}", "").replaceAll("\\{\\{", "").split("\\},\\{");
        Arrays.sort(arr, (o1, o2) -> {
            return o1.length() - o2.length();
        });

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            for (String str : arr[i].split(",")) {
                int num = Integer.parseInt(str);
                if (!list.contains(num)) {
                    list.add(num);
                }
            }
        }
        return list.toArray();
    }

    public static void main(String[] args) {
        String[] s = {"{{2},{2,1},{2,1,3},{2,1,3,4}}",
                "{{1,2,3},{2,1},{1,2,4,3},{2}}",
                "{{20,111},{111}}",
                "{{123}}",
                "{{4,2,3},{3},{2,3,4,1},{2,3}}"};
        int[][] result  = {{2, 1, 3, 4}, {2, 1, 3, 4}, {111, 20}, {123}, {3, 2, 4, 1}};

        for (int i = 0; i < s.length; i++) {
            Object[] answer = solution(s[i]);
            for (int j = 0; j < result[i].length; j++) {
                if (result[i][j] == (int)answer[j]) {
                    System.out.println("test pass");
                } else {
                    System.out.println("test fail");
                }
            }
        }
    }
}
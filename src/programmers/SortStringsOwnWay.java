package programmers;
/*
 문자열을 사전에 오름차순으로 선 정렬후
 key : order, value : index담은 리스트(같은 문자일수도있으므로) TreeMap에 담음
 TreeMap은 key를 오름차순으로 정렬해서 담으므로
 StringBuilder에 정렬된 키를 넣어 list의 요소들을 하나씩 옮겨담으면 된다.
*/
import java.util.*;

class SortStringsOwnWay {
    public static String[] solution(String[] strings, int n) {
        Arrays.sort(strings);

        Map<Integer, List<Integer>> map = new TreeMap<>();
        for (int i = 0; i < strings.length; i++) {
            List<Integer> list = map.get(strings[i].charAt(n) - 'a');
            if (map.containsKey(strings[i].charAt(n) - 'a')) {
                list = map.get(strings[i].charAt(n) - 'a');
                list.add(i);
                map.put(strings[i].charAt(n) - 'a', list);
            } else {
                list = new ArrayList<>();
                list.add(i);
                map.put(strings[i].charAt(n) - 'a', list);
            }
        }

        StringBuilder sb = new StringBuilder();

        for (Integer key : map.keySet()) {
            List<Integer> list = map.get(key);
            for (int i = 0; i < list.size(); i++) {
                sb.append(strings[list.get(i)]).append(" ");
            }
        }

        return sb.toString().split(" ");
    }

    public static void main(String[] args) {
        String[][] strings = {{"sun","bed", "car"}, {"abce", "abcd", "cdx"}};
        String[][] results = {{"car", "bed", "sun"}, {"abcd", "abce", "cdx"}};
        int[] n = {1, 2};

        for (int i = 0; i < strings.length; i++) {
            if (Arrays.equals(solution(strings[i], n[i]), results[i])) {
                System.out.println("test case " + (i + 1) + " is pass");
            }
        }
    }
}
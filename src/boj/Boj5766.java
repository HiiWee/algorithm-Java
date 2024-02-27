package boj;

// Map 사용
import java.io.*;
import java.util.*;

class Boj5766_1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder answer = new StringBuilder();
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            if (n == 0) {
                break;
            }

            Map<Integer, Integer> count = new HashMap<>();
            while (n-- > 0) {
                st = new StringTokenizer(br.readLine());
                for (int i = 0; i < m; i++) {
                    int player = Integer.parseInt(st.nextToken());
                    count.put(player, count.getOrDefault(player, 0) + 1);
                }
            }
            ArrayList<Integer> sortedKyes = new ArrayList<>(count.keySet());
            sortedKyes.sort((k1, k2) -> count.get(k2) - count.get(k1));

            int secondCount = getSecondCount(count, sortedKyes);
            List<Integer> secondPlayers = findSortedSecondPlayers(secondCount, count, sortedKyes);

            for (int player : secondPlayers) {
                answer.append(player).append(" ");
            }
            answer.append("\n");
        }

        bw.write(answer.toString());
        bw.flush();
        bw.close();
    }

    private static List<Integer> findSortedSecondPlayers(int secondCount, Map<Integer, Integer> count, List<Integer> sortedKeys) {
        List<Integer> secondPlayers = new ArrayList<>();

        for (int key : sortedKeys) {
            if (count.get(key) == secondCount) {
                secondPlayers.add(key);
            }
        }
        secondPlayers.sort(Comparator.naturalOrder());
        return secondPlayers;
    }

    public static int getSecondCount(Map<Integer, Integer> count, List<Integer> sortedKeys) {
        int secondCount = 0;
        for (int key : sortedKeys) {
            if (count.get(key) != count.get(sortedKeys.get(0))) {
                secondCount = count.get(key);
                break;
            }
        }

        return secondCount;
    }
}

// 배열 사용
class Boj5766_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder answer = new StringBuilder();
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            if (n == 0) {
                break;
            }

            int[] counts = new int[10001];
            while (n-- > 0) {
                st = new StringTokenizer(br.readLine());
                for (int i = 0; i < m; i++) {
                    counts[Integer.parseInt(st.nextToken())]++;
                }
            }
            int firstCount = 0;
            for (int count : counts) {
                firstCount = Math.max(firstCount, count);
            }

            int secondCount = 0;
            for (int count : counts) {
                if (count != firstCount) {
                    secondCount = Math.max(secondCount, count);
                }
            }
            List<Integer> secondPlayers = new ArrayList<>();
            for (int i = 1; i <= 10000; i++) {
                if (secondCount == counts[i]) {
                    secondPlayers.add(i);
                }
            }
            secondPlayers.sort(Comparator.naturalOrder());

            for (int player : secondPlayers) {
                answer.append(player).append(" ");
            }
            answer.append("\n");
        }

        bw.write(answer.toString());
        bw.flush();
        bw.close();
    }
}
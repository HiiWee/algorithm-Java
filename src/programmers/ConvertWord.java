package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class ConvertWord {

    // 단어를 쭉 깊숙히 방문 체크를 하며 파고 들다가, target 단어로 변환할 수 있으면 return을 한다.
    // 만약 할 수 없다면 방문한 단어에 대해 체크를 해제하고, 다음 단어를 찾아서 반복한다.

    // 풀 수 있는 방법은 백트래킹과, BFS가 될 것 같다.
    // 일반적인 DFS로 탐색을하게되면 최단거리로 탐색되지 않을 수 있으므로, 모든 지점을 방문하고 비교할 수 있는 백트래킹
    // 그리고 BFS는 먼저 도착하는 경우가 최단 경로이기 때문에 가능하다.

    private boolean[] visited;
    private int count = Integer.MAX_VALUE;

    static class Word {
        String word;
        int count;

        Word(String word, int count) {
            this.word = word;
            this.count = count;
        }
    }

    public int solution(String begin, String target, String[] words) {
        visited = new boolean[words.length];

        // backtracking(begin, target, words, 0);
        count = doBfs(begin, target, words);

        if (count == Integer.MAX_VALUE) {
            return 0;
        }
        return count;
    }

    public int doBfs(String startWord, String target, String[] words) {
        Queue<Word> que = new LinkedList<>();
        que.add(new Word(startWord, 0));

        while (!que.isEmpty()) {
            Word currentWord = que.poll();

            if (currentWord.word.equals(target)) {
                return currentWord.count;
            }

            for (int i = 0; i < words.length; i++) {

                if (!visited[i] && isPossible(words[i], currentWord.word)) {
                    que.add(new Word(words[i], currentWord.count + 1));
                    visited[i] = true;
                }
            }
        }
        return 0;
    }

    public void backtracking(String currentWord, String target, String[] words, int sum) {
        if (currentWord.equals(target)) {
            count = Math.min(count, sum);
            return;
        }

        for (int i = 0; i < words.length; i++) {
            if (!visited[i] && !currentWord.equals(words[i]) && isPossible(words[i], currentWord)) {
                visited[i] = true;
                backtracking(words[i], target, words, sum + 1);
                visited[i] = false;
            }
        }
    }

    public boolean isPossible(String nextWord, String currentWord) {
        int count = 0;
        for (int i = 0; i < nextWord.length(); i++) {
            char nextChar = nextWord.charAt(i);
            char currentChar = currentWord.charAt(i);
            if (nextChar != currentChar) {
                count++;
            }
        }
        return count == 1;
    }
}

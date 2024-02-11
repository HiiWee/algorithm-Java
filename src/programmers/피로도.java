package programmers;

class 피로도 {

    int[][] dungeons;
    int answer;

    public int solution(int k, int[][] dungeons) {
        this.dungeons = dungeons;

        search(0, k, new boolean[dungeons.length]);
        return answer;
    }

    public void search(int count, int hp, boolean[] visited) {
        answer = Math.max(count, answer);

        for (int i = 0; i < visited.length; i++) {
            if (!visited[i] && dungeons[i][0] <= hp) {
                visited[i] = true;
                search(count + 1, hp - dungeons[i][1], visited);
                visited[i] = false;
            }
        }
    }
}
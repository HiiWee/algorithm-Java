package boj;

import java.util.*;
import java.io.*;

class Boj2412 {
    static class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n, t;
    static List<Integer>[] list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        list = new ArrayList[t + 1];
        for (int i = 0; i <= t; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list[y].add(x);
        }
        // x 좌표를 오름차순 정렬
        for (int i = 0; i <= t; i++) {
            Collections.sort(list[i]);
        }

        bw.write(BFS() + "\n");
        bw.flush();
        bw.close();

    }

    public static int BFS() {
        Queue<Pos> que = new LinkedList<>();
        que.add(new Pos(0, 0));
        int move = 0;
        while (!que.isEmpty()) {
            int size = que.size();

            // 한 번 이동할때의 가능한 좌표들을 모두 담은 사이즈만큼 반복해야 move가 정상적으로 카운팅됨
            for (int i = 0; i < size; i++) {
                Pos p = que.poll();
                if (p.y == t) {
                    return move;
                }
                // +-2만큼 이동할 수 있으므로 해당 y좌표를 탐색
                for (int y = p.y - 2; y <= p.y + 2; y++) {
                    // 범위를 벗어나면 패스
                    if (y < 0 || y > t) {
                        continue;
                    }
                    // list[y]에 저장된 x좌표를 하나씩 돌며 +-2의 범위를 벗어나지 않는지 판별
                    for (int j = 0; j < list[y].size(); j++) {
                        int x = list[y].get(j);
                        // x의 좌표들을 오름차순 정렬했으므로 다음 점의 x좌표가 더 크면 종료
                        if (p.x + 2 < x) {
                            break;
                            // 다음점의 x좌표가 현재 점의 -2 보다 작다면 다음 수의 상태는 미확정이므로
                            // 다음수로 즉시 진행한다.
                        } else if (p.x - 2 > x) {
                            continue;
                        }
                        // 탐색한 값은 바로 지워줌, j도 1내려줘야 x좌표를 순차적으로 모두 탐방
                        list[y].remove(j);
                        que.add(new Pos(x, y));
                        j--;
                    }
                }
            }
            // 한번의 이동가능한 경우 모두 돌았으므로 move 증가
            move++;
        }
        return -1;
    }
}
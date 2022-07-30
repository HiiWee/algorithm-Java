package boj;

/*
    전체 사람 배열을 만들고 진실을 알고 있는 사람을 체크하여 담는다.
    (이때 큐에 진실을 알고 있는 사람을 담아놓는다.)
    이후 각 파티를 담을 인접리스트를 만들고 사람들을 각 파티에 모두 담는다.

    위에서 저장한 큐를 가지고 BFS를 돈다.
    파티를 이미 방문했거나, 진실을 알고있는 사람이 파티에 없다면 패스한다.
    진실을 알고있는 사람이 있는 파티가 있다면 파티 내부를 돌며 해당 사람들에게 진실을 알려주고 큐에 담는다.
    각 파티의 순회를 완료하면 전체 경우의 수에서 1을 빼준다(파티를 순회했다는 의미는 진실을 알고있는 파티이므로)
*/

import java.util.*;
import java.io.*;

class Boj1043 {
    static List<Integer>[] party;
    static Queue<Integer> que = new LinkedList<>();
    static boolean[] checkParty;
    static boolean[] checkPeople;
    static int n, m;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        party = new ArrayList[m];
        checkPeople = new boolean[n + 1];
        checkParty = new boolean[m];

        st = new StringTokenizer(br.readLine());
        int countTrue = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= countTrue; i++) {
            int num = Integer.parseInt(st.nextToken());
            que.add(num);
            checkPeople[num] = true;
        }

        for (int i = 0; i < m; i++) {
            party[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            int partyCount = Integer.parseInt(st.nextToken());

            for (int j = 0; j < partyCount; j++) {
                int num = Integer.parseInt(st.nextToken());
                party[i].add(num);
            }
        }

        bw.write(BFS(m) + "\n");
        bw.flush();
        bw.close();
    }

    public static int BFS(int count) {
        while (!que.isEmpty()) {
            int current = que.poll();
            for (int i = 0; i < m; i++) {
                if (checkParty[i] || !party[i].contains(current)) {
                    continue;
                }

                for (int j = 0; j < party[i].size(); j++) {
                    int people = party[i].get(j);
                    if (checkPeople[people]) {
                        continue;
                    }
                    que.add(people);
                    checkPeople[people] = true;
                }
                checkParty[i] = true;
                count--;
            }
        }
        return count;
    }
}



package boj;

import java.util.*;
import java.io.*;


/**
 * 두 개의 우선순위 큐를 이용해 최대힙, 최소힙을 구성한다. 따라서 I로 들어오는 숫자를 최대힙, 최소힙에 모두 삽입하고 map을 이용해 카운트 해준다.
 * 각 숫자를 하나씩 뺄때마다 만약 최대힙에서 뺀 숫자가 최소힙에서 이미 뽑은 숫자라면 의미가 없어지는 수가 되므로
 * 전체 어떤 수들이 몇개 들어왔는지 확인하는 작업을 위해 HashMap을 통해 각 수들을 카운트하여 중복을 제거하는 과정이 필요하다.
 *
 * 만약 D -1 입력으로 최소힙에서 요소를 하나 빼야하는데 map에 해당 요소의 카운트가 0이라면 이미 존재하지 않는수가 되므로
 * 해당작업 이전에 while문을 통해 뽑은 수의 map 카운트가 0인수들은 제외하고, 0을 넘는 값을 만나면 해당 값을 빼주어야 한다. 이후 map의 카운트도 -1 해준다.
 *
 * 모든 작업이 완료되고 최종적으로 map에 있는 요소들이 모두 0의 값을 가지고 있다면 EMPTY를 출력하고
 * 그렇지 않다면 동일하게 while문을 통해 map 카운트가 0이 아닌 수를 꺼내어 최대, 최소를 입력한다.
 */
class Boj7662_1 {
    static int t;
    static Map<Integer, Integer> map;
    static PriorityQueue<Integer> minQue;
    static PriorityQueue<Integer> maxQue;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int k = Integer.parseInt(br.readLine());
            map = new HashMap<>();
            minQue = new PriorityQueue<>();
            maxQue = new PriorityQueue<>(Comparator.reverseOrder());
            while (k-- > 0) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String token = st.nextToken();
                int num = Integer.parseInt(st.nextToken());

                if (token.equals("I")) {
                    maxQue.offer(num);
                    minQue.offer(num);
                    map.put(num, map.getOrDefault(num, 0) + 1);
                } else {
                    if (num == 1) {
                        remove(maxQue);
                        if (!maxQue.isEmpty()) {
                            map.put(maxQue.peek(), map.get(maxQue.peek()) - 1);
                            maxQue.poll();
                        }
                    } else if (num == -1) {
                        remove(minQue);
                        if (!minQue.isEmpty()) {
                            map.put(minQue.peek(), map.get(minQue.peek()) - 1);
                            minQue.poll();
                        }
                    }

                }
            }
            boolean flag = true;
            for (int key : map.keySet()) {
                if (map.get(key) > 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                sb.append("EMPTY\n");
            } else {
                remove(maxQue);
                remove(minQue);
                sb.append(maxQue.peek()).append(" ").append(minQue.peek()).append("\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void remove(PriorityQueue<Integer> que) {
        while (!que.isEmpty() && map.get(que.peek()) == 0) {
            que.poll();
        }
    }
}


/**
 * map의 카운트가 0이되면 key 자체를 제거하는 작업을 추가하여 조금 더 효율적인 방식이다.
 * 여기서 핵심은 map과 우선순위 큐를 이용해 이미 제거된 수는 고려하지 않게 하는 remove 메서드가 핵심이다. I 입력은 동일하지만
 * D가 들어오면 우선 map이 비어있는지 확인한다, map이 비어있다면 더이상 특정 힙에서 뺄 수 있는수가 없다는 의미이기 때문이다.
 *
 * map이 비어있지 않다면 두번째 토큰인 1, -1을 구분해 최대 최소힙을 찾음
 * remove() 메서드는 힙에서 뽑은 숫자가 map에 존재하는지 판단하고, 있다면 해당 수의 카운트를 -1빼준다.
 * 만약 카운트의 값이 0이된다면 해당 요소를 map에서 제거하여 최신화 시켜준다.
 */
class Boj7662_2 {
    static int t;
    static Map<Integer, Integer> map;
    static PriorityQueue<Integer> minQue;
    static PriorityQueue<Integer> maxQue;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int k = Integer.parseInt(br.readLine());
            map = new HashMap<>();
            minQue = new PriorityQueue<>();
            maxQue = new PriorityQueue<>(Comparator.reverseOrder());
            while (k-- > 0) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String token = st.nextToken();
                int num = Integer.parseInt(st.nextToken());

                if (token.equals("I")) {
                    maxQue.offer(num);
                    minQue.offer(num);
                    map.put(num, map.getOrDefault(num, 0) + 1);
                } else {
                    if (map.isEmpty()) {
                        continue;
                    }
                    if (num == 1) {
                        remove(maxQue);
                    } else {
                        remove(minQue);
                    }

                }
            }
            if (map.isEmpty()) {
                sb.append("EMPTY\n");
            } else {
                int num = remove(maxQue);
                sb.append(num).append(" ");
                if (!map.isEmpty()) {
                    num = remove(minQue);
                }
                sb.append(num).append("\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static int remove(PriorityQueue<Integer> que) {
        int num;

        while (true) {
            num = que.poll();

            int count = map.getOrDefault(num, 0);
            if (count == 0) {
                continue;
            }
            if (count == 1) {
                map.remove(num);
            } else {
                map.replace(num, count - 1);
            }
            break;
        }
        return num;
    }
}
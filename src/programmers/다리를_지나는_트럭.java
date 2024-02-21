package programmers;

import java.util.*;

class 다리를_지나는_트럭 {

    static class Truck {
        int weight, time;

        public Truck(int weight, int time) {
            this.weight = weight;
            this.time = time;
        }

        @Override
        public String toString() {
            return "{weight=" + weight + ", time=" + time + "}";
        }
    }

    public int solution(int length, int weight, int[] trucks) {
        int answer = 0;
        int sum = 0;
        int truckIndex = 0;

        Queue<Truck> que = new LinkedList<>();

        while (true) {
            // 시간을 검증함
            if (!que.isEmpty() && answer - que.peek().time == length) {
                Truck out = que.poll();
                sum -= out.weight;

                if (que.isEmpty() && truckIndex == trucks.length) {
                    break;
                }
            }

            // 다리에 올라가도 차량 대수를 넘지 않고, 무게도 넘지 않는다면 큐에 추가함 (단 모든 트럭을 올렸으면 추가하지 않음)
            if (truckIndex < trucks.length && que.size() < length && sum + trucks[truckIndex] <= weight) {
                sum += trucks[truckIndex];
                que.offer(new Truck(trucks[truckIndex++], answer));
            }
            answer++;
        }
        return answer + 1;
    }
}
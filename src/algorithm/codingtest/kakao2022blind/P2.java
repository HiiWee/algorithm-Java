package algorithm.codingtest.kakao2022blind;

/*
    끝을 기준으로 풀어보자
    - 배달 한다는 것은 어차피 가서 놓고 오기 때문에 수거와는 상관이 없다?
    - 맨 뒤에 집부터 앞으로 탐색하며 배달할 물품을 트럭에 싣고, 트럭이 전부 가득차면 중단

    - 만약 배달해야할 맨 뒤에 집보다 뒤쪽에 수거할 물품이 있다면 해당 수거물품 위치까지 이동하며
      배달할것 내려주고, 수거해온다.
    - 만약 배달할 집이 수거할 집보다 뒤에 있다면 배달집 기준으로 모든 물품을 내려주고, 수거할것들을
      차례로 담아 온다.
*/

class Solution2 {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        int count = 2*n;
        // 맨 뒤에 집부터 앞으로 탐색하여 배달할 물품을 트럭에 싣고, 트럭이 가득차면 중단
        while (count-- > 0) {
            int toDelivery = 0;
            int toPick = 0;
            int indexDelivery = -1;
            int indexPick = -1;

            for (int i = n - 1; i >= 0; i--) {
                if (deliveries[i] > 0) {
                    // 처음 발견한 위치 저장
                    if (indexDelivery == -1) {
                        indexDelivery = i;
                    }
                    // 끝에서부터 담아오면서 담을 수 있는건 전부 담는다.
                    if (deliveries[i] + toDelivery <= cap) {
                        toDelivery += deliveries[i];
                        // 담은 것들은 0 처리
                        deliveries[i] = 0;
                    }

                }
                if (pickups[i] > 0) {
                    // 처음 발견한 위치 저장
                    if (indexPick == -1) {
                        indexPick = i;
                    }
                    // 끝에서부터 담아오면서 담을 수 있는건 전부 담는다.
                    if (pickups[i] + toPick <= cap) {
                        toPick += pickups[i];
                        // 담은 것들은 0 처리
                        pickups[i] = 0;
                    }
                }
            }
            // System.out.println(indexDelivery + " " + indexPick);
            if (indexPick < indexDelivery) {
                answer += (indexDelivery + 1) * 2;
                n = indexDelivery + 1;
            } else {
                answer += (indexPick + 1) * 2;
                n = indexPick + 1;
            }

            boolean flag = true;
            for (int i = 0; i < n; i++) {
                if (deliveries[i] > 0 || pickups[i] > 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                break;
            }
        }

        return answer;
    }
}
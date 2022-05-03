package programmers;

/*
    moves의 길이만큼을 돌며 행을 탐색하고 값이 존재하면
    임시변수에 담고 기존 2차배열값은 0으로 변경
    이후 바구니에 옮기기전 같은 요소면 제거, 아니라면 위에 쌓음
*/

import java.util.*;

public class ClawMachineGame {
    public static int solution(int[][] board, int[] moves) {
        int answer = 0;

        List<Integer> basket = new ArrayList<>();

        for (int i = 0; i < moves.length; i++) {
            for (int j = 0; j < board.length; j++) {
                int pick = board[j][moves[i] - 1];
                if (pick > 0) {
                    board[j][moves[i] - 1] = 0;
                    if (basket.size() == 0) {
                        basket.add(pick);
                    } else if (basket.get(basket.size() - 1) == pick) {
                        basket.remove(basket.size() - 1);
                        answer += 2;
                    } else {
                        basket.add(pick);
                    }
                    break;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int[][] board = {{0, 0, 0, 0, 0}, {0, 0, 1, 0, 3}, {0, 2, 5, 0, 1}, {4, 2, 4, 4, 2}, {3, 5, 1, 3, 1}};
        int[] moves = {1, 5, 3, 5, 1, 2, 1, 4};
        int result = 4;

        if (solution(board, moves) == result) {
            System.out.println("test pass");
        }
    }

}
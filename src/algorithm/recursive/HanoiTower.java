package algorithm.recursive;

public class HanoiTower {
    static class Tower {
        public Tower() {
            System.out.println("하노이탑 생성");
        }
        public void solveHanoi(int N, String start, String to, String via) {
            if (N == 1) {
                move(1, start, to);
            }
            else {
                solveHanoi(N - 1, start, via, to);
                move(N, start, to);
                solveHanoi(N - 1, via, to, start);
            }

        }

        public void move(int N, String start, String to) {
            System.out.println(N + "번 원반을 " + start + "에서 " + to + "로 옮겼습니다.");
        }

    }
    public static void main(String[] args) {
        Tower hanoi = new Tower();
        hanoi.solveHanoi(3, "A", "C", "B");

    }
}

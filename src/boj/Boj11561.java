package boj;

/*
    징검다리를 최대의 횟수로 건너기 위해선 한번에 최소의 움직임으로 움직여야 한다.

    즉, 다음과 같이 움직이게 된다. 1칸 -> 2칸 -> 3칸 -> 4칸 ...
    ex) 만약 돌다리가 3개라면 1번째 돌다리(1칸), 3번째 돌다리(2칸)를 밟을 수 있다.
        결국 이동한 칸의 합 == 마지막에 밟게되는 돌이라는 의미가 된다.

    위의 규칙은 결국 차이가 1인 등차수열의 합이 마지막에 밟게되는 돌이라는 의미이므로 다음과 같이 식을 도출할 수 있다.
    k * (a + l) / 2 (k = 이동횟수, a = 첫 번째항, l = 마지막 항(공차가 1이므로 이동횟수와 동일)) -> k *(1 + k) / 2

    만약 n 값이 5이고, 3번 이동이 최대일것이라 생각해보면
    3 * (3 + 1) / 2 = 6, 마지막에 밟게 되는 돌이 6이라는 의미이므로 틀렸으므로 2로 정정
    2 * (2 + 1) / 2 = 2, 마지막에 밟게 되는 돌이 2라는 의미가 됨
    여기서 생각해볼것은 어차피 3번 밟으려 시도하면 최소로 이동해도 6번째 돌을 반드시 밟아야 하는것이다.
    하지만, 돌은 5개이므로 2번밟을 수 밖에 없다. 즉, 1번째 돌을 밟고 바로 5번째 돌을 밟아야 한다.(점프 길이의 제한은 이전 점프보다 커야한다는것 외에 제한이 없어서 가능)
    따라서 이동횟수를 key로 두고 이분탐색을 진행할 수 있다.

    위에 예시에서 봤듯 5개의 돌을 밟으려면 2번 이동해야 한다. 하지만 2의 등차수열의 값은 3이 되므로
    lower_bound를 이용하면 문제가 될 수 있다. lower_bound는 해당 값을 도출할 수 없다면 해당 값 보다 큰 값에서 종료하게 되고
    (n = 5인 경우 등차수열의 합은 5를 만들 수 없으므로 다음 큰 값인 3 반환, 실제 값은 2)

    해당 값을 도출할 수 있다면 해당 값이 처음 나타나는 위치를 반환하게 된다
    (n = 6인 경우 1칸 2칸 3칸을 움직이면 6번째 돌을 밟으므로 해당 위치인 3반환, 실제 값도 3)

    따라서 일관성이 없어지므로
    upper_bound를 이용해 (가능한 이동횟수 + 1)로 일관적으로 반환받아 출력할때 -1을 하여 출력한다.

    또한 low, high에서 high는 10^16값을 이용해도 되지만 등차수열의 합 공식과 이용하면 근의 공식을 이용해 최대값을 구할 수 있다.

    최소는 1일 것이고 최대는 n값이 1e16이 주어졌을때의 값이다. (n*(n + 1) / 2 = 1e16)
    n^2 + n -2*1e16 = 0 -> 근의 공식 이용

*/

import java.io.*;

class Boj11561 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {

            long n = Long.parseLong(br.readLine());
            long low = 1L;
            long high = (long) ((-1 + Math.sqrt(1 - (4 * -2 * 1e16))) / 2) + 1;

            while (low < high) {

                long mid = (low + high) / 2;
                System.out.println(low + " " + high + " " + mid);
                if (n <= mid * (mid + 1) / 2) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }

            bw.write(low + "\n");
        }
        bw.flush();
        bw.close();
    }
}
package boj;

import java.io.*;
import java.util.*;

class Boj1244 {
    private static final int MALE = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] switches = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            switches[i] = Integer.parseInt(st.nextToken());
        }
        int m = Integer.parseInt(br.readLine());

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int gender = Integer.parseInt(st.nextToken());
            int number = Integer.parseInt(st.nextToken());

            if (gender == MALE) {
                turnSwitchByMale(number, switches);
            } else {
                turnSwitchByFemale(number, switches);
            }
        }

        for (int i = 1; i <= n; i++) {
            if (i % 20 == 0) {
                bw.write(switches[i] + "\n");
            } else {
                bw.write(switches[i] + " ");
            }
        }

        bw.flush();
        bw.close();
    }

    public static void turnSwitchByMale(int number, int[] switches) {
        for (int i = number; i < switches.length; i += number) {
            switches[i] = 1 - switches[i];
        }
    }

    public static void turnSwitchByFemale(int number, int[] switches) {
        switches[number] = 1 - switches[number];
        for (int i = 1; i < switches.length / 2; i++) {
            int leftNumber = number - i;
            int rightNumber = number + i;

            if (leftNumber < 1 || rightNumber >= switches.length) {
                break;
            }
            if (switches[leftNumber] == switches[rightNumber]) {
                switches[leftNumber] = 1 - switches[leftNumber];
                switches[rightNumber] = 1 - switches[rightNumber];
            } else {
                break;
            }
        }
    }
}

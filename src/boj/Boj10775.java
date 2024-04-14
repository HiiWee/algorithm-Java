package boj;

import java.io.*;

class Boj10775 {

    static int g, p;
    static int[] gates;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        g = Integer.parseInt(br.readLine());
        p = Integer.parseInt(br.readLine());

        gates = new int[g + 1];
        for (int i = 1; i <= g; i++) {
            gates[i] = i;
        }

        int count = 0;
        while (p-- > 0) {
            int plane = Integer.parseInt(br.readLine());
            int gate = find(plane);
            if (gate == 0) {
                break;
            }
            union(gate, gate - 1);
            count++;
        }

        bw.write(Integer.toString(count));
        bw.flush();
        bw.close();
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) {
            return;
        }
        if (a < b) {
            gates[b] = a;
        } else {
            gates[a] = b;
        }
    }

    public static int find(int a) {
        if (a == gates[a]) {
            return a;
        }

        return gates[a] = find(gates[a]);
    }
}

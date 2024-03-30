package boj;

import java.io.*;
import java.util.*;

class Boj1449 {

    static int n;
    static float l;
    static float[] places;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Float.parseFloat(st.nextToken());
        places = new float[n + 1];
        places[n] = 2000.0f;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            places[i] = Float.parseFloat(st.nextToken());
        }
        Arrays.sort(places);

        int count = 0;
        float attachPlace = places[0] - 0.5f;
        for (int i = 1; i < n + 1; i++) {
            if (places[i] + 0.5f - attachPlace > l) {
                count++;
                attachPlace = places[i] - 0.5f;
            }
        }

        bw.write(Integer.toString(count));
        bw.flush();
        bw.close();
    }
}

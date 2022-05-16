package boj;

import java.io.*;

// 자릿수의 순서를 맞춰가며 계산
class Boj1373_1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String binary = br.readLine();
        StringBuilder result = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        int length = binary.length() - 1;
        int count = 0;
        for (int i = length; i >= 0; i--) {
            sb.append(binary.charAt(i));
            count++;
            if (count % 3 == 0) {
                int octal = 0;
                for (int j = count - 3; j < count; j++) {
                    octal += (sb.charAt(j) - '0') * Math.pow(2, j % 3);
                }
                result.append(octal);
            } else if (i == 0) {
                int index = count - binary.length() % 3;
                int octal = 0;
                for (int j = index ; j < count; j++) {
                    octal += (sb.charAt(j) - '0') * Math.pow(2, j % 3);
                }
                result.append(octal);
            }
        }
        result.append("\n");

        bw.write(result.reverse().toString());
        bw.flush();
        bw.close();
    }
}

// 자릿수를 반대로 계산
class Boj1373_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String binary = br.readLine();
        StringBuilder sb = new StringBuilder();
        int length = binary.length();

        if (length % 3 == 1) {
            sb.append((binary.charAt(0) - '0'));
        } else if (length % 3 == 2) {
            sb.append((binary.charAt(0) - '0') * 2 + binary.charAt(1) - '0');
        }
        for (int i = length % 3; i < length; i += 3) {
            sb.append((binary.charAt(i) - '0') * 4 + (binary.charAt(i + 1) - '0') * 2 + binary.charAt(i + 2) - '0');
        }
        sb.append("\n");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
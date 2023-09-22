package boj;

import java.io.*;

class Boj22859 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String line = br.readLine();
        String body = line.substring(6, line.length() - 7);

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < body.length(); i++) {
            if (body.startsWith("<div title=\"")) {
                int index = body.indexOf('"', i + 12);
                result.append("title : ").append(body, i + 12, index).append("\n");
                i = index + 2;
                index = body.indexOf("</div>", i);

                // 문장 파싱
                String wholeParagraph = body.substring(i, index);

                String[] splits = wholeParagraph.split("<p>");
                for (String split : splits) {
                    if (!split.isEmpty()) {
                        result.append(removeTag(split)).append("\n");
                    }
                }

                i = index + 5;
            }
        }

        bw.write(result.toString());
        bw.flush();
        bw.close();
    }

    public static String removeTag(String paragraph) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < paragraph.length(); i++) {
            char c = paragraph.charAt(i);
            if (c == '<') {
                i = paragraph.indexOf(">", i);
                continue;
            }
            builder.append(c);
        }
        return builder.toString().trim().replaceAll(" +", " ");
    }
}
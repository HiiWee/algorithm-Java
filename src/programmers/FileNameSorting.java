package programmers;

/*
    영소 대소문자, 숫자, " ", ".", "-"

    파일은 HEAD, NUMBER, TAIL
        HEAD : 숫자가 아닌 문자, 한 글자 이상
        NUMBER : 1 ~ 5사이의 연속된 숫자, 0으로 시작 가능
        TAIL : 나머지 부분, 숫자 혹은 아무글자 없을수도 있음
*/

import java.util.Arrays;

public class FileNameSorting {
    public static String[] solution(String[] files) {
        String[] answer = new String[files.length];
        String[][] seperateFile = new String[files.length][3];
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < files.length; i++) {
            String file = files[i];
            int index = -1;

            // HEAD 탐색
            for (int j = 0; j < file.length(); j++) {
                char c = file.charAt(j);
                if (c >= 48 && c <= 57) {
                    index = j;
                    break;
                }
            }
            // HEAD 저장
            seperateFile[i][0] = file.substring(0, index);

            // NUMBER 탐색
            int count = 0;
            int start = index;
            for (int j = index; j < file.length(); j++) {
                char c = file.charAt(j);
                if (c < 48 || c > 57) {
                    index = j;
                    break;
                } else {
                    count++;
                    if (count == 5) {
                        index = j + 1;
                        break;
                    } else if (j == file.length() - 1) {
                        index = j + 1;
                        break;
                    }
                }
            }
            // NUMBER 저장
            seperateFile[i][1] = file.substring(start, index);

            // TAIL 저장
            seperateFile[i][2] = file.substring(index, file.length());

        }

        // 이름기준으로 정렬하고, 동일한 이름일 경우 숫자기준 정렬
        Arrays.sort(seperateFile, (o1, o2) -> {
            String line1 = o1[0].toLowerCase();
            String line2 = o2[0].toLowerCase();
            if (!line1.equals(line2)) {
                return line1.compareTo(line2);
            } else {
                int num1 = Integer.parseInt(o1[1]);
                int num2 = Integer.parseInt(o2[1]);

                return num1 - num2;
            }
        });

        // 정렬된 결과 파일 옮겨 닮기
        for (int i = 0; i < files.length; i++) {
            sb.append(seperateFile[i][0])
                    .append(seperateFile[i][1])
                    .append(seperateFile[i][2]);
            answer[i] = sb.toString();
            sb.setLength(0);
        }

        return answer;
    }

    public static void main(String[] args) {
        String[][] filesList = {{"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"},
                {"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"},
                {"img000012345", "img1.png", "img2", "IMG02"}};
        String[][] results = {{"img1.png", "IMG01.GIF", "img02.png", "img2.JPG", "img10.png", "img12.png"},
                {"A-10 Thunderbolt II", "B-50 Superfortress", "F-5 Freedom Fighter", "F-14 Tomcat"},
                {"img000012345", "img1.png", "img2", "IMG02"}};

        for (int i = 0; i < filesList.length; i++) {
            String[] solution = solution(filesList[i]);
            boolean flag = true;
            for (int j = 0; j < solution.length; j++) {
                if (!results[i][j].equals(solution[j])) {
                    flag = false;
                }
            }
            if (flag) {
                System.out.println("Test " + (i + 1) + " is passed");
            }
        }
    }
}
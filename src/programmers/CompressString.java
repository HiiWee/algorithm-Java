package programmers;

class CompressString {
    public static int solution(String s) {
        int answer = 0;
        StringBuilder newS = new StringBuilder();
        StringBuilder existS = new StringBuilder();
        int length = s.length();

        for (int i = 0; i < length; i++) {

            int size = i + 1;
            int begin = 0;
            int count = 1;

            existS.setLength(0);
            existS.append(s);

            for (int j = 0; j < length / size; j++) {
                // 문자열을 size만큼 잘라감
                String temp = existS.substring(begin, size);
                existS.delete(begin, size);
                // 반복문의 처음이라면 newS에 바로 붙임
                if (j == 0) {
                    newS.append(temp);
                } else {
                    int newSLength = newS.length();
                    // 자른 문자열과 저장된 문자열이 동일할 경우
                    if (newS.substring(newSLength - size, newSLength).equals(temp)) {
                        count++;
                        // count값이 2인경우는 압축 문자열에 압축횟수 없으므로 문자열의 길이만큼 잘라내고
                        // 압축횟수 + 문자열로 대치시킴
                        if (count == 2) {
                            newS.replace(newSLength - size, newSLength, String.valueOf(count) + temp);
                            // 압축횟수가 1자릿수일 경우를 고려해 size + 1를 뻄
                        } else if (count < 11) {
                            newS.replace(newSLength - (size + 1), newSLength, String.valueOf(count) + temp);
                            // 압축횟수가 2자릿수일 경우를 고려해 size + 2를 뺌
                        } else {
                            newS.replace(newSLength - (size + 2), newSLength, String.valueOf(count) + temp);
                        }
                        // 문자열이 동일하지 않다면 그냥 붙이고 count값 원복
                    } else {
                        newS.append(temp);
                        count = 1;
                    }
                }
                // size보다 작아서 자르지 못한 문자열 처리
                if (existS.length() < size) {
                    newS.append(existS);
                    // 문자열의 길이 추출
                    if (i == 0) {
                        answer = newS.length();
                    } else {
                        if (answer > newS.length()) {
                            answer = newS.length();
                        }
                    }
                }
            }
            newS.setLength(0);
        }
        return answer;
    }

    public static void main(String[] args) {
        String[] s = {"aabbaccc", "ababcdcdababcdcd", "abcabcdede", "abcabcabcabcdededededede", "xababcdcdababcdcd", "aaaaaaaaaaaabcd", "xxxxxxxxxxyyy"};
        int [] results = {7, 9, 8, 14, 17, 6, 5};

        for (int i = 0; i < s.length; i++) {
            int result = solution(s[i]);
            if (results[i] == result)  {
                System.out.printf("테스트 통과!! \nexpect : %d\nresult : %d\n\n", results[i], result);
            }
        }
    }
}

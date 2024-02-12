package programmers;

class 조이스틱 {
    public int solution(String name) {
        int answer = 0;
        int cursorCount = Integer.MAX_VALUE;
        for (int i = 0; i < name.length(); i++) {
            answer += Math.min(name.charAt(i) - 'A', 'Z' - name.charAt(i) + 1);

            int endA = i + 1;
            while (endA < name.length() && name.charAt(endA) == 'A') {
                endA++;
            }
            cursorCount = Math.min(cursorCount, i * 2 + name.length() - endA);
            cursorCount = Math.min(cursorCount, (name.length() - endA) * 2 + i);

        }
        return answer + cursorCount;
    }
}
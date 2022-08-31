package programmers;

    /*
    각 전화번호는 String 값이므로 우선 정렬하게 되면 ASCII값 우선으로 정렬되는것이 기본이다.
    이후 임의의 전화번호와 임의의 전화번호 다음 전화번호를 꺼내어 비교하는데
    길이가 짧은 전화번호의 길이를 기준으로 길이가 긴 전화번호를 해당 길이만큼 앞에서부터 잘라낸다.
    이후 길이가 짧은 전화번호와 잘라낸 전화번호를 비교했을때 같다면 짧은 전화번호가 긴 전화번호의 접두어인 경우이므로 false를
    위를 만족하는 전화번호가 하나도 없다면 true를 반환한다.
*/

import java.util.*;

public class PhoneNumberList {
    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book);
        for (int i = 0; i < phone_book.length - 1; i++) {
            if (phone_book[i].length() > phone_book[i + 1].length()) {
                if (phone_book[i + 1].equals(phone_book[i].substring(0, phone_book[i + 1].length()))) {
                    return false;
                }
            } else {
                if (phone_book[i].equals(phone_book[i + 1].substring(0, phone_book[i].length()))) {
                    return false;
                }
            }
        }
        return true;
    }

    static class PhoneNumberListMain {
        public static void main(String[] args) {
            String[][] phone_book_list = {{"119", "97674223", "1195524421"}, {"123","456","789"}, {"12","123","1235","567","88"}};
            Boolean[] corrects = {false, true, false};

            PhoneNumberList phoneNumberList = new PhoneNumberList();

            for (int i = 0; i < phone_book_list.length; i++) {
                boolean answer = phoneNumberList.solution(phone_book_list[i]);
                if (corrects[i] == answer) {
                    System.out.println("Test " + (i + 1) + " is passed");
                }
            }
        }
    }
}

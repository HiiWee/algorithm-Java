/**
 * 2022 KAKAO BLIND RECRUITMENT
 * 신고 결과 받기
 * */
package programmers;

/*
    한 번에 한명의 유저
    한 유저를 여러번 신고해도 1회 처리
    k번 이상 신고된 유저는 정지, 해당 유저 신고한 모든 유저에게 정지 사실 메일 발송 (메일발송)
    신고내용 취합해 게시판 이용 정지 및 대상자에게 메일 발송
*/
/*
    신고한 목록을 신고인과 피신고인으로 나누기 위해 2개의 배열 선언하여 split으로 나눠담기
    신고자를 key, 신고한 사람 목록을(list 타입) value로 두는 HashMap 생성
*/
import java.util.*;
class ReportResult {
    public static int[] solution(String[] id_list, String[] report, int k) {

        int idLength = id_list.length;
        int reportLength = report.length;

        int[] answer = new int[idLength];
        // key : 신고인, value : 피 신고인
        Map<String, List> reportMap = new HashMap<>();
        // key : 피 신고인, value : 신고당한 횟수
        Map<String, Integer> reportCnt = new HashMap<>();
        String reportList;

        // 2개의 HashMap의 초기화 작업
        for (int i = 0; i < idLength; i++) {
            reportMap.put(id_list[i], null);
            reportCnt.put(id_list[i], 0);
        }
        for (int i = 0; i < reportLength; i++) {
            // report 1개를 받아옴
            reportList = report[i];
            // 신고인과 피 신고인으로 나눔
            String[] sepReport = reportList.split(" ");

            // 이미 존재하는 list가 없으면 새로 만들고 아니면 기존 리스트에 피 신고자 명단을 추가
            if (reportMap.get(sepReport[0]) == null) {
                List<String> newList = new ArrayList<>();
                newList.add(sepReport[1]);
                reportMap.put(sepReport[0], newList);
            } else {
                List<String> existList = reportMap.get(sepReport[0]);
                // 중복이 있다면 넣지 않음
                if (!existList.contains(sepReport[1]))
                    existList.add(sepReport[1]);
            }
        }
        // System.out.println(reportMap);
        // 반복하면서 각 멤버들의 신고당한 횟수를 저장함
        for (int i = 0; i < idLength; i++) {
            List<String> list = reportMap.get(id_list[i]);
            if (list != null) {
                for (String respondent: list) {
                    int count = reportCnt.get(respondent);
                    count++;
                    reportCnt.put(respondent, count);
                }
            }
        }
        // System.out.println(reportCnt);
        for (int i = 0; i < idLength; i++) {
            //  특정 인물(id_list[i]가 신고인)이 신고당한 횟수가 k 이상이면
            if (reportCnt.get(id_list[i]) >= k) {
                for (int j = 0; j < idLength; j++) {
                    // 피 신고인의 명단을 처음부터 받아옴
                    List<String> list = reportMap.get(id_list[j]);
                    // 만약 정지사유가 되는 특정인물이 신고인(id_list[i])이 신고한 리스트에 들어있다면
                    // 신고인이 메일을 받아야 하므로 answer에서 해당 인덱스의 정수값 1 증가
                    if (list != null && list.contains(id_list[i])) {
                        answer[j]++;
                    }
                }
            }
        }
        return answer;
    }
    public static void main(String[] args) {
        String[][] id_list = {{"muzi", "frodo", "apeach", "neo"}, {"con", "ryan"}};
        String[][] report = {{"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"}, {"ryan con", "ryan con", "ryan con", "ryan con"}};
        for (int i = 0; i < 2; i++) {
            int[] answer = solution(id_list[i], report[i], 2);
            for (int num: answer)
                System.out.print(num + ", ");
            System.out.println();
        }
    }
}

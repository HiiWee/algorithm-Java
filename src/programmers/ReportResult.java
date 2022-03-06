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

        int[] answer = new int[id_list.length];
        Map<String, List> reportMap = new HashMap<>();
        Map<String, Integer> reportCnt = new HashMap<>();
        Map<String, String> reverseReport = new HashMap<>();
        String reportList;

        for (int i = 0; i < id_list.length; i++) {
            reportMap.put(id_list[i], null);
            reportCnt.put(id_list[i], 0);
            reverseReport.put(id_list[i], null);
        }
        for (int i = 0; i < report.length; i++) {
            // report 1개를 받아옴
            reportList = report[i];
            // 신고인과 피 신고인으로 나눔
            String[] sepReportList = reportList.split(" ");

            // 이미 존재하는 list가 없으면 새로 만들고 아니면 기존 리스트에 피 신고자 명단을 추가
            if (reportMap.get(sepReportList[0]) == null) {
                List<String> defendant = new ArrayList<>();
                defendant.add(sepReportList[1]);
                reportMap.put(sepReportList[0], defendant);
            } else {
                List<String> exist = reportMap.get(sepReportList[0]);
                // 중복이 있다면 넣지 않음
                if (!exist.contains(sepReportList[1]))
                    exist.add(sepReportList[1]);
            }
        }
        //System.out.println(reportMap);
        // 반복하면서 각 멤버들의 신고당한 횟수를 저장함
        for (int i = 0; i < id_list.length; i++) {
            List<String> list = reportMap.get(id_list[i]);
            if (list != null) {
                for (String name: list) {
                    int count = reportCnt.get(name);
                    count++;
                    reportCnt.put(name, count);
                }
            }
        }
        //System.out.println(reportCnt);
        for (int i = 0; i < id_list.length; i++) {
            if (reportCnt.get(id_list[i]) >= k) {
                for (int j = 0; j < id_list.length; j++) {
                    List<String> list = reportMap.get(id_list[j]);
                    if (list == null) {
                        //System.out.println(list);
                        continue;
                    } else {
                        if(list.contains(id_list[i]))
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

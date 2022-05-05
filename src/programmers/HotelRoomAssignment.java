package programmers;

import java.util.*;

// 정확성 모두 통과, 효율성 전부 실패 코드 (항상 모든 경우를 탐색함, k값이 최대 10^12이므로 비효율적)
class HotelRoomAssignment_0 {
    public static long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];
        Set<Long> set = new HashSet<>();

        for (int i = 0; i < room_number.length; i++) {
            if (!set.contains(room_number[i])) {
                set.add(room_number[i]);
                answer[i] = room_number[i];
            } else {
                for (long j = room_number[i]; j <= k; j++) {
                    if (!set.contains(j)) {
                        set.add(j);
                        answer[i] = j;
                        break;
                    }
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        long k = 10;
        long[] room_number = {1L, 3L, 4L, 1L, 3L, 1L};
        long[] results = {1L, 3L, 4L, 2L, 5L, 6L};

        long[] answer = solution(k, room_number);

        for (int i = 0; i < answer.length; i++) {
            System.out.print(answer[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < results.length; i++) {
            System.out.print(results[i] + " ");
        }
    }
}

// 반복문을 이용한 풀이
class HotelRoomAssignment_1 {
    public static long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];
        Map<Long, Long> map = new HashMap<>();

        for (int i = 0; i < room_number.length; i++) {
            List<Long> list = new ArrayList<>();
            long room = room_number[i];
            if (!map.containsKey(room)) {
                map.put(room, room + 1);
                answer[i] = room;
            } else {
                long emptyRoom = map.get(room);
                list.add(room);
                while (true) {
                    if (!map.containsKey(emptyRoom)) {
                        map.put(emptyRoom, emptyRoom + 1);
                        answer[i] = emptyRoom;
                        break;
                    } else {
                        list.add(emptyRoom);
                        emptyRoom = map.get(emptyRoom);
                    }
                }

                for (long l : list) {
                    map.put(l, emptyRoom + 1);
                }

            }
        }
        return answer;
    }

    public static void main(String[] args) {
        long k = 10;
        long[] room_number = {1L, 3L, 4L, 1L, 3L, 1L};
        long[] results = {1L, 3L, 4L, 2L, 5L, 6L};

        long[] answer = solution(k, room_number);

        for (int i = 0; i < answer.length; i++) {
            System.out.print(answer[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < results.length; i++) {
            System.out.print(results[i] + " ");
        }
    }
}

// 재귀함수를 이용한 풀이(빈방을 주기적으로 업데이트 해줌)
class HotelRoomAssignMent_2 {
    static Map<Long, Long> map;

    public static long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];
        map = new HashMap<>();

        for (int i = 0; i < room_number.length; i++) {
            answer[i] = getEmptyRoomNumber(room_number[i]);
        }
        return answer;
    }

    public static long getEmptyRoomNumber(long room) {
        if (!map.containsKey(room)) {
            map.put(room, room + 1);
            return room;
        }
        long emptyRoom = getEmptyRoomNumber(map.get(room));
        map.put(room, emptyRoom + 1);
        return emptyRoom;
    }

    public static void main(String[] args) {
        long k = 10;
        long[] room_number = {1L, 3L, 4L, 1L, 3L, 1L};
        long[] results = {1L, 3L, 4L, 2L, 5L, 6L};

        long[] answer = solution(k, room_number);

        for (int i = 0; i < answer.length; i++) {
            System.out.print(answer[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < results.length; i++) {
            System.out.print(results[i] + " ");
        }
    }
}
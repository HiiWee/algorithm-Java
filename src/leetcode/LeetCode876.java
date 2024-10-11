package leetcode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class LeetCode876_1 {
    public ListNode middleNode(ListNode head) {
        int size = 0;
        ListNode start = head;
        while (start != null) {
            size++;
            start = start.next;
        }

        int targetSize = size / 2;
        size = 0;
        start = head;
        while (start != null && size < targetSize) {
            size++;
            start = start.next;
        }
        return start;
    }
}

/**
 * 좀 더 효율적인 방식
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class LeetCode876_2 {
    public ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}

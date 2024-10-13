package leetcode;

import java.util.*;

class LeetCode21_1 {

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode();
        ListNode current = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val > list2.val) {
                current.next = list2;
                list2 = list2.next;
            } else {
                current.next = list1;
                list1 = list1.next;
            }
            current = current.next;
        }

        if (list1 != null) {
            current.next = list1;
        } else {
            current.next = list2;
        }

        return dummy.next;
    }
}

class LeetCode21_2 {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        List<ListNode> listNodes = new ArrayList<>();

        while (list1 != null) {
            listNodes.add(list1);
            list1 = list1.next;
        }
        while (list2 != null) {
            listNodes.add(list2);
            list2 = list2.next;
        }

        listNodes.sort((n1, n2) -> n1.val - n2.val);

        for (int i = 0; i < listNodes.size() - 1; i++) {
            ListNode node = listNodes.get(i);
            node.next = listNodes.get(i + 1);
        }

        if (listNodes.isEmpty()) {
            return null;
        }
        listNodes.get(listNodes.size() - 1).next = null;

        return listNodes.get(0);
    }
}

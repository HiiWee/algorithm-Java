package leetcode;

// Definition for singly-linked list.
class ListNode {
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

class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode currentNode = head;
        ListNode tempNode = null;
        ListNode nextNode = null;

        while(currentNode != null) {
            nextNode = currentNode.next;
            currentNode.next = tempNode;
            tempNode = currentNode;
            currentNode = nextNode;
        }
        return tempNode;
    }
}

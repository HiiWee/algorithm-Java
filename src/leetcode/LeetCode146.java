package leetcode;

import java.util.*;

class LRUCache_LeetCode146 {

    static class Node {

        private final int key;
        private final int value;
        private Node left;
        private Node right;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return Integer.toString(value);
        }
    }

    private final Map<Integer, Node> nodes = new HashMap<>();
    private final Node head = new Node(0, 0);
    private final Node tail = new Node(-1, -1);
    private final int capacity;

    public LRUCache_LeetCode146(int capacity) {
        this.capacity = capacity;
        head.right = tail;
        tail.left = head;
    }

    public int get(int key) {
        if (!nodes.containsKey(key)) {
            return -1;
        }
        Node node = nodes.get(key);
        updateRecentView(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (nodes.containsKey(key)) {
            remove(nodes.get(key));
        }
        if (nodes.size() >= capacity) {
            remove(tail.left);
        }
        append(new Node(key, value));
    }

    private void updateRecentView(Node node) {
        remove(node);
        append(node);
    }

    private void remove(Node node) {
        node.left.right = node.right;
        node.right.left = node.left;
        nodes.remove(node.key);
    }

    private void append(Node node) {
        Node temp = head.right;
        node.right = temp;
        node.left = head;
        head.right = node;
        temp.left = node;
        nodes.put(node.key, node);
    }
}

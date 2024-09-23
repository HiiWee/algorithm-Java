package leetcode;

public class LeetCode208 {

    static class Trie {

        static class Node {
            private final char value;
            private final Node[] children;
            private boolean isEnd;

            public Node(char value) {
                this.value = value;
                this.children = new Node[26];
            }
        }

        private final Node root;

        public Trie() {
            root = new Node('0');
        }

        public void insert(String word) {
            char[] splitWords = word.toCharArray();
            insertSingle(root, 0, splitWords);
        }

        private void insertSingle(Node node, int level, char[] words) {
            if (level == words.length) {
                node.isEnd = true;
                return;
            }
            if (node.children[words[level] - 'a'] == null) {
                Node childNode = new Node(words[level]);
                node.children[words[level] - 'a'] = childNode;
            }

            insertSingle(node.children[words[level] - 'a'], level + 1, words);
        }

        public boolean search(String word) {
            return searchSingle(root, 0, word.toCharArray());
        }

        private boolean searchSingle(Node node, int level, char[] words) {
            if (level == words.length) {
                return node.isEnd;
            }
            if (node.children[words[level] - 'a'] == null) {
                return false;
            }

            return searchSingle(node.children[words[level] - 'a'], level + 1, words);
        }

        public boolean startsWith(String prefix) {
            char[] splitWords = prefix.toCharArray();

            return startsWithSingle(root, 0, splitWords);
        }

        private boolean startsWithSingle(Node node, int level, char[] words) {
            boolean result;
            if (level == words.length) {
                return true;
            }
            if (node.children[words[level] - 'a'] == null) {
                return false;
            }
            return startsWithSingle(node.children[words[level] - 'a'], level + 1, words);
        }
    }
}

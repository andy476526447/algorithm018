package com.example.javalib.arg.week07;

//leetcode-208.实现 Trie(前缀树):https://leetcode-cn.com/problems/implement-trie-prefix-tree/
public class Test_208 {

    public static void main(String[] args) {
        Test_208 test_208 = new Test_208();
        test_208.insert("apple");
        System.out.println(test_208.search("apple"));   // 返回 true
        System.out.println(test_208.search("app"));     // 返回 false
        System.out.println(test_208.startsWith("app")); // 返回 true
        test_208.insert("app");
        System.out.println(test_208.search("app"));     // 返回 true
    }


    TrieNode root = new TrieNode();

    /**
     * Initialize your data structure here.
     */
    public Test_208() {

    }

    /**
     * Inserts a word into the trie.
     * 时间复杂度：O(m)，m表示word的长度；
     * 空间复杂度：O(m)，最坏的情况是每个字符都需要再存一次，每个节点维护一个长度为数组和一个变量，耗费空间27*m,去掉常数后为O(m)
     */
    public void insert(String word) {
        TrieNode curNode = root;
        for (char ch : word.toCharArray()) {
            if (!curNode.containChar(ch)) {
                curNode.insertChar(ch);
            }
            curNode = curNode.getCurNodeByChar(ch);
        }
        curNode.setEnd();
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        TrieNode node = searchByPrefix(word);
        return node != null && node.isEnd();
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        return searchByPrefix(prefix) != null;
    }

    //时间复杂度为O(m)，空间复杂度为O(1)
    private TrieNode searchByPrefix(String word) {
        TrieNode curNode = root;
        for (char ch : word.toCharArray()) {
            if (!curNode.containChar(ch)) {
                return null;
            }
            curNode = curNode.getCurNodeByChar(ch);
        }
        return curNode;
    }

    class TrieNode {
        private TrieNode[] trieNodes = new TrieNode[26];
        private boolean isEnd = false;

        public boolean containChar(char ch) {
            return trieNodes[ch - 'a'] != null;
        }

        public boolean insertChar(char ch) {
            if (containChar(ch)) {
                return false;
            }

            trieNodes[ch - 'a'] = new TrieNode();
            return true;
        }

        public TrieNode getCurNodeByChar(int ch) {
            return trieNodes[ch - 'a'];
        }

        public void setEnd() {
            isEnd = true;
        }

        public boolean isEnd() {
            return isEnd;
        }
    }
}
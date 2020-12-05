package com.example.javalib.arg.week07;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

//leetcode-212.单词搜索II https://leetcode-cn.com/problems/word-search-ii/
public class Test_212 {

    private static final int[] rowOffset = {-1, 0, 1, 0}; //up,right,down,left
    private static final int[] colOffset = {0, 1, 0, -1};
    private char[][] mBoard = null;
    private List<String> mResult = new ArrayList<>();
    private TrieNode root = new TrieNode();
    private int mRowSize = 0;
    private int mColSize = 0;

    public static void main(String[] args) {
        Test_212 test_212 = new Test_212();
        char[][] board = {
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };
        String[] words = {"oath", "pea", "eat", "rain"};
        //String[] words = {"eat"};
        System.out.println(test_212.findWords(board, words));
    }

    public List<String> findWords(char[][] board, String[] words) {
        mBoard = board;
        mRowSize = mBoard.length;
        mColSize = mBoard[0].length;
        for (String word : words) {
            insertWord(word);
        }
        //对board中的每个字符进行检测，如果该字符存在于root对应的trie中，开始回溯检查相邻的字符
        for (int row = 0; row < mRowSize; row++) {
            for (int col = 0; col < mColSize; col++) {
                if (root.isContains(board[row][col])) {
                    backTracking(row, col, root);
                }
            }
        }
        return mResult;
    }

    private void backTracking(int row, int col, TrieNode parent) {
        char letter = mBoard[row][col];
        //parent节点表示的子树中，当前字符所指向的节点
        TrieNode curNode = parent.getCurNode(letter);
        //表示当前字符是一个word的结尾
        if (curNode.getWord() != null) {
            //既然是一个word的结尾了，说明在board中的存在word这个单词，添加到结果集
            mResult.add(curNode.getWord());
            //该word可能是另外一个单词的前缀，当前word已经判断过了，后面无需再判断，置null后可以避免重复判断
            curNode.setWord(null);
        }

        //根据题目要求，同一个位置的字符不能在一个单词中重复出现
        mBoard[row][col] = '#';

        //每一个非边缘字符可以在上/下/左/右方向前进
        for (int i = 0; i < 4; i++) {
            //移动一格后新的坐标
            int nextRow = row + rowOffset[i];
            int nextCol = col + colOffset[i];
            if (isEdge(nextRow, nextCol)) {
                continue;
            }
            //如果下一个字符也是存在于Trie中，进入到下一层
            if (curNode.isContains(mBoard[nextRow][nextCol])) {
                backTracking(nextRow, nextCol, curNode);
            }

        }
        //恢复为原来的字符
        mBoard[row][col] = letter;
        //表示parent为叶子节点。这里用于对Tied进行剪枝，可以避免board中检测重复的word
        if (curNode.isEnd()) {
            //删除掉
            curNode.remove(letter);
        }
    }

    public void insertWord(String word) {
        TrieNode curNode = root;
        for (char ch : word.toCharArray()) {
            if (!curNode.isContains(ch)) {
                curNode.put(ch);
            }
            curNode = curNode.getCurNode(ch);
        }
        curNode.setWord(word);
    }

    private boolean isEdge(int row, int col) {
        return row < 0 || row >= mRowSize || col < 0 || col >= mColSize;
    }

    class TrieNode {
        private TrieNode[] trieNodes = new TrieNode[26];
        private String word;

        public boolean isContains(char ch) {
            return ch != '#' && trieNodes[ch - 'a'] != null;
        }

        public void put(char ch) {
            if (!isContains(ch)) {
                trieNodes[ch - 'a'] = new TrieNode();
            }
        }

        public TrieNode getCurNode(char ch) {
            return trieNodes[ch - 'a'];
        }

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public void remove(char ch) {
            trieNodes[ch - 'a'] = null;
        }

        //只有在叶子节点中才会保存整个完整的word
        public boolean isEnd() {
            return word != null;
        }
    }

    //用于打印创建的trie
    public void printTrie() {
        TrieNode node = root;
        Deque<TrieNode> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                node = queue.poll();
                if (node == null) {
                    continue;
                }
                System.out.println(node.word);
                //表示表示叶子节点
                if (node.word == null) {
                    for (TrieNode trieNode : node.trieNodes) {
                        queue.offer(trieNode);
                    }
                }
            }
        }
    }
}
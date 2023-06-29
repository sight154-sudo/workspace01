package com.huawei.algorithm.leetcode.dataStructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author king
 * @date 2023/6/24-17:27
 * @Desc
 */
public class MyTrieNode {

    static class Node {
        int pass;
        int end;
        Node[] next;

        public Node() {
            pass = 0;
            end = 0;
            next = new Node[26];
        }
    }

    static class TrieNode {
        Node root;

        public TrieNode() {
            root = new Node();
        }

        public void insertStr(String str) {
            if (null == str || str.isEmpty()) {
                return;
            }
            char[] chs = str.toCharArray();
            Node node = root;
            node.pass++;
            int pass = 0;
            for (int i = 0; i < chs.length; i++) {
                pass = chs[i] - 97;
                if (node.next[pass] == null) {
                    node.next[pass] = new Node();
                }
                node = node.next[pass];
                node.pass++;
            }
            node.end++;
        }

        public int findStrPre(String str) {
            if (null == str || str.isEmpty()) {
                return 0;
            }
            char[] chs = str.toCharArray();
            Node node = root;
            int pass = 0;
            for (int i = 0; i < chs.length; i++) {
                pass = chs[i] - 97;
                if (node.next[pass] == null) {
                    return 0;
                }
                node = node.next[pass];
            }
            return node.pass;
        }

        public int findStrNum(String str) {
            if (null == str || str.isEmpty()) {
                return 0;
            }
            char[] chs = str.toCharArray();
            Node node = root;
            for (int i = 0; i < chs.length; i++) {
                if (node.next[chs[i] - 97] == null) {
                    return node.end;
                }
                node = node.next[chs[i] - 97 ];
            }
            return node.end;
        }

        public void deleteStr(String str) {
            if (findStrNum(str) < 1) {
                return;
            }
            char[] chs = str.toCharArray();
            Node node = root;
            node.pass--;
            int pass = 0;
            for (int i = 0; i < chs.length; i++) {
                pass = chs[i] - 97;
                if (--node.next[pass].pass == 0) {
                    node.next[pass] = null;
                    return;
                }
                node = node.next[pass];
            }
            node.end--;
        }

    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3};
        List<int[]> ints = Arrays.asList(arr1);
        System.out.println(ints.size());
        TrieNode trieNode = new TrieNode();
        trieNode.insertStr("abc");
        trieNode.insertStr("abcd");
        trieNode.insertStr("abd");
        System.out.println(trieNode.findStrNum("abc"));
        trieNode.deleteStr("abc");
        int maxValue = Integer.MAX_VALUE;
        System.out.println(maxValue);
    }
}

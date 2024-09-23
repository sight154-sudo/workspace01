package com.huawei.algorithm.leetcode.treeNodePractice;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author king
 * @date 2023/9/13-23:07
 * @Desc
 */
public class TreeNodePractice {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }

        public static TreeNode constructTreeNode(int[] arr, int index) {
            if (index >= arr.length) {
                return null;
            }
            if (arr[index] == -1) {
                return null;
            }
            TreeNode root = new TreeNode(arr[index]);
            root.left = constructTreeNode(arr, index * 2);
            root.right = constructTreeNode(arr, index * 2 + 1);
            return root;
        }

        public static void levelPrintTreeNode(TreeNode root) {
            if (root == null) {
                return;
            }
            LinkedList<TreeNode> list = new LinkedList<>();
            list.addLast(root);
            List<Integer> ans = new ArrayList<>();
            while (!list.isEmpty()) {
                TreeNode node = list.removeFirst();
                ans.add(node.val);
                if (node.left != null) {
                    list.addLast(node.left);
                }
                if (node.right != null) {
                    list.addLast(node.right);
                }
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < ans.size(); i++) {
                sb.append(ans.get(i));
                if (i != ans.size() - 1) {
                    sb.append("->");
                }
            }
            System.out.println(sb);
        }

    }

    @Test
    public void binaryTreePathsTest() {
        int[] arr = {-1, 1, 2, 3, 4, 5, 6, 8};
        TreeNode root = TreeNode.constructTreeNode(arr, 1);
        TreeNode.levelPrintTreeNode(root);
        List<String> strings = binaryTreePaths(root);
        System.out.println(strings);

        Map<String, Integer> map = new HashMap<>();
        map.put("11", 101);
        map.put("22", 202);
        map.forEach((k, v) -> System.out.println(k + ":" + v));
    }

    /**
     * 给你一个二叉树的根节点 root ，按 任意顺序 ，返回所有从根节点到叶子节点的路径。
     * <p>
     * 叶子节点 是指没有子节点的节点。
     * leetCode 257
     *
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> list = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        binaryTreePathsForce(root, tmp, list);
        return list;
    }

    public void binaryTreePathsForce(TreeNode root, List<Integer> list, List<String> ans) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        binaryTreePathsForce(root.left, list, ans);
        binaryTreePathsForce(root.right, list, ans);
        if (root.left == null && root.right == null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                if (i == list.size() - 1) {
                    sb.append(list.get(i));
                } else {
                    sb.append(list.get(i)).append("->");
                }
            }
            ans.add(sb.toString());
        }
        list.remove(list.size() - 1);
    }

    static class Info {
        int usedMax;
        int noUsedMax;
    }

    @Test
    public void rob5Test() {
        TreeNode root = TreeNode.constructTreeNode(new int[]{-1, 4, 1, -1, 2, -1, -1, -1, 3}, 1);
        TreeNode.levelPrintTreeNode(root);
        System.out.println(rob5(root));
    }

    public int rob5(TreeNode root) {
        Info info = process(root);
        return Math.max(info.usedMax, info.noUsedMax);
    }

    public Info process(TreeNode root) {
        if (root == null) {
            return new Info();
        }
        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);
        Info info = new Info();
        info.usedMax = leftInfo.noUsedMax + rightInfo.noUsedMax + root.val;
        // 当前选择 1 ： 左右都选   左选右不选  左不选右选   左不选右不选
        int p1 = leftInfo.usedMax + rightInfo.usedMax;
        int p2 = leftInfo.usedMax + rightInfo.noUsedMax;
        int p3 = leftInfo.noUsedMax + rightInfo.usedMax;
        int p4 = leftInfo.noUsedMax + rightInfo.noUsedMax;
        info.noUsedMax = Math.max(p1, Math.max(p2, Math.max(p3, p4)));
        return info;
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        preorderTraversalForce(list, root);
        return list;
    }

    public List<Integer> preorderTraversalNoForce1(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            list.add(node.val);
            if (node.right != null) {
                stack.add(node.right);
            }
            if (node.left != null) {
                stack.add(node.left);
            }
        }
        return list;
    }

    public List<Integer> preorderTraversalNoForce2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                list.add(root.val);
                stack.add(root);
                root = root.left;
            }
            if (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                root = node.right;
            }
        }
        return list;
    }

    private void preorderTraversalForce(List<Integer> list, TreeNode root) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        preorderTraversalForce(list, root.left);
        preorderTraversalForce(list, root.right);
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        postorderTraversalForce(list, root);
        return null;
    }

    public List<Integer> postorderTraversalNoForce1(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> ans = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            ans.add(node);
            if (node.left != null) {
                stack.add(node.left);
            }
            if (node.right != null) {
                stack.add(node.right);
            }
        }
        while (!ans.isEmpty()) {
            list.add(ans.pop().val);
        }
        return list;
    }

    public List<Integer> postorderTraversalNoForce2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            TreeNode node = stack.peek();
            if (node.right == null || node.right == pre) {
                stack.pop();
                list.add(node.val);
                root = null;
                pre = node;
            } else {
                root = node.right;
            }

        }
        return list;
    }

    private void postorderTraversalForce(List<Integer> list, TreeNode root) {
        if (root == null) {
            return;
        }
        postorderTraversalForce(list, root.left);
        postorderTraversalForce(list, root.right);
        list.add(root.val);
    }


    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        inorderTraversalForce(list, root);
        return list;
    }

    public List<Integer> inorderTraversalNoFroce1(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            if (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                list.add(node.val);
                root = node.right;
            }
        }
        return list;
    }

    private void inorderTraversalForce(List<Integer> list, TreeNode root) {
        if (root == null) {
            return;
        }
        inorderTraversalForce(list, root.left);
        list.add(root.val);
        inorderTraversalForce(list, root.right);
    }

    public List<Integer> levelOrder(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addFirst(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.removeLast();
            list.add(node.val);
            if (node.left != null) {
                queue.addFirst(node.left);
            }
            if (node.right != null) {
                queue.addFirst(node.right);
            }
        }
        return list;
    }

    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        Map<TreeNode, Integer> map = new HashMap<>();
        deque.addFirst(root);
        map.put(root, 1);
        List<Integer> tmp = new ArrayList<>();
        int curLevel = 1;
        while (!deque.isEmpty()) {
            TreeNode node = deque.removeLast();
            Integer curlv = map.get(node);
            if (curlv != curLevel) {
                curLevel++;
                list.add(new ArrayList<>(tmp));
                tmp.clear();
            }
            tmp.add(node.val);
            if (node.left != null) {
                deque.addFirst(node.left);
                map.put(node.left, curlv + 1);
            }
            if (node.right != null) {
                deque.addFirst(node.right);
                map.put(node.right, curlv + 1);
            }
        }
        list.add(tmp);
        return list;
    }

    public List<List<Integer>> levelOrder3(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addFirst(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            List<Integer> tmp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = deque.removeLast();
                tmp.add(node.val);
                if (node.left != null) deque.addFirst(node.left);
                if (node.right != null) deque.addFirst(node.right);
            }
            list.add(tmp);
        }
        return list;
    }

    public List<List<Integer>> levelOrder3Recursion(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        levelOrder3Recursion(root, 0, list);
        return list;
    }

    public void levelOrder3Recursion(TreeNode node, int deep, List<List<Integer>> list) {
        if (node == null) {
            return;
        }
        deep++;
        if (list.size() < deep) {
            List<Integer> tmp = new ArrayList<>();
            list.add(tmp);
        }
        list.get(deep - 1).add(node.val);
        levelOrder3Recursion(node.left, deep, list);
        levelOrder3Recursion(node.right, deep, list);
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addFirst(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            List<Integer> tmp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = deque.removeLast();
                tmp.add(node.val);
                if (node.left != null) deque.addFirst(node.left);
                if (node.right != null) deque.addFirst(node.right);
            }
            list.add(tmp);
        }
        Collections.reverse(list);
        return list;
    }

    /**
     * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
     * leetCode 199
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addFirst(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = deque.removeLast();
                if (i == size - 1) {
                    list.add(node.val);
                }
                if (node.left != null) deque.addFirst(node.left);
                if (node.right != null) deque.addFirst(node.right);
            }
        }
        return list;
    }

    /**
     * 给定一个非空二叉树的根节点 root , 以数组的形式返回每一层节点的平均值。与实际答案相差 10-5 以内的答案可以被接受。
     * leetCode 637
     *
     * @param root
     * @return
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addFirst(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            double num = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = deque.removeLast();
                num += node.val;
                if (node.left != null) deque.addFirst(node.left);
                if (node.right != null) deque.addFirst(node.right);
            }
            list.add(num / size);
        }
        return list;
    }

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Deque<Node> deque = new LinkedList<>();
        deque.addFirst(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            List<Integer> tmp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node node = deque.removeLast();
                tmp.add(node.val);
                if (node != null) {
                    for (Node child : node.children) {
                        deque.addFirst(child);
                    }
                }
            }
            list.add(tmp);
        }
        return list;
    }

    /**
     * 给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。
     * leetCode 515
     *
     * @param root
     * @return
     */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addFirst(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode node = deque.removeLast();
                max = Math.max(node.val, max);
                if (node.left != null) deque.addFirst(node.left);
                if (node.right != null) deque.addFirst(node.right);
            }
            result.add(max);
        }
        return result;
    }

    @Test
    public void levelOrderTest() {
        TreeNode root = TreeNode.constructTreeNode(new int[]{-1, 1, 2, 3, 4, 6, 5, -1, -1, -1, 7, 8}, 1);
        TreeNode.levelPrintTreeNode(root);
        System.out.println(levelOrder(root));
        System.out.println(levelOrder2(root));
    }

    /**
     * 给定一个二叉树 root ，返回其最大深度。
     * leetCode 104
     * 二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addFirst(root);
        int deep = 0;
        while (!deque.isEmpty()) {
            int size = deque.size();
            deep++;
            for (int i = 0; i < size; i++) {
                TreeNode node = deque.removeLast();
                if (node.left != null) deque.addFirst(node.left);
                if (node.right != null) deque.addFirst(node.right);
            }
        }
        return deep;
    }

    /**
     * 111. 二叉树的最小深度
     *
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addFirst(root);
        int deep = 0;
        while (!deque.isEmpty()) {
            int size = deque.size();
            deep++;
            for (int i = 0; i < size; i++) {
                TreeNode node = deque.removeLast();
                if (node.left != null) deque.addFirst(node.left);
                if (node.right != null) deque.addFirst(node.right);
                if (node.left == null && node.right == null) {
                    return deep;
                }
            }
        }
        return deep;
    }

    public int mindDepth2(TreeNode root) {
        return mindDepth2Recursion(root);
    }

    private int mindDepth2Recursion(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = mindDepth2Recursion(root.left);
        int right = mindDepth2Recursion(root.right);
        if (root.left != null && root.right == null) {
            return 1 + left;
        }
        if (root.right != null && root.left == null) {
            return 1 + right;
        }
        return 1 + Math.min(left, right);
    }

    public int maxDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int maxDeep = 0;
        getMaxDepth(root, 0, maxDeep);
        return maxDeep;
    }

    public int maxDepth2(TreeNode root) {
        return getMaxDepthRecursion(root);
    }

    private int getMaxDepthRecursion(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getMaxDepthRecursion(root.left);
        int right = getMaxDepthRecursion(root.right);
        return 1 + Math.max(left, right);
    }

    private void getMaxDepth(TreeNode node, int deep, int maxDeep) {
        if (node == null) {
            return;
        }
        deep++;
        maxDeep = Math.max(maxDeep, deep);
        getMaxDepth(node.left, deep, maxDeep);
        getMaxDepth(node.right, deep, maxDeep);
    }

    /**
     * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
     * leetCode 226
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        return invertTreeRecursion(root);
    }

    private TreeNode invertTreeRecursion(TreeNode node) {
        if (node == null) {
            return null;
        }
        TreeNode left = invertTreeRecursion(node.left);
        TreeNode right = invertTreeRecursion(node.right);
        node.right = left;
        node.left = right;
        return node;
    }

    private void invertTreeRecursion2(TreeNode node) {
        if (node == null) {
            return;
        }
        swapNode(node);
        invertTreeRecursion2(node.left);
        invertTreeRecursion2(node.right);
    }

    private void swapNode(TreeNode node) {
        TreeNode tmp = node.left;
        node.left = node.right;
        node.right = tmp;
    }

    public TreeNode invertTree2(TreeNode root) {
        if (root == null) {
            return root;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            swapNode(node);
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
        return root;
    }

    public TreeNode invertTree3(TreeNode root) {
        if (root == null) {
            return root;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addFirst(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = deque.removeLast();
                swapNode(node);
                if (node.left != null) deque.addFirst(node.left);
                if (node.right != null) deque.addFirst(node.right);
            }
        }
        return root;
    }

    static class symmetricInfo {
        boolean isSymmetric;

    }

    /**
     * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return false;
        }
        return isSymmetricRecursion(root.left, root.right);
    }

    public boolean isSymmetric2(TreeNode root) {
        if (root == null) {
            return false;
        }
        Stack<TreeNode> s1 = new Stack<>();
        s1.add(root.left);
        s1.add(root.right);
        while (!s1.isEmpty()) {
            TreeNode left = s1.pop();
            TreeNode right = s1.pop();
            if (left == null && right == null) {
                continue;
            }
            if (left == null || right == null || left.val != right.val) {
                return false;
            }
            s1.push(right.left);
            s1.push(left.right);
            s1.push(right.right);
            s1.push(left.left);
        }
        return true;
    }

    public boolean isSymmetricRecursion(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        } else if (left == null || right == null) {
            return false;
        } else if (left.val != right.val) {
            return false;
        } else {
            return isSymmetricRecursion(left.left, right.right) && isSymmetricRecursion(left.right, right.left);
        }
    }

    @Test
    public void isSymmetricTest() {
        TreeNode root = TreeNode.constructTreeNode(new int[]{-1, 1, 2, 2, -1, 3, -1, 3}, 1);
        System.out.println(isSymmetric2(root));
    }

    @Test
    public void invertTreeTest() {
        TreeNode root = TreeNode.constructTreeNode(new int[]{-1, 1, 2, 3}, 1);
        TreeNode.levelPrintTreeNode(root);
        TreeNode node = invertTree(root);
        TreeNode.levelPrintTreeNode(node);
    }


    static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    /**
     * 给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
     * leetCode 222
     *
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        return countNodesRecursion(root);
    }

    public int countNode2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        int l = 0;
        int r = 0;
        while (left != null) {
            left = left.left;
            l++;
        }
        while (right != null) {
            right = right.right;
            r++;
        }
        if (l == r) {
            return (2 << l) - 1;
        }
        return countNode2(root.left) + countNode2(root.right) + 1;
    }

    public int countNodes1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        int count = 1;
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            count++;
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
        return count;
    }

    @Test
    public void countNodesTest() {
        TreeNode root = TreeNode.constructTreeNode(new int[]{-1, 1, 2, 3, 4, 5, 6}, 1);
        System.out.println(countNodes(root));
    }

    private int countNodesRecursion(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = countNodes(root.left);
        int right = countNodes(root.right);
        return 1 + left + right;
    }

    /**
     * 给定一个二叉树，判断它是否是高度平衡的二叉树。
     * leetCode 110
     *
     * @param root
     * @return
     */
    public boolean isBalanced1(TreeNode root) {
        if (root == null) {
            return true;
        }
        int l = getDepth(root.left);
        int r = getDepth(root.right);
        return Math.abs(l - r) <= 1 && isBalanced1(root.left) && isBalanced1(root.right);
    }

    static class BalanceInfo {
        boolean isBalance;
        int lp;
        int rp;

        public BalanceInfo(boolean isBalance, int lp, int rp) {
            this.isBalance = isBalance;
            this.lp = lp;
            this.rp = rp;
        }
    }

    public boolean isBalanced3(TreeNode root) {
        BalanceInfo info = isBalancedRecursion(root);
        return info.isBalance;
    }

    private BalanceInfo isBalancedRecursion(TreeNode root) {
        if (root == null) {
            return new BalanceInfo(true, 0, 0);
        }
        BalanceInfo left = isBalancedRecursion(root.left);
        BalanceInfo right = isBalancedRecursion(root.right);
        BalanceInfo info = new BalanceInfo(left.isBalance & right.isBalance, 0, 0);
        info.lp = Math.max(left.lp, left.rp) + 1;
        info.rp = Math.max(right.lp, right.rp) + 1;
        info.isBalance &= Math.abs(info.lp - info.rp) <= 1;
        return info;
    }

    @Test
    public void isBalanceTest() {
        TreeNode root = TreeNode.constructTreeNode(new int[]{-1, 1, 2, 2, 3, 3, -1, -1, 4, 4}, 1);
        System.out.println(isBalanced(root));
    }

    public boolean isBalanced2(TreeNode root) {
        if (root == null) {
            return true;
        }
        return Math.abs(getDepth(root.left) - getDepth(root.right)) <= 1;
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return height(root) > 0;
    }

    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = height(root.left);
        int right = height(root.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        } else {
            return Math.max(left, right) + 1;
        }
    }


    public int getDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getDepth(root.left);
        int right = getDepth(root.right);
        return Math.max(left, right) + 1;
    }


    /**
     * 给你一个二叉树的根节点 root ，按 任意顺序 ，返回所有从根节点到叶子节点的路径。
     *
     * @param root
     * @return
     */
    public List<String> binaryTreePaths1(TreeNode root) {
        List<String> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        List<Integer> tmp = new ArrayList<>();
        binaryTreeRecursion(root, tmp, list);
        return list;
    }

    public List<String> binaryTreePaths2(TreeNode root) {
        List<String> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        binaryTreePathsRecursion2(root, list, "");
        return list;
    }

    public void binaryTreePathsRecursion2(TreeNode root, List<String> ans, String str) {
        if (root == null) {
            return;
        }
        str = str + root.val + "->";
        binaryTreePathsRecursion2(root.left, ans, str);
        binaryTreePathsRecursion2(root.right, ans, str);
        if (root.left == null && root.right == null) {
            StringBuilder sb = new StringBuilder(str);
            ans.add(sb.substring(0, sb.length() - 2));
        }
    }

    @Test
    public void binaryTreePathTest1() {
        TreeNode root = TreeNode.constructTreeNode(new int[]{-1, 1, 2, 3, 4, 5}, 1);
        System.out.println(binaryTreePaths2(root));
    }

    public void binaryTreeRecursion(TreeNode root, List<Integer> tmp, List<String> list) {
        tmp.add(root.val);
        if (root.left == null && root.right == null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < tmp.size(); i++) {
                if (i == tmp.size() - 1) {
                    sb.append(tmp.get(i));
                    break;
                }
                sb.append(tmp.get(i)).append("->");
            }
            list.add(sb.toString());
            return;
        }
        /*if (root.left != null ) {
            binaryTreeRecursion(root.left, tmp, list);
            tmp.remove(tmp.size()-1);
        }
        if (root.right != null) {
            binaryTreeRecursion(root.right, tmp, list);
            tmp.remove(tmp.size()-1);
        }*/

        binaryTreeRecursion(root.left, tmp, list);
        binaryTreeRecursion(root.right, tmp, list);
        tmp.remove(tmp.size() - 1);
    }

    public boolean isSameTree1(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        Stack<TreeNode> p1 = new Stack<>();
        Stack<TreeNode> q1 = new Stack<>();
        p1.add(p);
        q1.add(q);
        while (!p1.isEmpty() || !q1.isEmpty()) {
            TreeNode node1 = p1.pop();
            TreeNode node2 = q1.pop();
            if (node1.val != node2.val) {
                return false;
            }
            if (node1.right != null && node2.right != null) {
                p1.add(node1.right);
                q1.add(node2.right);
            }
            if ((node1.right == null && node2.right != null) || (node1.right != null && node2.right == null)) {
                return false;
            }
            if (node1.left != null && node2.left != null) {
                p1.add(node1.left);
                q1.add(node2.left);
            }
            if ((node1.left == null && node2.left != null) || (node1.left != null && node2.left == null)) {
                return false;
            }
        }
        return p1.isEmpty() && q1.isEmpty();
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val == q.val) {

            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
        return false;
    }

    @Test
    public void isSameTreeNodeTest() {
        TreeNode root1 = TreeNode.constructTreeNode(new int[]{-1, 1, 2}, 1);
        TreeNode root2 = TreeNode.constructTreeNode(new int[]{-1, 1, -1, 2}, 1);
        System.out.println(isSameTree(root1, root2));
    }

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.val == subRoot.val) {
                if (isSameTree(node, subRoot)) {
                    return true;
                }
            }
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
        return false;
    }

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return root.val;
        }
        return sumOfLeftLeavesRecursion(root.left, -1) + sumOfLeftLeavesRecursion(root.right, 1);
    }

    public int sumOfLeftLeavesRecursion(TreeNode root, int flag) {
        if (root == null) {
            return 0;
        }
        int left = sumOfLeftLeavesRecursion(root.left, -1);
        int right = sumOfLeftLeavesRecursion(root.right, 1);
        if (root.left == null && root.right == null) {
            return flag == -1 ? root.val : 0;
        }
        return left + right;
    }

    public int sumOfLeftLeavesRecursion(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int sum = 0;
        int left = sumOfLeftLeavesRecursion(root.left);
        int right = sumOfLeftLeavesRecursion(root.right);
        if (root.left != null && root.left.left == null && root.left.right == null) {
            sum += root.left.val;
        }
        return sum + left + right;
    }


    public int sumOfLeftLeaves1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 0;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addFirst(root);
        int sum = 0;
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = deque.removeLast();
                if (node.left != null && node.left.left == null && node.left.right == null) {
                    sum += node.left.val;
                }
                if (node.left != null) deque.addFirst(node.left);
                if (node.right != null) deque.addFirst(node.right);
            }
        }
        return sum;
    }

    public int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 计算1+100

        Deque<TreeNode> deque = new LinkedList<>();
        deque.addFirst(root);
        int sum = 0;
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = deque.removeLast();
                if (node.left == null && node.right == null) {
                    sum = node.val;
                }
                if (node.right != null) deque.addFirst(node.right);
                if (node.left != null) deque.addFirst(node.left);
            }
        }
        return sum;
    }

    /**
     * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。如果存在，返回 true ；否则，返回 false 。
     * <p>
     * 叶子节点 是指没有子节点的节点。
     * leetCode 112
     *
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        // 思路一： 求出所有的路径，计算路径和，若与目标值相同，则返回true，否则返回False

        return hasPathSumRecursion(root, targetSum);
    }

    @Test
    public void hasPathSumTest() {
        TreeNode root = TreeNode.constructTreeNode(new int[]{-1, 5, 4, 8, 11, -1, 13, 4, 7, 2, -1, -1, -1, -1, -1, 1}, 1);
//        TreeNode root = TreeNode.constructTreeNode(new int[]{-1, 1,2,3}, 1);
        System.out.println(hasPathSum(root, 22));
    }

    public boolean hasPathSum1(TreeNode root, int targetSum) {

        return false;
    }

    /**
     * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
     * LeetCode 113
     *
     * @param root
     * @param targetSum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        List<Integer> list = new ArrayList<>();
        list.add(root.val);
        pathSumRecursion(root, result, list, targetSum - root.val);
        return result;
    }

    @Test
    public void pathSumTest() {
        TreeNode root = TreeNode.constructTreeNode(new int[]{-1, 5, 4, 8, 11, -1, 13, 4, 7, 2, -1, -1, -1, -1, 5, 1}, 1);
        System.out.println(pathSum1(root, 22));
    }

    public void pathSumRecursion(TreeNode root, List<List<Integer>> result, List<Integer> list, int target) {

        if (root.left == null && root.right == null && target == 0) {
            result.add(new ArrayList<>(list));
            return;
        }
        if (root.left == null && root.right == null) {
            return;
        }
        if (root.left != null) {
            target -= root.left.val;
            list.add(root.left.val);
            pathSumRecursion(root.left, result, list, target);
            target += root.left.val;
            list.remove(list.size() - 1);
        }


        if (root.right != null) {
            target -= root.right.val;
            list.add(root.right.val);
            pathSumRecursion(root.right, result, list, target);
            target += root.right.val;
            list.remove(list.size() - 1);
        }
    }

    public List<List<Integer>> pathSum1(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        List<Integer> list = new ArrayList<>();
        pathSumRecursion1(root, result, list, targetSum);
        return result;
    }

    public void pathSumRecursion1(TreeNode root, List<List<Integer>> result, List<Integer> list, int target) {
        if (root == null) {
            return;
        }
        target -= root.val;
        list.add(root.val);
        pathSumRecursion1(root.left, result, list, target);
        pathSumRecursion1(root.right, result, list, target);
        if (root.left == null && root.right == null && target == 0) {
            result.add(new ArrayList<>(list));
        }
        list.remove(list.size() - 1);
    }

    public boolean hasPathSumRecursion(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        targetSum -= root.val;
        boolean left = hasPathSumRecursion(root.left, targetSum);
        boolean right = hasPathSumRecursion(root.right, targetSum);
        if (root.left == null && root.right == null) {
            if (targetSum == 0) {
                return true;
            }
        }
        return left || right;
    }

    /**
     * 给定一个不重复的整数数组 nums 。 最大二叉树 可以用下面的算法从 nums 递归地构建:
     * leetCode 654
     * 创建一个根节点，其值为 nums 中的最大值。
     * 递归地在最大值 左边 的 子数组前缀上 构建左子树。
     * 递归地在最大值 右边 的 子数组后缀上 构建右子树。
     * 返回 nums 构建的 最大二叉树 。
     *
     * @param nums
     * @return
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        // TODO 单调栈解法
        if (nums == null) {
            return null;
        }
        return constructMaximumBinaryTree(nums, 0, nums.length - 1);
    }

    @Test
    public void constructMaximumBinaryTree() {
        int[] nums = {3, 2, 1, 6, 0, 5};
        TreeNode root = constructMaximumBinaryTree(nums);
        TreeNode.levelPrintTreeNode(root);
    }

    public TreeNode constructMaximumBinaryTree(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int idx = getMaxIndex(nums, left, right);
        TreeNode node = new TreeNode(nums[idx]);
        node.left = constructMaximumBinaryTree(nums, left, idx - 1);
        node.right = constructMaximumBinaryTree(nums, idx + 1, right);
        return node;
    }

    private int getMaxIndex(int[] nums, int left, int right) {
        if (left == right) {
            return left;
        }
        int idx = left;
        for (int i = left + 1; i <= right; i++) {
            if (nums[idx] < nums[i]) {
                idx = i;
            }
        }
        return idx;
    }

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return null;
        }
        if (root1 == null && root2 != null) {
            return root2;
        }
        if (root1 != null && root2 == null) {
            return root1;
        }
        return mergeTreesRecursion1(root1, root2);
    }

    private TreeNode mergeTreesRecursion1(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        TreeNode root = new TreeNode(root1.val + root2.val);
        root.left = mergeTreesRecursion1(root1.left, root2.left);
        root.right = mergeTreesRecursion1(root1.right, root2.right);
        return root;
    }

    public TreeNode mergeTrees2(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        Stack<TreeNode> stack1 = new Stack<>();
        stack1.push(root2);
        stack1.push(root1);
        while (!stack1.isEmpty()) {
            TreeNode node1 = stack1.pop();
            TreeNode node2 = stack1.pop();
            node1.val += node2.val;
            if (node1.left != null && node2.left != null) {
                stack1.push(node2.left);
                stack1.push(node1.left);
            }
            if (node1.right != null && node2.right != null) {
                stack1.push(node2.right);
                stack1.push(node1.right);
            }
            if (node1.left == null && node2.left != null) {
                node1.left = node2.left;
            }
            if (node1.right == null && node2.right != null) {
                node1.right = node2.right;
            }
        }
        return root1;
    }

    @Test
    public void mergeTreesTest() {
        TreeNode root1 = TreeNode.constructTreeNode(new int[]{-1, 1, 3, 2, 5}, 1);
        TreeNode root2 = TreeNode.constructTreeNode(new int[]{-1, 2, 1, 3, -1, 4, -1, 7}, 1);
        TreeNode root = mergeTrees(root1, root2);
        TreeNode.levelPrintTreeNode(root);
    }

    public TreeNode mergeTreesRecursion(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return null;
        }
        TreeNode root = new TreeNode((node1 != null ? node1.val : 0) + (node2 != null ? node2.val : 0));
        root.left = mergeTreesRecursion(node1 != null ? node1.left : null, node2 != null ? node2.left : null);
        root.right = mergeTreesRecursion(node1 != null ? node1.right : null, node2 != null ? node2.right : null);
        return root;
    }

    /**
     * 给定二叉搜索树（BST）的根节点 root 和一个整数值 val。
     * leetCode 700
     * 你需要在 BST 中找到节点值等于 val 的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 null 。
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null || root.val == val) {
            return root;
        }
        TreeNode node;
        if (root.val > val) {
            node = searchBST(root.left, val);
        } else {
            node = searchBST(root.right, val);
        }
        return node;
    }

    public TreeNode searchBST1(TreeNode root, int val) {
        if (root == null) {
            return root;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.val == val) {
                return node;
            }
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return null;
    }

    /**
     * 98. 验证二叉搜索树
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean isBST = true;
        if (root.left != null) {
            isBST &= root.val > getNodeMax(root.left);
        }
        if (root.right != null) {
            isBST &= root.val < getNodeMin(root.right);
        }
        return isBST & isValidBST(root.left) & isValidBST(root.right);
    }

    static class BstInfo {
        int max;
        int min;
        boolean isBst;

        public BstInfo() {
        }

        public BstInfo(int leftMax, int rightMin, boolean isBst) {
            this.max = leftMax;
            this.min = rightMin;
            this.isBst = isBst;
        }
    }

    public boolean isValidBST1(TreeNode root) {
        return processBST(root).isBst;
    }

    public BstInfo processBST(TreeNode root) {
        if (root == null) {
            return null;
        }

        BstInfo left = processBST(root.left);
        BstInfo right = processBST(root.right);
        int max = root.val;
        int min = root.val;
        boolean isBsf = true;
        if (left != null) {
            max = Math.max(max, left.max);
            min = Math.min(min, left.min);
        }
        if (right != null) {
            max = Math.max(max, right.max);
            min = Math.min(min, right.min);
        }
        if (left != null && !left.isBst) {
            isBsf = false;
        }
        if (right != null && !right.isBst) {
            isBsf = false;
        }
        if (left != null && root.val <= left.max) {
            isBsf = false;
        }
        if (right != null && root.val >= right.min) {
            isBsf = false;
        }
        BstInfo bstInfo = new BstInfo(max, min, isBsf);
        return bstInfo;
    }

    @Test
    public void isValidBSTTest() {
        TreeNode root = TreeNode.constructTreeNode(new int[]{-1, 2, 1, 3}, 1);
//        TreeNode root = TreeNode.constructTreeNode(new int[]{-1, 5,4,6,-1,-1,3,7}, 1);
        System.out.println(isValidBST2(root));
    }

    public boolean isValidBST2(TreeNode root) {
        if (root == null) {
            return true;
        }
        List<Integer> list = new ArrayList<>();
        midOrder(root, list);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1) >= list.get(i)) {
                return false;
            }
        }
        return true;
    }

    public boolean isValidBST3(TreeNode root) {
        if (root == null) {
            return true;
        }
        Stack<TreeNode> stack = new Stack<>();
        long pre = Long.MIN_VALUE;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            if (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                if (node.val <= pre) {
                    return false;
                }
                pre = node.val;
                root = node.right;
            }
        }
        return true;
    }

    public boolean isValidBST4(TreeNode root) {
        return isValidBstRecursion(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBstRecursion(TreeNode node, long max, long min) {
        if (node == null) {
            return true;
        }
        boolean b1 = isValidBstRecursion(node.left, max, Math.min(min, node.val));
        boolean b2 = isValidBstRecursion(node.right, Math.max(max, node.val), min);
        if (node.val <= max || node.val >= min) {
            return false;
        }
        return b1 && b2;
    }

    public void midOrder(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        midOrder(node.left, list);
        list.add(node.val);
        midOrder(node.right, list);
    }


    public int getNodeMax(TreeNode node) {
        if (node == null) {
            return Integer.MIN_VALUE;
        }
        int max = node.val;
        return Math.max(max, Math.max(getNodeMax(node.left), getNodeMax(node.right)));
    }

    public int getNodeMin(TreeNode node) {
        if (node == null) {
            return Integer.MAX_VALUE;
        }
        int min = node.val;
        return Math.min(min, Math.min(getNodeMin(node.left), getNodeMin(node.right)));
    }

    @Test
    public void getMinimumDifferenceTest() {
//        TreeNode root = TreeNode.constructTreeNode(new int[]{-1, 4, 2, 6, 1, 3}, 1);
//        TreeNode root = TreeNode.constructTreeNode(new int[]{-1, 1, 0, 48, -1, -1, 12, 49}, 1);
//        TreeNode root = TreeNode.constructTreeNode(new int[]{-1, 5, 4, 7}, 1);
        TreeNode root = TreeNode.constructTreeNode(new int[]{-1, 236, 104, 701, -1, 227, -1, 911}, 1);
        System.out.println(getMinimumDifference2(root));
    }

    public int getMinimumDifference(TreeNode root) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        int res = Integer.MAX_VALUE;
        if (root.left != null) {
            res = Math.min(res, Math.abs(root.val - getNodeMax(root.left)));
        }
        if (root.right != null) {
            res = Math.min(res, Math.abs(root.val - getNodeMin(root.right)));
        }
        return Math.min(res, Math.min(getMinimumDifference(root.left), getMinimumDifference(root.right)));
    }

    public int getMinimumDifference2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        getMinimumDifferenceRecursion(root);
        pre = null;
        return val;
    }

    int val = Integer.MAX_VALUE;
    TreeNode pre;

    private void getMinimumDifferenceRecursion(TreeNode root) {
        if (root == null) {
            return;
        }
        getMinimumDifferenceRecursion(root.left);
        if (pre != null) {
            val = Math.min(val, Math.abs(pre.val - root.val));
        }
        pre = root;
        getMinimumDifferenceRecursion(root.right);
    }

    @Test
    public void findModeTest() {
        TreeNode root = TreeNode.constructTreeNode(new int[]{-1, 6, 2, 8, 0, 4, 7, 9, -1, -1, 2, 6}, 1);
//        TreeNode root = TreeNode.constructTreeNode(new int[]{-1, 1, 2, 3}, 1);
        System.out.println(Arrays.toString(findMode3(root)));
    }

    /**
     * 501. 二叉搜索树中的众数
     *
     * @param root
     * @return
     */
    public int[] findMode(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        Stack<TreeNode> stack = new Stack<>();
        int maxCount = 0;
        List<Integer> list = new ArrayList<>();
        Map<Integer, Integer> treeMap = new HashMap<>();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                TreeNode node = stack.pop();
                treeMap.put(node.val, treeMap.getOrDefault(node.val, 0) + 1);
                root = node.right;
            }
        }
        Set<Map.Entry<Integer, Integer>> entries =
                treeMap.entrySet();
        for (Map.Entry<Integer, Integer> entry : entries) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            if (maxCount == 0) {
                maxCount = value;
                list.add(key);
            } else if (value > maxCount) {
                list.clear();
                list.add(key);
                maxCount = value;
            } else if (value == maxCount) {
                list.add(key);
            }
        }
        return list.stream().mapToInt(v -> v).toArray();
    }

    int count = 0;
    int maxCount = 0;

    public int[] findMode2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        searchBst(root, list);
        return list.stream().mapToInt(a -> a).toArray();
    }

    public int[] findMode3(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        int count = 0;
        int maxCount = 0;
        List<Integer> list = new ArrayList<>();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                TreeNode node = stack.pop();
                if (pre == null) {
                    count = 1;
                } else if (pre.val == node.val) {
                    count++;
                } else {
                    count = 1;
                }
                pre = node;
                if (count == maxCount) {
                    list.add(node.val);
                } else if (count > maxCount) {
                    maxCount = count;
                    list.clear();
                    list.add(node.val);
                }

                root = node.right;
            }
        }
        return list.stream().mapToInt(a -> a).toArray();
    }


    private void searchBst(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        searchBst(root.left, list);
        if (pre == null) {
            count = 1;
        } else if (pre.val == root.val) {
            count++;
        } else {
            count = 1;
        }
        pre = root;
        if (count == maxCount) {
            list.add(root.val);
        } else if (count > maxCount) {
            maxCount = count;
            list.clear();
            list.add(root.val);
        }
        searchBst(root.right, list);
    }

    @Test
    public void mapSort() {
        Map<String, Integer> map = new HashMap<>();
        map.put("aa", 10);
        map.put("ab", 12);
        map.put("ac", 18);
        map.put("ad", 9);
        List<Map.Entry<String, Integer>> entries = map.entrySet().stream().sorted((a, b) -> b.getValue() - a.getValue()).collect(Collectors.toList());

    }

    static class CommonNode {
        boolean findA;
        boolean findB;
        TreeNode node;

        public CommonNode(boolean findA, boolean findB, TreeNode node) {
            this.findA = findA;
            this.findB = findB;
            this.node = node;
        }
    }

    /**
     * 236. 二叉树的最近公共祖先
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        return lowestCommonAncestorRecursion(root, p, q).node;
    }

    @Test
    public void lowestCommonAncestorTest() {
        TreeNode root = TreeNode.constructTreeNode(new int[]{-1, 3, 5, 1, 6, 2, 0, 8, -1, -1, 7, 4}, 1);
        TreeNode p = new TreeNode(5);
        TreeNode q = new TreeNode(0);
        System.out.println(lowestCommonAncestor(root, p, q).val);
    }

    public CommonNode lowestCommonAncestorRecursion(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return new CommonNode(false, false, null);
        }
        CommonNode left = lowestCommonAncestorRecursion(root.left, p, q);
        CommonNode right = lowestCommonAncestorRecursion(root.right, p, q);
        boolean findA = root.val == p.val || left.findA || right.findA;
        boolean findB = root.val == q.val || left.findB || right.findB;
        TreeNode node;
        if (left.node != null) {
            node = left.node;
        } else if (right.node != null) {
            node = right.node;
        } else if (findA && findB) {
            node = root;
        } else {
            node = null;
        }
        return new CommonNode(findA, findB, node);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root.val == p.val || root.val == q.val) {
            // 如果没找到，或者找到了p或q， 则直接返回，不用在向子树上找
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            // 两个节点为的最近公共节点已经找到
            return left;
        } else if (left != null && right == null) {
            // 左边已经找到了一个节点
            return left;
        } else if (left == null && right != null) {
            // 右边已经找到了一个节点
            return right;
        } else {
            return null;
        }
    }

    /**
     * 235. 二叉搜索树的最近公共祖先
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root.val == p.val || root.val == q.val) {
            return root;
        }
        TreeNode left = null;
        TreeNode right = null;
        if ((root.val > p.val && root.val < q.val) || (root.val < p.val && root.val > q.val)) {
            left = lowestCommonAncestor1(root.left, p, q);
            right = lowestCommonAncestor1(root.right, p, q);
        } else if (root.val > p.val && root.val > q.val) {
            left = lowestCommonAncestor1(root.left, p, q);
        } else {
            right = lowestCommonAncestor1(root.right, p, q);
        }
        if (left != null && right != null) {
            return root;
        } else if (left == null && right != null) {
            return right;
        } else if (left != null && right == null) {
            return left;
        }
        return null;
    }

    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode node = root;
        while (true) {
            if (node.val > p.val && node.val > q.val) {
                node = node.left;
            } else if (node.val < p.val && node.val < q.val) {
                node = node.right;
            } else {
                break;
            }
        }
        return node;
    }

    @Test
    public void lowestCommonAncestor1Test() {
        TreeNode root = TreeNode.constructTreeNode(new int[]{-1, 6, 2, 8, 0, 4, 7, 9, -1, -1, 3, 5}, 1);
        TreeNode p = new TreeNode(2);
        TreeNode q = new TreeNode(4);
        TreeNode node = lowestCommonAncestor1(root, p, q);
        System.out.println(node.val);
    }

    /**
     * 701. 二叉搜索树中的插入操作
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        // 此题并不需要添加节点后二叉树平衡， 直接找到空节点后，添加元素
        if (root == null) {
            return new TreeNode(val);
        }
        TreeNode node = root;
        while (true) {
            // 往左找
            if (node.val > val) {
                if (node.left == null) {
                    node.left = new TreeNode(val);
                    break;
                }
                node = node.left;
            } else {
                // 往右找
                if (node.right == null) {
                    node.right = new TreeNode(val);
                    break;
                }
                node = node.right;
            }
        }
        return root;
    }

    public TreeNode insertIntoBST1(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        insertInfoBSTRecursion(root, val);
        return root;
    }

    private void insertInfoBSTRecursion(TreeNode root, int val) {
        if (root == null) {
            TreeNode node = new TreeNode(val);
            if (pre.val > val) {
                pre.left = node;
            } else {
                pre.right = node;
            }
            return;
        }
        pre = root;
        if (root.val > val) {
            insertInfoBSTRecursion(root.left, val);
        } else {
            insertInfoBSTRecursion(root.right, val);
        }
    }

    /**
     * 450. 删除二叉搜索树中的节点
     *
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode1(TreeNode root, int key) {
        if (root == null) {
            return root;
        }
        TreeNode node = root;
        TreeNode pre = null;
        while (node != null) {
            if (node.val == key) {
                break;
            }
            pre = node;
            if (node.val > key) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        // 第一种情况
        // 未找到节点
        if (node == null) {
            return root;
        }

        // 找到节点
        /**
         * 第二种情况：左右孩子都为空（叶子节点），直接删除节点， 返回NULL为根节点
         * 第三种情况：删除节点的左孩子为空，右孩子不为空，删除节点，右孩子补位，返回右孩子为根节点
         * 第四种情况：删除节点的右孩子为空，左孩子不为空，删除节点，左孩子补位，返回左孩子为根节点
         * 第五种情况：左右孩子节点都不为空，则将删除节点的左子树头结点（左孩子）放到删除节点的右子树的最左面节点的左孩子上，返回删除节点右孩子为新的根节点。
         */
        if (pre == null) {
            return deleteOne(node);
        } else if (pre.left != null && pre.left.val == key) {
            pre.left = deleteOne(node);
        } else {
            pre.right = deleteOne(node);
        }
        return root;
    }

    public TreeNode deleteOne(TreeNode node) {
        if (node == null) {
            return node;
        }
        if (node.left == null) {
            return node.right;
        }
        if (node.right == null) {
            return node.left;
        }
        TreeNode cur = node.right;
        while (cur.left != null) {
            cur = cur.left;
        }
        cur.left = node.left;
        return node.right;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return root;
        }
        if (root.val == key) {
            if (root.left == null && root.right == null) {
                root = null;
                return root;
            } else if (root.left == null && root.right != null) {
                TreeNode tmp = root.right;
                return tmp;
            } else if (root.left != null && root.right == null) {
                TreeNode tmp = root.left;
                return tmp;
            } else {
                TreeNode left = root.left;
                TreeNode node = root.right;
                while (node.left != null) {
                    node = node.left;
                }
                node.left = left;
                TreeNode tmp = root.right;
                return tmp;
            }
        }
        if (root.val > key) root.left = deleteNode(root.left, key);
        if (root.val < key) root.right = deleteNode(root.right, key);
        return root;
    }

    @Test
    public void deleteNodeTest() {
        TreeNode root = TreeNode.constructTreeNode(new int[]{-1, 5, 3, 6, 2, 4, -1, 7}, 1);
        root = deleteNode(root, 2);
        TreeNode.levelPrintTreeNode(root);
    }

    /**
     * 669. 修剪二叉搜索树
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {
        /*if (root.val >= low && root.val <= high) {
            return trimBSTRecursion(root, low, high);
        } else if (root.val < low) {
            TreeNode tmp = root.right;
            root = null;
            return trimBSTRecursion(tmp, low, high);
        } else {
            TreeNode tmp = root.left;
            root = null;
            return trimBSTRecursion(tmp, low, high);
        }*/

        return trimBSTRecursion(root, low, high);
    }

    public TreeNode trimBST1(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }
        // 找到头节点
        while (root != null) {
            if (root.val >= low && root.val <= high) {
                break;
            }
            if (root.val < low) {
                root = root.right;
            } else {
                root = root.left;
            }
        }
        TreeNode cur = root;
        while (cur != null) {
            while (cur.left != null && cur.left.val < low) {
                cur.left = cur.left.right;
            }
            cur = cur.left;
        }
        cur = root;
        while (cur != null) {
            while (cur.right != null && cur.right.val > high) {
                cur.right = cur.right.left;
            }
            cur = cur.right;
        }
        return root;
    }

    @Test
    public void trimBSTTest() {
//        TreeNode root = TreeNode.constructTreeNode(new int[]{-1, 1, 0, 2}, 1);
        TreeNode root = TreeNode.constructTreeNode(new int[]{-1, 3, 0, 4, -1, 2, -1, -1, -1, -1, 1}, 1);
        TreeNode node = trimBST(root, 1, 3);
        TreeNode.levelPrintTreeNode(node);
    }

    public TreeNode trimBSTRecursion(TreeNode root, int low, int high) {
        if (root == null) {
            return root;
        }
        if (root.val < low) {
            return trimBSTRecursion(root.right, low, high);
        }
        if (root.val > high) {
            return trimBSTRecursion(root.left, low, high);
        }
        root.left = trimBSTRecursion(root.left, low, high);
        root.right = trimBSTRecursion(root.right, low, high);
        return root;
    }


    /**
     * 108. 将有序数组转换为二叉搜索树
     *
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {

        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    @Test
    public void sortedArrayToBSTTest() {
        int[] nums = {-10, -3, 0, 5, 9};
        TreeNode root = sortedArrayToBST(nums);
        TreeNode.levelPrintTreeNode(root);
    }

    public TreeNode sortedArrayToBST(int[] nums, int start, int end) {
        if (end < start) {
            return null;
        }
        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(nums, start, mid - 1);
        root.right = sortedArrayToBST(nums, mid + 1, end);
        return root;
    }

    /**
     * 538. 把二叉搜索树转换为累加树
     *
     * @param root
     * @return
     */
    public TreeNode convertBST1(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.add(cur);
                cur = cur.right;
            } else {
                TreeNode node = stack.pop();
                if (pre != null) {
                    node.val += pre.val;
                }
                pre = node;
                cur = node.left;
            }
        }
        return root;
    }

    public TreeNode convertBST(TreeNode root) {
        convertBSTRecursion(root);
        return root;
    }

    private void convertBSTRecursion(TreeNode root) {
        if (root == null) {
            return;
        }
        convertBSTRecursion(root.right);
        if (pre != null) {
            root.val +=pre.val;
        }
        pre = root;
        convertBSTRecursion(root.left);

    }

    public void rightRootLeft(TreeNode root) {
        if (root == null) {
            return;
        }
        rightRootLeft(root.right);
        System.out.println(root.val+"->");
        rightRootLeft(root.left);
    }

    @Test
    public void convertBSTTest() {
        TreeNode root = TreeNode.constructTreeNode(new int[]{-1,4,1,6,0,2,5,7,-1,-1,-1,3,-1,-1,-1,8}, 1);
//        rightRootLeft(root);
        TreeNode node = convertBST(root);
        TreeNode.levelPrintTreeNode(node);
    }
}

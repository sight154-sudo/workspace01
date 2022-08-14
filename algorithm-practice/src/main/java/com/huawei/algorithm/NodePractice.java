package com.huawei.algorithm;

import org.junit.Test;

import javax.management.relation.InvalidRoleInfoException;
import javax.sound.sampled.Port;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class NodePractice {

    @Test
    public void reverseIterator() {
        int[] arr = {1, 3, 5, 1};
        Node head = constructNode(arr);
        printNode(head);
        Node node = reverseNodeRecursion(head);
        printNode(node);
    }

    /**
     * 翻转链表  使用迭代
     *
     * @param head
     * @return
     */
    public Node reverseNodeIterator(Node head) {
        Node cur = head;
        Node pre = null;
        while (cur != null) {
            Node tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }

    public Node reverseNodeRecursion(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node node = reverseNodeRecursion(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }

    @Test
    public void isPalindromeNode() {
        int[] arr = {1};
        Node head = constructNode(arr);
        printNode(head);
        System.out.println(isPalindromeNode1(head));
    }

    /**
     * 判断是否是回文链表
     *
     * @param head
     * @return
     */
    public Boolean isPalindromeNode(Node head) {
        // 存放在数组中，然后使用双指针判断
        ArrayList list = new ArrayList();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        for (int i = 0, j = list.size() - 1; i < j; i++, j--) {
            if (list.get(i) != list.get(j)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 先找出链表的中点，再循环判断
     *
     * @param head
     * @return
     */
    public Boolean isPalindromeNode1(Node head) {
        // 1  -> 3 -> 5 -> 3 -> 1
        // 1 -> 2 -> 2 -> 1
        Node middle = middleNode(head);
        Node node = reverseNodeIterator(middle);
        while (node != null) {
            if (head.val != node.val) {
                return false;
            }
            node = node.next;
            head = head.next;
        }
        return true;
    }


    Node frontNode;

    /**
     * 使用递归判断 是否是回文链表
     *
     * @param head
     * @return
     */
    public Boolean isPalindromeNodeRecursion(Node head) {
        frontNode = head;
        return isPalindromeNodeRc(head);
    }

    private Boolean isPalindromeNodeRc(Node head) {
        if (head != null) {
            //先递归走到链表尾部  再从链表头部开始比较
            if (!isPalindromeNodeRc(head.next)) {
                return false;
            }
            if (head.val != frontNode.val) {
                return false;
            }
            frontNode = frontNode.next;
        }
        return true;
    }

    @Test
    public void middleNode() {
        int[] arr = {};
        Node head = constructNode(arr);
        printNode(head);
        Node node = middleNode(head);
        System.out.println(node.val);
    }

    /**
     * 找出链表的中心节点  使用快慢指针查找
     *
     * @param head
     * @return
     */
    public Node middleNode(Node head) {
        if (head == null) return head;
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }


    @Test
    public void mergeTwoNode() {
        int[] arr1 = {1, 4, 7, 9};
        int[] arr2 = {1, 3, 6, 8};
        Node node1 = constructNode(arr1);
        Node node2 = constructNode(arr2);
        Node node = mergeTwoNodeRecursion(node1, node2);
        printNode(node);
    }

    /**
     * 合并两个有序链表
     *
     * @param node1
     * @param node2
     * @return
     */
    public Node mergeTwoNode(Node node1, Node node2) {
        Node tmp = new Node();
        Node cur = tmp;
        while (node1 != null && node2 != null) {
            if (node1.val > node2.val) {
                cur.next = node2;
                node2 = node2.next;
            } else {
                cur.next = node1;
                node1 = node1.next;
            }
            cur = cur.next;
        }
        if (node1 == null) {
            cur.next = node2;
        }
        if (node2 == null) {
            cur.next = node1;
        }
        return tmp.next;
    }

    public Node mergeTwoNodeRecursion(Node node1, Node node2) {
        //找到小的节点，然后用小的节点的下一个节点与另一个链表比较 依次类推
        if (node1 == null) {
            //表示node1已经比较完毕
            return node2;
        } else if (node2 == null) {
            return node1;
        } else if (node1.val > node2.val) {
            node2.next = mergeTwoNodeRecursion(node1, node2.next);
            return node2;
        } else {
            node1.next = mergeTwoNodeRecursion(node1.next, node2);
            return node1;
        }
    }

    @Test
    public void hasCycle() {
        Node node1 = new Node(1, null);
        Node node2 = new Node(2, null);
        Node node3 = new Node(3, null);
        Node node4 = new Node(4, null);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;
        Boolean flag = hasCycle(node1);
        System.out.println(flag);
    }

    /**
     * 判断链表是否有环 使用快慢指针
     *
     * @param node
     * @return
     */
    public Boolean hasCycle(Node node) {
        if (node == null) return false;
        Node slow = node;
        Node fast = node.next;
        while (fast != null && fast.next != null) {
            if (slow == fast) {
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }

    List<Integer> list = new ArrayList<>();

    /**
     * 二叉树的前序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(BinaryTreeNode root) {
        if (root == null) return null;
        list.add(root.val);
        preorderTraversal(root.left);
        preorderTraversal(root.right);
        return list;
    }

    /**
     * n叉树的前序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> preorder(NTreeNode root) {
        if (root == null) return list;
        list.add(root.val);
//        preorder(root.children);
        return null;
    }

    /**
     * 构造链表
     *
     * @param arr
     * @return
     */
    public Node constructNode(int[] arr) {
        Node head = new Node();
        Node cur = head;
        for (int i : arr) {
            Node tmp = new Node(i, null);
            cur.next = tmp;
            cur = tmp;
        }
        return head.next;
    }

    /**
     * 打印链表
     *
     * @param head
     */
    public void printNode(Node head) {
        if (head == null) {
            System.out.println("null");
            return;
        }
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val).append("->");
            head = head.next;
        }
        sb.append("null");
        System.out.println(sb.toString());
    }

    @Test
    public void levelConstruct() {
        Integer[] arr = {null, 1, 2, 3, null, 5, 6, 7, null, null, 8, 9, null, null, null, 10};
        BinaryTreeNode head = levelConstruct(arr, 1);
        levelForeach(head);
        System.out.println();
        suffixPrint(head);
        System.out.println();
        suffixPrintNoRecursion(head);
    }

    public BinaryTreeNode levelConstruct(Integer[] arr, int index) {
        if (index >= arr.length || arr[index] == null) {
            return null;
        }
        BinaryTreeNode node = new BinaryTreeNode(arr[index], null, null);
        BinaryTreeNode head = node;
        node.left = levelConstruct(arr, 2 * index);
        node.right = levelConstruct(arr, 2 * index + 1);
        return head;
    }

    /**
     * 求二叉树叶子节点的数目
     *
     * @param root
     * @param num
     */
    public void leafNodeNum(BinaryTreeNode root, int num) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            num++;
        }
        leafNodeNum(root.left, num);
        leafNodeNum(root.right, num);
    }

    /**
     * 求二叉树的深度
     *
     * @param root
     */
    public void treeDepth(BinaryTreeNode root) {

    }

    /**
     * 层序遍历
     *
     * @param root
     */
    public void levelPrint(BinaryTreeNode root) {

    }

    /**
     * 二叉树前序遍历
     *
     * @param head
     */
    public void prePrint(BinaryTreeNode head) {
        if (head == null) {
            return;
        }
        System.out.print(head.val + "->");
        prePrint(head.left);
        prePrint(head.right);
    }

    /**
     * 非递归前序遍历
     *
     * @param root
     */
    public void prePrintNoRecursion(BinaryTreeNode root) {
        Stack<BinaryTreeNode> stack = new Stack<>();
        BinaryTreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                System.out.print(cur.val + "->");
                stack.add(cur);
                cur = cur.left;
            }
            if (!stack.isEmpty()) {
                cur = stack.pop().right;
            }
        }
    }

    /**
     * 中序遍历
     *
     * @param node
     */
    public void middlePrint(BinaryTreeNode node) {
        //{null, 1, 2, 3, null, 5, 6, 7};
        if (node == null) {
            return;
        }
        middlePrint(node.left);
        System.out.print(node.val + "->");
        middlePrint(node.right);
    }

    public void middlePrintNoRecursion(BinaryTreeNode root) {
        Stack<BinaryTreeNode> stack = new Stack<>();
        BinaryTreeNode tmp = root;
        while (!stack.isEmpty() || tmp != null) {
            // 一直遍历左子树，将根节点添加到栈中
            while (tmp != null) {
                stack.add(tmp);
                tmp = tmp.left;
            }
            // 取左子树的左叶子节点
            if (!stack.isEmpty()) {
                tmp = stack.pop();
                System.out.print(tmp.val + "->");
                // 然后遍历 该根节点下的右子树下的左子节点
                tmp = tmp.right;
            }

        }
    }

    public void middlePrintRecursion2(BinaryTreeNode root) {
        Stack<BinaryTreeNode> stack = new Stack<>();
        BinaryTreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                // 添加到栈中
                stack.add(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                System.out.print(cur.val + "->");
                cur = cur.right;
            }
        }
    }

    @Test
    public void stackDemo() {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 5; i++) {
            stack.add(i);
        }
        System.out.println(stack);
        for (Integer i : stack) {
            System.out.println(i);
        }
        System.out.println(stack);
    }

    /**
     * 后序遍历
     *
     * @param node
     */
    public void suffixPrint(BinaryTreeNode node) {
        if (node == null) {
            return;
        }
        suffixPrint(node.left);
        suffixPrint(node.right);
        System.out.print(node.val + "->");
    }

    public void suffixPrintNoRecursion(BinaryTreeNode root) {
        Stack<BinaryTreeNode> stack = new Stack<>();
        BinaryTreeNode cur = root;
        BinaryTreeNode pre = cur;
        // 先遍历到左子树的最左节点
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        while (!stack.isEmpty()) {
            cur = stack.pop();
            // 判断一个根节点被访问过的前提是，条件是右子节点为空，或cur为pre
            if (cur.right == null || cur.right == pre) {
                System.out.print(cur.val + "->");
                pre = cur;
            } else {
                // 右子树未遍历过，需要重复添加到栈中
                // 根元素再次入栈
                stack.add(cur);
                // 此时的右节点一定存在
                cur = cur.right;
                // 在依次放入左子树的根节点
                while (cur != null) {
                    stack.add(cur);
                    cur = cur.left;
                }
            }
        }
    }

    public void levelForeach(BinaryTreeNode root) {
        LinkedList<BinaryTreeNode> list = new LinkedList<>();
        list.add(root);
        BinaryTreeNode cur = root;
        while (!list.isEmpty()) {
            cur = list.poll();
            System.out.print(cur.val + "->");
            if (cur.left != null) {
                list.addLast(cur.left);
            }
            if (cur.right != null)
                list.addLast(cur.right);
        }
    }

    @Test
    public void buildTree() {
        int[] preorder = {3, 9, 6, 10, 20, 15, 7};
        int[] inorder = {6, 9, 10, 3, 15, 20, 7};
        BinaryTreeNode root = buildTree(preorder, inorder);
        levelForeach(root);
    }

    /**
     * 由先序遍历和中序遍历构造一颗二叉树
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public BinaryTreeNode buildTree(int[] preorder, int[] inorder) {
        int m = preorder.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(preorder, 0, m - 1, map, 0, m - 1);
    }

    private BinaryTreeNode buildTree(int[] preorder, int pleft, int pright, Map<Integer, Integer> map, int inleft, int inright) {
        if (pleft > pright || inleft > inright) {
            return null;
        }
        int pindex = map.get(preorder[pleft]);
        BinaryTreeNode root = new BinaryTreeNode(preorder[pleft], null, null);
        root.left = buildTree(preorder, pleft + 1, pindex - inleft + pleft, map, inleft, pindex - 1);
        root.right = buildTree(preorder, pindex - inleft + pleft + 1, pright, map, pindex + 1, inright);
        return root;
    }

    /**
     * 由中序遍历和后序遍历求二叉树
     *
     * @param inorder
     * @param postorder
     * @return
     */
    public BinaryTreeNode buildTreeByPrePost(int[] inorder, int[] postorder) {
        int m = inorder.length;
        int n = postorder.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            map.put(inorder[i], i);
        }
        return buildTreeByPrePost(postorder, 0, m - 1, map, 0, n - 1);
    }

    private BinaryTreeNode buildTreeByPrePost(int[] postorder, int inleft, int inright, Map<Integer, Integer> map, int pleft, int pright) {
        if (inleft > inright || pleft > pright) {
            return null;
        }
        int pindex = map.get(postorder[pright]);
        BinaryTreeNode root = new BinaryTreeNode(postorder[pright], null, null);
        root.left = buildTreeByPrePost(postorder, inleft, pindex - 1, map, pleft, pindex - inleft + pleft - 1);
        root.right = buildTreeByPrePost(postorder, pindex + 1, inright, map, pindex - inleft + pleft, pright - 1);
        return root;
    }

    @Test
    public void buildTreeByPrePost() {
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};
        BinaryTreeNode root = buildTreeByPrePost(inorder, postorder);
        levelForeach(root);
    }

    public BinaryTreeNode buildTreeByPrePost1(int[] inorder, int[] postorder) {
        int m = inorder.length;
        int n = postorder.length;
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < m; i++) {
            map.put(inorder[i], i);
        }
        return buildTreeByPrePost1(postorder, map, 0 , m - 1);
    }

    int index = 4;

    private BinaryTreeNode buildTreeByPrePost1(int[] postorder, Map<Integer, Integer> map ,int begin, int end) {
        if (begin > end) {
            return null;
        }
        int val = postorder[index];
        int pindex = map.get(val);
        BinaryTreeNode root = new BinaryTreeNode(val, null, null);
        index--;
        root.right = buildTreeByPrePost1(postorder, map, pindex+1, end);
        root.left = buildTreeByPrePost1(postorder, map, begin, pindex-1);
        return root;
    }

    @Test
    public void buildTreeByPrePost1() {
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};
        BinaryTreeNode root = buildTreeByPrePost1(inorder, postorder);
        levelForeach(root);
    }

}

/**
 * 二叉树构造
 */
class BinaryTreeNode {
    int val;
    BinaryTreeNode left;
    BinaryTreeNode right;

    BinaryTreeNode() {
    }

    BinaryTreeNode(int val) {
        this.val = val;
    }

    BinaryTreeNode(int val, BinaryTreeNode left, BinaryTreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class NTreeNode {
    public int val;
    public List<NTreeNode> children;

    public NTreeNode() {
    }

    public NTreeNode(int _val) {
        val = _val;
    }

    public NTreeNode(int _val, List<NTreeNode> _children) {
        val = _val;
        children = _children;
    }
}


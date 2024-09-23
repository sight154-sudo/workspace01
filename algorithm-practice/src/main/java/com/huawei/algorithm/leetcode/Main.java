package com.huawei.algorithm.leetcode;

import com.google.common.collect.Lists;
import com.huawei.algorithm.leetcode.picPractice.Edge;
import com.huawei.algorithm.leetcode.picPractice.Node;
import org.junit.Test;

import java.util.*;

public class Main {

    /**
     * 给定一组字符串，求合并这些字符串后，字典序最小，输出最小字典序的字符串
     *
     * @param list
     * @return
     */
    public String mergeMinDictStr(List<String> list) {
        Collections.sort(list, (a, b) -> a.concat(b).compareTo(b.concat(a)));
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s);
        }
        return sb.toString();
    }


    public String mergeMinDictStrForce(List<String> list) {
        return mergeMinDictStrForce(list, "", "");
    }

    public String mergeMinDictStrForce(List<String> list, String pre, String min) {
        if (list.isEmpty()) {
            return pre;
        }
        String res;
        for (int i = 0; i < list.size(); i++) {
            res = mergeMinDictStrForce(removeItem(list, i), pre.concat(list.get(i)), min);
            if (min.length() == 0) {
                min = res;
            } else {
                min = min.compareTo(res) > 0 ? res : min;
            }
        }
        return min;
    }

    public List<String> removeItem(List<String> list, int index) {
        List<String> newList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (i != index) {
                newList.add(list.get(i));
            }
        }
        return newList;
    }

    public void randomMergeMinDictStr(int count, int maxVal, int len) {
        for (int c = 0; c < count; c++) {
            List<String> list1 = new ArrayList<>();
            List<String> list2 = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                String str = getRandStr(maxVal);
                list1.add(str);
                list2.add(str);
            }
            String res1 = mergeMinDictStr(list1);
            String res2 = mergeMinDictStrForce(list2);
            if (!res1.equals(res2)) {
                System.out.println(list1);
                System.out.printf("res1 : %s   res2: %s\n", res1, res2);
                System.out.println("Fuck Code!!!");
            }
        }
    }

    public String getRandStr(int len) {
        int r = (int) (Math.random() * len) + 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < r; i++) {
            char c = (char) ((int) (Math.random() * 26) + 97);
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * {{1,2},{1,5},{2,4},{4,5}}
     * 有一个会议预订时间的二维数组， 返回会议时间不重叠时的，最多的可以正常开会的最多会议数量
     *
     * @param meetTime
     * @return
     */
    public int getMaxMeetCount(int[][] meetTime) {
        Arrays.sort(meetTime, Comparator.comparingInt(a -> a[1]));
        int startTime = 0;
        int result = 0;
        for (int[] meet : meetTime) {
            if (meet[0] >= startTime) {
                startTime = meet[1];
                result++;
            }
        }
        return result;
    }

    public void randGetMaxMeetCountTest(int count, int maxVal, int len) {
        for (int c = 0; c < count; c++) {
            int[][] arr1 = new int[len][2];
            int[][] arr2 = new int[len][2];
            for (int i = 0; i < len; i++) {
                int start = (int) (Math.random() * (maxVal - 1)) + 1;
                int end = (int) (Math.random() * maxVal) + 1;
                while (end <= start) {
                    end = (int) (Math.random() * maxVal) + 1;
                }
                arr1[i][0] = start;
                arr2[i][0] = start;
                arr1[i][1] = end;
                arr2[i][1] = end;
            }
//            printArr(arr2);
            int res1 = getMaxMeetCount(arr1);
            int res2 = getMaxMeetCount(arr2);
            if (res1 != res2) {
                System.out.printf("res1: %d   res2: %d", res1, res2);
                System.out.println("Fuck Code!!!");
            }
        }
    }

    public void printArr(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(Arrays.toString(arr[i]));
        }
        System.out.println();
    }

    public int getMaxMeetCountForce(int[][] meetTime) {
        return getMaxMeetCountForce(meetTime, 0, 0);
    }

    public int getMaxMeetCountForce(int[][] meetTime, int startTime, int res) {
        if (meetTime.length == 0) {
            return res;
        }
        int ans = res;
        for (int i = 0; i < meetTime.length; i++) {
            if (meetTime[i][0] >= startTime) {
                startTime = meetTime[i][1];
                int q = getMaxMeetCountForce(removeMeet(meetTime, i), startTime, res + 1);
                ans = Math.max(ans, q);
            }
        }
        return ans;
    }

    public int[][] removeMeet(int[][] meet, int index) {
        int[][] newMeet = new int[meet.length - 1][2];
        int j = 0;
        for (int i = 0; i < meet.length; i++) {
            if (i != index) {
                newMeet[j++] = meet[i];
            }
        }
        return newMeet;
    }


    @Test
    public void getMaxMeetCountTest() {
//        int[][] meetTime = {{69, 83}, {93, 97}, {44, 98}, {3, 64},{68, 85},{17, 94},{21, 97},{1, 89},{46, 87},{19, 30}};
//        System.out.println(getMaxMeetCountForce(meetTime));
        randGetMaxMeetCountTest(10000, 10000, 10000);
    }


    @Test
    public void mergeMinDictStrTest() {
        List<String> list = Lists.newArrayList("ab", "b", "a", "ba");
        String minStr = mergeMinDictStrForce(list);
        System.out.println(minStr);
        randomMergeMinDictStr(20, 10, 10);
//        for (int i = 0; i < 100; i++) {
//            System.out.println(getRandStr());
//        }

    }

    @Test
    public void canFinishTest() {
        int n = 2;
        /*int[][] arr = {
                {1,0}
        };*/
        int[][] arr = {
                {0, 1}, {1, 0}
        };
        System.out.println(canFinish(n, arr));
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] in = new int[numCourses];
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < prerequisites.length; i++) {
            list.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }
        for (int i = 0; i < in.length; i++) {
            if (in[i] == 0) {
                boolean visited = dfs(in, list, i);
                if (!visited) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean dfs(int[] in, List<List<Integer>> list, int num) {
        if (in[num] == 1) {
            return false;
        }
        if (in[num] == 2) {
            return true;
        }
        in[num] = 1;
        for (Integer i : list.get(num)) {
            boolean flag = dfs(in, list, i);
            if (!flag) {
                return false;
            }
        }
        in[num] = 2;
        return true;
    }


    public boolean canFinish1(int numCourses, int[][] prerequisites) {
        int N = numCourses;
        int[] in = new int[N];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            in[prerequisites[i][0]]++;
            List<Integer> list = map.computeIfAbsent(prerequisites[i][1], k -> new ArrayList<>());
            list.add(prerequisites[i][0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < in.length; i++) {
            if (in[i] == 0) {
                queue.add(i);
            }
        }
        int count = 0;
        while (!queue.isEmpty()) {
            count++;
            Integer peek = queue.poll();
            List<Integer> list2 = map.get(peek);
            if (list2 != null) {
                for (Integer num : list2) {
                    in[num]--;
                    if (in[num] == 0) {
                        queue.add(num);
                    }
                }
            }
        }
        return count == N;
    }


    @Test
    public void printMidIteratorTest() {
        String str = "a{b{d,e{g,h{,i}}},c{f}}";
        printMidIterator(str);
    }

    public void printMidIterator(String str) {
        // a{b{d,e{g,h{,i}}},c{f}}
        int index = 0;
        int k = 1;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode<Character> parent = null;
        TreeNode<Character> node = null;
        while (index < str.length()) {
            switch (str.charAt(index)) {
                case '{':
                    stack.push(node);
                    k = 1;
                    break;
                case ',':
                    k = 2;
                    break;
                case '}':
                    stack.pop();
                    break;
                default:
                    char c = str.charAt(index);
                    node = new TreeNode<>(c);
                    if (parent == null) {
                        parent = node;
                    } else {
                        if (k == 1) {
                            stack.peek().left = node;
                        } else {
                            stack.peek().right = node;
                        }
                    }
            }
            index++;
        }
        prePrint2(parent);
    }

    public void midPrint(TreeNode head) {
        Stack<TreeNode> stack = new Stack<>();
        List<TreeNode> list = new ArrayList<>();
        while (head != null || !stack.isEmpty()) {
            while (head != null) {
                stack.add(head);
                head = head.left;
            }
            if (!stack.isEmpty()) {
                head = stack.pop();
                list.add(head);
                head = head.right;
            }
        }
        for (TreeNode n : list) {
            System.out.print(n.val + "->");
        }
    }

    public void postPrint(TreeNode head) {
        Stack<TreeNode> stack = new Stack<>();
        List<TreeNode> list = new ArrayList<>();
        TreeNode pre = null;
        while (head != null || !stack.isEmpty()) {
            while (head != null) {
                stack.add(head);
                head = head.left;
            }
            TreeNode peek = stack.peek();
            if (peek.right == null || peek.right == pre) {
                list.add(peek);
                stack.pop();
                pre = peek;
            } else {
                head = peek.right;
            }
        }
        StringBuffer sb = new StringBuffer();
        for (TreeNode node : list) {
            sb.append(node.val).append("->");
        }
        System.out.println(sb.substring(0, sb.length() - 1));
    }

    @Test
    public void printTest() {
        int[] arr = {-1, 1, 2, 3, 4, 5, 6, 7};
        TreeNode parent = constructTreeNode(arr, 1);
        postPrintRecursion(parent);
        postPrint(parent);
    }

    public TreeNode constructTreeNode(int[] arr, int index) {
        if (index >= arr.length || arr[index] == -1) {
            return null;
        }
        TreeNode node = new TreeNode(arr[index]);
        node.left = constructTreeNode(arr, 2 * index);
        node.right = constructTreeNode(arr, 2 * index + 1);
        return node;
    }

    public void prePrintRecursion(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.val + "->");
        prePrintRecursion(node.left);
        prePrintRecursion(node.right);
    }

    public void midPrintRecursion(TreeNode node) {
        if (node == null) {
            return;
        }
        midPrintRecursion(node.left);
        System.out.print(node.val + "->");
        midPrintRecursion(node.right);
    }

    public void postPrintRecursion(TreeNode node) {
        if (node == null) {
            return;
        }
        postPrintRecursion(node.left);
        postPrintRecursion(node.right);
        System.out.print(node.val + "->");
    }

    public void prePrint(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        stack.add(node);
        List<TreeNode> list = new ArrayList<>();
        while (!stack.isEmpty()) {
            TreeNode peek = stack.pop();
            list.add(peek);
            if (peek.right != null) {
                stack.add(peek.right);
            }
            if (peek.left != null) {
                stack.add(peek.left);
            }
        }
        for (TreeNode n : list) {
            System.out.print(n.val + "->");
        }
    }

    public void prePrint2(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        List<TreeNode> list = new ArrayList<>();
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.add(node);
                list.add(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                node = stack.pop();
                node = node.right;
            }
        }
        for (TreeNode n : list) {
            System.out.print(n.val + "->");
        }
    }


    class TreeNode<T> {
        T val;
        TreeNode left;
        TreeNode right;

        public TreeNode(T c) {
            this.val = c;
        }
    }

    @Test
    public void changePositionTest() {
//        String s1 = "010";
        String s1 = "010001";
//        String s2 = "110";
        String s2 = "111010";
       /* int len = 100000;
        int n = 0;
        StringBuffer sb = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        while (n < len) {
            sb.append(Math.random() > 0.5 ? '0':'1');
            sb2.append(Math.random() > 0.5 ? '0':'1');
            n++;
        }
        String s1 = sb.toString();
        String s2 = sb2.toString();
        System.out.println(changePosition(s1, s2));*/
        System.out.println(changePosition1(s1, s2));
        randomChangePosition(1000, 10000);
    }

    public int changePosition(String s1, String s2) {
        int count = 0;
        for (int i = 0; i < s1.length(); i++) {
            for (int j = i + 1; j < s1.length(); j++) {
                if ((s1.charAt(i) == '0' && s1.charAt(j) == '1') || (s1.charAt(i) == '1' && s1.charAt(j) == '0')) {
                    if (!(s2.charAt(i) == '1' && s2.charAt(j) == '1')) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public int changePosition1(String s1, String s2) {
        int zero = 0;
        int one = 0;
        int zeroOne = 0; // s1为0   s2为0时的个数 与其他1的个数
        int oneZero = 0; // s1为1   s2为0时的个数 与其他0的个数 减去s1= 0 s2=0重复计算的个数
        for (int i = 0; i < s1.length(); i++) {
            int a = s1.charAt(i) - 48;
            int b = s2.charAt(i) - 48;
            if (a == 0) {
                zero++;
                if (b == 0) {
                    zeroOne++;
                }
            } else {
                one++;
                if (b == 0) {
                    oneZero++;
                }
            }
        }
        return zeroOne * one + (oneZero * (zero - zeroOne));
    }


    public void randomChangePosition(int count, int len) {
        for (int c = 0; c < count; c++) {
            StringBuffer sb = new StringBuffer();
            StringBuffer sb2 = new StringBuffer();
            int n = 0;
            while (n < len) {
                sb.append(Math.random() > 0.5 ? '0' : '1');
                sb2.append(Math.random() > 0.5 ? '0' : '1');
                n++;
            }
            String s1 = sb.toString();
            String s2 = sb2.toString();
            if (changePosition(s1, s2) != changePosition1(s1, s2)) {
                System.out.println(s1 + "  " + s2);
                System.out.println("Fuck Code!!!");
            } else {
                System.out.println("Nice Code!!!");
            }
        }
    }

    @Test
    public void test111() {
        String str = "1234a";
        System.out.println(str.matches("\\d+"));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Integer> map = new HashMap<>();
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            if ("#".equals(s)) {
                break;
            } else if (s.matches("\\d")) {
                Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
                List<Map.Entry<String, Integer>> list = new ArrayList<>(entrySet);
                Collections.sort(list, (a, b) -> a.getValue() == b.getValue() ? a.getKey().compareTo(b.getKey()) : b.getValue() - a.getValue());
                Integer n = Integer.parseInt(s);
                StringBuffer sb = new StringBuffer();
                int i = 0;
                while (i < n) {
                    sb.append(list.get(i).getKey()
                    ).append(",");
                    i++;
                }
                System.out.println(sb.substring(0, sb.length() - 1));
            } else {
                map.put(s, map.getOrDefault(s, 0) + 1);
            }
        }

    }

    @Test
    public void findKthLargestTest() {
        randomFindKthLargest(10, 1000, 10);
    }

    public void randomFindKthLargest(int count, int maxVal, int len) {
        for (int c = 0; c < count; c++) {
            int[] arr1 = new int[len];
            int[] arr2 = new int[len];
            for (int i = 0; i < len; i++) {
                int val = (int) (Math.random() * maxVal) - (int) (Math.random() * maxVal);
                arr1[i] = val;
                arr2[i] = val;
            }
            int k = (int) Math.random() * len + 1;
            k = Math.min(k, len);
            if (findKthLargest(arr1, k) != findKthLargest1(arr2, k)) {
                System.out.println("fuck code!!!");
            } else {
                System.out.println("Nice Code!!!");
            }
        }
    }


    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    public int findKthLargest1(int[] nums, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int num : nums) {
            priorityQueue.add(num);
            if (priorityQueue.size() > k) {
                priorityQueue.poll();
            }
        }
        return priorityQueue.peek();
    }

    public void minShi(int[][] arr) {
        /*int M = arr.length;
        int N = arr[0].length;
        int[][] dp = new int[M][N];
        for (int i = 0; i < arr.length; i++) {

        }*/
        boolean[][] flag = new boolean[arr.length][arr[0].length];
        int min = minShi(arr, -1, 0, 0, flag);
        System.out.println(min);
    }

    int[][] base = {
            {0, 1}, {1, 1}, {1, 0}
    };

    public int minPath2(int[][] arr, int i, int j, int pre) {
        if (i < 0 || j < 0 || i >= arr.length || j >= arr[0].length) {
            return Integer.MAX_VALUE;
        }
        if (i == arr.length - 1 && j == arr[0].length - 1) {

            return arr[i][j] == pre ? arr[i][j] - 1 : arr[i][j];
        }
        int num = arr[i][j];
        int res = Integer.MAX_VALUE;
        for (int[] nums : base) {
            int p1 = minPath2(arr, i + nums[0], j + nums[1], num);
            if (p1 != Integer.MAX_VALUE) {
                res = Math.min(res, p1 + (num == pre ? num - 1 : num));
            }
        }
        /*if (j + 1 < arr[0].length) {
            int p1 = num + minPath(arr, i, j + 1);
            res = Math.min(p1, res);
        }
        if (i + 1 < arr.length) {
            int p2 = num + minPath(arr, i + 1, j);
            res = Math.min(res, p2);
        }
        if (i + 1 < arr.length && j + 1 < arr[0].length) {
            int p3 = num + minPath(arr, i + 1, j + 1);
            res = Math.min(res, p3);
        }*/
        return res;
    }

    public int minPath(int[][] arr, int i, int j) {
        if (i < 0 || j < 0 || i >= arr.length || j >= arr[0].length) {
            return Integer.MAX_VALUE;
        }
        if (i == arr.length - 1 && j == arr[0].length - 1) {
            return arr[i][j];
        }
        int num = arr[i][j];
        int res = Integer.MAX_VALUE;
        for (int[] nums : base) {
            int p1 = minPath(arr, i + nums[0], j + nums[1]);
            if (p1 != Integer.MAX_VALUE) {
                res = Math.min(res, p1 + num);
            }
        }
        /*if (j + 1 < arr[0].length) {
            int p1 = num + minPath(arr, i, j + 1);
            res = Math.min(p1, res);
        }
        if (i + 1 < arr.length) {
            int p2 = num + minPath(arr, i + 1, j);
            res = Math.min(res, p2);
        }
        if (i + 1 < arr.length && j + 1 < arr[0].length) {
            int p3 = num + minPath(arr, i + 1, j + 1);
            res = Math.min(res, p3);
        }*/
        return res;
    }

    public int minPathDp2(int[][] arr) {
        int m = arr.length;
        int n = arr[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = arr[0][0];
        for (int i = 1; i < n; i++) {
            if (arr[0][i - 1] == arr[0][i]) {
                dp[0][i] = dp[0][i - 1] + arr[0][i] - 1;
            } else {
                dp[0][i] = dp[0][i - 1] + arr[0][i];
            }

        }
        for (int i = 1; i < m; i++) {
            if (arr[i - 1][0] == arr[i][0]) {
                dp[i][0] = dp[i - 1][0] + arr[i][0] - 1;
            } else {
                dp[i][0] = dp[i - 1][0] + arr[i][0];
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int x = arr[i][j] == arr[i - 1][j] ? arr[i][j] - 1 : arr[i][j];
                int y = arr[i][j] == arr[i][j - 1] ? arr[i][j] - 1 : arr[i][j];
                int z = arr[i][j] == arr[i - 1][j - 1] ? arr[i][j] - 1 : arr[i][j];
                dp[i][j] = Math.min(dp[i][j - 1] + y, Math.min(dp[i - 1][j] + x, dp[i - 1][j - 1] + z));
            }
        }
        return dp[m - 1][n - 1];
    }

    @Test
    public void minPathTest() {
        int[][] arr = {
                {0, 2, 2},
                {1, 2, 1},
                {1, 2, 1},
        };
        System.out.println(minPathDp2(arr));
        System.out.println(minPath2(arr, 0, 0, -1));

        randMinPath(100, 1000, 10);
    }

    public int minPathDp(int[][] arr) {
        int m = arr.length;
        int n = arr[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = arr[0][0];
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + arr[0][i];
        }
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + arr[i][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = arr[i][j] + Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1]));
            }
        }
        return dp[m - 1][n - 1];
    }

    public void randMinPath(int count, int maxVal, int len) {
        for (int c = 0; c < count; c++) {
            int m = (int) (Math.random() * len) + 1;
            int n = (int) (Math.random() * len) + 1;
            int[][] arr1 = new int[m][n];
            int[][] arr2 = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int val = (int) (Math.random() * maxVal);
                    arr1[i][j] = val;
                    arr2[i][j] = val;
                }
            }
            if (minPath2(arr1, 0, 0, -1) != minPathDp2(arr2)) {
                System.out.println("Fuck Code!!!");
            } else {
                System.out.println("Nice Code!!!");
            }

        }
    }

    @Test
    public void minShiTest() {
        int[][] arr = {
                {0, 2, 1, 1},
                {2, 1, 2, 1},
                {1, 3, 4, 1},
        };
        int[][] arr2 = {
                {0, 2},
                {3, 2},
                {1, 2},
        };
        minShiRecursion(arr2);
        minShiPlus(arr2);
        randMinShi(10, 30, 5);
    }

    public void randMinShi(int count, int maxVal, int len) {
        for (int c = 0; c < count; c++) {
            int m = (int) (Math.random() * len) + 1;
            int n = (int) (Math.random() * len) + 1;
            int[][] arr1 = new int[m][n];
            int[][] arr2 = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int val = (int) (Math.random() * maxVal) + 1;
                    arr1[i][j] = val;
                    arr2[i][j] = val;
                }
            }
            if (minShiRecursion(arr1) != minShiPlus(arr2)) {
                System.out.println("Fuck Code!!!");
            } else {
                System.out.println("Nice Code!!!");
            }

        }
    }

    public int minShiDp(int[][] arr) {
        int m = arr.length;
        int n = arr[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = arr[0][0];

        return 0;
    }


    // resur
    public int minShiRecursion(int[][] arr) {
        boolean[][] flag = new boolean[arr.length][arr[0].length];
        return minShi(arr, -1, 0, 0, flag);
    }

    public int minShi(int[][] arr, int pre, int i, int j, boolean[][] flag) {
        if (i == arr.length - 1 && j == arr[0].length - 1) {
            return arr[i][j] == pre ? arr[i][j] - 1 : arr[i][j];
        }
        if (i < 0 || j < 0 || i >= arr.length || j >= arr[0].length) {
            return Integer.MAX_VALUE;
        }
        if (flag[i][j]) {
            return Integer.MAX_VALUE;
        }
        int res = Integer.MAX_VALUE;
        int num = arr[i][j];
        flag[i][j] = true;
        int[][] base = {{0, 1}, {0, -1}, {1, 0}, {1, -1}, {1, 1}, {-1, -1}, {-1, 0}, {-1, 1}};
        for (int[] nums : base) {
            int add = (pre == num ? num - 1 : num);
            int p = minShi(arr, num, i + nums[0], j + nums[1], flag);
            if (p != Integer.MAX_VALUE) {
                res = Math.min(add + p, res);
            }
        }
        flag[i][j] = false;
        return res;
    }

    public int minShiPlus(int[][] arr) {
        int m = arr.length;
        int n = arr[0].length;

        int[][] dp = new int[m][n];
        PriorityQueue<Point> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a.dis));
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][0] = arr[0][0];
        boolean[][] visited = new boolean[m][n];
        priorityQueue.add(new Point(0, 0, arr[0][0]));
        int[][] base = {{0, 1}, {0, -1}, {1, 0}, {1, -1}, {1, 1}, {-1, -1}, {-1, 0}, {-1, 1}};
        while (!priorityQueue.isEmpty()) {
            Point current = priorityQueue.poll();
            for (int[] bs : base) {
                int newX = current.x + bs[0];
                int newY = current.y + bs[1];
                if (newX >= 0 && newX < m && newY >= 0 && newY < n && !visited[newX][newY]) {
                    visited[newX][newY] = true;
                    int newDis = current.dis + arr[newX][newY];
                    if (arr[current.x][current.y] == arr[newX][newY]) {
                        newDis -= 1;
                    }
                    if (newDis < dp[newX][newY]) {
                        dp[newX][newY] = newDis;
                        priorityQueue.add(new Point(newX, newY, newDis));
                    }
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    class Point {
        int x;
        int y;
        int dis;

        public Point(int x, int y, int dis) {
            this.x = x;
            this.y = y;
            this.dis = dis;
        }
    }

    /**
     * 有 n 个网络节点，标记为 1 到 n。
     * 给你一个列表 times，表示信号经过 有向 边的传递时间。 times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点， wi 是一个信号从源节点传递到目标节点的时间。
     * 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。
     *
     * @param times
     * @param n
     * @param k
     * @return
     */
    public int networkDelayTime1(int[][] times, int n, int k) {
        Map<Integer, Node<Integer>> map = new HashMap<>();
        for (int i = 0; i < times.length; i++) {
            int from = times[i][0];
            int to = times[i][1];
            if (!map.containsKey(from)) {
                map.put(from, new Node<>(from));
            }
            if (!map.containsKey(to)) {
                map.put(to, new Node<>(to));
            }
            Node<Integer> fromNode = map.get(from);
            Node<Integer> toNode = map.get(to);
            Edge edge = new Edge(times[i][2], fromNode, toNode);
            fromNode.edge.add(edge);
            fromNode.next.add(toNode);
            toNode.edge.add(edge);
        }
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[k] = 0;
        boolean[] bool = new boolean[n + 1];
        Node<Integer> minNode = getMinDisNode(dp, bool, map);
        while (minNode != null) {
            int dis = dp[minNode.val];
            List<Edge> edges = minNode.edge;
            for (Edge edge : edges) {
                Node<Integer> to = edge.to;
                dp[to.val] = Math.min(dp[to.val], dis + edge.weight);
            }
            bool[minNode.val] = true;
            minNode = getMinDisNode(dp, bool, map);
        }
//        System.out.println(Arrays.toString(dp));
        int max = -1;
        for (int i = 1; i < dp.length; i++) {
            if (dp[i] == Integer.MAX_VALUE) {
                return -1;
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }

    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, Node2> map = new HashMap<>();
        for (int i = 0; i < times.length; i++) {
            int from = times[i][0];
            int to = times[i][1];
            if (!map.containsKey(from)) {
                map.put(from, new Node2(from));
            }
            if (!map.containsKey(to)) {
                map.put(to, new Node2(to));
            }
            Node2 fromNode = map.get(from);
            Node2 toNode = map.get(to);
            fromNode.next.put(toNode, times[i][2]);
        }
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[k] = 0;
        boolean[] bool = new boolean[n + 1];
        Node2 minNode = getMinDisNode2(dp, bool, map);
        while (minNode != null) {
            int dis = dp[minNode.val];
            Map<Node2, Integer> nextMap = minNode.next;
            for (Map.Entry<Node2, Integer> entry : nextMap.entrySet()) {
                Node2 to = entry.getKey();
                int weight = entry.getValue();
                dp[to.val] = Math.min(dp[to.val], dis + weight);
            }
            bool[minNode.val] = true;
            minNode = getMinDisNode2(dp, bool, map);
        }
        int max = -1;
        for (int i = 1; i < dp.length; i++) {
            if (dp[i] == Integer.MAX_VALUE) {
                return -1;
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }

    class Node2 {
        public int val;
        public Map<Node2, Integer> next;

        public Node2(int val) {
            this.val = val;
            next = new HashMap<>();
        }
    }

    @Test
    public void networkDelayTimeTest() {
        int[][] times = {
                {2, 1, 4}, {2, 3, 1}, {3, 4, 1}
        };
        /*int[][] times = {
                {1,2,1}
        };*/
        /*int[][] times = {
                {1,2,1}
        };*/
        int n = 4;
        int k = 2;
        System.out.println(networkDelayTime(times, n, k));
        System.out.println(networkDelayTime1(times, n, k));
    }

    public Node2 getMinDisNode2(int[] dp, boolean[] bool, Map<Integer, Node2> map) {
        int min = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 1; i < dp.length; i++) {
            if (!bool[i] && dp[i] < min) {
                min = dp[i];
                index = i;
            }
        }
        return map.get(index);
    }


    public Node getMinDisNode(int[] dp, boolean[] bool, Map<Integer, Node<Integer>> map) {
        int min = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 1; i < dp.length; i++) {
            if (!bool[i] && dp[i] < min) {
                min = dp[i];
                index = i;
            }
        }
        return map.get(index);
    }

    @Test
    public void subStrConditionTest() {
        // xxcdefg
        //cdefghi
        String a = "umgflrbtyh";
        String b = "pwkmpgamlk";
        int v = 5;
        System.out.println(subStrConditionForce(a, b, v));
        System.out.println(subStrCondition(a, b, v));
        randomSubStrCondition(100, 1000);
    }

    public void randomSubStrCondition(int count, int len) {
        for (int c = 0; c < count; c++) {
            StringBuffer sb1 = new StringBuffer();
            StringBuffer sb2 = new StringBuffer();
            for (int i = 0; i < len; i++) {
                char c1 = (char) ((int) (Math.random() * 26) + 97);
                char c2 = (char) ((int) (Math.random() * 26) + 97);
                sb1.append(c1);
                sb2.append(c2);
            }
            String a = sb1.toString();
            String b = sb2.toString();
            int v = (int) (Math.random() * 26);
            if (subStrCondition(a, b, v) != subStrConditionForce(a, b, v)) {
                System.out.println(a + " " + b + " v:" + v);
                System.out.println("Fuck Code");
            }
        }
    }

    public int subStrConditionForce(String a, String b, int v) {
        int[] sub = new int[a.length()];
        for (int i = 0; i < a.length(); i++) {
            sub[i] = Math.abs(a.charAt(i) - b.charAt(i));
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < a.length(); i++) {
            for (int j = i; j < a.length(); j++) {
                int res = getSum(sub, i, j);
                if (res <= v) {
                    max = Math.max(max, j - i + 1);
                } else {
                    break;
                }
            }
        }
        return max;
    }

    public int getSum(int[] sub, int i, int j) {
        int res = 0;
        for (; i <= j; i++) {
            res += sub[i];
        }
        return res;
    }

    public int subStrCondition(String a, String b, int v) {
        int max = Integer.MIN_VALUE;
        int[] sub = new int[a.length()];
        for (int i = 0; i < a.length(); i++) {
            sub[i] = Math.abs(a.charAt(i) - b.charAt(i));
        }
        int win = 0;
        int i = 0;
        for (int j = 0; j < a.length(); j++) {
            win += sub[j];
            while (win > v) {
                win -= sub[i++];
            }
            max = Math.max(max, j - i + 1);
        }
        return max;
    }

    public int[] findStrongMe(int[][] arr) {
        Map<Integer, Node3> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int p = arr[i][0];
            int z = arr[i][1];
            if (!map.containsKey(p)) {
                map.put(p, new Node3(p));
            }
            if (!map.containsKey(z)) {
                map.put(z, new Node3(z));
            }
            Node3 parent = map.get(p);
            Node3 zi = map.get(z);
            parent.next.add(zi);
        }
        int[] res = new int[map.size() + 1];
        for (Map.Entry<Integer, Node3> entry : map.entrySet()) {
            boolean[] bool = new boolean[map.size() + 1];
            Node3 node = entry.getValue();
            int dp = getStrongMe(node, bool, node.val);
            res[node.val] = dp;
        }
        return res;
    }

    @Test
    public void getStrongMeTest() {
        int[][] arr = {
                {4, 3}, {3, 2}
        };
        /*int[][] arr = {
                {2,1}, {3, 2}
        };*/
        /*int[][] arr = {
                {2, 1}, {2, 5}, {2, 3}, {5, 4}, {1, 6}, {6, 4}, {3, 4}
        };*/
        int[] res = findStrongMe(arr);
        StringBuffer sb = new StringBuffer();
        int i = 1;
        for (; i < res.length - 1; i++) {
            sb.append(res[i]).append(",");
        }
        sb.append(res[i]);
        System.out.println(sb);
    }

    public int getStrongMe(Node3 parent, boolean[] bool, int k) {
        if (parent.next == null) {
            return 0;
        }
        bool[parent.val] = true;
        int res = 0;
        for (Node3 node : parent.next) {
            if (!bool[node.val]) {
                res += getStrongMe(node, bool, k);
                if (k > node.val) {
                    res++;
                }
            }
        }
        return res;
    }


    class Node3 {
        int val;
        List<Node3> next;

        public Node3(int val) {
            this.val = val;
            next = new ArrayList<>();
        }
    }

    @Test
    public void robotRunTest() {
        int[][] arr1 = {
                {0, 0, 1}, {0, 0, 0}, {1, 0, 0}
        };
        int[][] arr = {
                {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1}, {1, 1, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 0}
        };
        System.out.println(Arrays.toString(robotRun(arr)));
    }

    public int[] robotRun(int[][] arr) {
        robotRunForce(arr, 0, 0);
        int noArrived = 0;
        int c = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 0) {
                    noArrived++;
                } else if (arr[i][j] == 3) {
                    c++;
                }
            }
        }
        return new int[]{c, noArrived};
    }

    public boolean robotRunForce(int[][] arr, int i, int j) {
        if (i >= arr.length || j >= arr[0].length) {
            return false;
        }
        if (i == arr.length - 1 && j == arr[0].length - 1) {
            // 走到右下角
            arr[i][j] = 2;
            return true;
        }
        if (arr[i][j] == 1) {
            return false;
        }
        arr[i][j] = 2;
        boolean p1 = robotRunForce(arr, i, j + 1);
        boolean p2 = robotRunForce(arr, i + 1, j);
        if (!p1 && !p2) {
            arr[i][j] = 3;
        }
        return p1 || p2;
    }

    @Test
    public void sundayTest() {
        int[][] arr = {
                {2, 1, 0, 3}, {0, 1, 2, 1}, {0, 3, 0, 0}, {0, 0, 0, 0}
        };
        /*int[][] arr = {
                {2, 1, 2, 3}, {0, 1, 0, 0}, {0, 1, 0, 0}, {0, 1, 0, 0}
        };*/
        System.out.println(sunday1(arr));
    }

    public int sunday1(int[][] arr) {
        List<int[]> arrived = new ArrayList<>();
        int[] a = {-1, -1};
        int[] b = {-1, -1};
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 2) {
                    if (a[0] != -1) {
                        b[0] = i;
                        b[1] = j;
                    } else {
                        a[0] = i;
                        a[1] = j;
                    }
                } else if (arr[i][j] == 3) {
                    int[] target = new int[]{i, j};
                    arrived.add(target);
                }
            }
        }
        int count = 0;
        for (int[] ints : arrived) {
            boolean[][] bool = new boolean[arr.length][arr[0].length];
            boolean cana = canArrived(arr, bool, a[0], a[1], ints);
            for (int i = 0; i < bool.length; i++) {
                Arrays.fill(bool[i], false);
            }
            boolean canb = canArrived(arr, bool, b[0], b[1], ints);
            if (cana && canb) {
                count++;
            }

        }
        return count;
    }

    public boolean canArrived(int[][] arr, boolean[][] bool, int i, int j, int[] target) {
        if (i < 0 || j < 0 || i >= arr.length || j >= arr[i].length || bool[i][j]) {
            return false;
        }
        bool[i][j] = true;
        if (arr[i][j] == 1) {
            return false;
        }
        if (i == target[0] && j == target[1]) {
            return true;
        }
        int[][] base = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean res = false;
        for (int[] bs : base) {
            int newI = i + bs[0];
            int newJ = j + bs[1];
            boolean flag = canArrived(arr, bool, newI, newJ, target);
            if (flag) {
                return true;
            }
        }
        return res;
    }


    public int sunday(int[][] arr) {
        int[] a = {-1, -1};
        int[] b = {-1, -1};
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 2) {
                    if (a[0] != -1) {
                        b[0] = i;
                        b[1] = j;
                    } else {
                        a[0] = i;
                        a[1] = j;
                    }
                }
            }
        }
        List<int[]> aList = new ArrayList<>();
        boolean[][] abool = new boolean[arr.length][arr[0].length];
        sundayForce(arr, abool, aList, a[0], a[1]);
        List<int[]> bList = new ArrayList<>();
        boolean[][] bbool = new boolean[arr.length][arr[0].length];
        sundayForce(arr, bbool, bList, b[0], b[1]);
        int count = 0;
        for (int[] aInt : aList) {
            for (int[] bInt : bList) {
                if (aInt[0] == bInt[0] && aInt[1] == bInt[1]) {
                    count++;
                }
            }
        }
        return count;
    }

    public void sundayForce(int[][] arr, boolean[][] bool, List<int[]> list, int i, int j) {
        if (i < 0 || j < 0 || i >= arr.length || j >= arr[i].length || bool[i][j]) {
            return;
        }
        bool[i][j] = true;
        if (arr[i][j] == 1) {
            return;
        }
        if (arr[i][j] == 3) {
            list.add(new int[]{i, j});
        }
        int[][] base = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] bs : base) {
            int newI = i + bs[0];
            int newJ = j + bs[1];
            sundayForce(arr, bool, list, newI, newJ);
        }

    }

    class ServerNode {
        public String str;
        public List<ServerNode> next;

        public ServerNode(String str) {
            this.str = str;
            next = new ArrayList<>();
        }
    }

    @Test
    public void getNormalServerTest() {
        ArrayList<String> stringList = Lists.newArrayList("a1-a2", "a5-a6", "a2-a3");
        ArrayList<String> server = Lists.newArrayList("a5", "a2");
        System.out.println(getNormalServer(stringList, server));
    }


    public String getNormalServer(List<String> stringList, List<String> server) {
        Map<String, ServerNode> map = new HashMap<>();
        Map<String, Boolean> boolMap = new HashMap<>();
        for (int i = 0; i < stringList.size(); i++) {
            String[] split = stringList.get(i).split("-");
            String p = split[1];
            String z = split[0];
            boolMap.put(p, true);
            boolMap.put(z, true);
            if (!map.containsKey(p)) {
                map.put(p, new ServerNode(p));
            }
            if (!map.containsKey(z)) {
                map.put(z, new ServerNode(z));
            }
            ServerNode pNode = map.get(p);
            ServerNode zNode = map.get(z);
            pNode.next.add(zNode);
        }
        for (int i = 0; i < server.size(); i++) {
            ServerNode serverNode = map.get(server.get(i));
            Deque<ServerNode> deque = new LinkedList<>();
            deque.addLast(serverNode);
            while (!deque.isEmpty()) {
                ServerNode poll = deque.pollFirst();
                boolMap.put(poll.str, false);
                for (ServerNode node : poll.next) {
                    if (boolMap.get(node.str)) {
                        deque.addLast(node);
                    }
                }
            }
        }
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, Boolean> entry : boolMap.entrySet()) {
            if (entry.getValue()) {
                sb.append(entry.getKey()).append(",");
            }
        }
        return sb.length() > 0 ? sb.substring(0, sb.length() - 1) : ",";
    }

    @Test
    public void getMinLeafPathTest() {
        int[] arr = {-1, 3, 5, 7, -1, 10, 2, 4};
        System.out.println(getMinLeafPath(arr));
    }

    public String getMinLeafPath(int[] arr) {
        TreeNode1 parent = getTreeNode1(arr, 1);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        getAllPath(parent, result, list);
        return "";
    }

    public void getAllPath(TreeNode1 node, List<List<Integer>> result, List<Integer> list) {
        if (node == null) {
            return;
        }
        list.add(node.val);
        getAllPath(node.left, result, list);
        getAllPath(node.right, result, list);
        if (node.right == null && node.left == null) {
            result.add(new ArrayList<>(list));
        }
        list.remove(list.size() - 1);
    }

    public TreeNode1 getTreeNode1(int[] arr, int index) {
        if (index >= arr.length || arr[index] == -1) {
            return null;
        }
        TreeNode1 node = new TreeNode1(arr[index]);
        node.left = getTreeNode1(arr, index * 2);
        node.right = getTreeNode1(arr, index * 2 + 1);
        return node;
    }

    class TreeNode1 {
        int val;
        TreeNode1 left;
        TreeNode1 right;

        public TreeNode1(int val) {
            this.val = val;
        }

    }

    @Test
    public void getNoLeafPostTest() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        getNoLeafPost(arr);
    }

    public String getNoLeafPost(int[] arr) {
        int len = arr.length;
        int size = 2 * 1;
        int i = 1;
        while (size - 1 < len) {
            i++;
            size *= 2;
        }
        int res = (int) (Math.pow(2, i - 1) - 1);
        int[] newArr = new int[res + 1];
        newArr[0] = -1;
        for (int j = 1; j < newArr.length; j++) {
            newArr[j] = arr[j - 1];
        }
        TreeNode1 parent = getTreeNode1(newArr, 1);
//        postPrint();
        postPrint(parent);
        return "";
    }

    public void postPrint(TreeNode1 head) {
        Stack<TreeNode1> stack = new Stack<>();
        List<TreeNode1> list = new ArrayList<>();
        TreeNode1 pre = null;
        while (head != null || !stack.isEmpty()) {
            while (head != null) {
                stack.add(head);
                head = head.left;
            }
            TreeNode1 peek = stack.peek();
            if (peek.right == null || peek.right == pre) {
                list.add(peek);
                pre = peek;
                stack.pop();
            } else {
                head = peek.right;
            }
        }
        StringBuffer sb = new StringBuffer();
        for (TreeNode1 treeNode1 : list) {
            sb.append(treeNode1.val).append("->");
        }
        System.out.println(sb.substring(0, sb.length() - 1));
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k > nums.length) {
            return false;
        }
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            sum += num;
            max = Math.max(max, num);
        }
        if (sum % k != 0) {
            return false;
        }
        int subSum = sum / k;
        if (max > subSum) {
            return false;
        }

        return false;
    }

    public boolean canPartitionK(int[] nums, int k, int[] bucket, int index, int target) {
        if (index == nums.length) {
            return true;
        }
        for (int i = 0; i < k; i++) {
            if (bucket[i] + nums[index] > target) {
                continue;
            }
            bucket[i] += nums[index];
            if (canPartitionK(nums, k, bucket, index + 1, target)) {
                return true;
            }
            bucket[i] -= nums[index];
        }
        return false;
    }


    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        subsets(nums, result, list, 0);

        return result;
    }

    public void subsets(int[] nums, List<List<Integer>> result, List<Integer> list, int start) {
        result.add(new ArrayList<>(list));
        for (int i = start; i < nums.length; i++) {
            list.add(nums[i]);
            subsets(nums, result, list, i + 1);
            list.remove(list.size() - 1);
        }
    }

    @Test
    public void subsetsWithDupTest() {
        int[] nums = {1, 2, 2};
        System.out.println(subsetsWithDup(nums));
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        subsetsWithDup(nums, result, list, 0);
        return result;
    }

    public void subsetsWithDup(int[] nums, List<List<Integer>> result, List<Integer> list, int start) {
        result.add(new ArrayList<>(list));
        for (int i = start; i < nums.length; i++) {
            if (list.contains(nums[i])) {
                continue;
            }
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            list.add(nums[i]);
            subsetsWithDup(nums, result, list, i + 1);
            list.remove(list.size() - 1);
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        combine(n, k, result, list, 1);
        return result;
    }

    public void combine(int n, int k, List<List<Integer>> result, List<Integer> list, int index) {
        if (list.size() == k) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = index; i <= n; i++) {
            list.add(i);
            combine(n, k, result, list, i+1);
            list.remove(list.size()-1);
        }
    }

    @Test
    public void combinationSum3Test() {
        int k = 3;
        int n = 9;
        System.out.println(combinationSum3(k, n));
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        combinationSum3(n, k, result, list, 1);
        return result;
    }

    public void combinationSum3(int n, int k, List<List<Integer>> result, List<Integer> list, int index) {
        if (n == 0 && list.size() == k) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = index; i <= 9 ; i++) {
            if (n - i < 0) {
                continue;
            }
            n-=i;
            list.add(i);
            combinationSum3(n, k, result, list, i+1);
            list.remove(list.size()-1);
            n+=i;
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();

        return result;
    }

    public void permute (List<List<Integer>> result, List<Integer> list, int[] nums, int index) {

    }
}

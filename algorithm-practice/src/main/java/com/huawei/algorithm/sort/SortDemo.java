package com.huawei.algorithm.sort;


import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author king
 * @date 2022/8/9-23:04
 * @Desc
 */
public class SortDemo {
    /**
     * 爬楼梯 70
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 01背包问题
     *
     * @param item 每个物品的重量
     * @param val  每个物品的价值
     * @param w    背包最大重量
     * @return
     */
    public int zeroonePack(int[] item, int[] val, int w) {
        // dp[i][j] 表示拿第i个物品时，载重最大为j时，所取价值最多为dp[i][j]
        // dp[i][j] = max(dp[i-1][j], dp[i-1][j-item[i]]+val[i])
        int m = item.length;
        int[][] dp = new int[m + 1][w + 1];
        // 外层物品的重量
        for (int i = 1; i <= item.length; i++) {
            // 内层背包的重量
            for (int j = 1; j <= w; j++) {
                if (item[i - 1] <= j) {
                    // 如果当前物品可以放入背包,则判断前一个物品，与放入当前物品后，的价值进行比较
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - item[i - 1]] + val[i - 1]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        for (int i = 0; i <= m; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        return dp[m][w];
    }

    @Test
    public void zeroonepack() {
        int[] val = {15, 20, 30};
        int[] item = {1, 3, 4};
        int w = 4;
        int i = zeroonePack1(item, val, w);
        System.out.println(i);
    }

    public int zeroonePack1(int[] item, int[] val, int w) {
        // dp[i] 表示最大载重为i时，，所取价值最多为dp[i]
        // dp[j] = max(dp[j], dp[j-item[i]]+val[j])
        int m = item.length;
        int[] dp = new int[w + 1];
        // 外层物品的重量
        for (int i = 0; i < item.length; i++) {
            // 内层背包的重量
            for (int j = w; j >= item[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - item[i]] + val[i]);
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[w];
    }

    /**
     * 分割和子集
     *
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        // 分解为dp问题，查询是否存在背包最大载重为target时，能放满背包
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0) {
            return false;
        }
        int target = Arrays.stream(nums).sum() / 2;
        // dp[i] = max(dp[i], dp[j-nums[i]]+nums[i]);
        int[] dp = new int[target + 1];
        for (int i = 0; i < nums.length; i++) {
            for (int j = target; j >= nums[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[target] == target;
    }

    public int lastStoneWeight(int[] arr) {
        // 将石头分为近似下等的两堆，相撞后剩下的石头重量最小
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        int target = sum / 2;
        // dp[i][j] 表示选择第i个石头时， 最大载重为j时，的最大价值dp[i][j]
        int[][] dp = new int[arr.length + 1][target + 1];
        // 初始化时，都为0
        for (int i = 1; i <= arr.length; i++) {
            for (int j = 0; j <= target; j++) {
                dp[i][j] = dp[i - 1][j];
                if (arr[i - 1] <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - arr[i - 1]] + arr[i - 1]);
                }
            }
        }
        System.out.println(Arrays.toString(dp[arr.length]));
        return sum - dp[arr.length][target] * 2;
    }

    @Test
    public void lastStoneWeight() {
        int[] arr = {31, 26, 33, 21, 40};
        System.out.println(lastStoneWeight(arr));
    }

    /**
     * 目标和
     *
     * @param arr
     * @param m
     * @return
     */
    public int targetSum(int[] arr, int k) {
        // 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：运算结果等于 target 的不同 表达式 的数目
        // 设构成负数和为m , 则正数和为sum-m-m = target => m = (sum-target)/2  且m>0, sum-target为偶数
        // 转换方程 得 : dp[i][j] 从数组中前i个数中取数，和为j的方式有dp[i][j]种
        // dp[i][j] 当arr[i] > m 时，不选， dp[i][j] = dp[i-1][j] ，当arr[i] < m时， 有两种可以，选或不选
        // 选时: dp[i][j] = dp[i-1][j-arr[i]]  不选时 dp[i][j] = dp[i-1][j]  合并为 dp[i][j] =  dp[i-1][j-arr[i]] + dp[i-1][j]
        // 初始化 当i =0 时， 若j = 0 ,则dp[1][j] = 1 不选，有一种方式， 若j > 0, 则没有可能 dp[i][j] = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        int target = (sum - k) / 2;
        if (target < 0 || (sum - k) % 2 != 0) {
            return 0;
        }
        int[][] dp = new int[arr.length + 1][target + 1];
        for (int i = 0; i <= target; i++) {
            dp[0][i] = i == 0 ? 1 : 0;
        }
        for (int i = 1; i <= arr.length; i++) {
            for (int j = 0; j <= target; j++) {
                if (arr[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - arr[i - 1]];
                }
            }
        }
        return dp[arr.length][target];
    }

    public int targetSum1(int[] arr, int target) {
        int sum = Arrays.stream(arr).sum();
        int diff = sum - target;
        if (diff < 0 || diff % 2 != 0) {
            return 0;
        }
        int neg = diff / 2;
        int[] dp = new int[neg + 1];
        dp[0] = 1;
        for (int i = 0; i < arr.length; i++) {
            for (int j = neg; j >= arr[i]; j--) {
                dp[j] = dp[j] + dp[j - arr[i]];
            }
        }
        return dp[neg];
    }

    @Test
    public void targetSum() {
        int[] arr = {1};
        int target = 1;
        System.out.println(targetSum(arr, target));
    }

    @Test
    public void targetSum1() {
        int[] arr = {1, 1, 1, 1, 1};
        int target = 3;
        System.out.println(targetSum1(arr, target));
    }

    /**
     * M个相同苹果放到N个相同篮子里有多少种放法,允许有篮子不放。
     * 1<=M<=10，1<=N<=10
     * <p>
     * 例如5个苹果三个篮子，3，1，1 和 1,1,3是同一种放法
     *
     * @param m
     * @param n
     * @return
     */
    public int shareApple(int m, int n) {
        // m 或n = 0
        if (m < 0 || n < 0) {
            return 0;
        }
        // 当只有1个苹果或，1个盘子时，放法都为1
        if (m == 0 || m == 1 || n == 1) {
            return 1;
        }
        // 当n > m 时，说明有n-m个盘子空着， f(m,n) = f(m,m)
        if (n > m) {
            return shareApple(m, m);
        }
        // 当n < m时，有两种情况 1:有空盘子， 即至少有一个空盘子 f(m,n) = f(m,n-1)
        // 2 : 无空盘子，每个盘子至少有一个 ,每个盘子都去除一个后 f(m,n) = f(m-n, n)
        return shareApple(m, n - 1) + shareApple(m - n, n);
    }

    @Test
    public void shareApples() {
        System.out.println(shareApple(0, 8));
    }

    /**
     * 分苹果
     *
     * @param m
     * @param n
     * @return
     */
    public int shareApplesByDfs(int m, int n) {
        // dp[i][j] 表示第i个苹果时，有j个盘子可放，有dp[i][j]种放法
        // 当 j > i  dp[i][j] = dp[i][i]  当j < i时，苹果有两个情况放在j个盘子，或不放 dp[i][j] = dp[i][j-1] + dp[i-j][j]
        // 初始化 当i = 1 或j =1时，都只有一种放法
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            dp[0][i] = 1;
            dp[1][i] = 1;
        }
        for (int i = 0; i <= m; i++) {
            dp[i][1] = 1;
        }
        for (int i = 2; i <= m; i++) {
            for (int j = 2; j <= n; j++) {
                if (j > i) {
                    dp[i][j] = dp[i][i];
                } else {
                    dp[i][j] = dp[i - j][j] + dp[i][j - 1];
                }
            }
        }
        for (int i = 0; i <= m; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        return dp[m][n];
    }

    @Test
    public void shareApplesByDfs() {
        System.out.println(shareApplesByDfs(7, 3));
    }


    @Test
    public void canPartition() {
        int[] nums = {1, 6, 11, 5};
        System.out.println(canPartition(nums));
    }

    /**
     * coin[2,5,7] amount 27    最小硬币数 {}
     *
     * @param coins  每枚硬币的面值
     * @param amount 使用硬币拼奏出目标数
     * @return 组成amount的最小硬币数
     */
    public int coinChange(int[] coins, int amount) {
        // dp[i] = Math.min(dp[i-2]+1, dp[i-5]+1, dp[i-7]+1)
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                if (i - coins[j] >= 0 && dp[i - coins[j]] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount];
    }

    @Test
    public void coinChange() {
        int[] coins = {2, 5, 7};
        System.out.println(coinChange(coins, 27));
    }

    /**
     * m条流水线，n个任务,求完成任务所花费的时间
     *
     * @param m
     * @param n
     * @param costs
     * @return
     */
    public int taskTime(int m, int n, int[] costs) {
        Arrays.sort(costs);
        int[] res = new int[m];
        String s = "";
//        List<Integer> collect = Arrays.stream(s.split("")).map(Integer::parseInt).sorted(Comparator.comparingInt(o -> o)).collect(Collectors.toList());
        for (int i = 0; i < costs.length; i += m) {
            for (int j = 0; j < m && i + j < costs.length; j++) {
                res[j] += costs[j + i];
            }
        }
        int max = res[0];
        for (int i = 0; i < m; i++) {
            if (max < res[i]) {
                max = res[i];
            }
        }
        return max;
    }

    @Test
    public void taskTime() {
        int m = 3, n = 5;
        int[] costs = {8, 4, 2, 3, 10};
        System.out.println(taskTime(m, n, costs));
    }

    @Test
    public void testaaa() {
        char c = '\t';
        System.out.println((int) c);
    }

    /**
     * 路灯问题
     *
     * @param arr
     * @return
     */
    public String lampsProblem(int[] arr) {
        // 更新路灯的照明距离
        for (int i = 0; i < arr.length; i++) {
            int lDis = arr[i] - 100;
            int left = i - 1;
            // 更新左侧
            while (left >= 0 && lDis > 0) {
                if (arr[left] == 100) {
                    left--;
                    lDis -= 100;
                    continue;
                }
                if (arr[left] < lDis) {
                    arr[left] = lDis > 100 ? 100 : lDis;
                }
                left--;
                lDis -= 100;
            }
            int rDis = arr[i] - 100;
            int right = i + 1;
            // 更新右侧
            while (right < arr.length && rDis > 0) {
                if (arr[right] == 100) {
                    right++;
                    rDis -= 100;
                    continue;
                }
                if (arr[right] < rDis) {
                    arr[right] = rDis > 100 ? 100 : rDis;
                }
                right++;
                lDis -= 100;
            }
        }
        int res = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            int num = arr[i] + arr[i + 1];
            if (num < 100) {
                res += 100 - num;
            }
        }
        return res + "";
    }

    @Test
    public void lamp() {
//        int[] arr = {50,20,80,20,30,300};
//        int[] arr = {50,70,20,70};
//        int[] arr = {50,20,60,20,30,230};
        int[] arr = {50, 20, 60, 120, 30, 230};
        System.out.println(lampsProblem(arr));
    }

    /**
     * 水果搬运问题
     */
    public void fruitProblem() {

    }

    @Test
    public void lengthOfLIS() {
//        int[] nums = {10,9,2,5,3,7,101,18};
//        int[] nums = {2, 9, 3, 6, 5, 1, 7};
        int[] nums = {0, 1, 0, 3, 2, 3};
        int i = lengthOfLIS(nums);
        System.out.println("i = " + i);
    }

    /**
     * 求最长递增子序列
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        // 0, 1, 0, 3, 2, 3
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
        }
        return dp[nums.length - 1];
    }

    @Test
    public void lwstDis() {
        String s1 = "mitcmu";
        String s2 = "mtacnu";
        System.out.println(lwstDis(s1, s2));
    }

    /**
     * 莱文斯坦距离
     *
     * @param s1
     * @param s2
     * @return
     */
    public int lwstDis(String s1, String s2) {
        char[] src = s1.toCharArray();
        char[] dest = s2.toCharArray();
        // src:  ymoti    dest: mw
        int[][] dp = new int[dest.length][src.length];
        // 初始化
        if (src[0] == dest[0]) {
            dp[0][0] = 0;
        } else {
            dp[0][0] = 1;
        }
        for (int i = 1; i < src.length; i++) {
            dp[0][i] = dest[0] == src[i] ? dp[0][i - 1] : dp[0][i - 1] + 1;
        }
        for (int i = 1; i < dest.length; i++) {
            dp[i][0] = src[0] == dest[i] ? dp[i - 1][0] : dp[i - 1][0] + 1;
        }
        // 转移方程
        // dp[i][j] = src[i] == dest[j] => dp[i][j] = min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])
        // src[i] != dest[j] => dp[i][j] = min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])+1
        for (int i = 1; i < dest.length; i++) {
            for (int j = 1; j < src.length; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
                if (src[j] != dest[i]) {
                    dp[i][j] = Math.min(dp[i - 1][j] + 1, Math.min(dp[i][j - 1] + 1, dp[i - 1][j - 1] + 1));
                }
            }
        }
        return dp[dest.length - 1][src.length - 1];
    }

    @Test
    public void maxCommonSubString() {
        String s1 = "mitcmu";
        String s2 = "mtacnu";
        System.out.println(maxCommonSubString(s1, s2));
    }

    /**
     * 最长公共子串
     *
     * @param s1
     * @param s2
     * @return
     */
    public int maxCommonSubString(String s1, String s2) {
        char[] src = s1.toCharArray();
        char[] dest = s2.toCharArray();
        int[][] dp = new int[dest.length][src.length];
        // 初始化
        if (src[0] == dest[0]) {
            dp[0][0] = 1;
        } else {
            dp[0][0] = 0;
        }
        for (int i = 1; i < src.length; i++) {
            dp[0][i] = dest[0] == src[i] ? 1 : dp[0][i - 1];
        }
        for (int i = 1; i < dest.length; i++) {
            dp[i][0] = src[0] == dest[i] ? 1 : dp[i - 1][0];
        }
        // 转移方程
        // dp[i][j] = src[i] == dest[j] => dp[i][j] = min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])
        // src[i] != dest[j] => dp[i][j] = min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])+1
        for (int i = 1; i < dest.length; i++) {
            for (int j = 1; j < src.length; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], Math.max(dp[i][j - 1], dp[i - 1][j - 1]));
                if (src[j] == dest[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j], Math.max(dp[i][j - 1], dp[i - 1][j - 1] + 1));
                }
            }
        }
        return dp[dest.length - 1][src.length - 1];
    }

    @Test
    public void maxSubArray() {
//        int[] nums = {2,3,-2,4};
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
//        int[] nums = {-2, -3, -1, -5};
        int i = maxSubArray(nums);
        System.out.println("i = " + i);
    }

    /**
     * 最大子数组和
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        // -2,1,-3,4,-1,2,1,-5,4  =>  4,-1,2,1  => 6
        // 贪心算法 若子数组和少于0，则重新计算和
        /*int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] + sum < 0) {
                sum = 0;
            } else {
                sum+= nums[i];
            }
            max = Math.max(max, sum);
        }*/
        /*int pre = 0, sum = nums[0];
        for (int i = 0; i < nums.length; i++) {
            pre = Math.max(pre + nums[i], pre);
            sum = Math.max(pre, sum);
        }*/
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int result = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            result = Math.max(result, dp[i]);
        }
        return result;
    }

    @Test
    public void maxProduct2() {
//        int[] nums = {2, 3, -2, 4, -5};
//        int[] nums = {-4,-3,-2};
        int[] nums = {2, -5, -2, -4, 3};
        int max = maxProduct(nums);
        System.out.println("max = " + max);
    }

    /**
     * 乘积最大的子数组的乘积
     *
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        int max = nums[0], min = nums[0], res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int tmp = max;
            max = Math.max(nums[i], Math.max(max * nums[i], min * nums[i]));
            min = Math.min(nums[i], Math.min(tmp * nums[i], min * nums[i]));
            res = Math.max(max, res);
        }
        return res;
    }

    @Test
    public void testaaabbb() {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

    /**
     * 判断是否可以构造三角形
     *
     * @param args
     */
    public static void main111(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[3];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
        }
        int a = arr[0], b = arr[1], c = arr[2];
        if (a + b > c && a + c > b && b + c > a) {
            System.out.println(true);
            return;
        }
        System.out.println(false);
        Arrays.sort(arr);
        if (arr[0] + arr[1] > arr[2]) {
            System.out.println(true);
            return;
        }
        System.out.println(false);
    }

    /**
     * 给定4个数，可以组成三个数的()
     * @param args
     */
    public static void main1112(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[4];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
        }
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        constructNum(arr, list, sb);
        System.out.println(list);
    }

    private static void constructNum(int[] arr, List<String> list, StringBuilder sb) {
        if (sb.length() == 3) {
            list.add(sb.toString());
            return;
        }
        for (int i : arr) {
            if (sb.toString().contains(i+"")) {
                continue;
            }
            sb.append(i);
            constructNum(arr, list, sb);
            sb.setLength(sb.length()-1);
        }
    }


    /**
     * 百钱买百鸡
     */
    @Test
    public void baiMoneyBuyChecken() {
        int count = 0;
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i <= 100 / 5; i++) {
            for (int j = 0; j < 100 / 3; j++) {
                if ((5 * i + 3 * j) > 100) {
                    break;
                }
                for (int k = 0; k < 100; k += 3) {
                    int coin = 5 * i + j * 3 + k / 3;
                    int num = i + j + k;
                    if (coin > 100 || num > 100) {
                        continue;
                    }
                    if (coin == 100 && num == 100) {
                        List<Integer> tmp = Arrays.asList(i, j, k);
                        lists.add(tmp);
                        count++;
                    }
                }
            }
        }
        System.out.println(count);
        System.out.println(lists);
        for (List<Integer> list : lists) {
            for (Integer i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    @Test
    public void secondNum() {
        int[] arr = {123,42,13,123,32};
        int max = Math.max(arr[0], arr[1]);
        int second = Math.min(arr[0], arr[1]);
        for (int i = 2; i < arr.length; i++) {
            if (arr[i] > max) {
                int tmp = max;
                max = arr[i];
                second = tmp;
            } else if (arr[i] <= max && arr[i] > second) {
                second = arr[i];
            }
        }
        System.out.println("max : "+max+" second: "+second);
    }

    @Test
    public void compileTest() {
        Pattern compile = Pattern.compile("([\\d,]*\\d)");
        String s = "[[11,2,34,5],[12],[11,2,3,23,1]]";
        Matcher matcher = compile.matcher(s);
        while (matcher.find()) {
            String group = matcher.group(0);
            System.out.println(group);
        }
    }

    @Test
    public void findNum() {
        int i = 0;
        while (i < Integer.MAX_VALUE) {
            if (isFull(i+100) && isFull(i+268)) {
                System.out.println(i);
                break;
            }
            i++;
        }
    }

    @Test
    public void isFull() {
//        System.out.println(isFull(9));
        System.out.println(Math.sqrt(9)%1);
    }

    public boolean isFull(int num){
        return String.valueOf(Math.sqrt(num)).contains(".");
    }

}

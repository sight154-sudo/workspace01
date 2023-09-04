package com.huawei.algorithm;

import com.sun.deploy.panel.ITreeNode;
import org.junit.Test;

import java.util.*;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class ArrayPractice {

    @Test
    public void twoSum() {
        int[] nums = {1, 4, 6, 8, 11, 0};
        int target = 10;
        int[] res = twoSumByHash(nums, target);
        System.out.println(Arrays.toString(res));
    }


    /**
     * 暴力破解  两数之和
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    res[0] = i;
                    res[1] = j;
                    break;
                }
            }
        }
        return res;
    }

    /**
     * 使用hash表
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSumByHash(int[] nums, int target) {
        //hash表中的key存放数组中的元素，value存放元素的索引
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);
        }
        return null;
    }


    @Test
    public void testSort() {
        List<Map<String, Object>> list = new ArrayList<>();
        int i = 1;
        while (i < 100000) {
            Map<String, Object> map = new HashMap<>();
            map.put("item" + i, String.format("values%d", (i + 1)));
            map.put("date", new Date());
            list.add(map);
            i++;
        }
        long start = System.currentTimeMillis();
        list.stream().sorted((o1, o2) -> {
            Date o1d = (Date) o1.get("date");
            Date o2d = (Date) o2.get("date");
            return o2d.compareTo(o1d);

        });
        System.out.println("耗时: " + (System.currentTimeMillis() - start));
        List<Map<String, Object>> collect = list.stream().limit(10).collect(Collectors.toList());
        System.out.println(collect);
//        System.out.println(list);
    }

    public long converTimeToLong(Date date) {
        return 1;
    }

    @Test
    public void testRemoveElement() {
        int[] nums = {3, 2, 2, 2, 1};
        int val = 2;
        int len = removeElement(nums, val);
        for (int i = 0; i < len; i++) {
            System.out.print(nums[i] + ",");
        }
    }

    /**
     * 移除元素
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        //使用双指针
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            if (nums[end] == val) {
                end--;
                continue;
            }
            if (nums[start] != val) {
                start++;
                continue;
            }
            swap(nums, start, end);
            end--;
            start++;
        }
        return nums[start] == val ? start : start + 1;
    }

    private void swap(int[] nums, int src, int dest) {
        int tmp = nums[src];
        nums[src] = nums[dest];
        nums[dest] = tmp;
    }

    @Test
    public void testSearchInsert() {
        int[] nums = {1, 3, 5, 7};
        int target = 7;
        int index = searchInsert(nums, target);
        System.out.println("index = " + index);
    }

    /**
     * 搜索插入的位置  时间复杂度为log(n) 使用二分查找
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        int mid = 0;
        while (start < end) {
            mid = (start + end) / 2;
            if (nums[mid] > target) {
                end = mid - 1;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return nums[start] < target ? start + 1 : start;
    }


    /**
     * 最大连续子序和
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        //[-2,1,-3,4,-1,2,1,-5,4]可以将问题拆分为一个个子问题
        /*
            经过-2的最大子序和是？  经过1的最大子序和是？ 。。。但既不能确认-2的位置，也不能确定子序的长度
            转变问题  连续子序和，且只需要知道最大和
            以-2为结尾的最大子序和是?  以1为结尾的最大子序和是? ...
            设 d(i) 是以i号位置为结尾的最大子序和
            则  d(i) = d(i-1)+nums[i] 可以看出d(i)的值与d(i-1)有关，因为如果 d(i-1) > 0 则在d(i) = d(i-1)+nums[i]
            若d(i-1)<=0,则舍弃d(i-1),此时d(i) = nums[i]  所以   dp(i) = max(nums[i],d(i-1)+nums[i])
         */
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        for (int i = 1; i < len; i++) {
            if (dp[i - 1] > 0) {
                dp[i] = dp[i - 1] + nums[i];
            } else {
                dp[i] = nums[i];
            }
        }
        int res = dp[0];
        for (int i = 1; i < len; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    @Test
    public void testMaxSubArray1() {
//        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        int[] nums = {-2, -3, -4, -1, -3};
        int sum = maxSubArray1(nums);
        System.out.println("sum = " + sum);
    }

    /**
     * 最大连续子序和   使用分治法
     * 思路：将数组分解成三份  第一份  start ->  mid    第二   mid-> end   第三份为 经过mid  mid+1的连续子序列
     * 依次分治，求出最大值
     *
     * @param nums
     * @return
     */
    public int maxSubArray1(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        return maxSubArraySum(nums, start, end);
    }

    public int maxSubArraySum(int[] nums, int start, int end) {
        if (start >= end) {
            return nums[start];
        }
        int mid = (start + end) / 2;
        return max(maxSubArraySum(nums, start, mid),
                crossMaxSum(nums, start, mid, end),
                maxSubArraySum(nums, mid + 1, end));
    }

    public int crossMaxSum(int[] nums, int start, int mid, int end) {
        int sum = 0;
        int leftSum = Integer.MIN_VALUE;
        int rightSum = Integer.MIN_VALUE;
        for (int i = mid; i >= start; i--) {
            sum += nums[i];
            if (sum > leftSum) {
                leftSum = sum;
            }
        }
        sum = 0;
        for (int i = mid + 1; i <= end; i++) {
            sum += nums[i];
            if (sum > rightSum) {
                rightSum = sum;
            }
        }
        return leftSum + rightSum;
    }

    public int max(int num1, int num2, int num3) {
        return Math.max(num1, Math.max(num2, num3));
    }


    @Test
    public void testMaxSubArray2() {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
//        int[] nums = {-2,-3,-4,-1,-3};
        int sum = maxSubArray1(nums);
        System.out.println("sum = " + sum);
    }

    /**
     * 使用贪心算法
     *
     * @param nums
     * @return
     */
    public int maxSubArray2(int[] nums) {
        int result = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            result = Math.max(result, sum);
            if (sum < 0) {
                // 表示需要重新计算连续子序列
                sum = 0;
            }
        }
        return result;
    }

    /**
     * 删除有序数组中的重复项
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        // ex: {1,1,2} ->  {1,2,_}     {0,0,1,1,1,2,2,3,3,4}  -> {[0,1,2,3,4}
        int slow = 0;
        int fast = 1;
        while (fast < nums.length) {
            if (nums[slow] == nums[fast]) {
                fast++;
            } else if (nums[slow] < nums[fast]) {
                nums[slow + 1] = nums[fast];
                fast++;
                slow++;
            }
        }
        return nums.length == 0 ? slow : slow + 1;
    }

    /**
     * 全排列 46
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        LinkedList<Integer> ans = new LinkedList<>();
        permuteRecur(list, nums, ans);
        return list;
    }

    private void permuteRecur(List<List<Integer>> list, int[] nums, LinkedList<Integer> ans) {
        if (nums.length == ans.size()) {
            List<Integer> tmp = new ArrayList<Integer>(ans);
            list.add(tmp);
            return;
        }
        for (int num : nums) {
            if (!ans.contains(num)) {
                ans.addLast(num);
                permuteRecur(list, nums, ans);
                ans.removeLast();
            }
        }
    }

    @Test
    public void decryptTest() {
        int[] arr = {5, 7, 1, 4};
        int[] arr1 = {5, 7, 1, 4};
        System.out.println(Arrays.toString(decrypt2(arr, 3)));
        System.out.println(Arrays.toString(decrypt(arr1, 3)));
    }

    public int[] decrypt1(int[] code, int k) {
        int len = code.length;
        if (k == 0) {
            return new int[len];
        }
        int[] back = Arrays.copyOf(code, len);
        if (k > 0) {
            for (int i = 0; i < code.length; i++) {
                int tmp = 0;
                int j = k;
                int index = (i + 1) % len;
                while (j-- > 0) {
                    tmp += back[index];
                    index = (index + 1) % len;
                }
                code[i] = tmp;
            }
        } else {
            for (int i = 0; i < code.length; i++) {
                int tmp = 0;
                int j = k;
                int index = (i + k) < 0 ? (i + k + len) % len : i + k;
                while (j++ < 0) {
                    tmp += back[index];
                    index = (index + 1) % len;
                }
                code[i] = tmp;
            }
        }
        return code;
    }

    public int[] decrypt(int[] code, int k) {
        int len = code.length;
        if (k == 0) {
            return new int[len];
        }
//        {5,2,7,4}
        int[] arr = new int[code.length * 2];
        System.arraycopy(code, 0, arr, 0, len);
        System.arraycopy(code, 0, arr, len, len);
        int i = k > 0 ? 1 : len + k;
        int j = k > 0 ? k : len - 1;
        int num = 0;
        for (int n = i; n <= j; n++) {
            num += arr[n];
        }
        for (int n = 0; n < len; n++) {
            code[n] = num;
            num -= arr[i++];
            num += arr[++j];
        }
        return code;
    }

    public int[] decrypt2(int[] code, int k) {
        // 使用前缀和求解
        int len = code.length;
        if (k == 0) {
            return new int[len];
        }
        // {5,2,7,1}  {0,5,7,14,15,20,22,29,30}
        int[] arr = new int[len * 2 + 1];
        for (int i = 1; i < arr.length; i++) {
            arr[i] = arr[i - 1] + code[(i - 1) % len];
        }
        for (int i = 1; i <= len; i++) {
            if (k > 0) {
                code[i - 1] = arr[i + k] - arr[i];
            } else {
                code[i - 1] = arr[len + i - 1] - arr[len + i + k - 1];
            }
        }
        return code;
    }

    public int arrContinueSum(int[] arr, int target) {
        return arrContinueSumBack(arr, 0, arr.length - 1, target);
    }

    @Test
    public void arrContinueSumTest() {
        int[] arr = {3, 4, 7};
        int target = 7;
        System.out.println(arrContinueSum(arr, target));
        System.out.println(arrContinueSum1(arr, target));
    }

    public void validarrContinueSum(int n, int maxVal, int count) {
        boolean flag = true;
        for (int j = 0; j < count; j++) {
            int len = (int) (Math.random() * n) + 1;
            int[] arr = new int[len];
            for (int i = 0; i < len; i++) {
                arr[i] = (int) (Math.random() * maxVal) + 1;
            }
            if (arrContinueSum(arr, 1) != arrContinueSum1(arr, 1)) {
                System.out.println(Arrays.toString(arr));
                flag = false;
                System.out.println("Fuck Code!!!");
            }
        }
        if (flag) {
            System.out.println("Nice Code!!!");
        }
    }

    public int arrContinueSumBack(int[] arr, int L, int R, int target) {
        if (L > R) {
            return 0;
        }

        int leftCount = arrContinueSumBack(arr, L, R - 1, target);
        int rightCount = arrContinueSumBack(arr, L + 1, R, target);
        int sum = 0;
        int count = 0;
        for (int i = L; i <= R; i++) {
            sum += arr[i];
            if (sum >= target) {
                count++;
            }
        }
        return count + leftCount + rightCount;
    }

    public int arrContinueSum1(int[] arr, int target) {
        int[] preSum = new int[arr.length + 1];
        for (int i = 1; i <= arr.length; i++) {
            preSum[i] = preSum[i - 1] + arr[i - 1];
        }
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (preSum[j + 1] - preSum[i] >= target) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 给定一个正整数 n ，将其拆分为 k 个 正整数 的和（ k >= 2 ），并使这些整数的乘积最大化。
     * <p>
     * 返回 你可以获得的最大乘积 。   leetcode  343
     *
     * @param n
     * @return
     */
    public int integerBreak(int n) {
        // n = 10   3+3+4   => 3*3*4 36
        if (n == 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }
        return integerBreakBack(n, 1);
    }

    @Test
    public void intergerBreakTest() {
        System.out.println(integerBreak(4));
    }

    public int integerBreakBack(int n, int pre) {
        if (n == 0) {
            return 1;
        }
        int mult = Integer.MIN_VALUE;
        for (int i = pre; i <= n; i++) {
            int ans = i * integerBreakBack(n - i, pre);
            mult = Math.max(mult, ans);
        }
        return mult;
    }

    /**
     * 给定一个字符串数组arr， 一个目标字符串s， arr数组中的第个字符串代表一个贴纸， 你可以把单个字符剪开，目的是拼出目标字符串，
     * 返回需要至少张贴纸可以完成这个任务
     *
     * @param str
     * @param s
     * @return
     */
    public int strTie(String[] str, String s) {
        // str:["ba", "c", "abcd"]   s: "babac"
        Map<String, Integer> map = new HashMap<>();
        int ans = strTieBack(str, 0, s, map);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    @Test
    public void strTieTest() {
        String[] str = {"with", "example", "science"};
        String s = "thehat";
        System.out.println(strTie(str, s));
    }

    public int strTieBack(String[] str, int index, String rest, Map<String, Integer> map) {
        if (rest.length() == 0) {
            return 0;
        }
        if (index == str.length) {
            return Integer.MAX_VALUE;
        }
        if (map.containsKey(rest)) {
            return map.get(rest);
        }
        int ways = Integer.MAX_VALUE;
        for (int i = index; i < str.length; i++) {
            if (rest.length() >= 0) {
                String s = getString(str[i], rest);
                index = s.equals(rest) ? index + 1 : index;
                int ans = strTieBack(str, index, s, map);
                if (ans != Integer.MAX_VALUE) {
                    ways = Math.min(ways, ans + 1);
                }
            }

        }
        map.put(rest, ways);
        return ways;
    }

    private String getString(String s, String rest) {
        String ans = "";
        char[] ch1 = s.toCharArray();
        boolean[] bool = new boolean[ch1.length];
        char[] ch2 = rest.toCharArray();
        for (int i = 0; i < ch2.length; i++) {
            boolean flag = false;
            for (int j = 0; j < ch1.length; j++) {
                if (ch2[i] == ch1[j] && !bool[j]) {
                    flag = true;
                    bool[j] = true;
                    break;
                }
            }
            if (!flag) {
                ans += ch2[i];
            }
        }
        return ans;
    }

    @Test
    public void getStringTest() {
        String str = "abacca";
        String s = "acbcdaaab";
        System.out.println(getString(str, s));
    }

    /**
     * 假定一个固定大小的为w的窗口， 依次滑过数组arr, 返回每次滑过的最大值
     *
     * @param arr
     * @param w
     * @return
     */
    public int[] getWindowMax(int[] arr, int w) {
        // arr = {4,3,5,4,3,3,6,7}
        // ans => {5,5,5,4,6,7}
        int n = arr.length;
        int[] ans = new int[n - w + 1];
        Deque<Integer> deque = new LinkedList<>();
        int index = 0;
        for (int R = 0; R < n; R++) {
            while (!deque.isEmpty() && arr[deque.peekLast()] <= arr[R]) {
                deque.pollLast();
            }
            deque.addLast(R);
            if (deque.peekFirst() == R - w) {
                deque.pollFirst();
            }
            if (R + 1 >= w) {
                ans[index++] = arr[deque.peekFirst()];
            }
        }
        return ans;
    }

    @Test
    public void getWindowMaxTest() {
        int[] arr = {4, 3, 5, 6, 3, 3};
        int[] windowMax = getWindowMax(arr, 3);
        System.out.println(Arrays.toString(windowMax));
        int[] windowMax1 = getWindowMaxForce(arr, 3);
        System.out.println(Arrays.toString(windowMax1));
    }


    public int[] getWindowMaxForce(int[] arr, int w) {
        if (arr == null) {
            return new int[0];
        }
        if (arr.length == 1) {
            return new int[]{arr[0]};
        }
        if (arr.length == 2) {
            return new int[]{Math.max(arr[0], arr[1])};
        }
        int n = arr.length;
        int[] ans = new int[n - w + 1];
        int index = 0;
        for (int i = 0, j = 2; j < n; i++, j++) {
            int num = getMax(arr, i, j);
            ans[index++] = num;
        }
        return ans;
    }

    private int getMax(int[] arr, int i, int j) {
        int max = arr[i];
        while (i <= j) {
            max = Math.max(arr[i], max);
            i++;
        }
        return max;
    }

    private int getMin(int[] arr, int i, int j) {
        int min = arr[i];
        while (i <= j) {
            min = Math.min(arr[i], min);
            i++;
        }
        return min;
    }

    /**
     * 给定一个数组， 求这个数组中有多少个子数组中的最大值与最小值的差小于等于sum
     *
     * @param arr
     * @param sum
     * @return
     */
    public int getWindowSubSumForce(int[] arr, int sum) {
        int count = 0;
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int max = getMax(arr, i, j);
                int min = getMin(arr, i, j);
                if (max - min <= sum) {
                    count++;
                }
            }
        }
        return count;
    }

    @Test
    public void getWindowSubSumTest() {
        int[] arr = {7, 2, 3, 6, 8, 0};
        int sum = 2;
        System.out.println(getWindowSubSumForce(arr, sum));
        System.out.println(getWindowSubSum(arr, sum));
        validGetWindowSubSum(100, 100, 10000);
    }

    public void validGetWindowSubSum(int n, int maxVal, int count) {
        boolean flag = true;
        for (int j = 0; j < count; j++) {
            int len = (int) (Math.random() * maxVal) + 1;
            int[] arr = new int[len];
            for (int i = 0; i < len; i++) {
                int num = (int) (Math.random() * maxVal) + 1;
                arr[i] = num;
            }
            int sum = (int) (Math.random() * maxVal) + 1;
            if (getWindowSubSum(arr, sum) != getWindowSubSumForce(arr, sum)) {
                flag = false;
                System.out.println(Arrays.toString(arr));
                System.out.println("Fuck Code!!!");
            }
        }
        if (flag) {
            System.out.println("Nice Code!!!");
        }
    }

    public int getWindowSubSum(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum < 0) {
            return 0;
        }
        int count = 0;
        int n = arr.length;
        Deque<Integer> qMax = new LinkedList<>();
        Deque<Integer> qMin = new LinkedList<>();
        //推导: 若L...R 的最大值与最小值差小于等于sum, 则 L...R 之间的子数组的最大值与最小值差小于等于sum (最大值只会小于等于max, 最小值只会大于等于min)
        //推导: 若L...R 的最大值与最小值差大于sum, 则L-n ... R 与L ... R+n 之间的子数组的最大值与最小值差大于sum (最大值只会大于等于max, 最小值只会小于等于min)
        int R = 0;
        for (int L = 0; L < n; L++) {
            while (R < n) {
                while (!qMax.isEmpty() && arr[qMax.peekLast()] <= arr[R]) {
                    qMax.pollLast();
                }
                qMax.addLast(R);
                while (!qMin.isEmpty() && arr[qMin.peekLast()] >= arr[R]) {
                    qMin.pollLast();
                }
                qMin.addLast(R);
                if (arr[qMax.peekFirst()] - arr[qMin.peekFirst()] > sum) {
                    break;
                } else {
                    R++;
                }
            }
            count += R - L;
            if (qMax.peekFirst() == L) {
                qMax.pollFirst();
            }
            if (qMin.peekFirst() == L) {
                qMin.pollFirst();
            }
        }
        return count;
    }

    @Test
    public void canCompleteCircuitTest() {
//        int[] gas = {1, 2, 3, 4, 5};
        int[] gas = {4};
//        int[] cost = {3, 4, 5, 1, 2};
        int[] cost = {5};
        System.out.println(canCompleteCircuit(gas, cost));
        System.out.println(canCompleteCircuitForce(gas, cost));
        System.out.println(canCompleteCircuitPlus(gas, cost));
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        for (int i = 0; i < n; i++) {
            int restGas = gas[i] - cost[i];
            if (restGas < 0) {
                continue;
            }
            boolean flag = false;
            int j = (i + 1) % n;
            while (j != i) {
                restGas += gas[j] - cost[j];
                if (restGas < 0) {
                    flag = true;
                    break;
                }
                j = (j + 1) % n;
            }
            if (!flag) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
     * <p>
     * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
     * leetcode 134
     *
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuitForce(int[] gas, int[] cost) {
        int n = gas.length;
        // arr表示汽车跑到下一个的剩余油量
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = gas[i] - cost[i];
        }
        for (int i = 0; i < n; i++) {
            if (arr[i] < 0) {
                continue;
            }
            int restGas = arr[i];
            boolean flag = true;
            int j = (i + 1) % n;
            while (j != i) {
                restGas += arr[j];
                if (restGas < 0) {
                    flag = false;
                    break;
                }
                j = (j + 1) % n;
            }
            if (flag) {
                return i;
            }
        }
        return -1;
    }

    public int canCompleteCircuitPlus(int[] gas, int[] cost) {
        int n = gas.length;
        // arr表示汽车跑到下一个的剩余油量
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = gas[i] - cost[i];
        }
        int[] preSum = new int[n * 2 + 1];
        for (int i = 1; i <= n * 2; i++) {
            preSum[i] = preSum[i - 1] + arr[(i - 1) % n];
        }
        Deque<Integer> qMin = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        int w = n;
        for (int i = 1; i < preSum.length; i++) {
            while (!qMin.isEmpty() && preSum[qMin.peekLast()] >= preSum[i]) {
                qMin.pollLast();
            }
            qMin.addLast(i);
            if (qMin.peekFirst() == i - w) {
                qMin.pollFirst();
            }
            if (i >= w) {
                if (preSum[qMin.peekFirst()] - preSum[i - w] >= 0) {
                    list.add(i - w);
                }
            }
        }
        return list.isEmpty() ? -1 : list.get(0);
    }

    public int getMinCoins(int[] coins, int aim) {

        return 0;
    }

    /**
     * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
     * leetCode 239
     * 返回 滑动窗口中的最大值
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) {
            return null;
        }
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        int index = 0;
        Deque<Integer> qMax = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            while (!qMax.isEmpty() && nums[qMax.peekLast()] <= nums[i]) {
                qMax.pollLast();
            }
            qMax.addLast(i);
            // 修改窗口过期的范围
            if (qMax.peekFirst() == i - k) {
                qMax.pollFirst();
            }
            if (i + 1 >= k) {
                ans[index++] = nums[qMax.peekFirst()];
            }
        }
        return ans;
    }

    public int[][] getNearLess(int[] arr) {
        int n = arr.length;
        int[][] ans = new int[n][2];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                Integer pop = stack.pop();
                ans[pop][0] = stack.isEmpty() ? -1 : stack.peek();
                ans[pop][1] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            ans[pop][0] = stack.isEmpty() ? -1 : stack.peek();
            ans[pop][1] = -1;
        }
        return ans;
    }

    public int[][] getNearLessForce(int[] arr) {
        int n = arr.length;
        int[][] ans = new int[n][2];
        for (int i = 0; i < n; i++) {
            int left = -1;
            int right = -1;
            for (int l = i - 1; l >= 0; l--) {
                if (arr[l] < arr[i]) {
                    left = l;
                    break;
                }
            }
            for (int r = i + 1; r < n; r++) {
                if (arr[r] < arr[i]) {
                    right = r;
                    break;
                }
            }
            ans[i][0] = left;
            ans[i][1] = right;
        }
        return ans;
    }

    @Test
    public void getNearLessTest() {
        int[] arr = {3, 1, 4, 5, 4, 2};
//        int[][] ans = getNearLess(arr);
        int[][] ans = getNearLessHasRepeat(arr);
        int[][] ans1 = getNearLessForce(arr);

        for (int i = 0; i < ans.length; i++) {
            System.out.println(Arrays.toString(ans[i]));
        }
        for (int i = 0; i < ans1.length; i++) {
            System.out.println(Arrays.toString(ans1[i]));
        }
    }

    public int[][] getNearLessHasRepeat(int[] arr) {
        int n = arr.length;
        int[][] ans = new int[n][2];
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
                List<Integer> pop = stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                for (Integer l : pop) {
                    ans[l][0] = left;
                    ans[l][1] = i;
                }
            }
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
                stack.peek().add(i);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }
        while (!stack.isEmpty()) {
            List<Integer> pop = stack.pop();
            int left = stack.isEmpty() ? -1 : stack.peek().get(pop.size() - 1);
            for (Integer l : pop) {
                ans[l][0] = left;
                ans[l][1] = -1;
            }
        }
        return ans;
    }

    /**
     * 给定一个数组arr, 求所有子数组中，子数组和与子数组中最小值的乘积的最大值
     *
     * @param arr
     * @return
     */
    public int getSubArrMax(int[] arr) {
        // 依次求以i位置为最小值的子数组和为最大值的数组，最终求最大值
        int n = arr.length;
        int[] pre = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            pre[i] = pre[i - 1] + arr[i - 1];
        }
        int max = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                int x = stack.pop();
                int left = stack.isEmpty() ? 0 : stack.peek() + 1;
                int sum = getSum(pre, left, i - 1);
                max = Math.max(max, sum * arr[x]);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int x = stack.pop();
            int left = stack.isEmpty() ? 0 : stack.peek() + 1;
            int sum = getSum(pre, left, n - 1);
            max = Math.max(max, sum * arr[x]);
        }
        return max;
    }

    /**
     * 给定一个数组，数组中的元素为每个矩形的高度，求构成矩形的最大面积为多少
     *
     * @param arr
     * @return
     */
    public int getMaxArea(int[] arr) {
        int n = arr.length;
        Stack<Integer> stack = new Stack<>();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                Integer pop = stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek();
                int right = i;
                max = Math.max(max, (right - left - 1) * arr[pop]);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            int left = stack.isEmpty() ? -1 : stack.peek();
            int right = n;
            max = Math.max(max, (right - left - 1) * arr[pop]);
        }
        return max;
    }

    @Test
    public void getMaxAreaTest() {
        int[] arr = {5, 2, 4, 3, 2, 3};
        System.out.println(getMaxArea(arr));
    }

    @Test
    public void getSubArrMaxTest() {
        int[] arr = {16, 24};
        System.out.println(getSubArrMaxForce(arr));
        System.out.println(getSubArrMax(arr));
        validGetSugArrMax(100, 1000, 10000);
    }

    public void validGetSugArrMax(int n, int maxVal, int count) {
        boolean flag = true;
        for (int j = 0; j < count; j++) {
            int len = (int) (Math.random() * n) + 1;
            int[] arr = new int[len];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = (int) (Math.random() * maxVal) + 1;
            }
            if (getSubArrMax(arr) != getSubArrMaxForce(arr)) {
                System.out.println(Arrays.toString(arr));
                flag = false;
                System.out.println("Fuck Code!!!");
            }
        }
        if (flag) {
            System.out.println("Nice Code!!!");
        }
    }

    public int getSubArrMaxForce(int[] arr) {
        int n = arr.length;
        int[] pre = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            pre[i] = pre[i - 1] + arr[i - 1];
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int min = getMin(arr, i, j);
                int sum = pre[j + 1] - pre[i];
                max = Math.max(max, sum * min);
            }
        }
        return max;
    }

    private int getSum(int[] pre, int left, int i) {
        return pre[i + 1] - pre[left];
    }

    /**
     * 给你一个字符串数组 tokens ，表示一个根据 逆波兰表示法 表示的算术表达式。
     * <p>
     * 请你计算该表达式。返回一个表示表达式值的整数。
     * leetcode 150
     *
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            String s = tokens[i];
            if (s.length() > 1) {
                stack.push(Integer.valueOf(s));
            } else {
                char c = s.toCharArray()[0];
                if (c >= '0' && c <= '9') {
                    stack.push(c - 48);
                } else {
                    int a = stack.pop();
                    int b = stack.pop();
                    switch (c) {
                        case '*':
                            stack.push(a * b);
                            break;
                        case '/':
                            stack.push(b / a);
                            break;
                        case '+':
                            stack.push(b + a);
                            break;
                        case '-':
                            stack.push(b - a);
                            break;
                    }
                }
            }
        }
        return stack.pop();
    }

    @Test
    public void evalRPNTest() {
        String[] tokens = {"4", "13", "5", "/", "+"};
        System.out.println(evalRPN(tokens));
    }

    /**
     * 给定一个由 0 和 1 组成的矩阵 matrix ，找出只包含 1 的最大矩形，并返回其面积。
     * leetCode 85
     *
     * @param matrix
     * @return
     */
    public int maximalRectangle(char[][] matrix) {
        int max = Integer.MIN_VALUE;
        int[] height = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                height[j] = matrix[i][j] == '0' ? 0 : height[j] + 1;
            }
            max = getZeroMax(height, max);
        }
        return max;
    }

    public int getZeroMax(int[] height, int max) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] >= height[i]) {
                Integer pop = stack.pop();
                int l = stack.isEmpty() ? -1 : stack.peek();
                int r = i;
                max = Math.max(max, (r - l - 1) * height[pop]);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            int l = stack.isEmpty() ? -1 : stack.peek();
            int r = height.length;
            max = Math.max(max, (r - l - 1) * height[pop]);
        }
        return max;
    }

    @Test
    public void numSubMatTest() {
        int[][] mat = {{0, 1, 1, 0}, {0, 1, 1, 1}, {1, 1, 1, 0}};
        System.out.println(numSubmat(mat));
    }

    /**
     * 给你一个 m x n 的二进制矩阵 mat ，请你返回有多少个 子矩形 的元素全部都是 1 。
     * leetCode 1504
     *
     * @param mat
     * @return
     */
    public int numSubmat(int[][] mat) {
        int n = mat[0].length;
        int[] height = new int[n];

        int count = 0;
        for (int i = 0; i < mat.length; i++) {
            int index = 0;
            for (int j = 0; j < n; j++) {
                height[index++] = mat[i][j] == 0 ? 0 : height[j] + 1;
            }
            // 计算以当前行为底的子矩形的都为1的矩形
            count += getSubMat(height);
        }
        return count;
    }

    private int getSubMat(int[] height) {
        int n = height.length;
        Stack<Integer> stack = new Stack<>();
        int count = 0;
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && height[stack.peek()] >= height[i]) {
                Integer pop = stack.pop();
                if (height[pop] > height[i]) {
                    int l = stack.isEmpty() ? -1 : stack.peek();
                    int r = i;
                    int len = r - l - 1;
                    count += (height[pop] - (l == -1 ? height[r] : Math.max(height[l], height[r]))) * (len * (len + 1) / 2);
                }
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            int l = stack.isEmpty() ? -1 : stack.peek();
            int r = n;
            int len = r - l - 1;
            int down = l == -1 ? 0 : height[l];
            count += (height[pop] - down) * (len * (len + 1) / 2);
        }
        return count;
    }

    /**
     * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
     * leetCode 84
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] stack = new int[n];
        int cur = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            while (cur != -1 && heights[stack[cur]] >= heights[i]) {
                int pop = stack[cur--];
                int l = cur == -1 ? -1 : stack[cur];
                int r = i;
                max = Math.max(max, (r - l - 1) * heights[pop]);
            }
            stack[++cur] = i;
        }
        while (cur != -1) {
            int pop = stack[cur--];
            int l = cur == -1 ? -1 : stack[cur];
            int r = n;
            max = Math.max(max, (r - l - 1) * heights[pop]);
        }
        return max;
    }

    @Test
    public void largestRectangleAreaTest() {
        int[] heights = {2, 1, 5, 6, 2, 3};
        System.out.println(largestRectangleArea(heights));
    }

    /**
     * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     *
     * @param nums
     * @return
     */
    public int maxSubArray11(int[] nums) {
        int max = Integer.MIN_VALUE;
        int n = nums.length;
        int[] pre = new int[n];
        pre[0] = nums[0];
        for (int i = 1; i < n; i++) {
            pre[i] = pre[i - 1] + nums[i];
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
            max = Math.max(max, getMaxSub(pre, 0, i));
        }

        return max;
    }

    @Test
    public void maxSubArray11Test() {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray11(nums));
    }

    private int getMaxSub(int[] pre, int l, int r) {
        int max = Integer.MIN_VALUE;
        for (int i = l; i <= r; i++) {
            max = Math.max(max, i == 0 ? pre[r] : pre[r] - pre[i - 1]);
        }
        return max;
    }

    public int findKthLastestNum(int[] num, int k) {

        return findKthLastestNumRecur(num, 0, num.length - 1, k);
    }

    public int findKthLastestNumRecur(int[] num, int i, int j, int k) {
        if (i >= j) {
            return num[i];
        }
        int index = partition(num, 0, num.length - 1);
        if (index + 1 == k) {
            return num[index];
        } else if (index + 1 > k) {
            return findKthLastestNumRecur(num, i, index - 1, k);
        } else {
            return findKthLastestNumRecur(num, index + 1, j, k);
        }
    }

    public int partition(int[] num, int l, int r) {
        int base = num[r];
        int p = l;
        for (; l <= r; l++) {
            if (num[l] < base) {
                swap(num, l, p++);
            }
        }
        swap(num, p, r);
        return p;
    }

    /**
     * 给定一个整数数组 arr，找到 min(b) 的总和，其中 b 的范围为 arr 的每个（连续）子数组。
     * leetCode 907
     *
     * @param arr
     * @return
     */
    public int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        int[] left = getLeftMinSub(arr);
        int[] right = getRightMinSub(arr);
        long sum = 0;
        for (int i = 0; i < n; i++) {
            int start = i - left[i];
            int end = right[i] - i;
            sum += (start * end * arr[i]) % 1000000007;
        }
        return (int) sum % 1000000007;
    }

    @Test
    public void sumSubArrayMinsTest() {
        int[] arr = {71, 55, 82, 55};
        System.out.println(sumSubarrayMins(arr));
    }

    private int[] getLeftMinSub(int[] arr) {
        int[] left = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int index = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[i] > arr[j]) {
                    index = j;
                    break;
                }
            }
            left[i] = index;
        }
        return left;
    }

    private int[] getRightMinSub(int[] arr) {
        int n = arr.length;
        int[] right = new int[n];
        for (int i = 0; i < n; i++) {
            int index = n;
            for (int j = i + 1; j < n; j++) {
                if (arr[i] >= arr[j]) {
                    index = j;
                    break;
                }
            }
            right[i] = index;
        }
        return right;
    }

    public int quickCounting(int base, int p) {

        String s = "";
        s.contains("");
        return 0;
    }

    @Test
    public void printSanJiaoTest() {
        printSanJiao(9);
    }

    public void printSanJiao(int n) {
        int[][] arr = new int[n][n];
        int i = 0, j = 0;
        int num = 1;
        int m = 0; // m == 0时表示从左往右打印  m == 1 从右上打印到左下  m == 2从左下到左上
        int max = (n * n - n) / 2 + n;
        while (num <= max) {
            arr[i][j] = num++;
            if (m == 0) {
                j++;
                if (j == n || arr[i][j] != 0) {
                    m = 1;
                    i++;
                    j -= 2;
                }
            } else if (m == 1) {
                i++;
                j--;
                if (j < 0 || arr[i][j] != 0) {
                    m = 2;
                    i -= 2;
                    j++;
                }
            } else {
                i--;
                if (arr[i][j] != 0) {
                    m = 0;
                    i++;
                    j++;
                }
            }
        }
        for (int k = 0; k < n; k++) {
            System.out.println(Arrays.toString(arr[k]));
        }
    }

    @Test
    public void manacherTest() {
        String s = "wefvaewfw123332bbc";
        System.out.println(manacher(s));
    }

    public int manacher(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] ch = convertStr(s);
        int[] ptr = new int[ch.length];
        // 当前字符的回文半径
        int C = -1;
        // 当前字符的最右回文边界
        int R = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < ch.length; i++) {
            ptr[i] = R > i ? Math.min(ptr[2 * C - i], R - i) : 1;
            while (i + ptr[i] < ch.length && i - ptr[i] > -1) {
                if (ch[i + ptr[i]] == ch[i - ptr[i]]) {
                    ptr[i]++;
                } else {
                    break;
                }
            }
            if (i + ptr[i] > R) {
                R = i + ptr[i];
                C = i;
            }
            max = Math.max(max, ptr[i]);
        }
        return max - 1;
    }

    private char[] convertStr(String s) {
        char[] ch = s.toCharArray();
        char[] ans = new char[s.length() * 2 + 1];
        for (int i = 0; i < ch.length; i++) {
            ans[2 * i] = '#';
            ans[i * 2 + 1] = ch[i];
        }
        ans[s.length() * 2] = '#';
        return ans;
    }


    /**
     * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度
     * leetCode  300
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        //  {10,9,2,5,3,7,101,18}   => {2,3,7,101}
        int n = nums.length;
        // dp[i]  表示 以i为结尾的最长递增子序列  求dp[i+1] = max(dp[i], dp[i-1], ... dp[1]) + 1;
        int[] dp = new int[n];
        dp[0] = 1;
        int ans = 1;
        for (int i = 1; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(dp[i], ans);
        }
        return ans;
    }

    @Test
    public void lengthOfLisTest() {
        int[] arr = {0, 1, 0, 3, 2, 3};
//        int[] arr = {10,9,2,5,3,7,101,18};
//        int[] arr = {7,7,7,7,7,7,7};
        System.out.println(lengthOfLISForce(arr, 1, arr[0]));
    }

    public int lengthOfLISForce(int[] nums, int index, int pre) {
        if (index == nums.length) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        if (nums[index] > pre) {
            int p1 = lengthOfLISForce(nums, index + 1, pre);
            int p2 = lengthOfLISForce(nums, index + 1, nums[index]) + 1;
            max = Math.max(max, Math.max(p1, p2));
        } else {
            int p = lengthOfLISForce(nums, index + 1, pre);
            max = Math.max(max, p);
        }
        return max;
    }

    /**
     * 给定一个未经排序的整数数组，找到最长且 连续递增的子序列，并返回该序列的长度
     * 连续递增的子序列 可以由两个下标 l 和 r（l < r）确定，如果对于每个 l <= i < r，
     * 都有 nums[i] < nums[i + 1] ，那么子序列 [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] 就是连续递增子序列。
     * leetCode 674
     *
     * @param nums
     * @return
     */
    public int findLengthOfLCIS(int[] nums) {
        int n = nums.length;
        int pre = 1;
        int max = 1;
        for (int i = 1; i < n; i++) {
            int cur = 1;
            if (nums[i - 1] < nums[i]) {
                cur = pre + 1;
            }
            pre = cur;
            max = Math.max(cur, max);
        }
        return max;
    }

    /**
     * 给定一个未排序的整数数组 nums ， 返回最长递增子序列的个数 。
     * <p>
     * 注意 这个数列必须是 严格 递增的。
     * leetCode 673
     *
     * @param nums
     * @return
     */
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int[] cnt = new int[n];
        int maxLen = 0;
        // {1,3,5,4,7}
        // {1,2,3,3,4}
        int max = 1;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            cnt[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1;
                        cnt[i] = cnt[j];
                    } else if (dp[i] == dp[j] + 1) {
                        cnt[i] += cnt[j];
                    }
                }
            }
            max = Math.max(max, dp[i]);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] == max) {
                ans += cnt[i];
            }
        }
        return ans;
        // 有n种糖果, 每种糖果的甜度都不同，且每种糖果都有无限个，求n种糖果中选取一些不能组成甜度的最大值是多少
    }

    @Test
    public void findNumberOfLISTest() {
        int[] arr = {1, 1, 1, 2, 2, 2};
        System.out.println(findNumberOfLIS(arr));
    }

    /**
     * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
     * leetCode 152
     *
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int[] max = new int[n];
        int[] min = new int[n];
        max[0] = nums[0];
        min[0] = nums[0];
        for (int i = 1; i < n; i++) {
            max[i] = Math.max(nums[i], Math.max(nums[i] * max[i - 1], nums[i] * min[i - 1]));
            min[i] = Math.min(nums[i], Math.min(nums[i] * max[i - 1], nums[i] * min[i - 1]));
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, max[i]);
        }
        return ans;
    }

    @Test
    public void canJumpTest() {
//        int[] arr = {3,2,1,0,4};
        int[] arr = {3, 0, 8, 2, 0, 0, 1};
        System.out.println(canJump(arr));
    }

    /**
     * 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * leetCode 55
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int n = nums.length;
        // dp[i] 表示i位置可以跳跃的最远距离
        int maxDis = nums[0];
        for (int i = 0; i < n; i++) {
            if (maxDis >= i) {
                maxDis = Math.max(maxDis, i + nums[i]);
            } else {
                return false;
            }
            if (maxDis >= n - 1) {
                return true;
            }
        }
        return maxDis >= n - 1;
    }

    /**
     * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
     * <p>
     * 每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:
     * 返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int step = 0;
        int maxDis = -1;
        int n = nums.length;
        int cur = 0;
        // 2 3 1 1 4
        for (int i = 0; i < n - 1; i++) {
            if (maxDis < i + nums[i]) {
                maxDis = i + nums[i];
            }
            if (i == cur) {
                step++;
                cur = maxDis;
            }
        }
        return step;
    }

    @Test
    public void jumpTest() {
//        int[] nums= {2,1,1,1,4};
//        int[] nums= {1,4};
        int[] nums = {7, 0, 9, 6, 9, 6, 1, 7, 9, 0, 1, 2, 9, 0, 3};
        System.out.println(jump(nums));
    }

    /**
     * 这里有一个非负整数数组 arr，你最开始位于该数组的起始下标 start 处。当你位于下标 i 处时，
     * 你可以跳到 i + arr[i] 或者 i - arr[i]。
     * 请你判断自己是否能够跳到对应元素值为 0 的 任一 下标处。
     * leetCode 1306
     *
     * @param arr
     * @param start
     * @return
     */
    public boolean canReach(int[] arr, int start) {
        if (arr[start] == 0) {
            return true;
        }
        boolean[] bool = new boolean[arr.length];
        return canReachForce(arr, start, bool);
    }

    @Test
    public void canReachTest() {
        int[] arr = {4, 2, 3, 0, 3, 1, 2};
        System.out.println(canReach(arr, 5));
    }

    public boolean canReachForce(int[] arr, int start, boolean[] bool) {
        if (start < 0 || start >= arr.length) {
            return false;
        }
        if (arr[start] == 0) {
            return true;
        }
        bool[start] = true;
        boolean flag = true;
        for (int i = 0; i < bool.length; i++) {
            if (arr[i] != 0) {
                flag &= bool[i];
            }
        }
        if (flag) {
            return false;
        }
        return canReachForce(arr, start + arr[start], bool) || canReachForce(arr, start - arr[start], bool);
    }

    /**
     * 给你一个整数数组 arr ，你一开始在数组的第一个元素处（下标为 0）。
     * 每一步，你可以从下标 i 跳到下标 i + 1 、i - 1 或者 j ：
     * i + 1 需满足：i + 1 < arr.length
     * i - 1 需满足：i - 1 >= 0
     * j 需满足：arr[i] == arr[j] 且 i != j
     * 请你返回到达数组最后一个元素的下标处所需的 最少操作次数 。
     * leetCode 1345
     *
     * @param arr
     * @return
     */
    public int minJumps(int[] arr) {
        // 100,-23,-23,404,100,23,23,23,3,404
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            List<Integer> tmp = new ArrayList<>();
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    tmp.add(j);
                }
            }
            list.add(tmp);
        }
        boolean[] bool = new boolean[arr.length];
        return minJumpsForce(arr, 0, list, bool);
    }

    @Test
    public void minJumpsTest() {
//        int[] arr = {100, -23, -23, 404, 100, 23, 23, 23, 3, 404};
        int[] arr = {7, 7, 2, 1, 7, 7, 7, 3, 4, 1};
        System.out.println(minJumps(arr));
    }

    public int minJumpsForce(int[] arr, int index, List<List<Integer>> list, boolean[] bool) {
        if (index >= arr.length || index < 0) {
            return Integer.MAX_VALUE;
        }
        if (index == arr.length - 1) {
            return 0;
        }
        int p3 = Integer.MAX_VALUE;
        List<Integer> tmp = list.get(index);
        for (Integer j : tmp) {
            bool[j] = true;
            p3 = minJumpsForce(arr, j, list, bool);
            if (p3 != Integer.MAX_VALUE) {
                p3 = p3 + 1;
            }
        }
        int p1 = Integer.MAX_VALUE;
        if (index + 1 < arr.length && !bool[index + 1]) {
            bool[index + 1] = true;
            p1 = minJumpsForce(arr, index + 1, list, bool);
        }
        if (p1 != Integer.MAX_VALUE) {
            p1 = p1 + 1;
        }
        int p2 = Integer.MAX_VALUE;
        if (index - 1 >= 0 && !bool[index - 1]) {
            bool[index - 1] = true;
            p2 = minJumpsForce(arr, index - 1, list, bool);
        }
        if (p2 != Integer.MAX_VALUE) {
            p2 = p2 + 1;
        }
        return Math.min(p1, Math.min(p2, p3));
    }

    /**
     * 给你一个下标从 0 开始的整数数组 nums 和一个整数 k 。
     * 一开始你在下标 0 处。每一步，你最多可以往前跳 k 步，但你不能跳出数组的边界。也就是说，
     * 你可以从下标 i 跳到 [i + 1， min(n - 1, i + k)] 包含 两个端点的任意位置。
     * <p>
     * 你的目标是到达数组最后一个位置（下标为 n - 1 ），你的 得分 为经过的所有数字之和。
     * 请你返回你能得到的 最大得分 。
     * leetCode 1696
     *
     * @param nums
     * @param k
     * @return
     */
    public int maxResult(int[] nums, int k) {
//        return maxResultForce(nums, 0, k);
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            dp[i] = Integer.MIN_VALUE;
        }
        return maxResultCache(nums, 0, dp, k);
    }

    @Test
    public void maxResultTest() {
//        int[] arr = {1, -5, -20, 4};
        int[] arr = {10,-5,-2,4,0,3};
//        int[] arr = {1,-1,-2,4,-7,3};
        int k = 3;
        System.out.println(maxResultDp(arr, k));
    }

    public int maxResultDp(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        for (int i = 0; i < n-1; i++) {
            dp[i] = Integer.MIN_VALUE;
        }
        dp[n-1] = nums[n-1];
        Deque<Integer> deque = new LinkedList<>();
        deque.addLast(n-1);
        for (int i = n - 2; i >= 0; i--) {
            dp[i] = dp[deque.peekFirst()] + nums[i];
            while (!deque.isEmpty() && dp[deque.peekLast()] <= dp[i] ) {
                deque.pollLast();
            }
            deque.addLast(i);
            if (deque.peekFirst() > i+k-1) {
                deque.pollFirst();
            }
            /*for (int j = 1; j <= k && j+i < n; j++) {
                dp[i] = Math.max(dp[i], dp[j+i] + nums[i]);
            }*/
        }
        return dp[0];
    }

    public int maxResultForce(int[] nums, int index, int k) {
        if (index == nums.length) {
            return 0;
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 1; i + index <= nums.length && i <= k; i++) {
            int p = maxResultForce(nums, index + i, k) + nums[index];
            ans = Math.max(ans, p);
        }
        return ans;
    }

    public int maxResultCache(int[] nums, int index, int[] dp, int k) {
        if (index == nums.length) {
            return 0;
        }
        if (dp[index] != Integer.MIN_VALUE) {
            return dp[index];
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 1; i + index <= nums.length && i <= k; i++) {
            int p = maxResultCache(nums, index + i, dp, k) + nums[index];
            ans = Math.max(ans, p);
        }
        dp[index] = ans;
        return ans;
    }

}

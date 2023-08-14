package com.huawei.algorithm;

import org.junit.Test;

import java.util.*;
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
        int[] gas = {1, 2, 3, 4, 5};
        int[] cost = {3, 4, 5, 1, 2};
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
        for (int i = 1; i < n * 2; i++) {
            preSum[i] = preSum[i - 1] + arr[(i-1)%n];
        }
        Deque<Integer> qMin = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        int w = n;
        for (int i = 1; i < preSum.length; i++) {
            while (!qMin.isEmpty() && preSum[qMin.peekLast()] >= preSum[i]) {
                qMin.pollLast();
            }
            qMin.addFirst(i);
            if (qMin.peekFirst() == i - w) {
                qMin.pollFirst();
            }
            if (i >= w) {
                if (preSum[qMin.peekFirst()] - preSum[i - 1] >= 0) {
                    list.add(i);
                }
            }
        }
        return list.isEmpty() ? -1 : list.get(0);
    }

    public int getMinCoins(int[] coins, int aim) {

        return 0;
    }

}

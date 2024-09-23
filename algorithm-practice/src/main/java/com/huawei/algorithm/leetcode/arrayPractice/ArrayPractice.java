package com.huawei.algorithm.leetcode.arrayPractice;

import com.sun.prism.sw.SWPipeline;
import org.junit.Assert;
import org.junit.Test;

import javax.swing.text.Style;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author king
 * @date 2023/3/18-16:02
 * @Desc
 */
public class ArrayPractice {

    /**
     * 给定一个整数数组 nums和一个整数目标值 target，请你在该数组中找出 和为目标值 target的那两个整数，并返回它们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     */
    @Test
    public void twoSum() {
        int[] nums = {2, 7, 11, 15};
        System.out.println(Arrays.toString(twoSum(nums, 9)));
    }

    public int[] twoSum(int[] nums, int target) {
        // 暴力解法
        int[] res = new int[2];
        /*for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    res[0] = i;
                    res[1] = j;
                    break;
                }
            }
        }*/
        // 使用hashMap优化，将计算的结果存储到map中，并保存索引
        /*Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                res[0] = map.get(nums[i]);
                res[1] = i;
                break;
            }
            map.put(target-nums[i], i);
        }*/
        // 先排序，后查询
        Arrays.sort(nums);
        for (int i = 0, j = nums.length - 1; i < j; ) {
            if (nums[i] + nums[j] == target) {
                return new int[]{i, j};
            } else if (nums[i] + nums[j] < target) {
                i++;
            } else {
                j--;
            }
        }
        return res;
    }

    @Test
    public void threeSum() {
//        int[] nums = {-1, 0, 1, 2, -1, -4};
        int[] nums = {-1, 0, -1, 1};
        System.out.println(threeSum(nums));
    }

    /**
     * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
     * 你返回所有和为 0 且不重复的三元组。
     * 注意：答案中不可以包含重复的三元组。
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        // 暴力解法
        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        List<Set<Integer>> result = new ArrayList<>();
        // -1, 0, 1, 2, -1, -4    [-1,0,1]
        /*for (int i = 0; i < n; i++) {
            Integer target = nums[i] * -1;
            Map<Integer, Integer> firstMap = new HashMap<>();
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    if (firstMap.containsKey(nums[j])) {
                        Integer num = firstMap.get(nums[j]);
                        Set<Integer> set = new HashSet<>();
                        set.add(nums[i]);
                        set.add(nums[j]);
                        set.add(num);
                        if (!result.contains(set)) {
                            result.add(set);
                            res.add(Arrays.asList(nums[i], num,nums[j]));
                        }
                    }
                    firstMap.put(target - nums[j], nums[j]);
                }
            }
        }*/
        // 使用排序+双指针
        Arrays.sort(nums);
        for (int i = 0; i < n - 2; i++) {
            if (nums[i] > 0) {
                break;
            }
            if ((i > 0 && nums[i] == nums[i - 1])) {
                // 如果当前值与前一个值相等，代表重复，结束本次循环
                continue;
            }
            int start = i + 1;
            int end = n - 1;
            int target = -nums[i];
            while (start < end) {
                int sum = nums[start] + nums[end];
                if (target == sum) {
                    res.add(Arrays.asList(nums[i], nums[start], nums[end]));
                    while (start + 1 < end && nums[start] == nums[start + 1]) {
                        start++;
                    }
                    start++;
                    while (end - 1 > start && nums[end] == nums[end - 1]) {
                        end--;
                    }
                    end--;
                } else if (target > sum) {
                    start++;
                } else {
                    end--;
                }
            }
        }
        /*for (int i = 0; i < n - 2; i++) {
            for (int j = i+1; j < n-1; j++) {
                for (int k = j+1; k < n; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        Set<Integer> set = new HashSet<>();
                        set.add(nums[i]);
                        set.add(nums[j]);
                        set.add(nums[k]);
                        if (!result.contains(set)) {
                            result.add(set);
                            res.add(Arrays.asList(nums[i], nums[k],nums[j]));
                        }
                    }
                }
            }
        }*/
        return res;
    }

    @Test
    public void searchInsertTest() {
        int[] nums = {1};
        System.out.println(searchInsert(nums, 0));
    }

    /**
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     * 请必须使用时间复杂度为 O(log n) 的算法。
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        // nums = [1,3,5,6], target = 5  out => 2
        // nums = [1,3,5,6], target = 2  out => 1
        // nums = [1,3,5,6], target = 7  out => 4
        int start = 0;
        int end = nums.length - 1;
        int mid = 0;
        while (start <= end) {
            mid = (start & end) + ((start ^ end) >> 1);
            if (nums[mid] > target) {
                end = mid - 1;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return nums[mid] >= target ? mid : mid + 1;
    }

    @Test
    public void removeElementTest() {
        int[] nums = {2, 2};
        System.out.println(removeElement(nums, 2));
    }

    /**
     * 给你一个数组 num和一个值 val，你需要 原地 移除所有数值等于val的元素，并返回移除后数组的新长度。
     * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int start = 0, end = nums.length - 1;
        while (start < end) {
            while (start < end && nums[start] != val) {
                start++;
            }
            while (start < end && nums[end] == val) {
                end--;
            }
            if (start < end) {
                swap(nums, start, end);
                start++;
                end--;
            }
        }
        return nums[start] == val ? start : start + 1;
    }

    private void swap(int[] nums, int start, int end) {
        int tmp = nums[start];
        nums[start] = nums[end];
        nums[end] = tmp;
    }

    /**
     * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
     * <p>
     * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
     *
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        // 转换为字符串
        if (x < 0) {
            return false;
        }
        if (x >= 0 && x <= 9) {
            return true;
        }
        String s = String.valueOf(x);
        int start = 0, end = s.length() - 1;
        return isPalindrome(s, start, end);
    }

    public boolean isPalindrome(String s, int start, int end) {
        if (start >= end) {
            return true;
        }
        return s.charAt(start) != s.charAt(end) ? false : isPalindrome(s, ++start, --end);
    }

    @Test
    public void isPalindrome2() {
        int x = 1;
        System.out.println(isPalindrome3(x));
    }

    public boolean isPalindrome2(int x) {
        // 不转换为字符串处理
        if (x < 0) {
            return false;
        }
        if (x >= 0 && x <= 9) {
            return true;
        }
        int len = String.valueOf(x).length();
        int n = len / 2;
        int other = 0;
        int c = x;
        while (n > 0) {
            other = other * 10 + c % 10;
            c /= 10;
            n--;
        }
        return len % 2 == 0 ? other == c : other == c / 10;
    }

    public boolean isPalindrome3(int val) {
        // 截取一半数字，与另一半比较，相同为回文数， 巧妙利用回文数的特点： 对称
        if (val < 0) {
            return false;
        }
        int other = 0;
        while (other < val) {
            other = other * 10 + val % 10;
            val /= 10;
        }
        return other == val || other / 10 == val;
    }

    public int search(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        while (start <= end) {
            int mid = (end - start) / 2 + start;
            if (nums[mid] > target) {
                end = mid - 1;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 给定一个包含红色、白色和蓝色、共n 个元素的数组 nums ，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
     * 我们使用整数 0、1 和 2 分别表示红色、白色和蓝色
     * 链接：https://leetcode.cn/problems/sort-colors  75
     *
     * @param nums
     */
    public void sortColors(int[] nums) {
        int[] tmp = new int[3];
        for (int i = 0; i < nums.length; i++) {
            int index = nums[i];
            tmp[index]++;
        }
        int[] ans = new int[nums.length];
        int idx = 0;
        for (int i = 0; i < tmp.length; i++) {
            while (tmp[i]-- > 0) {
                ans[idx++] = i;
            }
        }
        System.arraycopy(ans, 0, nums, 0, nums.length);
    }

    @Test
    public void sortColorsTest() {
        int[] arr = {2, 0, 2, 1, 1, 0};
        sortColors1(arr);
        System.out.println(Arrays.toString(arr));
    }

    public void sortColors1(int[] nums) {
        int pre = -1;
        int suf = nums.length;
        for (int i = 0; i < suf; ) {
            if (nums[i] == 2) {
                swap(nums, --suf, i);
            } else if (nums[i] == 0) {
                swap(nums, ++pre, i++);
            } else {
                i++;
            }
        }
    }

    public List<List<Integer>> permute(int[] arr) {
        List<List<Integer>> ans = new ArrayList<>();
        permute(ans, new ArrayDeque<>(), arr);
        return ans;
    }

    @Test
    public void permuteTest() {
        int[] arr = {1, 2, 3};
        List<List<Integer>> lists = permute(arr);
        System.out.println(lists);
    }

    public void permute(List<List<Integer>> ans, Deque<Integer> list, int[] arr) {
        if (list.size() == arr.length) {
            List<Integer> tmp = new ArrayList<>(list);
            ans.add(tmp);
        }
        for (int num : arr) {
            if (!list.contains(num)) {
                list.addLast(num);
                permute(ans, list, arr);
                list.removeLast();
            }
        }
    }

    /**
     * 给定一个整数数组  nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
     * leetCode 698
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean canPartitionKSubsets(int[] nums, int k) {
        // [4, 3, 2, 3, 5, 2, 1]
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum % k != 0) {
            return false;
        }
        int target = sum / k;
        int[] bucket = new int[k];
        return canPartitionSubSetsForce(nums, k, 0, bucket, target);
    }

    @Test
    public void canPartitionKSubsetsTest() {
        int[] nums = {4, 5, 1, 2, 3, 3, 2};
        int k = 5;
        System.out.println(canPartitionKSubsets2(nums, k));
    }

    public boolean canPartitionKSubsets2(int[] nums, int k) {
        // [4, 3, 2, 3, 5, 2, 1]
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum % k != 0) {
            return false;
        }
        int target = sum / k;
        int[] bucket = new int[k];
        boolean[] used = new boolean[nums.length];
        return canPartitionSubSetsForce2(nums, k - 1, bucket, target, used);
    }

    public boolean canPartitionSubSetsForce2(int[] nums, int k, int[] bucket, int target, boolean[] used) {
        if (k < 0) {
            return true;
        }
        if (bucket[k] == target) {
            return canPartitionSubSetsForce2(nums, k - 1, bucket, target, used);
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            if (bucket[k] + nums[i] > target) {
                continue;
            }
            bucket[k] += nums[i];
            used[i] = true;
            if (canPartitionSubSetsForce2(nums, k, bucket, target, used)) {
                return true;
            }
            used[i] = false;
            bucket[k] -= nums[i];
        }
        return false;
    }

    public boolean canPartitionSubSetsForce(int[] nums, int k, int index, int[] bucket, int target) {
        if (index == nums.length) {
            return true;
        }
        for (int i = 0; i < k; i++) {
            if (bucket[i] + nums[index] > target) {
                continue;
            }
            if (i > 0 && bucket[i] == bucket[i - 1]) {
                continue;
            }
            bucket[i] += nums[index];
            if (canPartitionSubSetsForce(nums, k, index + 1, bucket, target)) {
                return true;
            }
            bucket[i] -= nums[index];
        }
        return false;
    }

    /**
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        subsetsForce(nums, 0, ans, tmp);
        return ans;
    }

    @Test
    public void subsetsTest() {
        int[] nums = {1, 2, 3};
        System.out.println(subsets(nums));
    }

    public void subsetsForce(int[] nums, int index, List<List<Integer>> ans, List<Integer> tmp) {
        ans.add(new ArrayList<>(tmp));
        for (int i = index; i < nums.length; i++) {
            tmp.add(nums[i]);
            subsetsForce(nums, i + 1, ans, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }

    /**
     * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
     * <p>
     * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
     * leetCode 90
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        // {1,2,2}
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        subsetsWithDupForce(nums, 0, ans, tmp);
        return ans;
    }

    @Test
    public void subsetsWithDupTest() {
        int[] nums = {2, 1, 2};
        System.out.println(subsetsWithDup(nums));
    }

    public void subsetsWithDupForce(int[] nums, int index, List<List<Integer>> ans, List<Integer> tmp) {
        ans.add(new ArrayList(tmp));
        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i - 1] == nums[i]) {
                continue;
            }
            tmp.add(nums[i]);
            subsetsWithDupForce(nums, i + 1, ans, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }

    /**
     * 找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：
     * leetCode 216
     * 只使用数字1到9
     * 每个数字 最多使用一次
     * 返回 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。
     *
     * @param k
     * @param n
     * @return
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        // k =3   n =7   {1,2,4}
        int[] nums = new int[9];
        for (int i = 0; i < 9; i++) {
            nums[i] = i + 1;
        }
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        combinationSum3Force(nums, k, n, 0, ans, tmp);
        return ans;
    }

    /**
     * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
     * leetCode 77
     * 你可以按 任何顺序 返回答案。
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        combineForce(n, k, 1, ans, tmp);
        return ans;
    }

    @Test
    public void combineTest() {
        System.out.println(combine(4, 2));
    }

    public void combineForce(int n, int k, int index, List<List<Integer>> ans, List<Integer> tmp) {
        if (k == 0) {
            ans.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = index; i <= n; i++) {
            tmp.add(i);
            k -= 1;
            combineForce(n, k, i + 1, ans, tmp);
            tmp.remove(tmp.size() - 1);
            k += 1;
        }
    }

    /**
     * 给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * <p>
     * candidates 中的每个数字在每个组合中只能使用 一次 。
     * leetCode 40
     * 注意：解集不能包含重复的组合。
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        combinationSum2Force(candidates, target, 0, ans, tmp);
        return ans;
    }

    @Test
    public void combinationSum2Test() {
        int[] arr = {2, 5, 2, 1, 2};
        int k = 5;
        System.out.println(combinationSum2(arr, k));
    }

    public void combinationSum2Force(int[] candidates, int target, int start, List<List<Integer>> ans, List<Integer> tmp) {
        if (target == 0) {
            ans.add(new ArrayList<>(tmp));
            return;
        }
        for (int index = start; index < candidates.length; index++) {
            if (index > start && candidates[index - 1] == candidates[index]) {
                continue;
            }
            if (candidates[index] > target) {
                continue;
            }
            target -= candidates[index];
            tmp.add(candidates[index]);
            combinationSum2Force(candidates, target, index + 1, ans, tmp);
            tmp.remove(tmp.size() - 1);
            target += candidates[index];
        }
    }

    @Test
    public void combinationSum3Test() {
        System.out.println(combinationSum3(3, 9));
    }

    public void combinationSum3Force(int[] nums, int k, int n, int index, List<List<Integer>> ans, List<Integer> tmp) {
        if (k == 0 && n == 0) {
            ans.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = index; i < nums.length; i++) {
            tmp.add(nums[i]);
            n -= nums[i];
            combinationSum3Force(nums, k - 1, n, i + 1, ans, tmp);
            tmp.remove(tmp.size() - 1);
            n += nums[i];
        }
    }

    public int getTmpSum(List<Integer> tmp) {
        int sum = 0;
        for (Integer integer : tmp) {
            sum += integer;
        }
        return sum;
    }

    /**
     * 编写一个程序，通过填充空格来解决数独问题。
     * leetCode 37
     * 数独的解法需 遵循如下规则：
     * <p>
     * 数字 1-9 在每一行只能出现一次。
     * 数字 1-9 在每一列只能出现一次。
     * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
     * 数独部分空格内已填入了数字，空白格用 '.' 表示。
     *
     * @param board
     */
    public void solveSudoku(char[][] board) {
        // 使用space存放可以填充数的坐标
        List<int[]> space = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '.') {
                    space.add(new int[]{i, j});
                } else {
                    int num = board[i][j] - '0' - 1;
                    line[i][num] = column[j][num] = true;
                    block[i / 3][j / 3][num] = true;
                }
            }
        }
        solveSudokuForce(board, 0, space);
        for (int i = 0; i < board.length; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
    }

    @Test
    public void solveSudokuTest() {
        char[][] board = {{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        solveSudoku(board);

    }

    boolean valid = false;
    // 表示每i行是否填充了出现的数字(j+1)
    boolean[][] line = new boolean[9][9];
    // 表示第j列是否填充了数字column[i+1]
    boolean[][] column = new boolean[9][9];
    // 表示第i,j个小的数独中是否填充了数字block[i][j][k+1]
    boolean[][][] block = new boolean[3][3][9];

    /**
     * @param board
     * @param pos   代表递归处理第pos个空白格
     */
    public void solveSudokuForce(char[][] board, int pos, List<int[]> spaces) {
        if (pos == spaces.size()) {
            valid = true;
            return;
        }
        int[] space = spaces.get(pos);
        int row = space[0];
        int col = space[1];
        for (int i = 0; i < 9 && !valid; i++) {
            if (!line[row][i] && !column[col][i] && !block[row / 3][col / 3][i]) {
                board[row][col] = (char) (i + 1 + 48);
                line[row][i] = column[col][i] = true;
                block[row / 3][col / 3][i] = true;
                solveSudokuForce(board, pos + 1, spaces);
                line[row][i] = column[col][i] = false;
                block[row / 3][col / 3][i] = false;
            }
        }
    }


    /**
     * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
     * <p>
     * 如果数组中不存在目标值 target，返回 [-1, -1]。
     * leetCode 34
     * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0 || target < nums[0] || target > nums[nums.length - 1]) {
            return new int[]{-1, -1};
        }
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                break;
            }
        }
        if (nums[mid] != target) {
            return new int[]{-1, -1};
        }
        int i = mid;
        int j = mid;
        while (i >= 0 && nums[i] == target) {
            i--;
        }
        while (j < nums.length && nums[j] == target) {
            j++;
        }
        return new int[]{i + 1, j - 1};
    }

    @Test
    public void searchRangeTest() {
        int[] nums = {1, 4};
        int target = 4;
        System.out.println(Arrays.toString(searchRange(nums, target)));
    }

    public int searchLeftRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        if (right >= 0 && right < nums.length && nums[right] == target) {
            return right;
        }
        if (left >= 0 && left < nums.length && nums[left] == target) {
            return left;
        }
        // 没找到
        return -1;
    }

    public int searchRightRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if (right >= 0 && right < nums.length && nums[right] == target) {
            return right;
        }
        if (left >= 0 && left < nums.length && nums[left] == target) {
            return left;
        }
        // 没找到
        return -1;
    }

    @Test
    public void searchRangeTest2() {
        int[] nums = {1, 2, 2, 2, 3, 4};
        int target = 0;
        System.out.println(searchLeftRange(nums, target));
        System.out.println(searchRightRange(nums, target));
    }

    @Test
    public void mySqrtTest() {
        System.out.println(mySqrt(2147395599));
    }

    public int mySqrt(int x) {
        int l = 0, r = x, ans = 0;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (mid == 0 || mid <= x / mid) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }

    /**
     * 给你一个正整数 num 。如果 num 是一个完全平方数，则返回 true ，否则返回 false 。
     * leetCode 367
     * 完全平方数 是一个可以写成某个整数的平方的整数。换句话说，它可以写成某个整数和自身的乘积。
     *
     * @param num
     * @return
     */
    public boolean isPerfectSquare(int num) {
        int i = 0;
        int j = num;
        int ans = -1;
        while (i <= j) {
            int mid = i + (j - i) / 2;
            if (mid != 0 && mid <= (num / mid)) {
                ans = mid;
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }
        return ans * ans == num;
    }

    @Test
    public void isPerfectSquareTest() {
        System.out.println(isPerfectSquare(14));
    }


    public int removeElement1(int[] nums, int val) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                nums[slow++] = nums[fast];
            }
        }
        return slow;
    }

    @Test
    public void removeDuplicatesTest() {
        int[] nums = {1, 1, 2};
        int len = removeDuplicates(nums);
        System.out.println(len);
        System.out.println(Arrays.toString(nums));
    }

    public int removeDuplicates(int[] nums) {
        int slow = 0;
        for (int fast = 1; fast < nums.length; fast++) {
            if (nums[fast] != nums[slow]) {
                nums[++slow] = nums[fast];
            }
        }
        return slow + 1;
    }

    /**
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * leetCode 283
     * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        // {0,1,0,3,12}  =>  {1,3,12,0,0}
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != 0) {
                nums[slow++] = nums[fast];
            }
        }
        Arrays.fill(nums, slow, nums.length, 0);
    }

    @Test
    public void moveZeroesTest() {
        int[] nums = {0, 1, 0, 3, 12};
        moveZeroes(nums);
    }

    /**
     * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
     * leetCode 977
     *
     * @param nums
     * @return
     */
    public int[] sortedSquares(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        if (nums[nums.length - 1] <= 0) {
            int[] tmp = Arrays.copyOf(nums, nums.length);
            for (int i = 0; i < tmp.length; i++) {
                nums[nums.length - i - 1] = tmp[i] * tmp[i];
            }
            return nums;
        }
        int len = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0) {
                break;
            }
            len++;
        }
        int[] tmp = new int[len];
        for (int i = 0; i < len; i++) {
            tmp[len - i - 1] = nums[i] * nums[i];
        }
        for (int i = len; i < nums.length; i++) {
            nums[i] *= nums[i];
        }
        int p1 = 0;
        int p2 = len;
        int index = 0;
        while (p1 < len && p2 < nums.length) {
            if (tmp[p1] < nums[p2]) {
                nums[index++] = tmp[p1++];
            } else {
                nums[index++] = nums[p2++];
            }
        }
        while (p1 < len) {
            nums[index++] = tmp[p1++];
        }
        while (p2 < nums.length) {
            nums[index++] = nums[p2++];
        }
        return nums;
    }

    public int[] sortedSquares1(int[] nums) {
        // 观察数组的数据，最大值必定出现在最左或最右， 使用双指针从大至小找出对应的数据
        int left = 0;
        int right = nums.length - 1;
        int[] ans = new int[nums.length];
        int index = ans.length - 1;
        while (left <= right) {
            int p1 = nums[left] * nums[left];
            int p2 = nums[right] * nums[right];
            if (p1 < p2) {
                ans[index--] = p2;
                right--;
            } else {
                ans[index--] = p1;
                left++;
            }
        }
        return ans;
    }

    @Test
    public void sortedSquaresTest() {
        int[] nums = {-4, -1, 0, 3, 10};
        System.out.println(Arrays.toString(sortedSquares(nums)));
    }

    public int minSubArrayLen(int target, int[] nums) {
        int maxLen = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= target) {
                    maxLen = Math.min(maxLen, j - i + 1);
                }
            }
        }
        return maxLen == Integer.MAX_VALUE ? 0 : maxLen;
    }

    public int minSubArrayLen1(int target, int[] nums) {
        int maxLen = Integer.MAX_VALUE;
        int[] pre = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            pre[i] = pre[i - 1] + nums[i - 1];
        }
        for (int i = 1; i < pre.length; i++) {
            int search = pre[i - 1] + target;
            // 求sum(bound) >= target+pre[i] 的位置
            int bound = binarySearch(pre, search);
            if (bound < pre.length) {
                maxLen = Math.min(maxLen, bound - (i - 1));
            }
        }
        return maxLen == Integer.MAX_VALUE ? 0 : maxLen;
    }

    public int getPreSum(int[] arr, int start, int end) {
        return start == 0 ? arr[end] : arr[end] - arr[start - 1];
    }

    public int minSubArrayLen2(int target, int[] nums) {
        // 使用滑动窗口 找出子数组和大于等于target的最小长度

        int maxLen = Integer.MAX_VALUE;
        int sum = 0;
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            sum += nums[j];
            while (sum >= target) {
                maxLen = Math.min(maxLen, j - i + 1);
                sum -= nums[i++];
            }
        }
        return maxLen == Integer.MAX_VALUE ? 0 : maxLen;
    }

    @Test
    public void binarySearch() {
        int[] arr = {1, 3, 4, 5, 7, 8};
        System.out.println(Arrays.binarySearch(arr, 5));
        System.out.println(binarySearch(arr, 5));
        Map<Integer, Integer> map = new HashMap<>();
    }

    public int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    /**
     * 你正在探访一家农场，农场从左到右种植了一排果树。这些树用一个整数数组 fruits 表示，其中 fruits[i] 是第 i 棵树上的水果 种类 。
     * 你想要尽可能多地收集水果。然而，农场的主人设定了一些严格的规矩，你必须按照要求采摘水果：
     * <p>
     * 你只有 两个 篮子，并且每个篮子只能装 单一类型 的水果。每个篮子能够装的水果总量没有限制。
     * 你可以选择任意一棵树开始采摘，你必须从 每棵 树（包括开始采摘的树）上 恰好摘一个水果 。采摘的水果应当符合篮子中的水果类型。每采摘一次，你将会向右移动到下一棵树，并继续采摘。
     * 一旦你走到某棵树前，但水果不符合篮子的水果类型，那么就必须停止采摘。
     * 给你一个整数数组 fruits ，返回你可以收集的水果的 最大 数目。
     * leetCode 904
     *
     * @param fruits
     * @return
     */
    public int totalFruit(int[] fruits) {
        // {0,1,2};
        int max = Integer.MIN_VALUE;
        boolean[] flag = new boolean[fruits.length];

        for (int i = 0; i < fruits.length; i++) {
            int sum = 0;
            int count = 0;
            if (i > 0 && fruits[i] == fruits[i - 1]) {
                continue;
            }
            for (int j = i; j < fruits.length; j++) {
                if (!flag[fruits[j]]) {
                    if (count == 2) {
                        Arrays.fill(flag, false);
                        break;
                    }
                    flag[fruits[j]] = true;
                    count++;
                }
                sum++;
            }
            max = Math.max(max, sum);
        }
        return max;
    }

    @Test
    public void totalFruitTest() {
//        int[] fruits = {1,2,3,2,2};
        int[] fruits = {5, 0, 5, 1, 5, 3};// 3
//        int[] fruits = {5, 3, 1, 5, 2, 4}; // 2
        System.out.println(totalFruit(fruits));
        System.out.println(totalFruit1(fruits));
        validTotalFruit(10000, 100);
        Set<Integer> set = new HashSet<>();
        set.add(12);
        set.add(13);
        set.remove(12);
        System.out.println(set);
    }

    public void validTotalFruit(int n, int count) {
        boolean flag = true;
        for (int j = 0; j < count; j++) {
            int len = (int) (Math.random() * n) + 1;
            int[] arr = new int[len];
            for (int i = 0; i < len; i++) {
                arr[i] = (int) (Math.random() * len);
            }
            int ans1 = totalFruit2(arr);
            int ans2 = totalFruit1(arr);
            if (ans1 != ans2) {
                flag = false;
                System.out.printf("ans1 = %d   ans2 = %d\n", ans1, ans2);
                System.out.println(Arrays.toString(arr));
                System.out.println("Fuck Code!!");
            }
        }
        if (flag) {
            System.out.println("Nice Code!!");
        }
    }

    public int totalFruit1(int[] fruits) {
        int ans = Integer.MIN_VALUE;
        int[] flag = new int[fruits.length];
        int count = 0;
        int start = 0;
        for (int end = 0; end < fruits.length; end++) {
            if (flag[fruits[end]] == 0) {
                count++;
            }
            flag[fruits[end]]++;
            while (count > 2) {
                flag[fruits[start]]--;
                if (flag[fruits[start]] == 0) {
                    count--;
                }
                start++;
            }
            ans = Math.max(ans, end - start + 1);
        }
        /*
        for (int start = 0; start < fruits.length; start++) {
            while (count <=2 && end < fruits.length) {
                flag[fruits[end]]++;
                if (!bool[fruits[end]]) {
                    bool[fruits[end]] = true;
                    count++;
                }
                end++;
                if (count < 3) {
                    ans = Math.max(ans, end - start);
                }
            }
            flag[fruits[start]]--;
            if (flag[fruits[start]] == 0) {
                bool[fruits[start]] = false;
                count--;
            }
        }
        */
        return ans;
    }

    public int totalFruit2(int[] total) {
        int ans = Integer.MIN_VALUE;
        int start = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int end = 0; end < total.length; end++) {
            map.put(total[end], map.getOrDefault(total[end], 0) + 1);
            if (map.size() > 2) {
                map.put(total[start], map.get(total[start]) - 1);
                if (map.get(total[start]) == 0) {
                    map.remove(total[start]);
                }
                start++;
            }
            ans = Math.max(ans, end - start + 1);
        }
        return ans;
    }

    /**
     * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
     * leetCode 76
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }
        Map<Character, Integer> map = new HashMap<>();
        char[] chs = t.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            map.put(chs[i], map.getOrDefault(chs[i], 0) + 1);
        }
        int minLen = Integer.MAX_VALUE;
        String ans = "";
        for (int i = 0; i < s.length(); i++) {
            int n = t.length();
            while (i + n <= s.length()) {
                String str = s.substring(i, i + n);
                Map<Character, Integer> tmp = new HashMap<>(map);
                if (judgeSubStr(str, tmp)) {
                    if (minLen > str.length()) {
                        minLen = str.length();
                        ans = str;
                    }
                }
                n++;
            }
        }
        return ans;
    }

    public String minWindow1(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }
        Map<Character, Integer> map = new HashMap<>();
//        int[] need = new int['z'-'A'+1];

        char[] chs = t.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            map.put(chs[i], map.getOrDefault(chs[i], 0) + 1);
//            need[chs[i] - 'A']++;
        }
        int tLen = t.length();
        int minLen = Integer.MAX_VALUE;
        int begin = -1;
        int start = 0;
        char[] sChars = s.toCharArray();
        Map<Character, Integer> allMap = new HashMap<>();
        for (int end = 0; end < sChars.length; end++) {
            allMap.put(sChars[end], allMap.getOrDefault(sChars[end], 0) + 1);
            if (map.containsKey(sChars[end]) && allMap.get(sChars[end]) <= map.get(sChars[end])) {
                tLen--;
            }
            while (tLen == 0) {
                if (minLen > end - start + 1) {
                    begin = start;
                    minLen = end - start + 1;
                }
                allMap.put(sChars[start], allMap.get(sChars[start]) - 1);
                if (map.containsKey(sChars[start]) && allMap.get(sChars[start]) < map.get(sChars[start])) {
                    tLen++;
                }
                start++;
            }
        }
        /*Map<Character, Integer> tmp = new HashMap<>(map);
        for (int end = 0; end < s.length(); end++) {
            if (tmp.containsKey(s.charAt(end))) {
                tmp.put(s.charAt(end), tmp.get(s.charAt(end)) - 1);
                if (tmp.get(s.charAt(end)) == 0) {
                    tmp.remove(s.charAt(end));
                }
            }
            while (tmp.isEmpty()) {
                if (map.containsKey(s.charAt(start))) {
                    break;
                }
                start++;
            }
            if (minLen > end - start + 1) {
                minLen = end-start+1;
                ans = s.substring(start, end+1);
            }
        }*/
        return begin == -1 ? "" : new String(sChars, begin, minLen);
    }

    public String minWindow2(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }
        int[] need = new int[128];

        char[] chs = t.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            need[chs[i]]++;
        }
        int tLen = t.length();
        int minLen = Integer.MAX_VALUE;
        int begin = -1;
        int start = 0;
        char[] sChars = s.toCharArray();
        for (int end = 0; end < sChars.length; end++) {
            // 统一减少， 当在t字符串中时，tlen--
            if (need[sChars[end]]-- > 0) {
                tLen--;
            }
            // 统一加回去， 当为0时，说明移除了t字符串的字符
            while (tLen == 0 && need[sChars[start]]++ < 0) start++;
            if (tLen == 0) {
                if (minLen > end - start + 1) {
                    begin = start;
                    minLen = end - start + 1;
                }
                start++;
                tLen++;
            }
        }
        return begin == -1 ? "" : new String(sChars, begin, minLen);
    }

    @Test
    public void minWindowTest() {
        String s = "ADOBECODEBANC";
        String t = "ABC";
//        String s = "bba";
//        String t = "ba";
        System.out.println(minWindow2(s, t));
        System.out.println(minWindow1(s, t));
        int a = 1;
        int[] arr = {1, 2};
        if (arr[a--]-- > 0) {
            System.out.println(a);
            System.out.println(Arrays.toString(arr));
        }
//        validMinWindowTest(500, 1000);
    }

    public void validMinWindowTest(int n, int count) {
        boolean flag = true;
        for (int j = 0; j < count; j++) {
            int len1 = (int) (Math.random() * n) + 1;
            int len2 = (int) (Math.random() * n) + 1;
            char[] c1 = new char[len1];
            char[] c2 = new char[len2];
            for (int i = 0; i < len1; i++) {
                c1[i] = getChar();
            }
            for (int i = 0; i < len2; i++) {
                c2[i] = getChar();
            }
            String s = new String(c1);
            String t = new String(c2);
            if (!minWindow2(s, t).equals(minWindow1(s, t))) {
                flag = false;
                System.out.println(s);
                System.out.println(t);
                System.out.println("Fuck Code!!");
            }
        }
        if (flag) {
            System.out.println("Nice Code");
        }
    }

    public char getChar() {
        int c = (int) (Math.random() * 26);
        return Math.random() > 0.5 ? (char) (c + 65) : (char) (c + 97);
    }

    public boolean judgeSubStr(String str, Map<Character, Integer> map) {
        char[] chs = str.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            if (map.containsKey(chs[i])) {
                map.put(chs[i], map.get(chs[i]) - 1);
                if (map.get(chs[i]) == 0) {
                    map.remove(chs[i]);
                }
            }
        }
        return map.size() == 0;
    }

    /**
     * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
     * leetCode 59
     *
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
        int[][] arr = new int[n][n];
        // 1  向右打印，  2 向下打印， 3 向左打印， 4 向上打印
        int flag = 1;
        int i = 0;
        int j = 0;
        int a = 1;
        while (a <= n * n) {
            switch (flag) {
                case 1:
                    for (; j < n && arr[i][j] == 0; j++) {
                        arr[i][j] = a++;
                    }
                    j--;
                    i++;
                    flag = 2;
                    break;
                case 2:
                    for (; i < n && arr[i][j] == 0; i++) {
                        arr[i][j] = a++;
                    }
                    i--;
                    j--;
                    flag = 3;
                    break;
                case 3:
                    for (; j >= 0 && arr[i][j] == 0; j--) {
                        arr[i][j] = a++;
                    }
                    j++;
                    i--;
                    flag = 4;
                    break;
                case 4:
                    for (; i >= 0 && arr[i][j] == 0; i--) {
                        arr[i][j] = a++;
                    }
                    i++;
                    j++;
                    flag = 1;
                    break;
            }
        }
        return arr;
    }

    @Test
    public void generateMatrixTest() {
        int[][] arr = generateMatrix(3);
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        List<Integer> list = spiralOrder(matrix);
        System.out.println(list);
    }

    /**
     * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
     * leetCode 54
     *
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        int i = 0;
        int j = 0;
        int l = matrix[0].length;
        int w = matrix.length;
        int count = matrix.length * matrix[0].length;
        int left = -1;
        int top = -1;
        int num = 0;
        int flag = 1;
        while (num < count) {
            switch (flag) {
                case 1:
                    for (; j < l; j++) {
                        list.add(matrix[i][j]);
                        num++;
                    }
                    i++;
                    j--;
                    flag = 2;
                    top++;
                    l--;
                    break;
                case 2:
                    for (; i < w; i++) {
                        list.add(matrix[i][j]);
                        num++;
                    }
                    i--;
                    j--;
                    w--;
                    flag = 3;
                    break;
                case 3:
                    for (; j > left; j--) {
                        list.add(matrix[i][j]);
                        num++;
                    }
                    i--;
                    j++;
                    left++;
                    flag = 4;
                    break;
                case 4:
                    for (; i > top; i--) {
                        list.add(matrix[i][j]);
                        num++;
                    }
                    i++;
                    j++;
                    flag = 1;
                    break;
            }
        }
        return list;
    }

    /**
     * 给定两个数组 nums1 和 nums2 ，返回 它们的交集 。输出结果中的每个元素一定是 唯一 的。我们可以 不考虑输出结果的顺序 。
     * leetCode 349
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        int[] arr = new int[1001];
        for (int i = 0; i < nums1.length; i++) {
            arr[nums1[i]]++;
        }
        for (int i = 0; i < nums2.length; i++) {
            if (arr[nums2[i]] > 0) {
                list.add(nums2[i]);
                arr[nums2[i]] = 0;
            }
        }
        return list.stream().mapToInt(k -> k).toArray();
    }

    /**
     * 编写一个算法来判断一个数 n 是不是快乐数。
     * <p>
     * 「快乐数」 定义为：
     * leetCode 202
     * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
     * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
     * 如果这个过程 结果为 1，那么这个数就是快乐数。
     * 如果 n 是 快乐数 就返回 true ；不是，则返回 false 。
     *
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        set.add(n);
        while (true) {
            int num = getPowNum(n);
            if (num == 1) {
                return true;
            }
            if (set.contains(num)) {
                return false;
            }
            set.add(num);
            n = num;
        }
    }

    public int getPowNum(int n) {
        int sum = 0;
        while (n > 0) {
            int num = n % 10;
            sum += num * num;
            n /= 10;
        }
        return sum;
    }

    /**
     * 给你四个整数数组 nums1、nums2、nums3 和 nums4 ，数组长度都是 n ，请你计算有多少个元组 (i, j, k, l) 能满足：
     * leetCode 454
     * 0 <= i, j, k, l < n
     * nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
     *
     * @param nums1
     * @param nums2
     * @param nums3
     * @param nums4
     * @return
     */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int n = nums1.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            int sum = nums1[i];
            for (int j = 0; j < n; j++) {
                sum += nums2[j];
                for (int k = 0; k < n; k++) {
                    sum += nums3[k];
                    for (int l = 0; l < n; l++) {
                        sum += nums4[l];
                        if (sum == 0) {
                            count++;
                        }
                        sum -= nums4[l];
                    }
                    sum -= nums3[k];
                }
                sum -= nums2[j];
            }
        }
        return count;
    }

    public int fourSumCount1(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int n = nums1.length;
        Map<Integer, Integer> map1 = new HashMap<>();
        int count = 0;
        for (int i = 0; i < n; i++) {
            int sum = nums1[i];
            for (int j = 0; j < n; j++) {
                sum += nums2[j];
                map1.put(sum * -1, map1.getOrDefault(sum * -1, 0) + 1);
                sum -= nums2[j];
            }
        }
        for (int i = 0; i < n; i++) {
            int sum = nums3[i];
            for (int j = 0; j < n; j++) {
                sum += nums4[j];
                if (map1.containsKey(sum)) {
                    count += map1.get(sum);
                }
                sum -= nums4[j];
            }
        }
        return count;
    }

    /**
     * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
     * leetCode 15
     * 你返回所有和为 0 且不重复的三元组。
     * <p>
     * 注意：答案中不可以包含重复的三元组。
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum1(int[] nums) {
        // -1,0,1,2,-1,-4   =>  [-1,-1,2],[-1,0,1]
        // -4,-1,-1,0,1,2
        List<List<Integer>> list = new ArrayList<>();
        Map<Integer, int[]> map = new HashMap<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (i == j || (j > 0 && nums[j - 1] == nums[j])) {
                    continue;
                }
                int sum = nums[i] + nums[j];
                if (!map.containsKey(sum)) {
                    map.put(sum, new int[]{nums[i], nums[j]});
                } else {
                    int[] arr = map.get(sum);
                    if (!isContains(nums[i], nums[j], arr)) {
                        map.put(sum, new int[]{nums[i], nums[j]});
                    }
                }
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i] * -1)) {
                List<Integer> tmp = new ArrayList<>();
                tmp.add(nums[i]);
                tmp.add(map.get(nums[i] * -1)[0]);
                tmp.add(map.get(nums[i] * -1)[1]);
                list.add(tmp);
            }
        }
        return list;
    }

    @Test
    public void treeSumTest1() {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum1(nums));
    }

    public boolean isContains(int i, int j, int[] arr) {
        for (int k = 0; k < arr.length; k++) {
            if (arr[k] == i || arr[k] == j) {
                return false;
            }
        }
        return true;
    }

    public List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int target = nums[i] * -1;
            int right = nums.length - 1;
            for (int j = i + 1; j < nums.length; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                while (right > j && nums[j] + nums[right] > target) {
                    right--;
                }
                if (right == j) {
                    break;
                }
                if (nums[j] + nums[right] == target) {
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(nums[i]);
                    tmp.add(nums[j]);
                    tmp.add(nums[right]);
                    list.add(tmp);
                }
            }
        }
        return list;
    }

    /**
     * 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。
     * 请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]]
     * （若两个四元组元素一一对应，则认为两个四元组重复）：
     * leetCode 18
     * 0 <= a, b, c, d < n
     * a、b、c 和 d 互不相同
     * nums[a] + nums[b] + nums[c] + nums[d] == target
     * 你可以按 任意顺序 返回答案 。
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int left = j + 1;
                long sum = nums[i] + nums[j];
                int right = nums.length - 1;
                while (left < right) {
                    if (left > j + 1 && nums[left] == nums[left - 1]) {
                        left++;
                        continue;
                    }
                    if (right < nums.length - 1 && nums[right] == nums[right + 1]) {
                        right--;
                        continue;
                    }
                    if (sum + nums[right] + nums[left] > target) {
                        right--;
                    } else if (sum + nums[right] + nums[left] < target) {
                        left++;
                    } else {
                        List<Integer> list1 = new ArrayList<>();
                        list1.add(nums[i]);
                        list1.add(nums[j]);
                        list1.add(nums[left]);
                        list1.add(nums[right]);
                        list.add(list1);
                        left++;
                        right--;
                    }
                }
            }
        }
        return list;
    }

    @Test
    public void fourSumTest() {
        int[] nums = {2, 2, 2, 2, 2, 2};
        int target = 8;
        System.out.println(fourSum(nums, target));
    }

    public int splitNum(int num) {
        int[] arr = new int[9];
        int index = arr.length - 1;
        while (num > 0) {
            int t = num % 10;
            arr[index--] = t;
            num /= 10;
        }
        Arrays.sort(arr);
        int num1 = 0;
        int num2 = 0;
        index++;
        while (index < arr.length) {
            num1 = num1 * 10 + arr[index++];
            if (index >= arr.length) {
                break;
            }
            num2 = num2 * 10 + arr[index++];
        }
        return num1 + num2;
    }

    @Test
    public void splitNumTest() {
        int num = 1000000000;
        System.out.println(splitNum(num));
    }

    public long maxKelements(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        for (int num : nums) {
            queue.add(num);
        }
        long res = 0;
        while (k > 0) {
            int num = queue.poll();
            res += num;
            queue.add((int) Math.ceil(num / 3.0d));
            k--;
        }
        return res;
    }

    @Test
    public void maxKelementsTest() {
        int[] nums = {1, 10, 3, 3, 3};
        System.out.println(maxKelements(nums, 3));
    }

    /**
     * 2917. 找出数组中的 K-or 值
     * 给你一个下标从 0 开始的整数数组 nums 和一个整数 k 。
     * nums 中的 K-or 是一个满足以下条件的非负整数：
     * 只有在 nums 中，至少存在 k 个元素的第 i 位值为 1 ，那么 K-or 中的第 i 位的值才是 1 。
     * 返回 nums 的 K-or 值。
     * <p>
     * 注意 ：对于整数 x ，如果 (2i AND x) == 2i ，则 x 中的第 i 位值为 1 ，其中 AND 为按位与运算符。
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKOr(int[] nums, int k) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            int count = 0;
            for (int num : nums) {
                int bit = 1 << i;
                if ((bit & num) == bit) {
                    count++;
                    if (count >= k) {
                        result += bit;
                        break;
                    }
                }
            }
        }
        return result;
    }

    /**
     * Alice 有 n 枚糖，其中第 i 枚糖的类型为 candyType[i] 。Alice 注意到她的体重正在增长，所以前去拜访了一位医生。
     * 医生建议 Alice 要少摄入糖分，只吃掉她所有糖的 n / 2 即可（n 是一个偶数）。Alice 非常喜欢这些糖，她想要在遵循医生建议的情况下，尽可能吃到最多不同种类的糖。
     * 给你一个长度为 n 的整数数组 candyType ，返回： Alice 在仅吃掉 n / 2 枚糖的情况下，可以吃到糖的 最多 种类数
     *
     * @param candyType
     * @return
     */
    public int distributeCandies(int[] candyType) {
        int mid = candyType.length / 2;
        Arrays.sort(candyType);
        int pre = -1;
        int type = 0;
        for (int num : candyType) {
            if (num != pre) {
                type++;
                pre = num;
                mid--;
            }
            if (mid == 0) {
                break;
            }
        }
        return type;
    }

    public int distributeCandies01(int[] candyType) {
        int mid = candyType.length / 2;
        Set<Integer> set = new HashSet<>();
        int type = 0;
        for (int num : candyType) {
            if (!set.contains(num)) {
                type++;
                mid--;
                set.add(num);
            }
            if (mid == 0) {
                break;
            }
        }
        return type;
    }

    @Test
    public void distributeCandiesTest() {
        int[] candyType = {6, 6, 6, 6};
        int result = distributeCandies(candyType);
        System.out.println(result);
    }

    /**
     * 2575. 找出字符串的可整除数组
     *
     * @param word
     * @param m
     * @return
     */
    public int[] divisibilityArray(String word, int m) {
        int len = word.length();
        int[] result = new int[len];
        long before = 0;
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            before = before * 10 + (chars[i] - 48);
            if (before % m == 0) {
                result[i] = 1;
            }
            before %= m;
        }
        return result;
    }

    @Test
    public void divisibilityArray() {
        String word = "998244353";
        int m = 3;
        System.out.println(Arrays.toString(divisibilityArray(word, m)));
    }

    @Test
    public void findKOrTest() {
        int[] nums = {10, 8, 5, 9, 11, 6, 8};
        int k = 1;
        int result = findKOr(nums, k);
        System.out.println(result);
//        System.out.println((2<<1));
    }

    /**
     * 2789. 合并后数组中的最大元素
     *
     * @param nums
     * @return
     */
    public long maxArrayValue(int[] nums) {
        // 将nums数组转换为List<Long>类型的集合
        List<Long> list = Arrays.stream(nums).mapToLong(a -> a).boxed().collect(Collectors.toList());

        // 调用mergeMaxList方法进行合并操作
        while (mergeMaxList(list)) {

        }
        // 返回合并后的最大值
        return list.get(0);
    }

    public long maxArrayValue1(int[] nums) {
        // 数组合并，i可以随意取值，需要满足num[i] <= num[i+1]时才可以合并， 数组合并后的子数组和与原数组的和是保持不变的
        // 对于数组中的某个元素i, i之前的元素合并的结果必须小于元素i才可以与i合并，但我们不知道i之后合并的结果，所有可以从后往前合并，
        // 对于数组i,
        int len = nums.length;
        int res = nums[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            res = res >= nums[i] ? res + nums[i] : nums[i];
        }
        return res;
    }

    @Test
    public void marArrayValueTest() {
        int[] num = {8, 1, 19, 6, 11};
        long max = maxArrayValue1(num);
        System.out.println(max);
    }


    public boolean mergeMaxList(List<Long> list) {
        if (list.size() == 1) {
            return false;
        }
        boolean isMerge = false;
        for (int i = list.size() - 1; i > 0; i--) {
            if (list.get(i) >= list.get(i - 1)) {
                list.set(i - 1, list.get(i) + list.get(i - 1));
                isMerge = true;
            }
        }
        return isMerge;
    }


    public int maxArea01(int[] height) {
        // 区域面积 =  min(a[i], a[j]) * (j-i)
        // 暴力解法
        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                maxArea = Math.max(maxArea, (j - i) * Math.min(height[i], height[j]));
            }
        }
        return maxArea;
    }

    public int maxArea(int[] height) {
        // 区域面积 =  min(a[i], a[j]) * (j-i)
        // 双指针法
        int i = 0, j = height.length - 1;
        int maxArea = 0;
        while (i < j) {
            maxArea = Math.max(maxArea, Math.min(height[i], height[j]) * (j - i));
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return maxArea;
    }

    @Test
    public void canCompleteCircuitTest() {
        int[] gas = {1, 2, 3, 4, 5};
        int[] cost = {3, 4, 5, 1, 2};
        System.out.println(canCompleteCircuit(gas, cost));
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int[] target = new int[gas.length];
        int len = gas.length;
        for (int i = 0; i < len; i++) {
            target[i] = gas[i] - cost[i];
        }
        int[] preSum = new int[2 * len + 1];
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i - 1] + target[(i - 1) % len];
        }
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 1; i < preSum.length; i++) {
            while (!deque.isEmpty() && preSum[deque.peekLast()] >= preSum[i]) {
                deque.pollLast();
            }
            deque.addLast(i);
            if (deque.peekFirst() == i - len) {
                deque.pollFirst();
            }
            if (i >= len) {
                if (preSum[deque.peekFirst()] - 1 >= 0) {
                    return (i - 1) % len;
                }
            }

        }
        return -1;
    }

    public int canCompleteCircuit1(int[] gas, int[] cost) {
        int[] target = new int[gas.length];
        int len = gas.length;
        for (int i = 0; i < len; i++) {
            target[i] = gas[i] - cost[i];
        }
        int[] preSum = new int[2 * len + 1];
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i - 1] + target[(i - 1) % len];
        }
        for (int i = 1; i < len + 1; i++) {
            int n = i;
            boolean flag = true;
            while (n - i <= len) {
                if (preSum[n] - preSum[i - 1] < 0) {
                    flag = false;
                    break;
                }
                n++;
            }
            if (flag) {
                return i - 1;
            }
        }
        return -1;
    }


    public int minSwaps(int[] nums) {
        int oneCnt = 0;
        for (int i = 0; i < nums.length; i++) {
            oneCnt += nums[i];
        }
        int n = nums.length;
        int zeroCnt = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n * 2; i++) {
            if (i >= oneCnt) {
                min = Math.min(zeroCnt, min);
                if (nums[(i - oneCnt) % n] == 0) {
                    zeroCnt--;
                }
            }
            if (nums[i % n] == 0) {
                zeroCnt++;
            }
        }

        return min;
    }

    @Test
    public void minimumStepsTest() {
        String s = "1010100";
        System.out.println(minimumSteps(s));
        System.out.println(minimumSteps1(s));
//        randomMinimumStep(100, 1000000);
    }

    public void randomMinimumStep(int count, int len) {
        for (int c = 0; c < count; c++) {
            StringBuilder sb1 = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            for (int i = 0; i < len; i++) {
                char ch = Math.random() > 0.5 ? '0' : '1';
                sb1.append(ch);
                sb2.append(ch);
            }
            if (minimumSteps(sb1.toString()) != minimumSteps(sb2.toString())) {
                System.out.println("Fuck code!!!");
            }
        }
    }


    public long minimumSteps(String s) {
        long step = 0;
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) == '1' && s.charAt(right) == '0') {
                step += right - left;
                left++;
                right--;
                continue;
            }
            if (s.charAt(left) == '0') {
                left++;
            }
            if (s.charAt(right) == '1') {
                right--;
            }
        }
        return step;
    }

    /**
     * leetcode 2938
     *
     * @param s
     * @return
     */
    public long minimumSteps1(String s) {
        long step = 0;
        int len = s.length();
        char[] chs = s.toCharArray();
        int pre = len;
        for (int i = len - 1; i >= 0; i--) {
            if (chs[i] == '0') {
                pre = Math.min(pre, i);
                int index = getOneZero(pre, chs);
                if (index == -1) {
                    return step;
                }
                step += (i - index);
                chs[index] = '0';
                pre = index;
            }
        }
        return step;
    }

    public int getOneZero(int index, char[] chs) {
        while (index >= 0) {
            if (chs[index] == '1') {
                return index;
            }
            index--;
        }
        return index;
    }

    public long minimumSteps2(String s) {
        long step = 0;
        int one = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                one++;
            } else {
                step += one;
            }
        }
        return step;
    }

    public boolean canMeasureWater(int x, int y, int target) {
        if (x + y < target) {
            return false;
        }
        if (x == target || y == target) {
            return true;
        }
        return false;
    }

    @Test
    public void testPrint() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            Thread.sleep(50);
            System.out.print("当前i为："+i);
//            System.out.printf("当前i为：%d ", i);
//            System.out.flush();
//            System.out.printf("\b");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            Thread.sleep(100);
//            System.out.print("当前i为："+i);
            System.out.printf("\r当前i为：%d", i);
            System.out.flush();
        }
    }
}

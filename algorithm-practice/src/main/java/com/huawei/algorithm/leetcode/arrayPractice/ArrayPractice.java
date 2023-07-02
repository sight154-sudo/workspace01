package com.huawei.algorithm.leetcode.arrayPractice;

import com.sun.prism.sw.SWPipeline;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

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
        int start = 0,end = nums.length-1;
        while (start <= end) {
            int mid = (end-start)/2+start;
            if (nums[mid] > target) {
                end = mid-1;
            } else if(nums[mid] < target) {
                start = mid+1;
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
        System.arraycopy(ans, 0, nums, 0 , nums.length);
    }

    @Test
    public void sortColorsTest() {
        int[] arr = {2,0,2,1,1,0};
        sortColors1(arr);
        System.out.println(Arrays.toString(arr));
    }

    public void sortColors1(int[] nums) {
        int pre = -1;
        int suf = nums.length;
        for (int i = 0; i < suf;) {
            if (nums[i] == 2) {
                swap(nums, --suf, i);
            } else if (nums[i] == 0) {
                swap(nums, ++pre, i++);
            } else {
                i++;
            }
        }
    }

}

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
        int[] nums = {1,3,5,7};
        int target = 7;
        int index = searchInsert(nums,target);
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
        int end = nums.length-1;
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
        return nums[start] < target?start+1:start;
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
            if(dp[i-1] > 0) {
                dp[i] = dp[i-1] + nums[i];
            }else{
                dp[i] = nums[i];
            }
        }
        int res = dp[0];
        for (int i = 1; i < len; i++) {
            res = Math.max(res,dp[i]);
        }
        return res;
    }

    @Test
    public void testMaxSubArray1(){
//        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        int[] nums = {-2,-3,-4,-1,-3};
        int sum = maxSubArray1(nums);
        System.out.println("sum = " + sum);
    }

    /**
     * 最大连续子序和   使用分治法
     * 思路：将数组分解成三份  第一份  start ->  mid    第二   mid-> end   第三份为 经过mid  mid+1的连续子序列
     * 依次分治，求出最大值
     * @param nums
     * @return
     */
    public int maxSubArray1(int[] nums) {
        int start = 0;
        int end = nums.length-1;
        return maxSubArraySum(nums,start,end);
    }

    public int maxSubArraySum(int[] nums,int start,int end) {
        if(start >= end) {
            return nums[start];
        }
        int mid = (start+end) / 2;
        return max(maxSubArraySum(nums,start,mid),
                crossMaxSum(nums,start,mid,end),
                maxSubArraySum(nums,mid+1,end));
    }

    public int crossMaxSum(int[] nums,int start, int mid, int end) {
        int sum = 0;
        int leftSum = Integer.MIN_VALUE;
        int rightSum = Integer.MIN_VALUE;
        for (int i = mid; i >= start ; i--) {
            sum+=nums[i];
            if(sum > leftSum) {
                leftSum = sum;
            }
        }
        sum = 0;
        for (int i = mid+1; i <= end ; i++) {
            sum+=nums[i];
            if(sum > rightSum) {
                rightSum = sum;
            }
        }
        return leftSum+rightSum;
    }

    public int max(int num1,int num2,int num3) {
        return Math.max(num1,Math.max(num2,num3));
    }


    @Test
    public void testMaxSubArray2(){
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
//        int[] nums = {-2,-3,-4,-1,-3};
        int sum = maxSubArray1(nums);
        System.out.println("sum = " + sum);
    }

    /**
     * 使用贪心算法
     * @param nums
     * @return
     */
    public int maxSubArray2(int[] nums) {
        int result = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum+=nums[i];
            result = Math.max(result,sum);
            if(sum < 0) {
                // 表示需要重新计算连续子序列
                sum = 0;
            }
        }
        return result;
    }

    /**
     * 删除有序数组中的重复项
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        // ex: {1,1,2} ->  {1,2,_}     {0,0,1,1,1,2,2,3,3,4}  -> {[0,1,2,3,4}
        int slow = 0;
        int fast = 1;
        while(fast < nums.length) {
            if( nums[slow] == nums[fast]) {
                fast++;
            }else if(nums[slow] < nums[fast]) {
                nums[slow+1] = nums[fast];
                fast++;
                slow++;
            }
        }
        return nums.length==0?slow:slow+1;
    }

}

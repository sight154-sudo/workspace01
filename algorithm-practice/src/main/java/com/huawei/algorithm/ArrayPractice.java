package com.huawei.algorithm;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class ArrayPractice {

    @Test
    public void twoSum() {
        int[] nums  = {1,4,6,8,11,0};
        int target = 10;
        int[] res = twoSumByHash(nums,target);
        System.out.println(Arrays.toString(res));
    }


    /**
     * 暴力破解  两数之和
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if(nums[i] + nums[j] == target) {
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
    public int[] twoSumByHash(int[] nums,int target) {
        //hash表中的key存放数组中的元素，value存放元素的索引
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        for (int i = 0; i < nums.length; i++) {
            if(map.containsKey(target-nums[i])) {
                return new int[]{i,map.get(target-nums[i])};
            }
            map.put(nums[i],i);
        }
        return null;
    }


    @Test
    public void testSort(){
        List<Map<String,Object>> list = new ArrayList<>();
        int i = 1;
        while( i < 100000) {
            Map<String,Object> map = new HashMap<>();
            map.put("item"+i,String.format("values%d",(i+1)));
            map.put("date",new Date());
            list.add(map);
            i++;
        }
        long start = System.currentTimeMillis();
        list.stream().sorted((o1,o2)->{
            Date o1d = (Date)o1.get("date");
            Date o2d = (Date)o2.get("date");
            return o2d.compareTo(o1d);

        });
        System.out.println("耗时: "+(System.currentTimeMillis() - start));
        List<Map<String, Object>> collect = list.stream().limit(10).collect(Collectors.toList());
        System.out.println(collect);
//        System.out.println(list);
    }

    public long converTimeToLong(Date date) {
        return 1;
    }

}

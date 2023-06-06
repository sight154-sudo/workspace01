package com.huawei.algorithm.leetcode.eorPractice;

import org.junit.Test;

/**
 * @author king
 * @date 2023/6/6-22:40
 * @Desc
 */
public class EorPractice {


    @Test
    public void eor1() {
        int [] arr = {2,3,4,5,5,3};
        eor1(arr);
    }


    /**
     * 在一个数组中，有两个数出现了一次，其他数都出现了偶数次，求这两个数
     * @param arr
     */
    public void eor1(int[] arr) {
        int eor = 0;
        for (int num : arr) {
            eor ^= num;
        }
        int one = eor ^(-eor);
        int other = 0;
        for (int num : arr) {
            // 找出最右侧为一，且在相同的位置上的数
            if ((one & num) != 0) {
                other ^= num;
            }
        }
        System.out.println("this one is : "+other+", other one: "+ (eor^other));
    }

    @Test
    public void eor2() {
        int[] arr = {266,266,3,3,3,3,3,5,5,5,5,5};
        eor2(arr, 2, 5);
    }

    /**
     * 一个数组中，有一个数出现了p次，其他数都出现了m次， 求出现p次的数
     * 条件： m>1  p<m
     * @param arr
     * @param p
     * @param m
     */
    public void eor2(int[] arr,int p, int m) {
        // 定义一个32长度的数组，用于存在数组中每个元素上的二进制位，相同+1
        int[] src = new int[32];
        for (int num : arr) {
            for (int i = 0; i < 32; i++) {
                src[i] += (num >> i)&1;
            }
        }
        int target = 0;
        for (int i = 0; i < src.length; i++) {
            if (src[i] % m != 0) {
                target |= (1<<i);
            }
        }
        System.out.println("target is :"+ target);
    }

}

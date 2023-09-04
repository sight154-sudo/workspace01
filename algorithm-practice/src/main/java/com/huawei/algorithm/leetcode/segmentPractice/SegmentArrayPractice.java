package com.huawei.algorithm.leetcode.segmentPractice;

/**
 * @date 2023/9/4-21:55
 * @Desc
 */
public class SegmentArrayPractice {

    static class SegmentArray {
        int len;
        int[] arr;
        int[] sums;
        int[] lazy; // 增加的懒数组

        int[] change; // change与update 表示更新的懒数组(单个change数组中的某个值若为0， 不能确定是否懒更新过)
        boolean[] update;

        public SegmentArray(int[] origin) {
            len = origin.length + 1;
            arr = new int[len];
            for (int i = 1; i < len; i++) {
                arr[i] = origin[i - 1];
            }
            sums = new int[len << 2];
            lazy = new int[len << 2];
            change = new int[len << 2];
            update = new boolean[len << 2];
        }

        public void pushUp(int rt) {
            sums[rt] = sums[rt << 1] + sums[rt << 1 | 1];
        }

        /**
         * 懒节点任务下发
         *
         * @param rt 当前任务下发的节点
         * @param ln 左节点数
         * @param rn 右节点数
         */
        public void pushDown(int rt, int ln, int rn) {
            if (update[rt]) {
                // 更新节点有懒更新过
                // 更新记录的更新节点，到左子节点
                change[rt << 1] = change[rt];
                // 左子节点更新标记设置为true
                update[rt << 1] = true;
                // 左子节点的增加数据，清空为0
                lazy[rt << 1] = 0;
                // 重新计算左子节点的数据值
                sums[rt << 1] = change[rt] * ln;
                // 右子节点同上
                change[rt << 1 | 1] = change[rt];
                update[rt << 1 | 1] = true;
                sums[rt << 1 | 1] = change[rt] * rn;
                lazy[rt << 1 | 1] = 0;
                update[rt] = false;
            }
            if (lazy[rt] != 0) {
                // 需要下发节点任务
                lazy[rt << 1] += lazy[rt];
                sums[rt << 1] += lazy[rt] * ln;
                lazy[rt << 1 | 1] += lazy[rt];
                sums[rt << 1 | 1] += lazy[rt] * rn;
                lazy[rt] = 0;
            }
        }

        /**
         * 构造线段树
         *
         * @param l  当前构造线段树的左起点
         * @param r  当前构造线段树的右起点
         * @param rt 当前的根节点
         */
        public void build(int l, int r, int rt) {
            if (l == r) {
                // l == r 时，表示 当前处理的为叶子节点， 直接赋值
                sums[rt] = arr[l];
                return;
            }
            // 计算中点位置
            int mid = (l + r) >> 1;
            // 更新左子树的节点数据
            build(l, mid, rt << 1);
            // 更新右子树的节点数据
            build(mid, r, rt << 1 | 1);
            // 子节点数据处理完成， 向上更新数据
            pushUp(rt);
        }

        /**
         * 任务： L~R 上的所有数据都加上C
         *
         * @param L  左边界
         * @param R  右边界
         * @param C  增加的值
         * @param l  当前操作节点的左边界
         * @param r  当前操作节点的右边界
         * @param rt 当前节点
         */
        public void add(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && r <= R) {
                // 当前节点包含的要操作的任务，不用继续往下分，更新，并懒加载
                sums[rt] += C * (R - L + 1);
                lazy[rt] += C;
                return;
            }
            int mid = (l + r) >> 1;
            // 往下分发任务前， 需要判断当前节点是否有懒信息，如果有，需要往下更新一层信息
            pushDown(rt, mid - l + 1, r - mid);
            if (L <= mid) {
                // 如果右边界比任务的左边界大，需要下发到左子树
                add(L, R, C, l, mid, rt << 1);
            }
            if (R > mid) {
                // 如果左边界比任务的右边界小， 需要下发到右子树
                add(L, R, C, mid + 1, r, rt << 1 | 1);
            }
            pushUp(rt);
        }

        /**
         * 任务： L~R 上的所有数据都设置为C
         *
         * @param L  左边界
         * @param R  右边界
         * @param C  增加的值
         * @param l  当前操作节点的左边界
         * @param r  当前操作节点的右边界
         * @param rt 当前节点
         */
        public void update(int L, int R, int C, int l, int r, int rt) {
            if (l >= L && R <= r) {
                sums[rt] = C * (r - l + 1);
                change[rt] = C;
                update[rt] = true;
                // 若之前增加操作懒处理过，直接设置为0
                lazy[rt] = 0;
                return;
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            if (mid >= L) {
                update(L, R, C, l, mid, rt<<1);
            }
            if (R > mid) {
                update(L, R, C, mid + 1, r, rt<<1 | 1);
            }
            pushUp(rt);
        }

        public int query(int L, int R, int l, int r, int rt) {
            if (l >= L && R >= r) {
                return sums[rt];
            }
            int mid = (l+r) >> 1;
            int ans = 0;
            if (mid >= L) {
                ans += query(L,R, l, mid, rt<<1);
            }
            if (R > mid) {
                ans += query(L,R, mid+1,r, rt<<1 | 1);
            }
            return ans;
        }
    }

    static class Right {
        int[] arr;
        int len;


        public Right(int[] origin) {
            len = origin.length+1;
            arr = new int[len];
            for (int i = 1; i < len; i++) {
                arr[i] = origin[i-1];
            }
        }

        public void add(int L, int R, int C) {
            for (int i = L; i <= R; i++) {
                arr[i] += C;
            }
        }

        public void update(int L, int R, int C) {
            for (int i = L; i <=R ; i++) {
                arr[i] = C;
            }
        }

        public int query(int L, int R) {
            int sum = 0;
            for (int i = L; i <= R; i++) {
                sum+=arr[i];
            }
            return sum;
        }
    }

    public static void main(String[] args) {
        int[] arr = {3,4,6,2,3,7};
        SegmentArray segmentArray = new SegmentArray(arr);
        Right right = new Right(arr);
    }
}

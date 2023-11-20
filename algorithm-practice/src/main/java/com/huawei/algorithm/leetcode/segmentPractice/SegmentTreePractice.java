package com.huawei.algorithm.leetcode.segmentPractice;

/**
 * 最长连续递增子串（严格递增）
 *
 * @author king
 * @date 2023/9/5-23:44
 * @Desc
 */
public class SegmentTreePractice {

    static class SegmentTree {
        int len;
        int[] arr;
        Segment[] segments;

        public SegmentTree(int[] origin) {
            len = origin.length + 1;
            arr = new int[len];
            for (int i = 1; i < arr.length; i++) {
                arr[i] = origin[i - 1];
            }
            segments = new Segment[len << 2];
        }

        public void pushUp(int rt) {
            segments[rt] = new Segment();
            Segment segment = segments[rt];
            Segment lSegment = segments[rt << 1];
            Segment rSegment = segments[rt << 1 | 1];
            segments[rt].mm = Math.max(lSegment.mm, rSegment.mm);
            segments[rt].l = lSegment.l;
            segments[rt].r = rSegment.r;
            segments[rt].lm = lSegment.lm;
            segments[rt].rm = rSegment.rm;
            if (arr[lSegment.r] < arr[rSegment.l]) {
                // 是否是完全递增
                if (lSegment.lm == lSegment.r - lSegment.l + 1) {
                    segment.lm = lSegment.lm + rSegment.lm;
                }
                // 是否是完全递增
                if (lSegment.rm == rSegment.r - rSegment.l + 1) {
                    segment.rm = lSegment.rm + rSegment.rm;
                }
                segment.mm = lSegment.rm + rSegment.lm;
            }
        }

        public void build(int l, int r, int rt) {
            if (l == r) {
                segments[rt] = new Segment(1, 1, 1, l, r);
                return;
            }
            int mid = (l + r) >> 1;
            build(l, mid, rt << 1);
            build(mid + 1, r, rt << 1 | 1);
            pushUp(rt);
        }

        public void update(int index, int C, int l, int r, int rt) {
            if (l == r) {
                arr[index] = C;
                return;
            }
            int mid = (l + r) >> 1;
            if (mid <= index) {
                update(index, C, l, mid, rt << 1);
            } else {
                update(index, C, mid + 1, r, rt << 1 | 1);
            }
            pushUp(rt);
        }

        public int query(int L, int R, int l, int r, int rt) {
            if (l >= L && r <= R) {
                return segments[rt].mm;
            }
            int mid = (l + r) >> 1;
            int ans = 0;
            if (mid >= L) {
                ans = Math.max(ans, query(L, R, l, mid, rt << 1));
            }
            if (R > mid) {
                ans = Math.max(ans, query(L, R, mid + 1, r, rt << 1 | 1));
            }
            if (arr[mid] < arr[mid + 1] && L <= mid && R >mid) {
                ans = Math.max(ans, Math.min(mid - l + 1, segments[rt << 1].rm) + Math.min(r - mid, segments[rt << 1|1].lm));
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        int[] origin = {1,2,3,7,5,6,4};
        SegmentTree segmentTree = new SegmentTree(origin);
        int l = 1;
        int r = origin.length;
        segmentTree.build(l, r, 1);
        System.out.println(segmentTree.query(4, 6, 1, 7, 1));
        segmentTree.update(4, 4, 1,7, 1);
        System.out.println(segmentTree.query(4,6,1,7,1));
    }


    static class Segment {
        // 以左边界为起点的最长连接递增子串
        int lm;
        // 以右边界为终点的最长连续递增子串
        int rm;
        // 区间内的最长连续递增子串
        int mm;
        int l;
        int r;

        public Segment() {

        }

        public Segment(int lm, int rm, int mm, int left, int right) {
            this.lm = lm;
            this.rm = rm;
            this.mm = mm;
            this.l = left;
            this.r = right;
        }
    }

}

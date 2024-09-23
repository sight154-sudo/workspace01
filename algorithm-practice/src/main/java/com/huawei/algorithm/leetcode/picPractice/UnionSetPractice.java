package com.huawei.algorithm.leetcode.picPractice;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * @author king
 * @date 2024/5/26-15:53
 * @Desc
 */
public class UnionSetPractice {

    class Node<V> {
        public V val;

        public Node(V v) {
            this.val = v;
        }
    }

    class UnionSet<V> {
        // 用于存放数据对应的节点
        public Map<V, Node<V>> nodeMap = new HashMap<>();
        // 用于存放当前节点的直系父节点
        public Map<Node<V>, Node<V>> parentMap = new HashMap<>();
        // 用户存放代表节点的节点数量
        public Map<Node<V>, Integer> sizeMap = new HashMap<>();

        public UnionSet(List<V> list) {
            for (V v : list) {
                Node<V> node = new Node<>(v);
                nodeMap.put(v, node);
                parentMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public Node findParent(Node node) {
            Stack<Node> stack = new Stack<>();
            Node cur = node;
            while (cur != parentMap.get(node)) {
                stack.add(cur);
                cur = parentMap.get(cur);
            }
            while (!stack.isEmpty()) {
                parentMap.put(stack.pop(), cur);
            }
            return cur;
        }

        public boolean isSameSet(V v1, V v2) {
            return findParent(nodeMap.get(v1)) == findParent(nodeMap.get(v2));
        }

        public void unionNode(V v1, V v2) {
            Node<V> aNode = nodeMap.get(v1);
            Node<V> bNode = nodeMap.get(v2);
            if (aNode != bNode) {
                Node aHead = findParent(aNode);
                Node bHead = findParent(bNode);
                Integer x = sizeMap.get(aHead);
                Integer y = sizeMap.get(bHead);
                Node small = x > y ? bHead : aHead;
                Node big = small == aHead ? bHead : aHead;
                parentMap.put(small, big);
                sizeMap.put(big, x + y);
                sizeMap.remove(small);
            }
        }

    }

    @Test
    public void testUnionSet() {
        List<Character> list = Lists.newArrayList('A', 'B', 'C', 'D', 'E', 'F');
        UnionSet<Character> unionSet = new UnionSet<>(list);
        System.out.println(unionSet.isSameSet('A', 'B'));
        unionSet.unionNode('A', 'B');
        unionSet.unionNode('A', 'C');
        unionSet.unionNode('D', 'F');
        System.out.println(unionSet.isSameSet('A', 'E'));
        System.out.println(unionSet.isSameSet('A', 'B'));
        unionSet.unionNode('A', 'E');
        System.out.println(unionSet.isSameSet('A', 'E'));
        unionSet.unionNode('D', 'E');
        System.out.println(unionSet.isSameSet('B', 'E'));
    }

    @Test
    public void findCirCleNumTest() {
//        int[][] isConnected = {{1,1,0},{1,1,0},{0,0,1}};
//        int[][] isConnected = {{1,0,0},{0,1,0},{0,0,1}};
//        int[][] isConnected = {{1,1,1},{1,1,1},{1,1,1}};
        int[][] isConnected = {
                {1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0},
                {1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1},
                {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1}
        };
        System.out.println(findCircleNum(isConnected));
    }

    public int findCircleNum(int[][] isConnected) {
        int N = isConnected.length;
        UnionFind unionFind = new UnionFind(N);
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < isConnected[i].length; j++) {
                if (isConnected[i][j] == 1) {
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind.sets;
    }

    public int findCircleNumDfs(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int province = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(isConnected, n, i, visited);
                province++;
            }
        }
        return province;
    }

    public void dfs(int[][] isConnected, int cities, int city, boolean[] visited) {

        for (int i = 0; i < cities; i++) {
            if (isConnected[city][i] == 1 && !visited[i]) {
                visited[i] = true;
                dfs(isConnected, cities, i, visited);
            }
        }

    }

    class UnionFind {
        // 用于存放每个城市的父城市
        int[] parent;
        // 每个代表城市的城市数量
        int[] size;
        int[] help;
        // 有多少个省份
        int sets;

        public UnionFind(int N) {
            parent = new int[N];
            size = new int[N];
            help = new int[N];
            sets = N;
            for (int i = 0; i < N; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        private int findParent(int n) {
            int cur = n;
            int index = 0;
            while (cur != parent[cur]) {
                help[index++] = cur;
                cur = parent[cur];
            }
            for (index--; index >= 0; index--) {
                parent[help[index]] = cur;
            }
            return cur;
        }

        public boolean isSameSet(int n1, int n2) {
            return findParent(n1) == findParent(n2);
        }

        public void union(int n1, int n2) {
            int p1 = findParent(n1);
            int p2 = findParent(n2);
            if (p1 != p2) {
                int s1 = size[p1];
                int s2 = size[p2];
                int small = s1 > s2 ? p2 : p1;
                int big = small == p2 ? p1 : p2;
                parent[small] = big;
                size[big] = s1 + s2;
                size[small] = 0;
                sets--;
            }
        }
    }
}

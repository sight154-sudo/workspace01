package com.huawei.algorithm.leetcode.util;

import java.util.*;

public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int N = scanner.nextInt();
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int i = 0; i < N - 1; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            graph.get(x).add(y);
            graph.get(y).add(x);
        }
        
        int[] result = new int[N + 1];
        int minDP = Integer.MAX_VALUE;

        for (int i = 1; i <= N; i++) {
            boolean[] visited = new boolean[N + 1];
            visited[i] = true;
            List<Integer> sizes = new ArrayList<>();
            
            for (int neighbor : graph.get(i)) {
                if (!visited[neighbor]) {
                    int size = dfs(graph, visited, neighbor);
                    sizes.add(size);
                }
            }
            
            int maxSize = sizes.isEmpty() ? 0 : Collections.max(sizes);
            if (N - 1 - maxSize > maxSize) {
                maxSize = N - 1 - maxSize;
            }
            result[i] = maxSize;
            minDP = Math.min(minDP, maxSize);
        }
        
        for (int i = 1; i <= N; i++) {
            if (result[i] == minDP) {
                System.out.print(i + " ");
            }
        }
    }
    
    private static int dfs(List<List<Integer>> graph, boolean[] visited, int node) {
        visited[node] = true;
        int size = 1;
        
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                size += dfs(graph, visited, neighbor);
            }
        }
        
        return size;
    }
}

package com.huawei.algorithm.leetcode.util;

import java.util.*;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int N = scanner.nextInt();
        scanner.nextLine(); // consume the newline
        char[][] board = new char[N][N];
        
        for (int i = 0; i < N; i++) {
            String line = scanner.nextLine();
            String[] chars = line.split(",");
            for (int j = 0; j < N; j++) {
                board[i][j] = chars[j].charAt(0);
            }
        }
        
        String word = scanner.nextLine();
        List<int[]> path = new ArrayList<>();
        
        // Try to find the word in the board
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == word.charAt(0) && dfs(board, word, i, j, 0, path)) {
                    StringBuilder result = new StringBuilder();
                    for (int[] pos : path) {
                        result.append(pos[0]).append(",").append(pos[1]).append(",");
                    }
                    // Remove the trailing comma
                    System.out.println(result.substring(0, result.length() - 1));
                    return;
                }
            }
        }
        
        // If no path found
        System.out.println("N");
    }

    private static boolean dfs(char[][] board, String word, int i, int j, int index, List<int[]> path) {
        // Base case: all characters matched
        if (index == word.length()) {
            return true;
        }

        // Boundary check and character match check
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(index)) {
            return false;
        }

        // Mark the cell as visited by replacing it with a special character
        char temp = board[i][j];
        board[i][j] = '#';
        path.add(new int[]{i, j});

        // Explore the four possible directions
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for (int[] dir : directions) {
            if (dfs(board, word, i + dir[0], j + dir[1], index + 1, path)) {
                return true;
            }
        }

        // Unmark the cell (backtracking)
        board[i][j] = temp;
        path.remove(path.size() - 1);

        return false;
    }
}

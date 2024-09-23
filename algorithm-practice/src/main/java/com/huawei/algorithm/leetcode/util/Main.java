package com.huawei.algorithm.leetcode.util;

import java.util.*;

public class Main {
    static class Node {
        int x, y, delay;

        Node(int x, int y, int delay) {
            this.x = x;
            this.y = y;
            this.delay = delay;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt();
        int N = sc.nextInt();
        int[][] matrix = new int[M][N];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        System.out.println(minimumDelay(matrix, M, N));
    }

    private static int minimumDelay(int[][] matrix, int M, int N) {
        int[][] directions = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1}, {0, 1},
                {1, -1}, {1, 0}, {1, 1}
        };

        int[][] delays = new int[M][N];
        for (int[] row : delays) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        delays[0][0] = matrix[0][0];

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.delay));
        pq.offer(new Node(0, 0, matrix[0][0]));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            for (int[] dir : directions) {
                int newX = current.x + dir[0];
                int newY = current.y + dir[1];

                if (newX >= 0 && newX < M && newY >= 0 && newY < N) {
                    int newDelay = current.delay + matrix[newX][newY];
                    if (matrix[newX][newY] == matrix[current.x][current.y]) {
                        newDelay -= 1; // reduce the delay by 1 if the delay is same as the current node
                    }

                    if (newDelay < delays[newX][newY]) {
                        delays[newX][newY] = newDelay;
                        pq.offer(new Node(newX, newY, newDelay));
                    }
                }
            }
        }

        return delays[M - 1][N - 1];
    }




    // kruskai算法
    // prim算法
    //

    public void findStrong(int[][] arr) {

    }

}
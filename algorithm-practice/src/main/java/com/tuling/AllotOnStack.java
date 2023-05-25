package com.tuling;

/**
 * @author king
 * @date 2023/5/25-23:04
 * @Desc
 */
public class AllotOnStack {
    /**
     * 使用如下参数不会发生GC
     *  ‐Xmx15m ‐Xms15m ‐XX:+DoEscapeAnalysis ‐XX:+PrintGC ‐XX:+EliminateAllocations
     * 使用如下参数都会发生大量GC
     *  ‐Xmx15m ‐Xms15m ‐XX:‐DoEscapeAnalysis ‐XX:+PrintGC ‐XX:+EliminateAllocations
     *  ‐Xmx15m ‐Xms15m ‐XX:+DoEscapeAnalysis ‐XX:+PrintGC ‐XX:‐EliminateAllocations
     * @param args
     */
    public static void main(String[] args) {
        // -XX:+PrintGC  开启gc日志
        long l = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            alloc();
        }
        System.out.println(System.currentTimeMillis()-l);
    }

    public static void alloc() {
        User1 user1 = new User1();
        user1.setA(1);
        user1.setS("awef");
    }
}

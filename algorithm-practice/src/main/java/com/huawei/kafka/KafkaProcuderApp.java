package com.huawei.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.file.Files;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author king
 * @date 2022/8/3-1:05
 * @Desc
 */
public class KafkaProcuderApp implements Runnable{

    public KafkaProcuderApp() {
    }

    @Override
    public void run() {
        System.out.println("准备完毕");
    }

    public void dowork() {
        producer.send(new ProducerRecord<>("heima129", "send msg11", "hello kafka"+atomic.getAndIncrement()));
    }

    private static KafkaProducer<String, String> producer;
    private static AtomicInteger atomic = new AtomicInteger(1);

    static{
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.31.132:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producer = new KafkaProducer<>(properties);
    }

    public static void main(String[] args) throws InterruptedException {
 /*       String token = String.format(
                "org.apache.kafka.common.security.scram.ScramLoginModule required username=%s password=%s;",
                "admin",
                "123456");
        System.out.println(token);*/
//配置security.protocol
//        properties.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");
//配置sasl.mechanism
//        properties.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
//配置sasl.jaas.config
//        properties.put(SaslConfigs.SASL_JAAS_CONFIG, token);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        KafkaProcuderApp app = new KafkaProcuderApp();
        app.startNThreadsByBarrier(1, app);
        Thread.sleep(2000);
        producer.close(Duration.ZERO);
    }

    public void startNThreadsByBarrier(int threadNums, Runnable finishTask) {
        // 设置栅栏解除时的动作，比如初始化某些值
        CyclicBarrier barrier = new CyclicBarrier(threadNums, finishTask);
        // 启动 n 个线程，与栅栏阀值一致，即当线程准备数达到要求时，栅栏刚好开启，从而达到统一控制效果
        for (int i = 0; i < threadNums; i++) {
//            Thread.sleep(100);
            new Thread(new CounterTask(barrier)).start();
        }
        System.out.println(Thread.currentThread().getName() + " out over...");
    }

    class CounterTask implements Runnable {

        // 传入栅栏，一般考虑更优雅方式
        private CyclicBarrier barrier;

        public CounterTask(final CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        public void run() {
            System.out.println(Thread.currentThread().getName() + " - " + System.currentTimeMillis() + " is ready...");
            try {
                // 设置栅栏，使在此等待，到达位置的线程达到要求即可开启大门
                barrier.await();
                dowork();
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " - " + System.currentTimeMillis() + " started...");
        }
    }
}



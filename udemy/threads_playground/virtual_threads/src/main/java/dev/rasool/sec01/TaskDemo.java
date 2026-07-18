package dev.rasool.sec01;

import java.util.concurrent.CountDownLatch;

public class TaskDemo {

    private final static int MAX_PLATFORM = 10;
    private final static int MAX_VIRTUAL = 500;

    static void main() {
        virtualThreadDemo1();
    }

    private static void platformThreadDemo1() {
        for (int i = 0; i < MAX_PLATFORM; i++) {
            int j = i;
            Thread t = new Thread(() -> Task.toIntensive(j));
            t.start();
        }
    }

    private static void platformThreadDemo2() {
        var builder = Thread.ofPlatform().name("OfThread-", 1);
        for (int i = 0; i < MAX_PLATFORM; i++) {
            int j = i;
            Thread t = builder.start(() -> Task.toIntensive(j));
//            Thread t = builder.unstarted(() -> Task.toIntensive(j));
//            t.start();
        }
    }

    private static void platformThreadDemo3() {
        CountDownLatch countDownLatch = new CountDownLatch(MAX_PLATFORM);
        var builder = Thread.ofPlatform().daemon().name("DaemonThread-", 1);
        for (int i = 0; i < MAX_PLATFORM; i++) {
            int j = i;
            Thread t = builder.start(() -> {
                        Task.toIntensive(j);
                        countDownLatch.countDown();
                    });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void virtualThreadDemo1() {
        CountDownLatch countDownLatch = new CountDownLatch(MAX_VIRTUAL);
        var builder = Thread.ofVirtual().name("VirtualThread-", 1);
        for (int i = 0; i < MAX_VIRTUAL; i++) {
            int j = i;
            Thread t = builder.start(() -> {
                Task.toIntensive(j);
                countDownLatch.countDown();
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}

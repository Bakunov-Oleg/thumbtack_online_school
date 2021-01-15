package net.thumbtack.school.threads;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Task11 {
    //“Ping Pong” на базе ReentrantLock и Conditional переменной

    public static void main(String[] args) {
        DoPingPong doPingPong = new DoPingPong();

        Ping ping = new Ping(doPingPong);
        Pong pong = new Pong(doPingPong);

        ping.start();
        pong.start();

        try {
            ping.join();
            pong.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class DoPingPong {
        private Lock lock = new ReentrantLock();
        private Condition pongCondition = lock.newCondition();
        private Condition pingCondition = lock.newCondition();

        private boolean pingIsRun = false;

        public void makePong() throws InterruptedException {
            lock.lock();
            try {
                if (pingIsRun) pongCondition.await();
                pingIsRun = true;
                System.out.println("ping");
                pingCondition.signal();
            } finally {
                lock.unlock();
            }
        }


        public void makePing() throws InterruptedException {
            lock.lock();
            try {
                if (!pingIsRun) pingCondition.await();
                System.out.println("pong");
                pingIsRun = false;
                pongCondition.signal();
            } finally {
                lock.unlock();
            }
        }

    }

    static class Pong extends Thread {
        private DoPingPong doPingPong;

        public Pong(DoPingPong doPingPong) {
            this.doPingPong = doPingPong;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    doPingPong.makePing();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Ping extends Thread {
        private DoPingPong doPingPong;

        public Ping(DoPingPong doPingPong) {
            this.doPingPong = doPingPong;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    doPingPong.makePong();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

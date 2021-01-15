package net.thumbtack.school.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Task10 {
    //Переписать упражнение 4, используя ReentrantLock
    static private Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();

        InputThread inThread = new InputThread(numbers, lock);
        DeletingThread delThread = new DeletingThread(numbers, lock);

        inThread.start();
        delThread.start();

        System.out.println("Input isAlive: " + inThread.isAlive());
        System.out.println("Deleting isAlive: " + delThread.isAlive());

        try {
            inThread.join();
            delThread.join();
        } catch (InterruptedException e) {
            System.out.println("InterruptedException caught");
        }
        System.out.println("Thread `Input` is alive: " + inThread.isAlive());
        System.out.println("Thread `Deleting` is alive: " + delThread.isAlive());
    }


    static class InputThread extends Thread {
        private final List<Integer> numbers;
        private Lock lock;

        public InputThread(List<Integer> numbers, Lock lock) {
            this.numbers = numbers;
            this.lock = lock;
        }

        public void run() {
            int value;
            for (int i = 0; i < 10000; i++) {
                try {
                    lock.lock();
                    value = (int) (Math.random() * 1000);
                    numbers.add(value);
                    System.out.println("Add value: " + value + " to list");
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    static class DeletingThread extends Thread {
        private final List<Integer> numbers;
        private Lock lock;

        public DeletingThread(List<Integer> numbers, Lock lock) {
            this.numbers = numbers;
            this.lock = lock;
        }

        public void run() {
            int value;
            int index;
            for (int i = 0; i < 10000; i++) {
                try {
                    lock.lock();
                    if (numbers.size() != 0) {
                        index = (int) (Math.random() * (numbers.size() - 1));
                        value = numbers.get(index);
                        numbers.remove(index);
                        System.out.println("Delete value: " + value + " by index: " + index + " on list");
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}

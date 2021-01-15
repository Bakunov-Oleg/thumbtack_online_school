package net.thumbtack.school.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Task4 {
    //В основном потоке создать ArrayList<Integer>.
    //Запустить 2 потока на базе разных классов, один поток 10000 раз добавляет в список случайное целое число,
    //другой 10000 раз удаляет элемент по случайному индексу (если при удалении выясняется, что список пуст, ничего не делать).
    // Использовать внешний synchronized блок. Потоки должны работать конкурентно, то есть одновременно должно идти и добавление, и удаление.

    static private ReadWriteLock lock = new ReentrantReadWriteLock();

    public static void main(String args[]) {
        List<Integer> numbers = new ArrayList<>();

        InputThread inThread = new InputThread(numbers);
        DeletingThread delThread = new DeletingThread(numbers);

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

        public InputThread(List<Integer> numbers) {
            this.numbers = numbers;
        }

        public void run() {
            int value;
            for (int i = 0; i < 10000; i++) {
                synchronized (numbers) {
                    value = (int) (Math.random() * 1000);
                    numbers.add(value);
                    System.out.println("Add value: "+ value + " to list");
                }
            }
        }
    }

    static class DeletingThread extends Thread {
        private final List<Integer> numbers;

        public DeletingThread(List<Integer> numbers) {
            this.numbers = numbers;
        }

        public void run() {
            int value;
            int index;
            for (int i = 0; i < 10000; i++) {
                synchronized (numbers) {
                    if (numbers.size() != 0) {
                        index = (int) (Math.random() * (numbers.size() - 1));
                        value = numbers.get(index);
                        numbers.remove(index);
                        System.out.println("Delete value: " + value + " by index: " + index + " on list");
                    }
                }
            }
        }
    }

}

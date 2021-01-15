package net.thumbtack.school.threads;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

enum Operation {
    ADD,
    DELETE
}

public class Task5 {
    //То же самое, но оба потока на базе одного и того же класса, использовать synchronized методы.
    //В конструктор класса потока передается параметр типа enum, указывающий, что должен делать этот поток.

    static private ReadWriteLock lock = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        ListWrapper listWrapper = new ListWrapper(numbers);

        MyThread inThread = new MyThread(listWrapper, Operation.ADD);
        MyThread delThread = new MyThread(listWrapper, Operation.DELETE);

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


    static class MyThread extends Thread {
        Operation operation;
        ListWrapper listWrapper;

        public MyThread(ListWrapper listWrapper, Operation operation) {
            this.listWrapper = listWrapper;
            this.operation = operation;
        }

        public void run() {
            if (operation.equals(Operation.DELETE)) {
                for (int i = 0; i < 10000; i++) {
                    listWrapper.delete();
                }

            } else if (operation.equals(Operation.ADD)) {
                for (int i = 0; i < 10000; i++) {
                    listWrapper.add();
                }
            }
        }
    }

    static class ListWrapper {
        private List<Integer> list;

        public ListWrapper(List<Integer> list) {
            this.list = list;
        }

        public synchronized void add() {
            int value;
            value = (int) (Math.random() * 1000);
            list.add(value);
            System.out.println("Add value: " + value + " to list");
        }

        public synchronized void delete() {
            int value;
            int index;
            if (list.size() != 0) {
                index = (int) (Math.random() * (list.size() - 1));
                value = list.get(index);
                list.remove(index);
                System.out.println("Delete value: " + value + " by index: " + index + " on list");
            }
        }
    }
}

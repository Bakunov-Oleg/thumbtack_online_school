package net.thumbtack.school.threads;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Task15 {
    //Реализовать очередь данных. Данные - экземпляр класса  Data с единственным методом int[] get().
    //Потоки-писатели ставят в очередь экземпляры Data,
    //количество экземпляров Data, которое ставит в очередь каждый писатель, определяется в его конструкторе.
    //Потоки - читатели берут их из очереди и распечатывают,
    //в конечном итоге должны взять все экземпляры Data, которые записали все писатели вместе взятые.
    //Количество тех и других потоков может быть любым и определяется в main.

    public static void main(String[] args) {

        BlockingQueue<Data> queue = new LinkedBlockingQueue<>();

        Consumer consumer1 = new Consumer(queue);
        Consumer consumer2 = new Consumer(queue);
        Producer producer1 = new Producer(queue, 100);
        Producer producer2 = new Producer(queue, 200);

        consumer1.start();
        consumer2.start();
        producer1.start();
        producer2.start();

        try {
            producer1.join();
            producer2.join();
        } catch (InterruptedException e) {
            System.out.println("InterruptedException caught");
        }

        try {
            queue.put(new Data(null));
            queue.put(new Data(null));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            consumer1.join();
            consumer2.join();
        } catch (InterruptedException e) {
            System.out.println("InterruptedException caught");
        }
    }

    public static class Data {

        private int[] array;

        public Data(int[] array) {
            this.array = array;
        }

        public int[] getArray() {
            return array;
        }

        public void setArray(int[] array) {
            this.array = array;
        }
    }

    public static class Producer extends Thread {
        private BlockingQueue<Data> queue;
        private int count;

        public Producer(BlockingQueue<Data> queue, int count) {
            this.queue = queue;
            this.count = count;
        }

        @Override
        public void run() {
            System.out.println("Producer worked!");
            try {
                for (int i = 0; i < count; i++) {
                    queue.put(new Data(new int[1]));
                    System.out.println("Producer add Data on queue = " + queue.size());
                }
                System.out.println("Producer workout!");
            } catch (InterruptedException e) {
                System.out.println("InterruptedException caught");
            }
        }
    }

    public static class Consumer extends Thread {
        private BlockingQueue<Data> queue;

        public Consumer(BlockingQueue<Data> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            System.out.println("Consumer worked!");
            try {
                while (true) {
                    Data data = queue.take();
                    if (data.getArray() != null) {
                        System.out.println("Consumer get Data on queue= " + queue.size());
                    } else {
                        System.out.println("Data array is NULL ");
                        break;
                    }
                }
                System.out.println("Consumer workout!");
            } catch (InterruptedException e) {
                System.out.println("InterruptedException caught");
            }
        }
    }
}

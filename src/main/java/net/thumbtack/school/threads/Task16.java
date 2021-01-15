package net.thumbtack.school.threads;

import java.util.concurrent.*;

interface Executable {
    void execute() throws InterruptedException;
}

public class Task16 {
    //Реализовать очередь задач.
    //Задача - экземпляр класса  Task или его наследника, имплементирующего Executable - свой интерфейс с единственным методом void execute().
    //Потоки - разработчики ставят в очередь экземпляры Task аналогично (15), потоки - исполнители берут их из очереди и исполняют.
    //Количество тех и других потоков может быть любым и определяется в main.

    public static void main(String[] args) {
        int count = 7;
        BlockingQueue<Task> queue = new LinkedBlockingQueue<>();
        ExecutorService executorServiceDev = Executors.newFixedThreadPool(10);
        ExecutorService executorServiceExec = Executors.newFixedThreadPool(10);

        for (int i = 0; i < count; i++) {
            executorServiceDev.submit(new Developer(queue, count));
            executorServiceExec.submit(new Executor(queue));
        }

        executorServiceDev.shutdown();

        try {
            executorServiceDev.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
            for (int i = 0; i < count; i++) {
                try {
                    queue.put(new Task(true));
                } catch (InterruptedException e) {
                    System.out.println("InterruptedException caught");
                }
            }
            executorServiceExec.shutdown();
            executorServiceExec.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Task implements Executable {

    boolean poison;

    public Task() {
        this.poison = false;
    }

    public Task(boolean isPoison) {
        this.poison = isPoison;
    }

    public Boolean isPoison() {
        return poison;
    }

    @Override
    public void execute() throws InterruptedException {
        Thread.sleep(150);
    }
}

class Developer implements Runnable {

    private BlockingQueue<Task> queue;
    private int count;

    public Developer(BlockingQueue<Task> queue, int count) {
        this.queue = queue;
        this.count = count;
    }

    @Override
    public void run() {
        System.out.println("Developer run!");
        try {
            for (int i = 0; i < count; i++) {
                queue.put(new Task());
            }
            //queue.put(new Task(true));
            System.out.println("Developer workout!");
        } catch (InterruptedException e) {
            System.out.println("InterruptedException caught");
        }
    }
}


class Executor implements Runnable {

    private BlockingQueue<Task> queue;

    public Executor(BlockingQueue<Task> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("Executor run!");
        while (true) {
            try {
                Task task = queue.take();
                if (task.isPoison()) {
                    break;
                }
                task.execute();
                Thread.sleep(150);
            } catch (InterruptedException e) {
                System.out.println("InterruptedException caught");
            }
        }
        System.out.println("Executor workout! ");
    }
}
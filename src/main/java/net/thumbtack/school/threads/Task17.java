package net.thumbtack.school.threads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

enum TypeActionCase {
    DEVELOPERSTART,
    DEVELOPEREND,
    TASKSADD,
    TASKEND
}

public class Task17 {
    //Реализовать очередь многостадийных  задач.
    //Многостадийная задача - экземпляр класса TaskOn17, имеющего список из стадий - List<Executable>, где Executable - интерфейс из задания 16.
    //Потоки - разработчики ставят в очередь экземпляры TaskOn17 аналогично (15), потоки - исполнители берут из очереди задачу,
    //исполняют очередную ее стадию, после чего, если это не последняя стадия,  ставят задачу обратно в очередь.
    //Количество тех и других потоков может быть любым и определяется в main.


    public static void main(String[] args) throws InterruptedException {
        int count = 5;
        BlockingQueue<TaskOn17> queue = new LinkedBlockingQueue<>();
        BlockingQueue<TypeActionCase> queueActions = new LinkedBlockingQueue<>();
        ExecutorService executorServiceDev = Executors.newFixedThreadPool(count);
        ExecutorService executorServiceExec = Executors.newFixedThreadPool(count);
        TypeActionCase action;
        int endedDevs = 0;
        int addedTasks = 0;
        int endedTasks = 0;


        for (int i = 0; i < count; i++) {
            executorServiceDev.submit(new DeveloperOn17(queue, count, queueActions));
            executorServiceExec.submit(new ExecutorOn17(queue, queueActions));
        }

        executorServiceDev.shutdown();
        executorServiceDev.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);

        try {
            while (true) {
                action = queueActions.take();
                switch (action) {
                    case TASKEND:
                        endedTasks++;
                        break;
                    case TASKSADD:
                        addedTasks++;
                        break;
                    case DEVELOPEREND:
                        endedDevs++;
                        break;
                    default:
                        break;
                }

                if (endedTasks == addedTasks && endedDevs == count) {
                    for (int i = 0; i < count; i++) {
                        queue.put(new TaskOn17(null, true));
                    }
                    break;
                }
            }
            executorServiceExec.shutdown();
            executorServiceExec.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

class TaskOn17 {

    boolean poison;

    List<Executable> executables;

    public TaskOn17(List<Executable> executables) {
        this.executables = executables;
        this.poison = false;
    }

    public TaskOn17(List<Executable> executables, boolean poison) {
        this.executables = executables;
        this.poison = poison;
    }

    public boolean isPoison() {
        return poison;
    }

    public List<Executable> getExecutables() {
        return executables;
    }

    public void setExecutables(List<Executable> executables) {
        this.executables = executables;
    }

    synchronized Executable take() {
        return executables.remove(0);
    }
}

class DeveloperOn17 implements Runnable {

    private final BlockingQueue<TaskOn17> queue;
    private final List<Executable> subtasks = Collections.synchronizedList(new ArrayList<>());
    private final int count;
    BlockingQueue<TypeActionCase> queueActions;

    public DeveloperOn17(BlockingQueue<TaskOn17> queue, int count, BlockingQueue<TypeActionCase> queueActions) {
        this.queue = queue;
        this.count = count;
        this.queueActions = queueActions;

        for (int i = 0; i < count; i++) {
            subtasks.add(new Task());
        }
//        subtasks.add(new Task(true));
    }

    @Override
    public void run() {
        System.out.println("DeveloperOn17 run!");
        try {
            for (int i = 0; i < count; i++) {
                queue.put(new TaskOn17(subtasks));
                queueActions.add(TypeActionCase.TASKSADD);

            }
            System.out.println("DeveloperOn17 workout!");
            queueActions.add(TypeActionCase.DEVELOPEREND);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException caught");
        }
    }
}


class ExecutorOn17 implements Runnable {

    private final BlockingQueue<TaskOn17> queue;
    BlockingQueue<TypeActionCase> queueActions;

    public ExecutorOn17(BlockingQueue<TaskOn17> queue, BlockingQueue<TypeActionCase> queueActions) {
        this.queue = queue;
        this.queueActions = queueActions;
    }

    @Override
    public void run() {
        System.out.println("ExecutorOn17 run!");
        while (true) {
            try {
                TaskOn17 task = queue.take();
                if (task.isPoison() == true) {
                    break;
                }
                if (task.getExecutables().isEmpty()) {
                    queueActions.put(TypeActionCase.TASKEND);
                } else {
                    task.take().execute();
                    queue.put(task);
                    System.out.println("ExecutorOn17 returned task: " + task + " to queue");
                    System.out.println("task: " + task + " size: " + task.getExecutables().size() + " returned to queue");
                }
            } catch (InterruptedException e) {
                System.out.println("InterruptedException caught");
            }
            System.out.println("ExecutorOn17 workout! ");
        }
    }
}